package DAOs;

import DTOs.Vendor;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlVendorDao extends MySqlDao implements VendorDaoInterface
{
    @Override
    public List<Vendor> getAllVendors() throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Vendor> productList = new ArrayList<>();

        try
        {
            connection = this.getConnection();
            String query = "SELECT * FROM VENDORS";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int vendorId = resultSet.getInt("VendorID");
                String vendorName = resultSet.getString("VendorName");
                Vendor p = new Vendor(vendorId,vendorName);
                productList.add(p);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("getAllProductsResultSet() " + e.getMessage());
        }
        finally
        {
            try
            {
                if(resultSet != null)
                {
                    resultSet.close();
                }
                if(preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if(connection != null)
                {
                    freeConnection(connection);
                }
            }
            catch (SQLException e)
            {
                throw new DaoException("getAllProducts() " + e.getMessage());
            }
        }
        return productList;
    }

    @Override
    public Vendor getVendorById(int productId)
    {
        Vendor vendor = null;
        return vendor;
    }
}
