package vo;

/**
 *
 * @author Mido
 */
public class stockVo {
    
    private int id;
    private int quantity;
    private String prodectQ;
    private int idProduct;
    private int idUser;
    private int idRayon;
    private String dateD;
    private String dateF;

    public String getDateD() {
        return dateD;
    }

    public void setDateD(String dateD) {
        this.dateD = dateD;
    }

    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }
    
    

    public stockVo(){
        
    }
    
    public stockVo(int id, int quantity, String prodectQ, int idRayon,String dateD,String dateF) {
        
        this.id = id;
        this.quantity = quantity;
        this.prodectQ = prodectQ;
        this.idRayon = idRayon;
        this.dateD = dateD;
        this.dateF = dateF;
    }

    public int getIdRayon() {
        return idRayon;
    }

    public void setIdRayon(int idRayon) {
        this.idRayon = idRayon;
    }

    
    
    public void setProdectQ(String prodectQ) {
        this.prodectQ = prodectQ;
    }

    public String getProdectQ() {
        return prodectQ;
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

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
}
