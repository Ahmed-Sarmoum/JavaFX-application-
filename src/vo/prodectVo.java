package vo;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author hamid
 */
public class prodectVo {
    
    private int id;
    private String nameP;
    private String description;
    private float priceP;
    private float pricePInitial;
    private String category;
    private String rayon;
    private int quntInRay;
    private int categoryInt;
    private int rayonInt;
    private String dateD;
    private String dateF;
    private int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    public prodectVo(){
        
        
    }
    
    
    public prodectVo(String name){
        
        super();
        
        this.nameP = name;
    }

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
    
  public prodectVo(int id, String name,String des,
            float price,float pricePInitial,int qunt,String category,String rayon){
        
        super();
        this.id = id;
        this.nameP = name;
        this.description = des;
        this.priceP =price;
        this.pricePInitial = pricePInitial;
        this.quntInRay = qunt;
        this.category = category;
        this.rayon = rayon;
     //   this.time = time;
    }
    
    public prodectVo(int id, String name,String des,
            float price,float pricePInitial,int qunt,String category,String rayon,int time){
        
        super();
        this.id = id;
        this.nameP = name;
        this.description = des;
        this.priceP =price;
        this.pricePInitial = pricePInitial;
        this.quntInRay = qunt;
        this.category = category;
        this.rayon = rayon;
        this.time = time;
    }
    public prodectVo(int id, String name,String des,
            float price,int qunt,String category,String rayon){
        
        super();
        this.id = id;
        this.nameP = name;
        this.description = des;
        this.priceP =price;
        this.quntInRay = qunt;
        this.category = category;
        this.rayon = rayon;
    }

    public float getPricePInitial() {
        return pricePInitial;
    }

    public void setPricePInitial(float pricePInitial) {
        this.pricePInitial = pricePInitial;
    }

    
    
    public int getQuntInRay() {
        return quntInRay;
    }

    public void setQuntInRay(int quntInRay) {
        this.quntInRay = quntInRay;
    }

    
    
    public int getCategoryInt() {
        return categoryInt;
    }

    public void setCategoryInt(int categoryInt) {
        this.categoryInt = categoryInt;
    }

    public int getRayonInt() {
        return rayonInt;
    }

    public void setRayonInt(int rayonInt) {
        this.rayonInt = rayonInt;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPriceP() {
        return priceP;
    }

    public void setPriceP(float priceP) {
        this.priceP = priceP;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }
    
    
    
}
