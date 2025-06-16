import java.sql.*;
import java.util.Scanner;

public class SQLInjectionExample {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "user", "password");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        Statement stmt = conn.createStatement();
        // Vulnerable SQL Query (Direct user input)
        String sql = "SELECT * FROM users WHERE username = '" + username + "'";

        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println("User found: " + rs.getString("username"));
        }
        stmt.close();
        conn.close();
    }
}
