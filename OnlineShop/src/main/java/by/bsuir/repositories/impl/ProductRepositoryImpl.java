package by.bsuir.repositories.impl;

import by.bsuir.domain.Product;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    private static final String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products WHERE category_id = ?";
    @Override
    public Product create(Product entity) {
        return null;
    }
    @Override
    public List<Product> read() {
        return null;
    }
    @Override
    public Product update(Product entity) {
        return null;
    }
    @Override
    public void delete(int id) {

    }

    @Override
    public List<Product> getProductsByCategoryId(Integer categoryId) throws SQLExecutionException {
        List<Product> productArrayList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY_ID);
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productArrayList.add(Product.builder().id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .categoryId(resultSet.getInt("category_id"))
                        .price(resultSet.getFloat("price"))
                        .imagePath(resultSet.getString("image_path")).build());
            }
            return productArrayList;
        } catch (SQLException e) {
            logger.warn("SQLException while getting products by category id. Full message: " + e.getMessage());
            throw new SQLExecutionException("How did you get here. Check us later");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
