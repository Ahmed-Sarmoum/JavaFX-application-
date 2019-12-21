package admins;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mido
 */
public class hashClass {
 
    public static String hashing(String plainText){
        
        StringBuffer cipherText = null;
            
            try {
                MessageDigest ms = MessageDigest.getInstance("MD5");
                ms.update(plainText.getBytes());
                
                byte data[] = ms.digest();
                
                cipherText = new StringBuffer();
                
                for(int i =0; i < data.length; i++){
                    cipherText.append(Integer.toString((data[i] & 0xff) + 0x100,16).substring(1));
                    
                }
                
            } catch (NoSuchAlgorithmException ex) {

                System.out.println("msg : " + ex.getMessage());
            }
        
        return cipherText.toString();
    }
}
