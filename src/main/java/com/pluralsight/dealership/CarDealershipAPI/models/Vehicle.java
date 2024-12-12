package com.pluralsight.dealership.CarDealershipAPI.models;

public class Vehicle {

    /**
     * Properties of a vehicle object
     */
    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;


    public Vehicle(){ }
    public Vehicle(int vin,
                   int year,
                   String make,
                   String model,
                   String vehicleType,
                   String color,
                   int odometer,
                   double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }


    /**
     * Get the vehicle's values
     */
    public int getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

    public int getOdometer() {
        return odometer;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Set the vehicle's values
     */

    public void setVin(int vin) {
        this.vin = vin;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public void setPrice(double price) {
        this.price = price;
    }




    @Override
    public String toString() {
//        String colorString;
//        if (color.equalsIgnoreCase("Red"))
//            colorString = ColorCodes.RED + color + ColorCodes.RESET;
//        else if (color.equalsIgnoreCase("Blue"))
//            colorString = ColorCodes.BLUE + color + ColorCodes.RESET;
//        else if (color.equalsIgnoreCase("Yellow"))
//            colorString = ColorCodes.YELLOW + color + ColorCodes.RESET;
//        else if (color.equalsIgnoreCase("Green"))
//            colorString = ColorCodes.GREEN + color + ColorCodes.RESET;
//        else
//            colorString = color;

        return String.format(" %5d | %5d | %10s | %10s | %7s | %15s | %8d | $%3.2f ",
                this.vin,
                this.year,
                this.make,
                this.model,
                this.vehicleType,
                this.color, //colorString,
                this.odometer,
                this.price
        );

    }
}
