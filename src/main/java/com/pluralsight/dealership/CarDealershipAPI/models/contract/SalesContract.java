package com.pluralsight.dealership.CarDealershipAPI.models.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pluralsight.dealership.CarDealershipAPI.models.Vehicle;
import com.pluralsight.dealership.CarDealershipAPI.util.Calculation;

import java.text.DecimalFormat;

public class SalesContract extends Contract {


    /**
     * Sales Information
     */

    private static final double SALES_TAX_PERCENTAGE = 0.05;
    private static final double RECORDING_FEE_AMOUNT = 100;
    private static final double VEHICLE_BELOW_10K_AMOUNT = 295;
    private static final double VEHICLE_ABOVE_10K_AMOUNT = 495;
    private static final double VEHICLE_AMOUNT_THRESHOLD = 10000;

    private double salesTax; // %5
    private double recordingFee; // $100.00
    private double processingFee; // a fee of $295 if vehicle under $10k, else $495
    private boolean isFinance; // do they want to finance (yes/no)

    private static final DecimalFormat df = new DecimalFormat("#.00");

    public SalesContract(){}
    public SalesContract(int contractId,
                         String contractDate,
                         int customerId,
                         Vehicle vin,
                         double salesTax,
                         double recordingFee,
                         double processingFee,
                         double totalPrice,
                         boolean isFinance,
                         double monthlyPayment) {
        super(contractId, contractDate, customerId, vin, totalPrice, monthlyPayment);
        this.salesTax = vehicleSold.getPrice() * SALES_TAX_PERCENTAGE;
        this.recordingFee = RECORDING_FEE_AMOUNT;
        this.processingFee = (vehicleSold.getPrice() < VEHICLE_AMOUNT_THRESHOLD) ? VEHICLE_BELOW_10K_AMOUNT : VEHICLE_ABOVE_10K_AMOUNT;
        this.isFinance = isFinance;
    }


    /**
     * Getters
     * @return
     */

    public double getSalesTax() {
        return salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public boolean isFinance() {
        return isFinance;
    }

    public double getSalesTaxPercentage() {
        return SALES_TAX_PERCENTAGE;
    }

    public double getRecordingFeeAmount() {
        return RECORDING_FEE_AMOUNT;
    }

    public double getProcessingAmount() {
        if (getVehicleSold() == null) {
            throw new IllegalStateException("VehicleSold must be set before calculating processing amount.");
        }
        return (getVehicleSold().getPrice() < VEHICLE_AMOUNT_THRESHOLD)
                ? VEHICLE_BELOW_10K_AMOUNT : VEHICLE_ABOVE_10K_AMOUNT;
    }

    public double getVehicleBelow10kAmount() {
        return VEHICLE_BELOW_10K_AMOUNT;
    }

    public double getVehicleAbove10kAmount() {
        return VEHICLE_ABOVE_10K_AMOUNT;
    }

    public double getVehicleAmount() {
        return VEHICLE_AMOUNT_THRESHOLD;
    }

    // Getters with formatted output for JSON
    @JsonProperty("salesTax")
    public String getFormattedSalesTaxValue() {
        return df.format(salesTax);
    }

    @JsonProperty("processingFee")
    public String getFormattedProcessingFee() {
        return df.format(processingFee);
    }
    @JsonProperty("recodingFee")
    public String getFormattedRecordingFee() {
        return df.format(recordingFee);
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
     * Setter
     * @param salesTax
     */
    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public void setFinance(boolean finance) {
        isFinance = finance;
    }

    @Override
    public double getTotalPrice(){
        return getVehicleSold().getPrice() + salesTax + processingFee + recordingFee;
    }

    @Override
    public double getMonthlyPayment(){
        if (isFinance) {

            double financeRate = (getVehicleSold().getPrice() < VEHICLE_AMOUNT_THRESHOLD) ? 0.0525 : 0.0425;
            double financeTerm = (getVehicleSold().getPrice() < VEHICLE_AMOUNT_THRESHOLD) ? 24 : 48;
            return Calculation.calculateLoanPayment(getTotalPrice(), financeRate, financeTerm);
        }
        else {
            return 0;
        }
    }


    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
