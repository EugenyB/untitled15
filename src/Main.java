import java.sql.*;

public class Main {

    public static void main(String[] args) {
	    new Main().run();
    }

    private void run() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/mainacaddemo1",
                "eugeny",
                "123")) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from person where name like ?");
            preparedStatement.setString(1, "Сидор%");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void runInsert() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/mainacaddemo1",
                "eugeny",
                "123")) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into person (name, age, salary) values (?,?,?)");
            preparedStatement.setString(1, "Qwerty");
            preparedStatement.setInt(2, 43);
            preparedStatement.setDouble(3, 1523);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void runSelect() {
        try  {
            // 1
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/mainacaddemo1",
                    "eugeny",
                    "123");
            // 2
            Statement statement = connection.createStatement();
            // 3a
            ResultSet resultSet = statement.executeQuery("select * from person");
            // 4a
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double salary = resultSet.getDouble("salary");
                System.out.println(id + " " + name + " " + age + " " + salary);
            }
            // 5
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
