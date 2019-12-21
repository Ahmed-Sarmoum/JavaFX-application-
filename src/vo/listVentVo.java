package vo;

import java.sql.Date;

/**
 *
 * @author Mido
 */
public class listVentVo {
    
    
    private int id;
    private int quantity;
    private float price;
    private float revenue;
    private Date date;
    private int idProduit;
    private String nameProduct;
    private int idUser;
    private String nameUser;

    
    
    public listVentVo() {
    }

 
    public listVentVo(String nameProduct,int quantity, float price, float revenue, Date date, String nameUser) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.revenue = revenue;
        this.date = date;
        this.nameProduct = nameProduct;
        this.nameUser = nameUser;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }
    
    

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
    
}
