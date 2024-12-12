package com.pluralsight.dealership.CarDealershipAPI.controllers;

import com.pluralsight.dealership.CarDealershipAPI.dao.interfaces.SalesContractDao;
import com.pluralsight.dealership.CarDealershipAPI.models.contract.LeaseContract;
import com.pluralsight.dealership.CarDealershipAPI.models.contract.SalesContract;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SalesContractCtrl {

    private final SalesContractDao salesContractDao;

    public SalesContractCtrl(SalesContractDao salesContractDao) {
        this.salesContractDao = salesContractDao;
    }

    //pass
    @RequestMapping(path = "/salesContract/contractId/{id}", method= RequestMethod.GET)
    public SalesContract getSalesContractById(@PathVariable int id) {
        System.out.println("Request accepted: " + id);
        SalesContract salesContract = salesContractDao.findSalesContractById(id);
        return salesContract;
    }

    @RequestMapping(path = "/salesContract", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public SalesContract addLeaseContract(@RequestBody SalesContract salesContract) {
        // Log the product details that are being added
        System.out.println("Adding new contract:");
        return salesContractDao.add(salesContract);
    }


}
