package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import vo.commandeVo;

/**
 *
 * @author Mido
 */
public class commandeDao extends Dao{

    private static commandeDao d;
    
    public static commandeDao getInstance(){
        
        if(d == null){
            d = new commandeDao();
        }else{
            return d;
        }
        return d;
    }
    
    public int insertCommande(commandeVo v) throws SQLException{
        
        int isInsert = 0;
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            con = Dao.getConnection();
            String sql = "INSERT INTO commande(idUser,idFournisseur,listProduct) VALUES (?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, v.getIdUser());
            ps.setInt(2, v.getIdProvider());
            ps.setString(3, v.getListProduct());
            
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
    
}
