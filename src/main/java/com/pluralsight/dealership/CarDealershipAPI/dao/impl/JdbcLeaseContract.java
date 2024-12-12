package com.pluralsight.dealership.CarDealershipAPI.dao.impl;

import com.pluralsight.dealership.CarDealershipAPI.dao.interfaces.LeaseContractDao;
import com.pluralsight.dealership.CarDealershipAPI.models.Vehicle;
import com.pluralsight.dealership.CarDealershipAPI.models.contract.LeaseContract;
import com.pluralsight.dealership.CarDealershipAPI.util.Calculation;
import com.pluralsight.dealership.CarDealershipAPI.util.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class JdbcLeaseContract implements LeaseContractDao {

    private DataSource dataSource;

    @Autowired
    public JdbcLeaseContract(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /***
     * FINDS A LEASE BY ITS ID
     * @param id
     * @return
     */
    @Override
    public LeaseContract findLeaseContractById(int id) {
        LeaseContract leaseContract = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement query = connection.prepareStatement(Queries.selectLeaseContractById())) {

            query.setInt(1, id);

            try (ResultSet resultSet = query.executeQuery()) {
                if (resultSet.next()) {
                    int contractId = resultSet.getInt(1);
                    String contractDate = resultSet.getString(2);
                    int customerId = resultSet.getInt(3);
                    int vin = resultSet.getInt(4);
                    double expectedEndingValue = resultSet.getDouble(5);
                    double leaseFee = resultSet.getDouble(6);
                    double totalPrice = resultSet.getDouble(7);
                    double monthlyPayment = resultSet.getDouble(8);

                    Vehicle vehicleSold = findVehicleByVin(vin); // Fetch vehicle details
                    leaseContract = new LeaseContract(
                            contractId,
                            contractDate,
                            customerId,
                            vehicleSold,
                            expectedEndingValue,
                            leaseFee,
                            totalPrice,
                            monthlyPayment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving lease contract with ID: " + id, e);
        }
        return leaseContract;
    }


    @Override
    public LeaseContract add(LeaseContract leaseContract) {
        // Check if vehicleSold is null, if so, fetch it based on VIN
        Vehicle vehicleSold = leaseContract.getVehicleSold();
        if (vehicleSold == null) {
            if (leaseContract.getVin() == 0) {
                throw new RuntimeException("Vehicle VIN must be provided in the lease contract.");
            }
            // Fetch the vehicle using the VIN from the leaseContract (make sure VIN is set)
            vehicleSold = findVehicleByVin(leaseContract.getVin());  // Ensure leaseContract has the VIN
            leaseContract.setVehicleSold(vehicleSold);  // Set the vehicle in the LeaseContract
        }

        if (vehicleSold == null) {
            throw new RuntimeException("Vehicle not found for VIN: " + leaseContract.getVin());
        }

        // Now that vehicleSold is not null, you can proceed with the calculations
        double vehiclePrice = vehicleSold.getPrice();

        // Calculate the Expected Ending Value and Lease Fee dynamically
        double expectedEndingValue = vehiclePrice * leaseContract.getExpectEndingValuePercentage();
        double leaseFee = vehiclePrice * leaseContract.getLeaseFeePercentage();

        // Calculate the Total Price and Monthly Payment based on dynamic values
        double totalPrice = expectedEndingValue + leaseFee;
        double monthlyPayment = Calculation.calculateLoanPayment(totalPrice, 0.04, 36); // Assuming 4% financing rate for 36 months

        // Set the calculated values in the leaseContract object
        leaseContract.setExpectEndingValue(expectedEndingValue);
        leaseContract.setLeaseFee(leaseFee);
        leaseContract.setTotalPrice(totalPrice);
        leaseContract.setMonthlyPayment(monthlyPayment);

        //  save the LeaseContract to the database
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement query = connection.prepareStatement(Queries.insertLeaseContract(), PreparedStatement.RETURN_GENERATED_KEYS)) {
                query.setString(1, leaseContract.getContractDate());
                query.setInt(2, leaseContract.getCustomerId());
                query.setInt(3, vehicleSold.getVin());
                query.setDouble(4, Double.parseDouble(String.format("%.2f",expectedEndingValue))); // Set the calculated expected ending value
                query.setDouble(5, Double.parseDouble(String.format("%.2f", leaseFee))); // Set the calculated lease fee
                query.setDouble(6, Double.parseDouble(String.format("%.2f", totalPrice))); // Set the calculated total price
                query.setDouble(7, Double.parseDouble(String.format("%.2f", monthlyPayment)));

                int rowsAffected = query.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("Insert failed, no rows affected.");
                }

                // Retrieve generated keys to set the contractId
                try (ResultSet generatedKeys = query.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        leaseContract.setContractId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Insert failed");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting lease contract", e);
        }

        return leaseContract;
    }


    /**HELPER METHOD
     *
     * @param vin
     * @return
     */
    private Vehicle findVehicleByVin(int vin) {
        Vehicle vehicle = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement vehicleQuery = connection.prepareStatement(Queries.selectVehicleByVin())) {

            vehicleQuery.setInt(1, vin);

            try (ResultSet vehicleResults = vehicleQuery.executeQuery()) {
                if (vehicleResults.next()) {
                    int getVin = vehicleResults.getInt(1);
                    int getYear = vehicleResults.getInt(2);
                    String getMake = vehicleResults.getString(3);
                    String getModel = vehicleResults.getString(4);
                    String getType = vehicleResults.getString(5);
                    String getColor = vehicleResults.getString(6);
                    int getOdometer = vehicleResults.getInt(7);
                    double getPrice = vehicleResults.getDouble(8);

                    vehicle = new Vehicle(getVin, getYear, getMake, getModel, getType, getColor, getOdometer, getPrice);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving vehicle with VIN: " + vin, e);
        }
        return vehicle;
    }
}