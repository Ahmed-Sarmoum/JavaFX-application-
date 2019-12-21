package vo;

import java.sql.Date;

/**
 *
 * @author Mido
 */
public class commandeVo {

    private int id;
    private int idUser;
    private int idProvider;
    private String listProduct;
    private Date date;

    public commandeVo() {
    }

    
    
    public commandeVo(int id, int idUser, int idProvider, String listProduct, Date date) {
        this.id = id;
        this.idUser = idUser;
        this.idProvider = idProvider;
        this.listProduct = listProduct;
        this.date = date;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }

    public String getListProduct() {
        return listProduct;
    }

    public void setListProduct(String listProduct) {
        this.listProduct = listProduct;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
