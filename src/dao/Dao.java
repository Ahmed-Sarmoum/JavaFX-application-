package dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mido
 */
public class Dao {//class Connection

    static Connection con = null;

    // Method Connection......
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection("jdbc:mysql://localhost/pfeproject", "root", "");

        if (con != null) {
            return con;
        } else {
            return null;
        }
    }
    
    // Method Close Connection......
    public void closeConnection() throws SQLException{
        
        if(con != null){
            con.close();
        }
    }
}
