package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import vo.chatVo;

/**
 *
 * @author Mido
 */
public class chatDao extends Dao{
    
    public static chatDao d;
    
    public static chatDao getInstace(){
    
        if(d == null){
            d = new chatDao();
        }else{
            return d;
        }
           return d;
    } 
    
    public int sendMsd(chatVo vo) throws SQLException{
        
        PreparedStatement ps = null;
        Connection con = null;
        int isInsert = 0;
        
        try{
            con = Dao.getConnection();
            
            String sql = "INSERT INTO chats(idExpéditeur,idDestinatair,dialog) VALUES(?,?,?)";
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, vo.getIdE());
            ps.setInt(2, vo.getIdD());
            ps.setString(3, vo.getDialog());
            
            ps.executeUpdate();
            isInsert = 1;
        }catch(SQLException|ClassNotFoundException e){
            System.out.println(e.getMessage());
        }finally{
            ps.close();
            con.close();
        }
        
        
        return isInsert;
    }
            
}
