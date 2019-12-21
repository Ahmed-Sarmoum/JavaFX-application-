package dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import vo.categVo;

/**
 *
 * @author Mido
 */
public class categoryDao extends Dao {
    
    public static categoryDao d;
    
    public static categoryDao getInstence(){
        
        if(d == null) {
            d = new categoryDao();
        }
        return d;
    }
    
    
    //Method for add Catedory...
    public int addCategory(categVo vo) throws SQLException{
   
        Connection con = null;
        PreparedStatement ps = null;
        int isInsert = 0;
        try{
            con = Dao.getConnection();
            String sql = "INSERT INTO catégories(nom,responsable,status,chefS) VALUES(?,?,?,?)";
            ps = (PreparedStatement) con.prepareStatement(sql);
            
            ps.setString(1, vo.getNameCategory());
            ps.setInt(2, vo.getIdRespos());
            ps.setString(3, vo.getStatus());
            ps.setString(4, vo.getChefSection());
            
            ps.executeUpdate();
            
            isInsert = 1;
            
        }catch(SQLException|ClassNotFoundException e){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
          
            
        }finally{
            ps.close();
            con.close();
            
        }
        return isInsert;
    }
    
    
    //Method for Update Catedory...
    public int updateCategory(categVo vo) throws SQLException{
    
        PreparedStatement ps = null;
        Connection con = null;
        int isUpdate = 0;
        
        try{
            con = Dao.getConnection();
                         
            String sql = "UPDATE catégories SET nom = ?,responsable = ?,status = ?"
                    + ",chefS = ? WHERE id = ? ";
            ps  = (PreparedStatement)con.prepareStatement(sql);
            
            ps.setString(1, vo.getNameCategory());
            ps.setInt(2, vo.getIdRespos());
            ps.setString(3, vo.getStatus());
            ps.setString(4, vo.getChefSection());
            ps.setInt(5, vo.getId());
            
            ps.executeUpdate();
            
            isUpdate = 1;
            
        }catch(SQLException|ClassNotFoundException e){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }finally{
            ps.close();
            con.close();
        }
        
        return isUpdate;
    }
    
    
    //Method for get Data...
    public categVo getData(int id) throws SQLException{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        categVo cv = null;
        
        try{
            con = Dao.getConnection();
            
            String sql = "SELECT nom,responsable,status,chefS FROM catégories WHERE id = ?";
            ps = (PreparedStatement)con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                cv = new categVo();
                
                cv.setNameCategory(rs.getString(1));
                cv.setIdRespos(rs.getInt(2));
                cv.setStatus(rs.getString(3));
                cv.setChefSection(rs.getString(4));
                
                //cv.setId(rs.getInt("id"));
            }
            
            
        }catch(SQLException|ClassNotFoundException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }finally{
            ps.close();
            con.close();
        }
        return cv;
    }
    
    public int deleteCategory(categVo vo) throws SQLException{
        
        PreparedStatement ps = null;
        Connection con = null;
        int isDelete = 0;
        
        try{
            con = Dao.getConnection();
            String sql = "DELETE FROM catégories WHERE id = ?";
            ps = (PreparedStatement)con.prepareStatement(sql);
            ps.setInt(1, vo.getId());
            ps.executeUpdate();
            isDelete = 1;
   
            
        }catch(SQLException|ClassNotFoundException e){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText(e.getMessage());

            a.showAndWait();

        } finally {
            con.close();
            ps.close();
        }
        return isDelete;
    }
}








