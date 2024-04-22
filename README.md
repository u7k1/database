# About
the [Database.java](https://github.com/u7k1/database/blob/main/Database.java) is a SQL class that give you simple actions with your database.
## Usage
### • Connect to your Database.
```java
String url = "jdbc:mysql://localhost:3306/mydatabase";
String username = "root";
String password = "password";

Database db = new Database();
db.connect(url, username, password);
```
### • Insert Data.
```java
Map<String, Object> values = new HashMap<>();
values.put("name", "Alice");
values.put("age", 30);
db.insert("users", values);

```
### • Create Table.
```java
db.createTable("users", "id INT PRIMARY KEY, name VARCHAR(255), age INT");
```
### • Update Data.
```java
db.update("users", "age = 31", "name = 'Alice'");
```
### • Query Data.
```java
db.query("users", "*", null);
//or
db.query("users", "*", "name = 'Alice'");
```
