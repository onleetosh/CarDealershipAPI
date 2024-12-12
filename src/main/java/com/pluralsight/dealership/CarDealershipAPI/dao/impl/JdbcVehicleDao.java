package com.pluralsight.dealership.CarDealershipAPI.dao.impl;


import com.pluralsight.dealership.CarDealershipAPI.dao.interfaces.VehicleDao;
import com.pluralsight.dealership.CarDealershipAPI.models.Vehicle;
import com.pluralsight.dealership.CarDealershipAPI.util.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcVehicleDao implements VehicleDao {

    private DataSource dataSource;

    @Autowired
    public JdbcVehicleDao(DataSource dataSource){
        this.dataSource = dataSource;
    }


    /**
     * GET ALL
     * @return
     */
    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(Queries.selectVehicle());
            ResultSet results = query.executeQuery())
        {
            while (results.next()){
                int vin = results.getInt(1);
                int year = results.getInt(2);
                String make = results.getString(3);
                String model = results.getString(4);
                String type = results.getString(5);
                String color = results.getString(6);
                int odometer = results.getInt(7);
                double price = results.getDouble(8);

                vehicles.add(new Vehicle(vin, year, make, model, type, color, odometer, price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    /**
     * GET BY COLOR
     */
    @Override
    public List<Vehicle> findVehicleByColor(String color) {

        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(
                    Queries.selectVehicleWhereColor()))
        {
            // Set the ID parameter for the query
            query.setString(1, color);

            // Execute the query and process the results
            try( ResultSet results = query.executeQuery()){
                while (results.next()){
                    int getVin = results.getInt(1);
                    int getYear = results.getInt(2);
                    String getMake = results.getString(3);
                    String getModel = results.getString(4);
                    String getType = results.getString(5);
                    String getColor = results.getString(6);
                    int getOdometer = results.getInt(7);
                    double getPrice = results.getDouble(8);

                    vehicles.add(new Vehicle(getVin, getYear, getMake, getModel, getType, getColor, getOdometer, getPrice));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles; // Return null if no matching category is found
    }

    /**
     * GET BY Type
     */
    @Override
    public List<Vehicle> findVehicleByType(String vehicleType) {

        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(
                    Queries.selectVehicleWhereType()))
        {
            // Set the ID parameter for the query
            query.setString(1, vehicleType);

            // Execute the query and process the results
            try( ResultSet results = query.executeQuery()){
                while (results.next()){
                    int getVin = results.getInt(1);
                    int getYear = results.getInt(2);
                    String getMake = results.getString(3);
                    String getModel = results.getString(4);
                    String getType = results.getString(5);
                    String getColor = results.getString(6);
                    int getOdometer = results.getInt(7);
                    double getPrice = results.getDouble(8);

                    vehicles.add(new Vehicle(getVin, getYear, getMake, getModel, getType, getColor, getOdometer, getPrice));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles; // Return null if no matching category is found
    }

    /**
     * GET BY MAX YEAR
     */
    @Override
    public List<Vehicle> findVehicleByMaxYear(int maxMax) {
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(
                    Queries.selectVehicleByMaxYear()))
        {
            // Set the ID parameter for the query
            query.setInt(1, maxMax);

            // Execute the query and process the results
            try( ResultSet results = query.executeQuery()){
                while (results.next()){
                    int getVin = results.getInt(1);
                    int getYear = results.getInt(2);
                    String getMake = results.getString(3);
                    String getModel = results.getString(4);
                    String getType = results.getString(5);
                    String getColor = results.getString(6);
                    int getOdometer = results.getInt(7);
                    double getPrice = results.getDouble(8);

                    vehicles.add(new Vehicle(getVin, getYear, getMake, getModel, getType, getColor, getOdometer, getPrice));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    /**
     * GET BY MIN YEAR
     */
    @Override
    public List<Vehicle> findVehicleByMinYear(int minYear) {
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(
                    Queries.selectVehicleByMinYear()))
        {
            // Set the ID parameter for the query
            query.setInt(1, minYear);

            // Execute the query and process the results
            try( ResultSet results = query.executeQuery()){
                while (results.next()){
                    int getVin = results.getInt(1);
                    int getYear = results.getInt(2);
                    String getMake = results.getString(3);
                    String getModel = results.getString(4);
                    String getType = results.getString(5);
                    String getColor = results.getString(6);
                    int getOdometer = results.getInt(7);
                    double getPrice = results.getDouble(8);

                    vehicles.add(new Vehicle(getVin, getYear, getMake, getModel, getType, getColor, getOdometer, getPrice));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }


    /**
     * GET BY MIN PRICE
     */
    @Override
    public List<Vehicle> findVehicleByMinPrice(double minPrice) {
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(
                    Queries.selectVehicleByMinPrice()))
        {
            // Set the ID parameter for the query
            query.setDouble(1, minPrice);

            // Execute the query and process the results
            try( ResultSet results = query.executeQuery()){
                while (results.next()){
                    int getVin = results.getInt(1);
                    int getYear = results.getInt(2);
                    String getMake = results.getString(3);
                    String getModel = results.getString(4);
                    String getType = results.getString(5);
                    String getColor = results.getString(6);
                    int getOdometer = results.getInt(7);
                    double getPrice = results.getDouble(8);

                    vehicles.add(new Vehicle(getVin, getYear, getMake, getModel, getType, getColor, getOdometer, getPrice));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }


    /**
     * GET BY MAX PRICE
     */
    @Override
    public List<Vehicle> findVehicleByMaxPrice(double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(
                    Queries.selectVehicleByMaxPrice()))
        {
            // Set the ID parameter for the query
            query.setDouble(1, maxPrice);

            // Execute the query and process the results
            try( ResultSet results = query.executeQuery()){
                while (results.next()){
                    int getVin = results.getInt(1);
                    int getYear = results.getInt(2);
                    String getMake = results.getString(3);
                    String getModel = results.getString(4);
                    String getType = results.getString(5);
                    String getColor = results.getString(6);
                    int getOdometer = results.getInt(7);
                    double getPrice = results.getDouble(8);

                    vehicles.add(new Vehicle(getVin, getYear, getMake, getModel, getType, getColor, getOdometer, getPrice));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;    }
    /**
     * GET BY MIN MILES
     */

    @Override
    public List<Vehicle> findVehicleByMinMileage(int minMileage) {
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(
                    Queries.selectVehicleByMinMileage()))
        {
            // Set the ID parameter for the query
            query.setInt(1, minMileage);

            // Execute the query and process the results
            try( ResultSet results = query.executeQuery()){
                while (results.next()){
                    int getVin = results.getInt(1);
                    int getYear = results.getInt(2);
                    String getMake = results.getString(3);
                    String getModel = results.getString(4);
                    String getType = results.getString(5);
                    String getColor = results.getString(6);
                    int getOdometer = results.getInt(7);
                    double getPrice = results.getDouble(8);

                    vehicles.add(new Vehicle(getVin, getYear, getMake, getModel, getType, getColor, getOdometer, getPrice));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;    }


    /**
     * GET BY MAX YEAR
     */
    @Override
    public List<Vehicle> findVehicleByMaxMileage(int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(
                    Queries.selectVehicleByMaxMileage()))
        {
            // Set the ID parameter for the query
            query.setInt(1, maxMileage);

            // Execute the query and process the results
            try( ResultSet results = query.executeQuery()){
                while (results.next()){
                    int getVin = results.getInt(1);
                    int getYear = results.getInt(2);
                    String getMake = results.getString(3);
                    String getModel = results.getString(4);
                    String getType = results.getString(5);
                    String getColor = results.getString(6);
                    int getOdometer = results.getInt(7);
                    double getPrice = results.getDouble(8);

                    vehicles.add(new Vehicle(getVin, getYear, getMake, getModel, getType, getColor, getOdometer, getPrice));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;    }

    /**
     * GET BY MAKe
     */
    @Override
    public List<Vehicle> findVehicleByMake(String make) {
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(
                    Queries.selectVehicleByMake()))
        {
            // Set the ID parameter for the query
            query.setString(1, make);

            // Execute the query and process the results
            try( ResultSet results = query.executeQuery()){
                while (results.next()){
                    int getVin = results.getInt(1);
                    int getYear = results.getInt(2);
                    String getMake = results.getString(3);
                    String getModel = results.getString(4);
                    String getType = results.getString(5);
                    String getColor = results.getString(6);
                    int getOdometer = results.getInt(7);
                    double getPrice = results.getDouble(8);

                    vehicles.add(new Vehicle(getVin, getYear, getMake, getModel, getType, getColor, getOdometer, getPrice));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;    }

    /**
     * GET BY MAKe
     * @param model
     * @return
     */
    @Override
    public List<Vehicle> findVehicleByModel(String model) {
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(
                    Queries.selectVehiclesByModel()))
        {
            // Set the ID parameter for the query
            query.setString(1, model);

            // Execute the query and process the results
            try( ResultSet results = query.executeQuery()){
                while (results.next()){
                    int getVin = results.getInt(1);
                    int getYear = results.getInt(2);
                    String getMake = results.getString(3);
                    String getModel = results.getString(4);
                    String getType = results.getString(5);
                    String getColor = results.getString(6);
                    int getOdometer = results.getInt(7);
                    double getPrice = results.getDouble(8);

                    vehicles.add(new Vehicle(getVin, getYear, getMake, getModel, getType, getColor, getOdometer, getPrice));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;    }

    //adds to vehicle to database but not into inventory
    @Override
    public Vehicle add(Vehicle vehicle) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement query = connection.prepareStatement(Queries.insertVehicle())) {

            // Set parameters
            query.setInt(1, vehicle.getVin());
            query.setInt(2, vehicle.getYear());
            query.setString(3, vehicle.getMake());
            query.setString(4, vehicle.getModel());
            query.setString(5, vehicle.getVehicleType());
            query.setString(6, vehicle.getColor());
            query.setInt(7, vehicle.getOdometer());
            query.setDouble(8, Double.parseDouble(String.format("%.2f",vehicle.getPrice())));

            // Execute update
            int affectedRows = query.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting vehicle failed, no rows affected.");
            }

            return vehicle;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting vehicle: " + e.getMessage(), e);
        }
    }

    @Override
    public Vehicle addToDealershipInventory(int dealershipId, Vehicle vehicle) {
        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement query = connection.prepareStatement(
                    Queries.insertVehicle()))
            {
                query.setInt(1, vehicle.getVin());
                query.setInt(2, vehicle.getYear());
                query.setString(3, vehicle.getMake());
                query.setString(4, vehicle.getModel());
                query.setString(5, vehicle.getVehicleType());
                query.setString(6, vehicle.getColor());
                query.setInt(7, vehicle.getOdometer());
                query.setDouble(8, Double.parseDouble(String.format("%.2f",vehicle.getPrice())));

                int rows = query.executeUpdate();

            }

            try(PreparedStatement query = connection.prepareStatement(
                    Queries.insertVehicleToDealershipInventory()))
            {
                query.setInt(1, dealershipId);
                query.setInt(2, vehicle.getVin());
                int rows = query.executeUpdate();
            }

            return vehicle;
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int vin) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement query = connection.prepareStatement(Queries.deleteVehicle())) {

            // Set the parameters for the query
            query.setInt(1, vin);

            // Execute the update query
            int rowsAffected = query.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Update failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating category", e);
        }
    }

    @Override
    public void update(int vin, Vehicle vehicle) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement query = connection.prepareStatement(Queries.updateVehicle())) {

            query.setInt(1, vehicle.getYear());
            query.setString(2, vehicle.getMake());
            query.setString(3, vehicle.getModel());
            query.setString(4, vehicle.getVehicleType());
            query.setString(5, vehicle.getColor());
            query.setInt(6, vehicle.getOdometer());
            query.setDouble(8, Double.parseDouble(String.format("%.2f",vehicle.getPrice())));
            query.setInt(8, vin); // VIN as part of the WHERE clause

            int rowsUpdated = query.executeUpdate();
            if (rowsUpdated == 0) {
                throw new RuntimeException("Vehicle with Vin " + vin + " not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating vehicle with vin " + vin, e);
        }
    }


}
