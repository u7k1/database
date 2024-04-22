# About
the [Database.java](https://github.com/u7k1/database/blob/main/Database.java) is a SQL class that give you simple actions with your database.
## Usage
### • Connecting to your Database.
```java
String url = "jdbc:mysql://localhost:3306/mydatabase";
String username = "root";
String password = "password";

Database db = new Database();
db.connect(url, username, password);
```
### • Inserting Data.
```java
Map<String, Object> values = new HashMap<>();
values.put("name", "Alice");
values.put("age", 30);
db.insert("users", values);

```
