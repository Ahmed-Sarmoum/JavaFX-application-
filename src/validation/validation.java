package validation;

/**
 *
 * @author Mido
 */
public class validation {

    public static boolean isEmpty(String... t) {

        for (String s : t) {
            if (s.isEmpty()) {
                return true;
            }
        }
        return false;
    }

 
    public static boolean isText(String ...t){
        
        for(String s:t){
            
            if(!s.matches("[a-zA-Z' ']+")){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isNumber(String ...t){
        
        for(String s:t){
            if(!s.matches("[0-9.]+")){
                return true;
            }
        }
        return false;
    }
    
}









