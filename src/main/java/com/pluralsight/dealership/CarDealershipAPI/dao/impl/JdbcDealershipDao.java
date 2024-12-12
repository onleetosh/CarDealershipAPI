package com.pluralsight.dealership.CarDealershipAPI.dao.impl;

import com.pluralsight.dealership.CarDealershipAPI.dao.interfaces.DealershipDao;
import com.pluralsight.dealership.CarDealershipAPI.models.Dealership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcDealershipDao implements DealershipDao {

    private DataSource dataSource;


    @Autowired
    public JdbcDealershipDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Dealership> getAllDealership() {
        return List.of();
    }

    @Override
    public Dealership findDealershipById(int dealershipId) {
        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement query = connection.prepareStatement(
                    """
                    select
                    DealershipID, DealershipName, address, phone
                    from dealership
                    where dealershipID = ?
                    """))
            {
                query.setInt(1, dealershipId);
                try(ResultSet results = query.executeQuery())
                {
                    Dealership dealership = null;
                    while(results.next()){

                        dealership = new Dealership(
                                results.getInt(1),
                                results.getString(2),
                                results.getString(3),
                                results.getString(4)
                        );

                    }
                    return dealership;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
