package DAOs;

import DTOs.Product;
import DTOs.Vendor;
import Exceptions.DaoException;

import java.util.List;

/**
 * Main author: Samuel Sukovský
 *
 */
public interface ProductsVendorsDaoInterface
{
    public List<Product> getProductsSoldByVendorId(int vendorId) throws DaoException;

    public List<Vendor> getVendorsSellingProductId(int productId) throws DaoException;

    public void deleteByProductID(int productId) throws DaoException;

    public void deleteByVendorID(int vendorId) throws DaoException;


}