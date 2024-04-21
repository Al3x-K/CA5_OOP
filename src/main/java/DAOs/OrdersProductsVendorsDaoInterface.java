package DAOs;

import DTOs.*;
import Exceptions.DaoException;

import java.util.List;

public interface OrdersProductsVendorsDaoInterface
{
    public List<Offer> getItemsByOrderId(int orderId) throws DaoException;

    public void deleteOrderItems(int orderId) throws DaoException;

    public void insertOrderItem(int orderId, Offer item) throws DaoException;
}