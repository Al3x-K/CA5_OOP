package DAOs;

import DTOs.Product;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlProductDao extends MySqlDao implements ProductDaoInterface
{
    /**
     * Main author: Aleksandra Kail
     *
     */
    @Override
    public List<Product> getAllProducts() throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Product> productList = new ArrayList<>();

        try
        {
            connection = this.getConnection();
            String query = "SELECT * FROM PRODUCTS";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int productId = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                Product p = new Product(productId,productName);
                productList.add(p);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("getAllProductsResultSet() " + e.getMessage());
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
                throw new DaoException("getAllProducts() " + e.getMessage());
            }
        }
        return productList;
    }

    /**
     * Main author: Aleksandra Kail
     *
     */
    @Override
    public Product getProductById(int productId) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM PRODUCTS WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,productId);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                int productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");

                product = new Product(productID,productName);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("getProductById() " + e.getMessage());
        }
        finally
        {
            try
            {
                if(resultSet != null)
                {
                    resultSet.close();
                }
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
                throw new DaoException("getProductById() " + e.getMessage());
            }
        }
        return product;
    }
}
