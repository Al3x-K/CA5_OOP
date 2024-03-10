package org.example;

import DAOs.ProductDaoInterface;
import DAOs.MySqlProductDao;
import DTOs.Product;
import Exceptions.DaoException;
import java.util.List;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {
        ProductDaoInterface IProductDao = new MySqlProductDao();

        try
        {
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
                System.out.println("Product found: " + product);
            }
            else
            {
                System.out.println("Product with that id not found");
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
            }}

        catch (DaoException e)
        {
            e.printStackTrace();
        }
    }
}
