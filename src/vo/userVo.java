package vo;

/**
 *
 * @author Mido
 */

public class userVo {

    
    private int id;
    private String firstName;
    private String lastName;
    private String Username;
    private String Password;
    private String phone;
    private String adress;
    private int age;
    private String type;
    private String image;
    
    public userVo(){
        
    }
    
    public userVo(int idU){
        super();
        
        this.id = idU;
    }
    public userVo(String name){
        super();
        
        this.lastName = name;
    }

    
    public userVo(int id,String firstname,String lastname,String username,
            String password,String Phone,String Adress,int Age,String type
    ,String Image){

        super();
        
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.Username = username;
        this.Password = password;
        this.phone = Phone;
        this.age = Age;
        this.adress = Adress;
        this.type = type;
        this.image = Image;
        
    }

    public userVo(int aInt, String string, String string0, String string1, String string2, String string3) {

        
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    
}
