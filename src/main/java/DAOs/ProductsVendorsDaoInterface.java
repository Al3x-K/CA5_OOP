package DAOs;

import DTOs.*;
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

    public List<Offer> getAllOffers() throws DaoException;

    public List<Offer> getOffersByVendorId(int vendorId) throws DaoException;

    public List<Offer> getOffersByProductId(int productId) throws DaoException;

    public Product getCheapestProductSoldByVendor(int vendorId) throws DaoException;

    public Vendor getVendorSellingProductForCheapest(int productId) throws DaoException;

    public void deleteByProductID(int productId) throws DaoException;

    public void deleteByVendorID(int vendorId) throws DaoException;

    public void updateProductsVendorsById(int pId, int vId, double price, int quantity) throws DaoException;

    public void insertOffer(Product p, Vendor v, double price, int quantity) throws DaoException;
}
