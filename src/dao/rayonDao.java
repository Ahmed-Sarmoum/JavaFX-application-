package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import vo.rayonVo;

/**
 *
 * @author Mido
 */
public class rayonDao extends Dao {

    public static rayonDao r;

    public static rayonDao getInstence() {

        if (r == null) {
            r = new rayonDao();
        }
        return r;

    }

    //Method for add rayon
    public int addRayon(rayonVo rv) throws SQLException {

        PreparedStatement ps = null;
        Connection con = null;
        int isInsert = 0;

        try {
            String sql = "INSERT INTO rayons(nom,nomR,idUser) VALUES(?,?,?)";
            con = Dao.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, rv.getNom());
            ps.setString(2, rv.getNomRes());
            ps.setInt(3, rv.getIdResC());

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

    //Method for delete rayons
    public int deleteRayon(rayonVo r) throws SQLException {

        PreparedStatement ps = null;
        Connection con = null;
        int isDelete = 0;

        try {
            con = Dao.getConnection();
            String sql = "DELETE FROM rayons WHERE id = ?";
            ps = con.prepareStatement(sql);

            ps.setInt(1, r.getId());

            ps.executeUpdate();

            isDelete = 1;
            
        } catch (SQLException | ClassNotFoundException e) {

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
