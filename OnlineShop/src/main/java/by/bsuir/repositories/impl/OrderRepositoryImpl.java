package by.bsuir.repositories.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.bsuir.domain.DiscountCode;
import by.bsuir.domain.Order;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.OrderRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private static final String GET_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE user_id = ?";
    private static final String GET_DISCOUNT_CODE_BY_ID = "SELECT * FROM discount_codes WHERE id = ?";

    @Override
    public Order create(Order entity) {
        return null;
    }

    @Override
    public List<Order> read() throws SQLExecutionException {
        return null;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Order> findAllByUserId(Integer userId) {
        List<Order> orderList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = Order.builder().id(resultSet.getInt("id"))
                        .price(resultSet.getFloat("price"))
                        .date(resultSet.getDate("date").toLocalDate())
                        .userId(resultSet.getInt("user_id"))
                        .creditCardNumber(resultSet.getString("cc_number"))
                        .shippingType(resultSet.getString("shipping_type"))
                        .shippingCost(resultSet.getFloat("shipping_cost"))
                        .address(resultSet.getString("address"))
                        .customerNotes(resultSet.getString("customer_notes")).build();
                if (resultSet.getObject("discount_code_id") != null) {
                    preparedStatement = connection.prepareStatement(GET_DISCOUNT_CODE_BY_ID);
                    preparedStatement.setInt(1, resultSet.getInt("discount_code_id"));
                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    if (resultSet1.next()){
                        order.setDiscountCode(DiscountCode.builder().id(resultSet1.getInt("id"))
                                .name(resultSet1.getString("name"))
                                .discount(resultSet1.getFloat("discount")).build());
                    }
                }

            }
        } catch (SQLException e) {
            logger.warn("SQLException while creating user. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
