package com.pluralsight.dealership.CarDealershipAPI.dao.interfaces;

import com.pluralsight.dealership.CarDealershipAPI.models.Dealership;
import com.pluralsight.dealership.CarDealershipAPI.models.Vehicle;

import java.util.List;

public interface VehicleDao {


    List<Vehicle> getAll();

    List<Vehicle> findVehicleByColor(String color);
    List<Vehicle> findVehicleByType(String vehicleType);
    List<Vehicle> findVehicleByMaxYear(int maxYear);
    List<Vehicle> findVehicleByMinYear(int minYear);
    List<Vehicle> findVehicleByMinPrice(double minPrice);
    List<Vehicle> findVehicleByMaxPrice(double maxPrice);
    List<Vehicle> findVehicleByMinMileage(int minMileage);
    List<Vehicle> findVehicleByMaxMileage(int maxMileage);
    List<Vehicle> findVehicleByMake(String make);
    List<Vehicle> findVehicleByModel(String model);

    Vehicle add(Vehicle vehicle);
    Vehicle addToDealershipInventory(int dealershipId, Vehicle vehicle);

    void delete(int vin);
    void update(int vin, Vehicle vehicle );

}
