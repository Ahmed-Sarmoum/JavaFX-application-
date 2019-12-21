package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import vo.listVentVo;

/**
 *
 * @author Mido
 */
public class listVentDao extends Dao{
    
    public static listVentDao d;
    
    public static listVentDao getInstance(){
        
        if(d == null){
            d = new listVentDao();
        }else{
            return d;
        }
        return d;
    }
    
    
    public int addVentP(listVentVo l) throws SQLException{
        
        PreparedStatement ps = null;
        Connection con = null;
        int isInsert = 0;
        
        try{
            String sql = "INSERT INTO listvente(quntité,price,revenue,idProd,idUser) VALUES (?,?,?,?,?)";
            con = dao.Dao.getConnection();
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, l.getQuantity());
            ps.setFloat(2, l.getPrice());
            ps.setFloat(3, l.getRevenue());
            ps.setInt(4, l.getIdProduit());
            ps.setInt(5, l.getIdUser());
            
            ps.executeUpdate();
            isInsert = 1;
            
        }catch(ClassNotFoundException|SQLException e){
            
        }finally{
            con.close();
            ps.close();
        }
        
        return isInsert;
        
    }
}










