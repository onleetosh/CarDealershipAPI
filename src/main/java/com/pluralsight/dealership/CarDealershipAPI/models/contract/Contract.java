package com.pluralsight.dealership.CarDealershipAPI.models.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pluralsight.dealership.CarDealershipAPI.models.Vehicle;

public abstract class Contract {

    protected int contractId;
    protected String contractDate;
    protected int customerId;
    protected Vehicle vehicleSold;
    protected double totalPrice;
    protected double monthlyPayment;


    public Contract(){}


    public Contract(int contractId,
                    String contractDate,
                    int customerId,
                    Vehicle vehicleSold,
                    double totalPrice,
                    double monthlyPayment) {
        this.contractId = contractId;
        this.contractDate = contractDate;
        this.customerId = customerId;
        this.vehicleSold = vehicleSold;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    public Contract(int contractId,
                    String contractDate,
                    int customerId,
                    Vehicle vehicleSold) {
        this.contractId = contractId;
        this.contractDate = contractDate;
        this.customerId = customerId;
        this.vehicleSold = vehicleSold;
        this.totalPrice = getTotalPrice();
        this.monthlyPayment = getMonthlyPayment();
    }


    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    @JsonProperty("vin") // Ensure this annotation exists
    private int vin;


    public int getVin() {
        if (this.vehicleSold != null) {
            return this.vehicleSold.getVin();
        }
        return vin; // Return the VIN directly if vehicleSold is null
    }

    public void setVin(int vin) {
        this.vin = vin;
    }



    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();


}
