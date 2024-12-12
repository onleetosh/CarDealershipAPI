package com.pluralsight.dealership.CarDealershipAPI.dao.interfaces;

import com.pluralsight.dealership.CarDealershipAPI.models.contract.LeaseContract;

import java.util.List;

public interface LeaseContractDao {

    LeaseContract findLeaseContractById(int id);

    LeaseContract add(LeaseContract leaseContract);
}
