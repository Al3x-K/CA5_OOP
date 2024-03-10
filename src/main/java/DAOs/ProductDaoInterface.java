package DAOs;

import DTOs.Product;
import Exceptions.DaoException;
import java.util.List;
public class ProductDaoInterface
{
    public List<Product> getALLProducts() throws DaoException;

    public Product getProductById(int id) throws DaoException;
}
