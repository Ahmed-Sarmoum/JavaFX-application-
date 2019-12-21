package vo;

/**
 *
 * @author Mido
 */
public class categVo {
    
    private int id;
    private int idRespos;
    private String nameCategory;
    private String status;
    private String chefSection;

    public categVo(){
        
    }
    
    public categVo(int id,String nameCategory,String status,String chefSection){
        super();
        
        this.nameCategory = nameCategory;
        this.id = id;
        this.status = status;
        this.chefSection = chefSection;
    }
    
    
    public int getIdRespos() {
        return idRespos;
    }

    public void setIdRespos(int idRespos) {
        this.idRespos = idRespos;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChefSection() {
        return chefSection;
    }

    public void setChefSection(String chefSection) {
        this.chefSection = chefSection;
    }
   
    
    
}
