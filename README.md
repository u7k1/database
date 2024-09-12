
# Simple Java Database Utility

The [Database.java](https://github.com/u7k1/database/blob/main/Database.java) is a lightweight utility class designed to simplify database interactions in Java using SQL. With this class, you can effortlessly manage database connections, perform CRUD (Create, Read, Update, Delete) operations, and execute basic queries.

## Key Features
- **Simplified Database Connection**: Easily connect to your MySQL database using JDBC.
- **Efficient Data Manipulation**: Insert, update, and query records with minimal code.
- **Custom Table Creation**: Define and create tables dynamically.

## Getting Started

To use this utility, ensure you have the following prerequisites:

### Prerequisites
- **Java 8** or higher.
- **MySQL JDBC Driver**.
  
  You can download the MySQL JDBC driver [here](https://dev.mysql.com/downloads/connector/j/).
  
- **MySQL Database** running locally or remotely.

### Installation

1. Download the `Database.java` file from the [repository](https://github.com/u7k1/database/blob/main/Database.java).
2. Add the MySQL JDBC driver to your project dependencies (e.g., via Maven, Gradle, or manually).

## Usage

Below are some basic examples to help you get started with the `Database.java` utility:

### 1. Connect to Your Database

```java
String url = "jdbc:mysql://localhost:3306/mydatabase";
String username = "root";
String password = "password";

Database db = new Database();
db.connect(url, username, password);
```

### 2. Create a Table

```java
db.createTable("users", "id INT PRIMARY KEY, name VARCHAR(255), age INT");
```

### 3. Insert Data

```java
Map(String, Object> values = new HashMap<>();
values.put("name", "Alice");
values.put("age", 30);
db.insert("users", values);
```

### 4. Update Data

```java
db.update("users", "age = 31", "name = 'Alice'");
```

### 5. Query Data

Fetch all records from a table:

```java
db.query("users", "*", null);
```

Fetch specific records with a condition:

```java
db.query("users", "*", "name = 'Alice'");
```

## Contributing

Contributions are welcome! Feel free to submit a pull request or open an issue if you find any bugs or have suggestions for improvements.

## License

This project is licensed under the MIT License. See the [LICENSE](https://github.com/u7k1/database/blob/main/LICENSE) file for details.
