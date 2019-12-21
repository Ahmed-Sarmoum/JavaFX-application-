package vo;

/**
 *
 * @author Mido
 */
public class providerVo {
    
    private int id;
    private String firstname;
    private String lastnaame;
    private String phone;
    private String location;
    private String category;
    private int idCategory;
    private int idUser;
    private String email;
    private String note;
    private double cridé;

    public providerVo() {
    }

    
    
    
    public providerVo(int id, String firstname, String lastnaame, String phone, String email, String category, double cridé, String location,String note) {
        this.id = id;
        this.firstname = firstname;
        this.lastnaame = lastnaame;
        this.phone = phone;
        this.location = location;
        this.category = category;
        this.email = email;
        this.note = note;
        this.cridé = cridé;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public double getCridé() {
        return cridé;
    }

    public void setCridé(double cridé) {
        this.cridé = cridé;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastnaame() {
        return lastnaame;
    }

    public void setLastnaame(String lastnaame) {
        this.lastnaame = lastnaame;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
