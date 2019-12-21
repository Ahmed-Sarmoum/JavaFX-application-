package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import vo.prodectVo;
import vo.quantityVo;
import vo.stockVo;

/**
 *
 * @author Mido
 */
public class quantityDao extends Dao{
    
    public static quantityDao q;
    
    public static quantityDao getInstence(){
        
        if(q == null){
            q = new quantityDao();
        }else{
            return q;
        }
        return q;
    }
    
    public int addGeneralQuantity(quantityVo v) throws SQLException{
        int isInsert = 0;
        try {
            PreparedStatement ps = null;
            Connection con = null;
            
            
            con = dao.Dao.getConnection();
            
            String sql = "INSERT INTO quntité(idProdect,quntitéMinimumStock"
                    + ",quntitéMinimumRayon,quntitéMaximum)"
                    + " VALUES (?,?,?,?)";
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, v.getIdProduct());
            ps.setInt(2, v.getQuantMinStock());
            ps.setInt(3, v.getQuantMinRayon());
            ps.setInt(4, v.getQuantMax());
            
            ps.executeUpdate();
            isInsert = 1;
            
        } catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(quantityDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            
            con.close();
            
        }
        
 
        return isInsert;
    
}
    
    public int addQuantity(stockVo v) throws SQLException{
        int isInsert = 0;
            
        try {
            
            PreparedStatement ps = null;
            Connection con = null;
            
            String sql = "INSERT INTO stocks(qunt,idProd,idUser,dateD,dateF) VALUES (?,?,?,?,?)";
            con = Dao.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, v.getQuantity());
            ps.setInt(2, v.getIdProduct());
            ps.setInt(3, v.getIdUser());
            ps.setString(4, v.getDateD());
            ps.setString(5, v.getDateF());
            
            ps.executeUpdate();
            
            isInsert = 1;
            
            
        } catch (ClassNotFoundException|SQLException ex) {

            Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("ERROR");
                    a.setHeaderText("!!");
                    a.setContentText(ex.getMessage());
                    
                    a.showAndWait();
        }finally{
            con.close();
        } 
        
        
        return isInsert;
    }
    public int addQuantityRayonInProduct(prodectVo v) throws SQLException{
        int isInsert = 0;
            
        try {
            
            PreparedStatement ps = null;
            Connection con = null;
            
            String sql = "UPDATE produits SET quntité =? WHERE id = ?";
            con = Dao.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, v.getQuntInRay());
            ps.setInt(2, v.getId());
            ps.executeUpdate();
            
            isInsert = 1;
            
        } catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(quantityDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            
            con.close();
            
                    
        }
        return isInsert;
    }
    
    public int deleteGeneralQuantity(quantityVo v) throws SQLException {

        PreparedStatement ps = null;
        Connection con = null;
        int isDelete = 0;

        try {
            con = Dao.getConnection();
            String sql = "DELETE FROM quntité WHERE id = ?";
            ps = con.prepareStatement(sql);           
            ps.setInt(1, v.getId());  
            ps.executeUpdate();
            
            isDelete = 2;
            
            
        } catch (ClassNotFoundException | SQLException ex) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }finally{
            con.close();
            ps.close();
        }
        return isDelete;
    }
    
    public int deleteQuantity(stockVo v) throws SQLException {

        PreparedStatement ps = null;
        Connection con = null;
        int isDelete = 0;

        try {
            con = Dao.getConnection();
            String sql = "DELETE FROM stocks WHERE id = ?";
            ps = con.prepareStatement(sql);           
            ps.setInt(1, v.getId());  
            ps.executeUpdate();
            
            isDelete = 2;
            
            
        } catch (ClassNotFoundException | SQLException ex) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }finally{
            con.close();
            ps.close();
        }
        return isDelete;
    }

    public int updateQuantityGeneral(quantityVo v) throws SQLException{
        
        
        PreparedStatement ps = null;
            Connection con = null;
            int isUpdate = 0;
        
        try {
            con = Dao.getConnection();
            String sql = "UPDATE quntité SET idProdect = ?,quntitéMinimumStock = ?"
                    + " ,quntitéMinimumRayon = ? ,quntitéMaximum = ? WHERE id = ?";
            
            
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, v.getIdProduct());
            ps.setInt(2, v.getQuantMinStock());
            ps.setInt(3, v.getQuantMinRayon());
            ps.setInt(4, v.getQuantMax());
            ps.setInt(5, v.getId());
            
            ps.executeUpdate();
            isUpdate = 1;
            
        }catch (ClassNotFoundException|SQLException e) {
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
    
    
     public int updateQuantity(stockVo v) throws SQLException{
        
        
        PreparedStatement ps = null;
            Connection con = null;
            int isUpdate = 0;
        
        try {
            con = Dao.getConnection();
            String sql = "UPDATE stocks SET qunt = ?,idProd = ? ,idUser = ?"
                    + " WHERE id = ?";
            
            
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, v.getQuantity());
            ps.setInt(2, v.getIdProduct());
            ps.setInt(3, v.getIdUser());
            ps.setInt(4, v.getId());
            
            ps.executeUpdate();
            isUpdate = 1;
            
        }catch (ClassNotFoundException|SQLException e) {
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

}










