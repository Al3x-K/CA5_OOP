package DAOs;

import DTOs.Product;
import DTOs.Vendor;
import Exceptions.DaoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlVendorDao extends MySqlDao implements VendorDaoInterface
{
    /**
     * Main author: Aleksandra Kail
     *
     */
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

    /**
     * Main author: Aleksandra Kail
     *
     */
    @Override
    public Vendor getVendorById(int vendorId) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Vendor vendor = null;

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM VENDORS WHERE VendorID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, vendorId);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                int vendorID = resultSet.getInt("VendorID");
                String vendorName = resultSet.getString("VendorName");

                vendor = new Vendor(vendorID,vendorName);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("getVendorById() " + e.getMessage());
        }
        finally
        {
            try
            {
                if(resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            }
            catch (SQLException e)
            {
                throw new DaoException("getVendorById() " + e.getMessage());
            }
        }
        return vendor;
    }

    /**
     * Main author: Samuel Sukovský
     *
     */
    @Override
    public void deleteVendor(int vendorId) throws DaoException
    {
        ProductsVendorsDaoInterface IProductsVendorsDao = new MySqlProductsVendorsDao();
        IProductsVendorsDao.deleteByVendorID(vendorId);

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = this.getConnection();
            String query = "DELETE FROM VENDORS WHERE VendorID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, vendorId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DaoException("deleteVendor() " + e.getMessage());
        }
        finally
        {
            try
            {
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            }
            catch (SQLException e)
            {
                throw new DaoException("deleteVendor() " + e.getMessage());
            }
        }
    }

    /**
     * Main author: Aleksandra Kail
     *
     */
    @Override
    public void updateVendorById(int id, Vendor updatedVendor) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = this.getConnection();

            String query = "UPDATE Vendors SET VendorName = ? WHERE VendorID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,updatedVendor.getName());
            preparedStatement.setInt(2,id);

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected == 0)
            {
                throw new DaoException("No Vendor found with ID: " + id);
            }
        }
        catch(SQLException e)
        {
            throw new DaoException("updateVendorById() " + e.getMessage());
        }
        finally
        {
            try
            {
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
                throw new DaoException("updateVendorById() " + e.getMessage());
            }
        }
    }

    public void insertVendor(Vendor vendor) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = this.getConnection();
            String query = "INSERT INTO vendors (VendorID, VendorName) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,vendor.getId());
            preparedStatement.setString(2, vendor.getName());

            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new DaoException("insertVendor() " + e.getMessage());
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("insertVendor() " + e.getMessage());
            }
        }
    }
}
