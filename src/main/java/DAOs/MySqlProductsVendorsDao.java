package DAOs;

import DTOs.Product;
import DTOs.Vendor;
import Exceptions.DaoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlProductsVendorsDao extends MySqlDao implements ProductsVendorsDaoInterface
{
    /**
     * Main author: Samuel Sukovský
     *
     */
    @Override
    public List<Product> getProductsSoldByVendorId(int vendorId) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Product> productList = new ArrayList<>();

        try
        {
            connection = this.getConnection();
            String query = "SELECT ProductID, ProductName FROM PRODUCTS JOIN PRODUCTSVENDORS USING(ProductID) WHERE VendorID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,vendorId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int productId = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                Product p = new Product(productId,productName);
                productList.add(p);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("getProductsSoldByVendorId() " + e.getMessage());
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
                throw new DaoException("getProductsSoldByVendorId() " + e.getMessage());
            }
        }
        return productList;
    }

    @Override
    public List<Vendor> getVendorsSellingProductId(int productId) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Vendor> vendorList = new ArrayList<>();

        try
        {
            connection = this.getConnection();
            String query = "SELECT VendorID, VendorName FROM VENDORS LEFT JOIN PRODUCTSVENDORS USING(VendorID) WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int vendorId = resultSet.getInt("VendorId");
                String vendorName = resultSet.getString("VendorName");
                Vendor v = new Vendor(vendorId, vendorName);
                vendorList.add(v);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("getVendorsSellingProductId() " + e.getMessage());
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
                throw new DaoException("getVendorsSellingProductId() " + e.getMessage());
            }
        }
        return vendorList;
    }

    @Override
    public void deleteByProductID(int productId) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = this.getConnection();
            String query = "DELETE FROM PRODUCTSVENDORS WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DaoException("deleteByProductID() " + e.getMessage());
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
                throw new DaoException("getVendorsSellingProductId() " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteByVendorID(int vendorId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();
            String query = "DELETE FROM PRODUCTSVENDORS WHERE VendorID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, vendorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("deleteByVendorID() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("getVendorsSellingProductId() " + e.getMessage());
            }
        }
    }

    /**
     * Main author: Aleksandra Kail
     *
     */
    public void updateProductsVendorsById(int pId, int vId, double price, int quantity) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();

            String query = "UPDATE productsvendors SET price = ?, quantity = ? WHERE productID = ? AND vendorID = ? ";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, quantity);
            preparedStatement.setInt(3, pId);
            preparedStatement.setInt(4, vId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new DaoException("No matching record found for product ID " + pId + " and vendor ID: " + vId);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("updateProductsVendors() " + e.getMessage());
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
                throw new DaoException("updateProductsVendors() " + e.getMessage());
            }
        }
    }

}