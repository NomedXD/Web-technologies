package by.bsuir.repositories.impl;

import by.bsuir.domain.DiscountCode;
import by.bsuir.domain.Order;
import by.bsuir.domain.Product;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderRepositoryImpl implements OrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(OrderRepositoryImpl.class);
    private static final String GET_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE user_id = ?";
    private static final String GET_ORDER_ID_BY_UUID = "SELECT id FROM orders WHERE uuid = ?";
    private static final String GET_DISCOUNT_CODE_BY_ID = "SELECT * FROM discount_codes WHERE id = ?";
    private static final String GET_ORDER_PRODUCTS = "SELECT * FROM orders_products JOIN products ON orders_products.product_id = products.id WHERE order_id = ?";
    private static final String CREATE_ORDER = "INSERT INTO orders(price, date, user_id, cc_number, shipping_type, shipping_cost, discount_code_id, address, customer_notes, uuid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_PRODUCT_ORDER_REFERENCE = "INSERT INTO orders_products(order_id, product_id) VALUES (?, ?)";
    private static final String REMOVE_ORDER_BY_ID = "DELETE FROM orders WHERE id = ?";

    @Override
    public void create(Order entity) throws SQLExecutionException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER);
            preparedStatement.setFloat(1, entity.getPrice());
            preparedStatement.setDate(2, Date.valueOf(entity.getDate()));
            preparedStatement.setInt(3, entity.getUserId());
            preparedStatement.setString(4, entity.getCreditCardNumber());
            preparedStatement.setString(5, entity.getShippingType());
            preparedStatement.setFloat(6, entity.getShippingCost());
            if (entity.getDiscountCode() == null) {
                preparedStatement.setNull(7, Types.VARCHAR);
            } else {
                preparedStatement.setInt(7, entity.getDiscountCode().getId());
            }
            preparedStatement.setString(8, entity.getAddress());
            preparedStatement.setString(9, entity.getCustomerNotes());
            UUID uuid = UUID.randomUUID();
            preparedStatement.setString(10, uuid.toString());
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(GET_ORDER_ID_BY_UUID);
            preparedStatement.setString(1, uuid.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt("id"));
            }
            preparedStatement = connection.prepareStatement(INSERT_PRODUCT_ORDER_REFERENCE);
            for (Product product:entity.getProductList()) {
                preparedStatement.setInt(1, entity.getId());
                preparedStatement.setInt(2, product.getId());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            logger.warn("SQLException while creating order. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
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
    public void delete(int id) throws SQLExecutionException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_ORDER_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.warn("SQLException while deleting order. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Order> findAllByUserId(Integer userId) throws SQLExecutionException {
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
                        .productList(new ArrayList<>())
                        .customerNotes(resultSet.getString("customer_notes")).build();
                if (resultSet.getObject("discount_code_id") != null) {
                    preparedStatement = connection.prepareStatement(GET_DISCOUNT_CODE_BY_ID);
                    preparedStatement.setInt(1, resultSet.getInt("discount_code_id"));
                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    if (resultSet1.next()) {
                        order.setDiscountCode(DiscountCode.builder().id(resultSet1.getInt("id"))
                                .name(resultSet1.getString("name"))
                                .discount(resultSet1.getFloat("discount")).build());
                    }
                }
                preparedStatement = connection.prepareStatement(GET_ORDER_PRODUCTS);
                preparedStatement.setInt(1, resultSet.getInt("id"));
                ResultSet resultSet1 = preparedStatement.executeQuery();
                while (resultSet1.next()) {
                    order.getProductList().add(Product.builder().id(resultSet1.getInt("id"))
                            .name(resultSet1.getString("name"))
                            .description(resultSet1.getString("description"))
                            .categoryId(resultSet1.getInt("category_id"))
                            .price(resultSet1.getFloat("price"))
                            .imagePath(resultSet1.getString("image_path")).build());
                }
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException e) {
            logger.warn("SQLException while creating user. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
