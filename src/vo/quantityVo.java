package vo;

/**
 *
 * @author Mido
 */
public class quantityVo {
    
    private int id;
    private int idProduct;
    private String productGQ;
    private int quantMinStock;
    private int quantMinRayon;
    private int quantMax;

    
    public quantityVo(){
        
    }
    
    public quantityVo(int id, String productGQ, int quantMinStock, int quantMinRayon, int quantMax) {
       
        this.id = id;
        this.productGQ = productGQ;
        this.quantMinStock = quantMinStock;
        this.quantMinRayon = quantMinRayon;
        this.quantMax = quantMax;
    }

    public String getProductGQ() {
        return productGQ;
    }

    public void setProductGQ(String productGQ) {
        this.productGQ = productGQ;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantMinStock() {
        return quantMinStock;
    }

    public void setQuantMinStock(int quantMinStock) {
        this.quantMinStock = quantMinStock;
    }

    public int getQuantMinRayon() {
        return quantMinRayon;
    }

    public void setQuantMinRayon(int quantMinRayon) {
        this.quantMinRayon = quantMinRayon;
    }

    public int getQuantMax() {
        return quantMax;
    }

    public void setQuantMax(int quantMax) {
        this.quantMax = quantMax;
    }
    
    
}
