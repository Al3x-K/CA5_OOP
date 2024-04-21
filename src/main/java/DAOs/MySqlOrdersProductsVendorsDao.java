package DAOs;

import DTOs.Offer;
import DTOs.Order;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlOrdersProductsVendorsDao extends MySqlDao implements OrdersProductsVendorsDaoInterface
{
    /**
     * Main author: Samuel Sukovský
     *
     */
    @Override
    public List<Offer> getItemsByOrderId(int orderId) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Order order = null;
        List<Offer> itemList = new ArrayList<>();

        try
        {
            connection = this.getConnection();
            String query = "SELECT ProductID, VendorID, ProductName, Price, Quantity FROM OrdersProductsVendors JOIN Products USING (ProductID) WHERE OrderID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,orderId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int productId = resultSet.getInt("ProductID");
                int vendorId = resultSet.getInt("VendorID");
                String productName = resultSet.getString("ProductName");
                double price = resultSet.getDouble("Price");
                int quantity = resultSet.getInt("Quantity");
                Offer o = new Offer(productId, vendorId, productName, price, quantity);
                itemList.add(o);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("getItemsByOrderId() " + e.getMessage());
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
                throw new DaoException("getItemsByOrderId() " + e.getMessage());
            }
        }
        return itemList;
    }

    /**
     * Main author: Samuel Sukovský
     *
     */
    @Override
    public void deleteOrderItems(int orderId) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = this.getConnection();
            String query = "DELETE FROM OrdersProductsVendors WHERE OrderID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DaoException("deleteOrderItems() " + e.getMessage());
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
                throw new DaoException("deleteOrderItems() " + e.getMessage());
            }
        }
    }

    /**
     * Main author: Samuel Sukovský
     *
     */
    public void insertOrderItem(int orderId, Offer item) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = this.getConnection();
            String query = "INSERT INTO OrdersProductsVendors (OrderID, ProductID, VendorID, Price, Quantity) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, item.getProductId());
            preparedStatement.setInt(3, item.getVendorId());
            preparedStatement.setDouble(4, item.getPrice());
            preparedStatement.setInt(5, item.getQuantity());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DaoException("insertOrderItem() " + e.getMessage());
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
                throw new DaoException("insertOrderItem() " + e.getMessage());
            }
        }
    }
}