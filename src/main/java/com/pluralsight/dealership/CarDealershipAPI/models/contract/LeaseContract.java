package com.pluralsight.dealership.CarDealershipAPI.models.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pluralsight.dealership.CarDealershipAPI.models.Vehicle;
import com.pluralsight.dealership.CarDealershipAPI.util.Calculation;

import java.text.DecimalFormat;

public class LeaseContract extends Contract {

    /**
     * Lease Information
     */
    private double expectEndingValue; //  e = p * (50/100)
    private double leaseFee; // f = p * (7/100)

    private double expectEndingValuePercentage = 0.5;
    private double leaseFeePercentage = 0.07;


    private static final DecimalFormat df = new DecimalFormat("#.00");

    public LeaseContract(){}
    public LeaseContract(int contractId,
                         String contractDate,
                         int customerId,
                         Vehicle vehicleSold,
                         double expectEndingValue,
                         double leaseFee,
                         double totalPrice,
                         double monthlyPayment) {
        super(contractId, contractDate, customerId, vehicleSold, totalPrice, monthlyPayment);
        this.expectEndingValue = vehicleSold.getPrice() * expectEndingValuePercentage;
        this.leaseFee = vehicleSold.getPrice() * leaseFeePercentage;

    }

    public LeaseContract(int contractId,
                         String contractDate,
                         int customerId,
                         Vehicle vehicleSold) {
        super(contractId, contractDate, customerId, vehicleSold);
        // Calculate values based on the vehicle price
        double vehiclePrice = vehicleSold.getPrice();
        this.expectEndingValue = vehiclePrice * expectEndingValuePercentage;
        this.leaseFee = vehiclePrice * leaseFeePercentage;
        this.totalPrice = this.expectEndingValue + this.leaseFee;
        this.monthlyPayment = Calculation.calculateLoanPayment(this.totalPrice, 0.04, 36);
    }


    public double getExpectEndingValue() {
        return expectEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public double getExpectEndingValuePercentage() {
        return expectEndingValuePercentage;
    }


    public double getLeaseFeePercentage() {
        return leaseFeePercentage;
    }

    public void setExpectEndingValue(double expectEndingValue) {
        this.expectEndingValue = expectEndingValue;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }


    // Getters with formatted output for JSON
    @JsonProperty("expectEndingValue")
    public String getFormattedExpectEndingValue() {
        return df.format(expectEndingValue);
    }

    @JsonProperty("leaseFee")
    public String getFormattedLeaseFee() {
        return df.format(leaseFee);
    }

    @JsonProperty("totalPrice")
    public String getFormattedTotalPrice() {
        return df.format(totalPrice);
    }

    @JsonProperty("monthlyPayment")
    public String getFormattedMonthlyPayment() {
        return df.format(monthlyPayment);
    }



    /**
     * Override to calculate and return the value amount for total price and monthly payment
     */

    //all leases are financed at 4.0% for 36 mos
    @Override
    public double getTotalPrice() {
        return expectEndingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double financeRate = 0.04;
        double financeTerm = 36;
        return Calculation.calculateLoanPayment(getTotalPrice(), financeRate, financeTerm);
    }

    public String toString() {
        return "LeaseContract{" +
                "contractId=" + contractId +
                ", contractDate='" + contractDate + '\'' +
                ", customerId=" + customerId +
                ", vehicleSold=" + vehicleSold +
                ", expectEndingValue=" + String.format("%.2f", expectEndingValue) +
                ", leaseFee=" + String.format("%.2f", leaseFee) +
                ", totalPrice=" + String.format("%.2f", totalPrice) +
                ", monthlyPayment=" + String.format("%.2f", monthlyPayment) +
                '}';
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
