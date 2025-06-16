import java.sql.*;

public class SQLInjection {
    public static void main(String[] args) {
        String account_owner_id = args.length > 0 ? args[0] : "1";
        
        // Vulnerable SQL statement (concatenation)
        String accountBalanceQuery = "SELECT accountNumber FROM accounts WHERE account_owner_id = '" + account_owner_id + "'";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test", "user", "password");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(accountBalanceQuery);

            while (rs.next()) {
                System.out.println("Account number: " + rs.getString("accountNumber"));
            }
            
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
