package com.pluralsight.dealership.CarDealershipAPI.dao.interfaces;

import com.pluralsight.dealership.CarDealershipAPI.models.Dealership;

import java.util.List;

public interface DealershipDao {


    List<Dealership> getAllDealership();

    Dealership findDealershipById(int dealershipId);
}
