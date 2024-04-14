package DAOs;

import DTOs.Product;
import Exceptions.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface ProductDaoInterface
{
    public List<Product> getAllProducts() throws DaoException;

    public Product getProductById(int id) throws DaoException;

    public void deleteProduct(int productId) throws DaoException;

    public void insertProducts(Product product) throws DaoException;

    public void updateProductById(int id, Product updatedProduct) throws DaoException;
}