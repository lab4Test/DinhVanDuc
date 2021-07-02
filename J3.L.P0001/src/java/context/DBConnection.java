/*

 */
package context;
//C:\Program Files\glassfish-4.1.1\javadb\lib\derby.jar
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    Connection conn;

    public DBConnection(String URL, String userName, String pass) {
        try {
            //URL: String connection, connect to DB
            //account: acc of SQLSever
            //Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                //get connection
                conn = DriverManager.getConnection(URL, userName, pass);
                System.out.println("Connected");
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            
        }
    }

    public DBConnection() {
        this("jdbc:sqlserver://localhost:1433;databaseName=Q3P0001",
                "sa","123456");
    }
    public Connection getConn() {
        return conn;
    }
    

}
