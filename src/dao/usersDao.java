package dao;

import admins.hashClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import vo.userVo;

/**
 *
 * @author Mido
 */
public class usersDao extends Dao{
    
    public usersDao(){
    
}
    
    public static usersDao d;
    
    public static usersDao getInstance(){
        
        if(d == null){
            d = new usersDao();
        }
        return d;
    }
    
    public int updateUser(userVo b) throws SQLException{
        PreparedStatement ps = null;
        Connection con = null;
        int isUpdate = 0;
        
        try{
            con = dao.Dao.getConnection();
            String sql = "UPDATE utilisateurs SET firstname = ?,lastname = ?"
                    + ",username = ?,password = ?,phone = ?,adress = ?,type = ?"
                    + ",image = ? WHERE id = ?";
            ps = (PreparedStatement)con.prepareStatement(sql);
            ps.setString(1, b.getFirstName());
            ps.setString(2, b.getLastName());
            ps.setString(3, b.getUsername());
            ps.setString(4, hashClass.hashing(b.getPassword()));
            ps.setString(5, b.getPhone());
            ps.setString(6, b.getAdress());
            ps.setString(7, b.getType());
            ps.setString(8, b.getImage());
            ps.setInt(9, b.getId());
            
            ps.executeUpdate();
            
            isUpdate = 1;
        }catch(ClassNotFoundException|SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Data insert");
            alert.setHeaderText("ERROR Dialog");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }finally{
            ps.close();
            con.close();
        }
        
        return isUpdate;
    }

    // Method for delete users .....
    public int deleteUsers(String uv) throws SQLException {

        PreparedStatement ps = null;
        Connection con = null;
        int instenc = 0;

        try {
            
            con = dao.Dao.getConnection();
            con.setAutoCommit(false);
            
            String sql = "DELETE FROM utilisateurs WHERE phone = ?";
            
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, uv);
            ps.executeUpdate();

            con.commit();
            instenc = 1;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText(ex.getMessage());
            
            alert.showAndWait();
           // con.rollback();

        } finally {
            ps.close();
            con.close();
            
        }

        return instenc;
    }

    // insert Users ...
    public int insert(userVo uv) throws SQLException {

        PreparedStatement ps = null;
        Connection con = null;
        int insert = 0;

        try {
            con = dao.Dao.getConnection();
            String sql = "INSERT INTO utilisateurs(firstname,lastname,username,password,phone,adress,age,type,image)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)";
            ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setString(1, uv.getFirstName());
            ps.setString(2, uv.getLastName());
            ps.setString(3, uv.getUsername());
            ps.setString(4, hashClass.hashing(uv.getPassword()));
            ps.setString(5, uv.getPhone());
            ps.setString(6, uv.getAdress());
            ps.setInt(7, uv.getAge());
            ps.setString(8, uv.getType());
            ps.setString(9, uv.getImage());

            ps.executeUpdate();

            insert = 1;

        } catch (ClassNotFoundException | SQLException e) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
          
        } finally {
            ps.close();
            con.close();
        }
        return insert;
    }

}
