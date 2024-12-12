package com.pluralsight.dealership.CarDealershipAPI.dao.interfaces;

import com.pluralsight.dealership.CarDealershipAPI.models.contract.SalesContract;

public interface SalesContractDao{


    SalesContract findSalesContractById(int id);

    SalesContract add(SalesContract salesContract);
}
