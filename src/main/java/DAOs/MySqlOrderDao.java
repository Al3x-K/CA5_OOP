package DAOs;

import DTOs.*;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class MySqlOrderDao extends MySqlDao implements OrderDaoInterface
{
    /**
     * Main author: Samuel Sukovský
     *
     */
    @Override
    public Order getOrderById(int orderId) throws DaoException
    {
        OrdersProductsVendorsDaoInterface IOrdersProductsVendorsDao = new MySqlOrdersProductsVendorsDao();

        List<Offer> itemList = IOrdersProductsVendorsDao.getItemsByOrderId(orderId);
        Order order = new Order(orderId, itemList);

        return order;
    }

    /**
     * Main author: Samuel Sukovský
     *
     */
    @Override
    public List<Order> getAllOrders() throws DaoException
    {
        OrdersProductsVendorsDaoInterface IOrdersProductsVendorsDao = new MySqlOrdersProductsVendorsDao();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orderList = new ArrayList<>();

        try
        {
            connection = this.getConnection();
            String query = "SELECT OrderID FROM orders";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int orderId = resultSet.getInt("OrderID");
                List<Offer> itemList = IOrdersProductsVendorsDao.getItemsByOrderId(orderId);
                orderList.add(new Order(orderId, itemList));
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("getAllOrders() " + e.getMessage());
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
                throw new DaoException("getAllOrders() " + e.getMessage());
            }
        }
        return orderList;
    }

    /**
     * Main author: Samuel Sukovský
     *
     */
    @Override
    public int getMaxOrderId() throws DaoException
    {
        OrdersProductsVendorsDaoInterface IOrdersProductsVendorsDao = new MySqlOrdersProductsVendorsDao();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;

        try
        {
            connection = this.getConnection();
            String query = "SELECT MAX(OrderID) FROM Orders";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                id = resultSet.getInt("MAX(OrderID)");
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("getMaxOrderId() " + e.getMessage());
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
                throw new DaoException("getMaxOrderId() " + e.getMessage());
            }
        }
        return id;
    }

    /**
     * Main author: Samuel Sukovský
     *
     */
    @Override
    public void deleteOrder(int orderId) throws DaoException
    {
        OrdersProductsVendorsDaoInterface IOrdersProductsVendorsDao = new MySqlOrdersProductsVendorsDao();
        IOrdersProductsVendorsDao.deleteOrderItems(orderId);

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = this.getConnection();
            String query = "DELETE FROM Orders WHERE OrderID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DaoException("deleteOrder() " + e.getMessage());
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
                throw new DaoException("deleteOrder() " + e.getMessage());
            }
        }
    }

    /**
     * Main author: Samuel Sukovský
     *
     */
    public void insertOrder(int orderId) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = this.getConnection();
            String query = "INSERT INTO Orders (orderId) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderId);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DaoException("insertOrder() " + e.getMessage());
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
                throw new DaoException("insertOrder() " + e.getMessage());
            }
        }
    }
}
