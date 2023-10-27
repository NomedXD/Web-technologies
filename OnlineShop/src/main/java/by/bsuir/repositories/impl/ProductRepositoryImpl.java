package by.bsuir.repositories.impl;

import by.bsuir.domain.Product;
import by.bsuir.domain.User;
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
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    private static final String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products WHERE category_id = ?";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private static final String CREATE_PRODUCT = "INSERT INTO products(name, description, category_id, price, image_path) VALUES(?, ?, ?, ?, ?)";
    @Override
    public void create(Product entity) throws SQLExecutionException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PRODUCT);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setInt(3, entity.getCategoryId());
            preparedStatement.setFloat(4, entity.getPrice());
            preparedStatement.setString(5, entity.getImagePath());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.warn("SQLException while creating product. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
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
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Optional<Product> findById(Integer productId) throws SQLExecutionException {
        Optional<Product> product = Optional.empty();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = Optional.of(Product.builder().id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .categoryId(resultSet.getInt("category_id"))
                        .price(resultSet.getFloat("price"))
                        .imagePath(resultSet.getString("image_path")).build());
            }
            return product;
        } catch (SQLException e) {
            logger.warn("SQLException while getting product by it's id. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
