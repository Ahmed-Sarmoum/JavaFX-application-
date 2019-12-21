package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import vo.providerVo;

/**
 *
 * @author Mido
 */
public class providerDao extends Dao{
 
    private static providerDao d;
    
    public static providerDao getInstance(){
        
        if(d == null){
            d = new providerDao();
        }else{
            return d;
        }
        return d;
    }
    
    public int insertProvider(providerVo v) throws SQLException{
        
        Connection con = null;
        PreparedStatement ps = null;
        int isInsert = 0;
        
        try{
            con = Dao.getConnection();
            
            String sql = "INSERT INTO fournisseur(firstname,lastname,phone,email,idCategory,idUser,"
                    + "location,note) VALUES(?,?,?,?,?,?,?,?)";
            
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getFirstname());
            ps.setString(2, v.getLastnaame());
            ps.setString(3, v.getPhone());
            ps.setString(4, v.getEmail());
            ps.setInt(5, v.getIdCategory());
            ps.setInt(6, v.getIdUser());
            ps.setString(7, v.getLocation());
            ps.setString(8, v.getNote());
            
            ps.executeUpdate();
            isInsert = 1;
            
        }catch(SQLException|ClassNotFoundException e){
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
        }
        
        return isInsert;
    }
    
    public int deleteProvider(providerVo id) throws SQLException{
        
        Connection con = null;
        PreparedStatement ps = null;
        int isDelete = 0;
        
        try{
            con = Dao.getConnection();
            String sql = "DELETE FROM fournisseur WHERE id = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id.getId());
            ps.executeUpdate();
            isDelete = 1;
            
        }catch(SQLException|ClassNotFoundException e){
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
        }
        return isDelete;
    }
    
    public int updateProvider(providerVo v) throws SQLException{
        
        PreparedStatement ps = null;
        Connection con = null;
        int isUpdate = 0;
        
        try{
            con = dao.Dao.getConnection();
            
            String sql = "UPDATE fournisseur SET firstname = ?,lastname = ?,phone = ?"
                    + ",email = ?,idCategory = ?,idUser = ? ,cridé = ?,location = ?,note = ? WHERE id = ? ";
            ps = con.prepareStatement(sql);
            
            ps.setString(1, v.getFirstname());
            ps.setString(2, v.getLastnaame());
            ps.setString(3, v.getPhone());
            ps.setString(4, v.getEmail());
            ps.setInt(5, v.getIdCategory());
            ps.setInt(6, v.getIdUser());
            ps.setDouble(7, v.getCridé());
            ps.setString(8, v.getLocation());
            ps.setString(9, v.getNote());
            ps.setInt(10, v.getId());
            
            ps.executeUpdate();
            isUpdate = 1;
            
        }catch(SQLException|ClassNotFoundException e){
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            
        }
        
        
        return isUpdate;
    }
    
}







