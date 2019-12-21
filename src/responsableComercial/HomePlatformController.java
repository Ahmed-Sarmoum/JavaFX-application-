package responsableComercial;

import allFunction.function;
import com.jfoenix.controls.JFXProgressBar;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class HomePlatformController implements Initializable {

    static public float bal;
    static public float reven;
    static public int kind;
    
    
    @FXML
    private Label balance;
    @FXML
    private Label revenue;

    @FXML
    private Label pKind;
    
    @FXML
    private Label cridé;
    
    @FXML
    private Text deco;

    @FXML
    private Text mult;

    @FXML
    private Text beut;

    @FXML
    private JFXProgressBar pAli;

    @FXML
    private JFXProgressBar pDec;

    @FXML
    private JFXProgressBar pMul;

    @FXML
    private JFXProgressBar pBeaut;

    @FXML
    private Text mob;

    @FXML
    private Text electro;

    @FXML
    private JFXProgressBar pMob;

    @FXML
    private JFXProgressBar pElectro;

    @FXML
    private Text rest;

    @FXML
    private Text pates;

    @FXML
    private Text brico;

    @FXML
    private Text lois;

    @FXML
    private JFXProgressBar pRes;

    @FXML
    private JFXProgressBar pPat;

    @FXML
    private JFXProgressBar pBri;

    @FXML
    private JFXProgressBar pLois;

    @FXML
    private Text mateInfo;

    @FXML
    private Text meubl;
    
    @FXML
    private Text alim;

    @FXML
    private JFXProgressBar pMateInf;

    @FXML
    private JFXProgressBar pMeub;


    private void progAlim(){
        function f = new function();
        
        f.calculatProg(alim, pAli);
    }
    private void progDec(){
        function f = new function();
        
        f.calculatProg(deco, pDec);
    }
    private void progMult(){
        function f = new function();
        
        f.calculatProg(mult, pMul);
    }
    private void progBuet(){
        function f = new function();
        
        f.calculatProg(beut, pBeaut);
    }
    private void progMob(){
        function f = new function();
        
        f.calculatProg(mob, pMob);
    }
    private void progElecto(){
        function f = new function();
        
        f.calculatProg(electro, pElectro);
    }
    private void progResto(){
        function f = new function();
        
        f.calculatProg(rest, pRes);
    }
    private void progPatess(){
        function f = new function();
        
        f.calculatProg(pates, pPat);
    }
    
    private void progBric(){
        function f = new function();
        
        f.calculatProg(brico, pBri);
    }
    private void progLois(){
        function f = new function();
        
        f.calculatProg(lois, pLois);
    }
    private void progMaterInfo(){
        function f = new function();
        
        f.calculatProg(mateInfo, pMateInf);
    }
    private void progMeubl(){
        function f = new function();
        
        f.calculatProg(meubl, pMeub);
    }
    
    @FXML
    void refresh(){
        dataRefreshHome();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   

        dataRefreshHome();

        
    }
    
    private void dataRefreshHome(){

            PreparedStatement ps = null, ps1 = null, ps2 = null,ps3;
        ResultSet rs = null, rs1 = null, rs2 = null,rs3;
        Connection con = null;

        try {
            String sql = "SELECT SUM(price) FROM listVente ";
            con = dao.Dao.getConnection();

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                String sql1 = "SELECT COUNT(id) FROM produits ";
                con = dao.Dao.getConnection();

                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                while (rs1.next()) {

                    String sql2 = "SELECT SUM(revenue) FROM listVente";
                    con = dao.Dao.getConnection();

                    ps2 = con.prepareStatement(sql2);
                    rs2 = ps2.executeQuery();

                    while (rs2.next()) {
                        String sql3 = "SELECT SUM(cridé) FROM fournisseur ";
                        ps3 = con.prepareStatement(sql3);
                        rs3 = ps3.executeQuery();
                        
                        while(rs3.next()){
                              // System.out.println(LocalDateTime.now());
                        balance.setText(String.valueOf(rs.getFloat(1)));
                        pKind.setText(String.valueOf(rs1.getInt(1)));
                        revenue.setText(String.valueOf(rs2.getFloat(1)));
                        cridé.setText(String.valueOf(rs3.getFloat(1)));
                    }
                }
            }
            }
        } catch (ClassNotFoundException | SQLException e) {

        }
        
        progAlim();
        progBric();
        progBuet();
        progDec();
        progElecto();
        progLois();
        progMaterInfo();
        progMeubl();
        progMob();
        progMult();
        progPatess();
        progResto();
    }

}
