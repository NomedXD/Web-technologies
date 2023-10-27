package by.bsuir.repositories.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.bsuir.domain.Category;
import by.bsuir.domain.Product;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {
    private static final Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);
    private static final String GET_All_CATEGORIES = "SELECT * FROM categories";
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM categories WHERE id = ?";
    private static final String CREATE_CATEGORY  = "INSERT INTO categories(name, some_text, image_path) VALUES (?, ?, ?)";
    private static final String GET_CATEGORY_BY_NAME = "SELECT * FROM categories WHERE name = ?";

    @Override
    public void create(Category entity) throws SQLExecutionException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CATEGORY);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSomeText());
            preparedStatement.setString(3, entity.getImagePath());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.warn("SQLException while creating category. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Category> read() throws SQLExecutionException {
        List<Category> categoryArrayList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_All_CATEGORIES);
            while (resultSet.next()) {
                categoryArrayList.add(Category.builder().id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .someText(resultSet.getString("some_text"))
                        .imagePath(resultSet.getString("image_path")).build());
            }
            return categoryArrayList;
        } catch (SQLException e) {
            logger.warn("SQLException while getting categories. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Category update(Category entity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<Category> findById(Integer categoryId) throws SQLExecutionException {
        Optional<Category> category = Optional.empty();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_BY_ID);
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = Optional.of(Category.builder().id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .someText(resultSet.getString("some_text"))
                        .imagePath(resultSet.getString("image_path")).build());
            }
            return category;
        } catch (SQLException e) {
            logger.warn("SQLException while getting category by it's id. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Optional<Category> findByName(String categoryName) throws SQLExecutionException {
        Optional<Category> category = Optional.empty();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_BY_NAME);
            preparedStatement.setString(1, categoryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = Optional.of(Category.builder().id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .someText(resultSet.getString("some_text"))
                        .imagePath(resultSet.getString("image_path")).build());
            }
            return category;
        } catch (SQLException e) {
            logger.warn("SQLException while getting category by it's name. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
