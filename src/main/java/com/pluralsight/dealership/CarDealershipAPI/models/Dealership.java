package com.pluralsight.dealership.CarDealershipAPI.models;

public class Dealership {

    private int dealershipID;
    private String name;
    private String address;
    private String phone;


    public Dealership(){ }

    public Dealership(int dealershipID, String name, String address, String phone) {
        this.dealershipID = dealershipID;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Get the dealership values
     * @return
     */

    public int getDealershipID() {
        return dealershipID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    /**
     * Set the dealership values
     * @return
     */

    public void setDealershipID(int dealershipID) {
        this.dealershipID = dealershipID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Dealership{" +
                "dealershipID=" + dealershipID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
