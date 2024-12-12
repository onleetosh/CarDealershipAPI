package com.pluralsight.dealership.CarDealershipAPI.util;

public class Queries {


    /**
     * Select vehicle queries
     * @return
     */
    public static String selectVehicle(){
        return "SELECT * FROM Vehicle";
    }
    public static String selectVehicleWhereColor(){
        return """
                SELECT * FROM Vehicle WHERE Vehicle.Color = ?
                """;
    }
    public static String selectVehicleWhereType() {
        return """
                SELECT * FROM Vehicle WHERE Vehicle.VehicleType = ?
                """;
    }
    public static String selectVehicleByMake(){
        return """
                SELECT * FROM Vehicle WHERE Make = ?
                """;
    }
    public static String selectVehiclesByModel(){
        return """
                SELECT * FROM Vehicle WHERE Model = ?
                """;
    }
    public static String selectVehicleByMinYear(){
        return """
                SELECT *
                FROM Vehicle
                WHERE Year = (SELECT MIN(?) FROM Vehicle);
                """;
    }
    public static String selectVehicleByMaxYear(){
        return """
                SELECT *
                FROM Vehicle
                WHERE Year = (SELECT MAX(?) FROM Vehicle);
                """;
    }
    public static String selectVehicleByMinPrice(){
        return """
                SELECT *
                FROM Vehicle
                WHERE Price = (SELECT MIN(?) FROM Vehicle);
                """;
    }
    public static String selectVehicleByMaxPrice(){
        return """
                SELECT *
                FROM Vehicle
                WHERE Price = (SELECT MAX(?) FROM Vehicle);
                """;
    }
    public static String selectVehicleByMinMileage() {
        return """
                SELECT *
                FROM Vehicle
                WHERE Odometer = (SELECT MIN(?) FROM Vehicle);
                """;

    }
    public static String selectVehicleByMaxMileage() {
        return """
                SELECT *
                FROM Vehicle
                WHERE Odometer = (SELECT MAX(?) FROM Vehicle);
                """;
    }

    /**
     * Insert, Delete, and Update vehicle queries
     * @return
     */

    public static String insertVehicle(){
        return """
            INSERT INTO 
                Vehicle (Vin, Year, Make, Model, VehicleType, Color, Odometer, Price)
            VALUES 
                (?, ?, ?, ?, ?, ?, ?, ?)
            """;
    }

    public static String insertVehicleToDealershipInventory(){
        return """
                     INSERT INTO `inventory` (`dealershipID`, `vin`)
                     VALUES (?, ?);
                     """;
    }

    public static String deleteVehicle(){
        return """
                DELETE FROM Vehicle WHERE Vin = ?
                """;
    }

    public static String updateVehicle(){
        return """
               UPDATE Vehicle
               SET  
                    Year = ?, 
                    Make = ?, 
                    Model = ?, 
                    VehicleType = ?, 
                    Color = ?, 
                    Odometer = ?, 
                    Price = ? 
               WHERE 
                    Vin = ?;
                """;
    }


    /**
     * Sales contract related queries
     * @return
     */
    public static String selectSalesContractById(){
        return """
                SELECT 
                    ContractId, 
                    ContractDate, 
                    CustomerId, 
                    Vin,
                    SalesTaxes,
                    RecordingFee,
                    ProcessingFee,
                    TotalPrice, 
                    isFinance,
                    MonthlyPayment 
                FROM
                    SalesContract
                WHERE ContractId = ?
                """;
    }


    /**
     * Sales contract related queries
     * @return
     */
    public static String selectLeaseContractById(){

        return """
                SELECT 
                    ContractId, 
                    ContractDate, 
                    CustomerId, 
                    Vin,
                    ExpectedFinalValue,
                    LeaseFee,
                    TotalPrice, 
                    MonthlyPayment 
                FROM
                    LeaseContract
                WHERE ContractId = ?
                """;
    }


    public static String selectVehicleByVin() {
        return  """
                SELECT * FROM Vehicle WHERE Vin = ?
                """;
    }



    public static String insertLeaseContract(){
        return """
                INSERT INTO LeaseContract
                    (ContractDate,
                     CustomerId, 
                     Vin,
                     ExpectedFinalValue,
                     LeaseFee, 
                     TotalPrice,
                     MonthlyPayment)
                VALUES
                    (?,?,?,?,?,?,?)
                """;
    }

    public static String insertSalesContract(){
        return """
                   INSERT INTO SalesContract
                    (ContractDate,
                     CustomerId, 
                     Vin,
                     SalesTaxes,
                     RecordingFee, 
                     ProcessingFee,
                     TotalPrice,
                     MonthlyPayment,
                     isFinance)
                VALUES
                    (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
    }

    public static String insertCustomer(){
        return """
                INSERT INTO Customer
                    (FirstName, LastName, Phone, Email)
                VALUES 
                    (?, ?, ?, ?)
                """;
    }




}
