import java.sql.*;
import java.util.Map;
import java.util.stream.Collectors;

public class Database {
    private Connection connection;
    private Statement statement;

    public Database() {
        this.connection = null;
        this.statement = null;
    }

    public void connect(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void disconnect() {
        try {
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database: " + e.getMessage());
        }
    }

    public void createTable(String tableName, String columns) {
        try {
            statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + columns + ")";
            statement.executeUpdate(query);
            System.out.println("Table " + tableName + " created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public void insert(String tableName, Map<String, Object> columnValues) {
        try {
            statement = connection.createStatement();

            // Remove the ID column from the map
            columnValues.remove("id");

            String columns = String.join(", ", columnValues.keySet());
            String values = columnValues.values().stream()
                    .map(value -> {
                        if (value instanceof String) {
                            return "'" + value + "'";
                        } else {
                            return value.toString();
                        }
                    })
                    .collect(Collectors.joining(", "));

            String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
            statement.executeUpdate(query);
            System.out.println("Data inserted into " + tableName + " successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
        }
    }

    public void update(String tableName, String setValues, String condition) {
        try {
            statement = connection.createStatement();
            String query = "UPDATE " + tableName + " SET " + setValues + " WHERE " + condition;
            statement.executeUpdate(query);
            System.out.println("Data updated in " + tableName + " successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating data: " + e.getMessage());
        }
    }

    public boolean delete(String tableName, String condition) {
        try {
            statement = connection.createStatement();
            String query = "DELETE FROM " + tableName + " WHERE " + condition;
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting data: " + e.getMessage());
            return false;
        }
    }

    public String query(String tableName, String columns, String condition) {
        try {
            statement = connection.createStatement();
            String query = "SELECT " + columns + " FROM " + tableName;
            if (condition != null && !condition.isEmpty()) {
                query += " WHERE " + condition;
            }
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // You can process each row of the result here
                return resultSet.getString(1); // Assuming one column
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error querying data: " + e.getMessage());
            // Return a default value or handle the exception as needed
        }

        // Provide a default return value here if needed
        return null;
    }


    public boolean isValid(String tableName, String condition) {
        try {
            statement = connection.createStatement();
            String query = "SELECT COUNT(*) FROM " + tableName;
            if (condition != null && !condition.isEmpty()) {
                query += " WHERE " + condition;
            }
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int count = resultSet.getInt(1);
            resultSet.close();
            return count > 0;
        } catch (SQLException e) {
            System.err.println("Error checking validity: " + e.getMessage());
            return false;
        } finally {
            // Ensure statement is closed even if an exception occurs
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
        }
    }
}
