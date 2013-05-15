/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Remco
 */
public class Drank extends MenuItem{

    private String type;
    
    public Drank(String naam, double prijs, String type, int productNr){
        super(naam, prijs, productNr);
        this.type = type;
    }
    
}

