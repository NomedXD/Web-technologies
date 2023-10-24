package by.bsuir.repositories.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.bsuir.domain.User;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private static final String CREATE_USER = "INSERT INTO users(mail, password, name, surname, date) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_ACCOUNT_DATA = "UPDATE users SET mobile = ?, street = ?, accommodation_number = ?, flat_number = ? WHERE id = ?";
    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE mail = ?";

    @Override
    public void create(User entity) throws SQLExecutionException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
            preparedStatement.setString(1, entity.getMail());
            preparedStatement.setString(2, BCrypt.withDefaults().hashToString(12, entity.getPassword().toCharArray()));
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setString(4, entity.getSurname());
            preparedStatement.setDate(5, Date.valueOf(entity.getDate()));
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.warn("SQLException while creating user. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<User> read() throws SQLExecutionException {
        return null;
    }

    @Override
    public User update(User entity) throws SQLExecutionException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ACCOUNT_DATA);
            preparedStatement.setString(1, entity.getMobile());
            preparedStatement.setString(2, entity.getStreet());
            preparedStatement.setString(3, entity.getAccommodationNumber());
            preparedStatement.setString(4, entity.getFlatNumber());
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.executeUpdate();
            return findUserByMail(entity.getMail()).get();
        } catch (SQLException e) {
            logger.warn("SQLException while updating user account data. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<User> findUserByMail(String userMail) throws SQLExecutionException {
        Optional<User> user = Optional.empty();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL);
            preparedStatement.setString(1, userMail);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(User.builder().id(resultSet.getInt("id"))
                        .mail(resultSet.getString("mail"))
                        .password(resultSet.getString("password"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .date(resultSet.getDate("date").toLocalDate())
                        .mobile(resultSet.getString("mobile"))
                        .street(resultSet.getString("street"))
                        .accommodationNumber(resultSet.getString("accommodation_number"))
                        .flatNumber(resultSet.getString("flat_number")).build());
            }
            return user;
        } catch (SQLException e) {
            logger.warn("SQLException while finding user. Full message: " + e.getMessage());
            throw new SQLExecutionException();
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
