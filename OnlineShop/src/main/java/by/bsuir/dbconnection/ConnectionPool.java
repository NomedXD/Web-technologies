package by.bsuir.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Connection pool to handle database request connection. Class is singleton
 */
public class ConnectionPool {
    private static volatile ConnectionPool instance;
    private static final String DB_PROPERTY_FILE = "application";
    private static final String DB_URL = "db.url";
    private static final String DB_LOGIN = "db.login";
    private static final String DB_PASS = "db.pass";
    private static final int MAX_CONNECTION_COUNT = 10;
    private static final int MIN_CONNECTION_COUNT = 5;

    private static final String url;
    private static final String login;
    private static final String pass;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_PROPERTY_FILE);
        url = resourceBundle.getString(DB_URL);
        login = resourceBundle.getString(DB_LOGIN);
        pass = resourceBundle.getString(DB_PASS);
    }

    private volatile int currentConnectionNumber = MIN_CONNECTION_COUNT;
    private final BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(MAX_CONNECTION_COUNT, true);

    /**
     * @return singleton connection pool object
     */
    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }

        return instance;
    }

    private ConnectionPool() {
        for (int i = 0; i < MIN_CONNECTION_COUNT; i++) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                pool.add(DriverManager.getConnection(url, login, pass));
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void openAdditionalConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            pool.add(DriverManager.getConnection(url, login, pass));
            currentConnectionNumber++;
        } catch (SQLException | ClassNotFoundException e) {
            throw new Exception("New connection wasn't add in the connection pool", e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            if (pool.isEmpty() && currentConnectionNumber < MAX_CONNECTION_COUNT) {
                openAdditionalConnection();
            }
            connection = pool.take();
        } catch (Exception ex) {
            Thread.currentThread().interrupt();
            System.out.println("Max count of connections was reached!");
        }
        return connection;
    }

    /**
     * Returns connection into connection pool
     *
     * @param connection of this connection pool
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            if (currentConnectionNumber > MIN_CONNECTION_COUNT) {
                currentConnectionNumber--;
            }
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Connection wasn't returned into pool properly");
            }
        }
    }

    /**
     * Close all connections from connection pool
     */
    public void disconnect() {
        pool.forEach(s -> {
            try {
                s.close();
            } catch (SQLException e) {
                System.out.println("Cannot disconnect pool connection");
            }
        });
    }
}
