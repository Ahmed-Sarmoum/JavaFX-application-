package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import vo.prodectVo;

/**
 *
 * @author mido
 * 
 */
public class prodectDao extends Dao{

    public static prodectDao p;

    public static prodectDao getInstence() {

        if (p == null) {
            p = new prodectDao();
        }
        return p;

    }

    // Add Prodect  ....
    public int addProdect(prodectVo pv) throws SQLException {

        PreparedStatement ps = null;
        Connection con = null;
        int isInsert = 0;

        try {
            con = Dao.getConnection();

            String sql = "INSERT INTO produits(nom,description,prix,prixInitial,idCatég,idRay) VALUES(?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);

            ps.setString(1, pv.getNameP());
            ps.setString(2, pv.getDescription());
            ps.setFloat(3, pv.getPriceP());
            ps.setFloat(4, pv.getPricePInitial());
            ps.setInt(5, pv.getCategoryInt());
            ps.setInt(6, pv.getRayonInt());

            ps.executeUpdate();
            isInsert = 1;

        } catch (SQLException | ClassNotFoundException e) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText(e.getMessage());

            alert.showAndWait();

        } finally {
            con.close();
            ps.close();
        }
        return isInsert;
    }

    //Delete Prodect Method .......
    public int deleteProdect(prodectVo v) throws SQLException {

        PreparedStatement ps = null;
        Connection con = null;
        int isDelete = 0;

        try {
            con = Dao.getConnection();
            String sql = "DELETE FROM produits WHERE id = ?";
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
    
    
    //Function for  update Prodect .....
    public int updateProdect(prodectVo v) throws SQLException{
        
        PreparedStatement ps = null;
            Connection con = null;
            int isUpdate = 0;
        
        try {
 
            con = Dao.getConnection();
            String sql = "UPDATE produits SET nom = ?,description = ?,prix = ?,prixInitial = ?"
                    + ",idCatég = ?,idRay = ? WHERE id = ?";
            
            ps = (PreparedStatement)con.prepareStatement(sql);
            
            ps.setString(1, v.getNameP());
            ps.setString(2, v.getDescription());
            ps.setFloat(3, v.getPriceP());
            ps.setFloat(4, v.getPricePInitial());
            ps.setInt(5, v.getCategoryInt());
            ps.setInt(6, v.getRayonInt());
            ps.setInt(7, v.getId());
            
            ps.executeUpdate();
            isUpdate  =  1;
            
            
        } catch (ClassNotFoundException|SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }finally{
            con.close();
            ps.close();
            
        }
        
        return isUpdate;
    }
    
    //Function for get data from database
    public prodectVo getData(int id) throws SQLException{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        prodectVo pv = null;
        
        try{
        con = Dao.getConnection();
        String sql = "SELECT nom,description,prix,prixInitial,idCatég,idRay FROM produits WHERE id = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        
        while(rs.next()){
            pv = new prodectVo();
            
            pv.setNameP(rs.getString(1));
            pv.setDescription(rs.getString(2));
            pv.setPriceP(rs.getFloat(3));
            pv.setPricePInitial(rs.getFloat(4));
            pv.setCategoryInt(rs.getInt(5));
            pv.setRayonInt(rs.getInt(6));
            
            
        }
        
        
        }catch(ClassNotFoundException|SQLException e){
            
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }finally{
            ps.close();
            con.close();
            
        }
        
        return pv;
    }

}











