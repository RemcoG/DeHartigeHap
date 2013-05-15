/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Remco
 */
public class Gerecht extends MenuItem{
    
    private String type;
    
    public Gerecht(String naam, double prijs, String type, int productNr){
        super(naam, prijs, productNr);
        this.type = type;
    }
    
}
