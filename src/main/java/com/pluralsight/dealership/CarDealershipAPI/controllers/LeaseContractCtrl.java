package com.pluralsight.dealership.CarDealershipAPI.controllers;

import com.pluralsight.dealership.CarDealershipAPI.dao.interfaces.LeaseContractDao;
import com.pluralsight.dealership.CarDealershipAPI.models.Vehicle;
import com.pluralsight.dealership.CarDealershipAPI.models.contract.LeaseContract;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaseContractCtrl {

    private final LeaseContractDao leaseContractDao;


    public LeaseContractCtrl(LeaseContractDao leaseContractDao) {
        this.leaseContractDao = leaseContractDao;
    }

    //pass
    @RequestMapping(path = "/leaseContract/contractId/{id}", method= RequestMethod.GET)
    public LeaseContract getLeaseContractById(@PathVariable int id) {
        System.out.println("Request accepted: " + id);
        LeaseContract leaseContract = leaseContractDao.findLeaseContractById(id);
        return leaseContract;
    }

    //pass
    @RequestMapping(path = "/leaseContract", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public LeaseContract addLeaseContract(@RequestBody LeaseContract leaseContract) {
        // Log the product details that are being added
        System.out.println("Adding new contract:");
        return leaseContractDao.add(leaseContract);
    }

}
