package vo;

/**
 *
 * @author Mido
 */
public class rayonVo {
    
   private int id;
   private String nom;
   private String nomRes;
   private int idResC;
   
   
   public  rayonVo(){
       
   }
   
   public rayonVo(int id,String nom,String nomRes){
       
       super();
       this.id = id;
       this.nom = nom;
       this.nomRes = nomRes;
   }

    public int getIdResC() {
        return idResC;
    }

    public void setIdResC(int idResC) {
        this.idResC = idResC;
    }

   
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomRes() {
        return nomRes;
    }

    public void setNomRes(String nomRes) {
        this.nomRes = nomRes;
    }
   
   
   
}
