package com.pluralsight.dealership.CarDealershipAPI.dao.impl;

import com.pluralsight.dealership.CarDealershipAPI.dao.interfaces.SalesContractDao;
import com.pluralsight.dealership.CarDealershipAPI.models.Vehicle;
import com.pluralsight.dealership.CarDealershipAPI.models.contract.SalesContract;
import com.pluralsight.dealership.CarDealershipAPI.util.Calculation;
import com.pluralsight.dealership.CarDealershipAPI.util.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.pluralsight.dealership.CarDealershipAPI.util.Calculation.calculateLoanPayment;

@Component
public class JdbcSalesContractDao implements SalesContractDao {

    private final DataSource dataSource;

    @Autowired
    public JdbcSalesContractDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public SalesContract findSalesContractById(int id) {
       SalesContract contract = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement query = connection.prepareStatement(Queries.selectSalesContractById())) {

            query.setInt(1, id);

            try (ResultSet resultSet = query.executeQuery()) {
                if (resultSet.next()) {
                    int contractId = resultSet.getInt(1);
                    String contractDate = resultSet.getString(2);
                    int customerId = resultSet.getInt(3);
                    int vin = resultSet.getInt(4);
                    double salesTax = resultSet.getDouble(5);
                    double recordingFee = resultSet.getDouble(6);
                    double processingFee = resultSet.getDouble(6);
                    double totalPrice = resultSet.getDouble(7);
                    boolean finance = resultSet.getBoolean(8);
                    double monthlyPayment = resultSet.getDouble(9);

                    Vehicle vehicleSold = findVehicleByVin(vin); // Fetch vehicle details
                    contract = new SalesContract(
                            contractId,
                            contractDate,
                            customerId,
                            vehicleSold,
                            salesTax,
                            recordingFee,
                            processingFee,
                            totalPrice,
                            finance,
                            monthlyPayment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving lease contract with ID: " + id, e);
        }
        return contract;
    }


    public SalesContract add(SalesContract salesContract) {
        // Check if vehicleSold is null, if so, fetch it based on VIN
        Vehicle vehicleSold = salesContract.getVehicleSold();
        if (vehicleSold == null) {
            if (salesContract.getVin() == 0) {
                throw new RuntimeException("Vehicle VIN must be provided in the sales contract.");
            }
            // Fetch the vehicle using the VIN from the salesContract
            vehicleSold = findVehicleByVin(salesContract.getVin());
            if (vehicleSold == null) {
                throw new RuntimeException("Vehicle not found for VIN: " + salesContract.getVin());
            }
            salesContract.setVehicleSold(vehicleSold); // Set the vehicle in the SalesContract
        }

        // Extract vehicle price and calculate shared costs
        double vehiclePrice = vehicleSold.getPrice();
        double salesTax = vehiclePrice * salesContract.getSalesTaxPercentage();
        double recordingFee = salesContract.getRecordingFeeAmount();
        double processingFee = salesContract.getProcessingAmount();
        double totalPrice = vehiclePrice + salesTax + recordingFee + processingFee;

        // Initialize finance-specific variables
        double monthlyPayment = 0;
        boolean financeOption = salesContract.isFinance();

        if (financeOption) {
            double financeRate = (vehiclePrice < 10000) ? 0.0525 : 0.0425; // Interest rate
            int financeTerm = (vehiclePrice < 10000) ? 24 : 48;           // Loan term
            monthlyPayment = calculateLoanPayment(totalPrice, financeRate, financeTerm);
        }

        // Set calculated values in the SalesContract object
        salesContract.setSalesTax(salesTax);
        salesContract.setRecordingFee(recordingFee);
        salesContract.setProcessingFee(processingFee);
        salesContract.setTotalPrice(totalPrice);
        salesContract.setMonthlyPayment(monthlyPayment);

        // Save the SalesContract to the database
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement query = connection.prepareStatement(
                    Queries.insertSalesContract(), PreparedStatement.RETURN_GENERATED_KEYS)) {

                query.setString(1, salesContract.getContractDate());
                query.setInt(2, salesContract.getCustomerId());
                query.setInt(3, vehicleSold.getVin());
                query.setDouble(4, salesTax);
                query.setDouble(5, recordingFee);
                query.setDouble(6, processingFee);
                query.setDouble(7, totalPrice);
                query.setDouble(8, monthlyPayment);
                query.setBoolean(9,financeOption);

                int rowsAffected = query.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("Insert failed, no rows affected.");
                }

                // Retrieve generated keys to set the contractId
                try (ResultSet generatedKeys = query.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        salesContract.setContractId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Insert failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting sales contract", e);
        }

        return salesContract;
    }

    /**
     * Helper method
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
