//import package
import java.sql.*;
public class DemoJdbc {
    /*
        import package
        load and register driver
        create connection
        create statement
        execute statement
        process result
        close
     */
    public static void main(String[] args) throws Exception {
        //load and register driver
        Class.forName("org.postgresql.Driver");

        //create connection
        String url = "jdbc:postgresql://localhost:5432/demo";
        String username = "postgres";
        String password = "1209";
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("====connection established====");

        //create and execute statement
        String sql = "select name from student where sid = 1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        String name = resultSet.getString("name");
        System.out.println("Name: "+name);

        //close connection
        connection.close();
        System.out.println("====connection closed====");
    }
}
