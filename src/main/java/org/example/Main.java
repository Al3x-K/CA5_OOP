package org.example;


import DTOs.Product;
import DTOs.Vendor;
import Exceptions.DaoException;
import DAOs.*;
import java.util.List;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {
        ProductDaoInterface IProductDao = new MySqlProductDao();
        VendorDaoInterface IVendorDao = new MySqlVendorDao();
        ProductsVendorsDaoInterface IProductsVendorsDao = new MySqlProductsVendorsDao();

        try
        {
            /**
             * Main author: Aleksandra Kail
             *
             */
            System.out.println("\nCall getAllProducts()");
            List<Product> products = IProductDao.getAllProducts();

            if(products.isEmpty())
            {
                System.out.println("There are no products");
            }
            else
            {
                for (Product product : products)
                {
                    System.out.println("Product: " + product.toString());
                }
            }

            System.out.println("\nCall getProductById()");
            int productId = 4;
            Product product = IProductDao.getProductById(productId);

            if(product != null)
            {
                System.out.println("Product with id " + productId + " was found: " + product);
            }
            else
            {
                System.out.println("Product with id " + productId + " is not valid.");
            }

            productId = 7;
            product = IProductDao.getProductById(productId);
            if(product != null)
            {
                System.out.println("Product with id " + productId + " was found: " + product);
            }
            else
            {
                System.out.println("Product with id " + productId + " is not valid.");
            }

            System.out.println("\nCall getAllVendors()");
            List<Vendor> vendors = IVendorDao.getAllVendors();

            if(vendors.isEmpty())
            {
                System.out.println("There are no vendors");
            }
            else
            {
                for (Vendor vendor : vendors)
                {
                    System.out.println("Vendor: " + vendor.toString());
                }
            }

            System.out.println("\nCall getVendorById()");
            int vendorId = 1;
            Vendor vendor = IVendorDao.getVendorById(vendorId);

            if(vendor != null)
            {
                System.out.println("Vendor with id " + vendorId + " was found: " + vendor);
            }
            else
            {
                System.out.println("Vendor with id " + vendorId + " is not valid.");
            }

            System.out.println("\nCall getVendorById()");
            vendorId = 3;
            vendor = IVendorDao.getVendorById(vendorId);

            if(vendor != null)
            {
                System.out.println("Vendor with id " + vendorId + " was found: " + vendor);
            }
            else
            {
                System.out.println("Vendor with id " + vendorId + " is not valid.");
            }

            /**
             * Main author: Samuel Sukovský
             *
             */

            IProductDao.deleteProduct(1);
            IVendorDao.deleteVendor(1);

            System.out.println("\nCall: getVendorsSellingProductId()");
            int id = 2;
            vendors = IProductsVendorsDao.getVendorsSellingProductId(id);

            if( vendors.isEmpty() )
                System.out.println("There are no vendors selling product " + id);
            else
            {
                for (Vendor v : vendors)
                    System.out.println("Vendor: " + v.toString());
            }

            System.out.println("\nCall: getProductsSoldByVendorId()");
            id = 2;
            products = IProductsVendorsDao.getProductsSoldByVendorId(id);

            if( products.isEmpty() )
                System.out.println("There are no products sold by vendor " + id);
            else {
                for (Product p : products)
                    System.out.println("Product: " + p.toString());
            }

            Product updatedProduct = new Product("Nose Piercing");

            /**
             * Main author: Aleksandra Kail
             *
             */

            System.out.println("\nCall: updateProductById()");
            IProductDao.updateProductById(3,updatedProduct);
            IProductDao.getProductById(3);
            if(updatedProduct != null)
            {
                System.out.println("Product with id " + id + " was found: " + updatedProduct);
            }
            else
            {
                System.out.println("Product with id " + id + " is not valid.");
            }

            Vendor updatedVendor = new Vendor("Luxe Gems");
            System.out.println("\nCall: updateVendorById()");
            IVendorDao.updateVendorById(3,updatedVendor);
            IVendorDao.getVendorById(3);
            if(updatedVendor != null)
            {
                System.out.println("Vendor with id " + id + " was found: " + updatedVendor);
            }
            else
            {
                System.out.println("Vendor with id " + id + " is not valid.");
            }

            System.out.println("\nCall: updateProductsVendorsById()");
            int pID = 3;
            int vID = 2;
            double newPrice = 99.99;
            int newQuantity =17;
            IProductsVendorsDao.updateProductsVendorsById(pID,vID,newPrice,newQuantity);
            IProductsVendorsDao.getProductsSoldByVendorId(2);
            if( products.isEmpty() )
                System.out.println("There are no products sold by vendor " + id);
            else {
                for (Product p : products)
                    System.out.println("Product: " + p.toString());
            }
            IProductsVendorsDao.getVendorsSellingProductId(3);
            if( vendors.isEmpty() )
                System.out.println("There are no vendors selling product " + id);
            else
            {
                for (Vendor v : vendors)
                    System.out.println("Vendor: " + v.toString());
            }
        }
        catch (DaoException e)
        {
            e.printStackTrace();
        }
    }
}
