package by.bsuir.repositories.impl;

import by.bsuir.domain.DiscountCode;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.DiscountCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DiscountCodeRepositoryImpl implements DiscountCodeRepository {
    private static final Logger logger = LoggerFactory.getLogger(DiscountCodeRepositoryImpl.class);
    private static final String GET_DISCOUNT_CODE_BY_NAME = "SELECT * FROM discount_codes WHERE name = ?";

    @Override
    public void create(DiscountCode entity) throws SQLExecutionException {
    }

    @Override
    public List<DiscountCode> read() throws SQLExecutionException {
        return null;
    }

    @Override
    public DiscountCode update(DiscountCode entity) throws SQLExecutionException {
        return null;
    }

    @Override
    public void delete(int id) throws SQLExecutionException {

    }

    @Override
    public Optional<DiscountCode> findDiscountCodeByName(String name) throws SQLExecutionException {
        Optional<DiscountCode> discountCode = Optional.empty();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_DISCOUNT_CODE_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                discountCode = Optional.of(DiscountCode.builder().id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .discount(resultSet.getFloat("discount")).build());
            }
            return discountCode;
        } catch (SQLException e) {
            logger.warn("SQLException while finding discount code. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
