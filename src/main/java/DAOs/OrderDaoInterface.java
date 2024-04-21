package DAOs;

import DTOs.Offer;
import DTOs.Order;
import DTOs.Product;
import DTOs.Vendor;
import Exceptions.DaoException;

import java.util.List;

public interface OrderDaoInterface
{
    public Order getOrderById(int orderId) throws DaoException;

    public List<Order> getAllOrders() throws DaoException;

    public int getMaxOrderId() throws DaoException;

    public void deleteOrder(int orderId) throws DaoException;

    public void insertOrder(int orderId) throws DaoException;
}
