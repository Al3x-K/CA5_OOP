package DAOs;

import DTOs.Product;
import Exceptions.DaoException;
import java.sql.*;
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

    /**
     * Main author: Samuel Sukovský
     *
     */
    @Override
    public void deleteProduct(int productId) throws DaoException
    {
        ProductsVendorsDaoInterface IProductsVendorsDao = new MySqlProductsVendorsDao();
        IProductsVendorsDao.deleteByProductID(productId);

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = this.getConnection();
            String query = "DELETE FROM PRODUCTS WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DaoException("deleteProduct() " + e.getMessage());
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
                throw new DaoException("deleteProduct() " + e.getMessage());
            }
        }
    }
    /**
     * Main author: Aleksandra Kail
     *
     */

    public void insertProducts(Product product) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = this.getConnection();
            String query = "INSERT INTO products (ProductID, ProductName) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());

            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new DaoException("insertProducts() " + e.getMessage());
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
                throw new DaoException("insertProducts() " + e.getMessage());
            }
        }
    }

    /**
     * Main author: Aleksandra Kail
     *
     */
    @Override
    public void updateProductById(int id, Product updatedProduct) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = this.getConnection();

            String query = "UPDATE Products SET ProductName = ? WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,updatedProduct.getName());
            preparedStatement.setInt(2,id);

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected == 0)
            {
                throw new DaoException("No Product found with ID: " + id);
            }
        }
        catch(SQLException e)
        {
            throw new DaoException("updateProductById() " + e.getMessage());
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
                throw new DaoException("updateProductById() " + e.getMessage());
            }
        }
    }

}
