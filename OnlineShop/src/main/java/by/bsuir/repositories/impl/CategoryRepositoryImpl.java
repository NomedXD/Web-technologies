package by.bsuir.repositories.impl;

import by.bsuir.domain.Category;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {
    private static final Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);
    private static final String GET_All_CATEGORIES = "SELECT * FROM categories";

    @Override
    public Category create(Category entity) {
        return null;
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
            throw new SQLExecutionException("How did you get here. Check us later");
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
    public Optional<Category> getCategoryById(Integer id) {
        return Optional.empty();
    }
}
