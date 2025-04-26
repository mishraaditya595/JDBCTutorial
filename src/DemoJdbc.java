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

        //create statement
        Statement statement = connection.createStatement();

        //execute statement
        //delete data
        displayData(statement);
        deleteData(statement);

        //insert data
        displayData(statement);
        insertData(statement);


        //update data
        displayData(statement);
        updateData(statement);


        displayData(statement);
        //insert data using prepared statement
        insertDataUsingPreparedStatement(connection);

        displayData(statement);

        //close connection
        connection.close();
        System.out.println("====connection closed====");
    }

    private static void insertDataUsingPreparedStatement(Connection connection) throws SQLException {
        int sid = 5;
        String name = "Ravi";
        int marks = 37;

        String query = "insert into student values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, sid);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, marks);
        preparedStatement.execute();

        System.out.println("Inserted data using prepared statements");
    }

    private static void displayData(Statement statement) throws SQLException {
        String sql = "select * from student";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.print(resultSet.getInt("sid")+ "-");
            System.out.print(resultSet.getString("name")+ "-");
            System.out.print(resultSet.getInt("marks"));
            System.out.println();
        }
        System.out.println("==== ====");
    }

    private static void deleteData(Statement statement) throws SQLException {
        String query = "delete from student where sid=4";
        boolean insertionStatus = statement.execute(query);
        System.out.println("Data deletion done");
    }

    private static void updateData(Statement statement) throws SQLException {
        String query = "update student set marks=28 where sid=4";
        boolean insertionStatus = statement.execute(query);
        System.out.println("Data updation done");
    }

    private static void insertData(Statement statement) throws SQLException {
        String query = "insert into student values (4, 'Shubham', 27)";
        boolean insertionStatus = statement.execute(query);
        System.out.println("Data insertion done");
    }
}
