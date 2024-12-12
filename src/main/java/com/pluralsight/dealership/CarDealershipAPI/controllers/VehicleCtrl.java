package com.pluralsight.dealership.CarDealershipAPI.controllers;

import com.pluralsight.dealership.CarDealershipAPI.dao.interfaces.VehicleDao;
import com.pluralsight.dealership.CarDealershipAPI.models.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleCtrl {

    private final VehicleDao vehicleDao;


    public VehicleCtrl(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }


    @RequestMapping(path="/vehicle", method= RequestMethod.GET)
    public List<Vehicle> getVehicles() {
        System.out.println("Fetching all products.");
        return  vehicleDao.getAll();

    }

    @RequestMapping(path="/vehicle/color/{color}", method = RequestMethod.GET)
    public List<Vehicle> getVehiclesByColor(@PathVariable String color) {
        System.out.println("Request accepted: " + color);
        return vehicleDao.findVehicleByColor(color);
    }

    //pass
    @RequestMapping(path="/vehicle/type/{vehicleType}", method = RequestMethod.GET)
    public List<Vehicle> getVehiclesByType(@PathVariable String vehicleType) {
        System.out.println("Request accepted: " + vehicleType);
        return vehicleDao.findVehicleByType(vehicleType);
    }


    @RequestMapping(path="/vehicle/make/{make}", method = RequestMethod.GET)
    public List<Vehicle> getVehiclesByMake(@PathVariable String make) {
        System.out.println("Request accepted: " + make);
        return vehicleDao.findVehicleByMake(make);
    }

    //pass
    @RequestMapping(path="/vehicle/model/{model}", method = RequestMethod.GET)
    public List<Vehicle> getVehiclesByModel(@PathVariable String model) {
        System.out.println("Request accepted: " + model);
        return vehicleDao.findVehicleByModel(model);
    }

    //pass
    @RequestMapping(path="/vehicle/year/max/{maxYear}", method = RequestMethod.GET)
    public List<Vehicle> getVehiclesByMaxYear(@PathVariable int maxYear) {
        System.out.println("Request accepted: " + maxYear);
        return vehicleDao.findVehicleByMaxYear(maxYear);
    }
    @RequestMapping(path="/vehicle/year/min/{minYear}", method = RequestMethod.GET)
    public List<Vehicle> getVehiclesByMinYear(@PathVariable int minYear) {
        System.out.println("Request accepted: " + minYear);
        return vehicleDao.findVehicleByMinYear(minYear);
    }

    @RequestMapping(path="/vehicle/price/max/{maxPrice}", method = RequestMethod.GET)
    public List<Vehicle> getVehiclesByMaxPrice(@PathVariable double maxPrice) {
        System.out.println("Request accepted: " + maxPrice);
        return vehicleDao.findVehicleByMaxPrice(maxPrice);
    }

    @RequestMapping(path="/vehicle/price/min/{minPrice}", method = RequestMethod.GET)
    public List<Vehicle> getVehiclesByMinPrice(@PathVariable double minPrice) {
        System.out.println("Request accepted: " + minPrice);
        return vehicleDao.findVehicleByMinPrice(minPrice);
    }


    //pass
    @RequestMapping(path="/vehicle/odometer/max/{maxMileage}", method = RequestMethod.GET)
    public List<Vehicle> getVehiclesByMaxMileage(@PathVariable int maxMileage) {
        System.out.println("Request accepted: " + maxMileage);
        return vehicleDao.findVehicleByMaxMileage(maxMileage);
    }

    //pass
    @RequestMapping(path="/vehicle/odometer/min/{minMileage}", method = RequestMethod.GET)
    public List<Vehicle> getVehiclesByMinMileage(@PathVariable int minMileage) {
        System.out.println("Request accepted: " + minMileage);
        return vehicleDao.findVehicleByMinMileage(minMileage);
    }


//pass
    @RequestMapping(path="/vehicle", method= RequestMethod.POST)
    @ResponseStatus(value= HttpStatus.CREATED)
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){
        // Log the product details that are being added
        System.out.println("Adding new vehicle:");
        return  vehicleDao.add(vehicle);
    }

    @RequestMapping(path = "/vehicle/dealership/{dealershipId}", method = RequestMethod.POST)
    @ResponseStatus(value= HttpStatus.CREATED)
    public Vehicle addVehicleToDealershipInventory(@PathVariable int dealershipId, @RequestBody Vehicle vehicle){
        return  vehicleDao.addToDealershipInventory(dealershipId, vehicle);
    }

//pass
    @RequestMapping(path = "/vehicle/{vin}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable int vin){
        // Log the categoryId
        System.out.println("Deleting Vehicle with Vin: " + vin);
        vehicleDao.delete(vin);
    }



    @RequestMapping(path = "/vehicle/vin/{vin}", method = RequestMethod.PUT)
    public void updateVehicle(@PathVariable int vin, @RequestBody Vehicle vehicle) {
        // Log the details
        vehicleDao.update(vin, vehicle);
    }

}
