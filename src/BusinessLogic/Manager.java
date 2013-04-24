package BusinessLogic;

import BusinessEntity.Bestelling;


public class Manager {

    Bestelling bestelling = new Bestelling();
    String menucode;

    
    public String Bestel(String menucode, int tafelnummer2){
        return bestelling.Bestel(menucode,tafelnummer2);
    }
    public int HaalTafelnummerOp(String menucode1){
        return bestelling.getTafelnummer(menucode1);
    }
    
 }