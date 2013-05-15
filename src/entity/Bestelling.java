package entity;

import dataStorage.Database;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bestelling {

    private ArrayList<MenuItem> menuItems;
    private Database db;
    private String naam, type, gerechtLijst, drankLijst="";
    private double prijs, bedrag;
    private MenuItem gerecht;
    private MenuItem drank;
    private int nummer=0;

    public Bestelling() {
        menuItems = new ArrayList<>();
        db = new Database();
        db.Connectie();
    }

    /*
     * @param menucode
     */
    public void addMenuItem(int menucode) {
        naam = db.getMenuItem("SELECT productNaam FROM product WHERE barcode ='" + menucode %1000 + "'");
        prijs = db.getPrijs("SELECT productPrijs FROM product WHERE barcode ='" + menucode %1000 + "'");
        type = db.getType("SELECT type FROM product WHERE barcode ='" + menucode %1000 + "'");
        nummer = db.getProductNr("SELECT productNr FROM product WHERE barcode ='" + menucode %1000 + "'");
        
        if(menucode / 100 %10 == 1){
        gerecht = new Gerecht(naam, prijs, type, nummer);
        menuItems.add(gerecht);
        
        }else{
            drank = new Drank(naam, prijs, type, nummer);
            menuItems.add(drank);
        }
    }

    public void saveGerechten(int tafelnummer) {        
      db.VoerInsertQueryuit("INSERT INTO bestelling (tafelNr, type, status, prijs) VALUES (" + tafelnummer + ", 'eten', 'geplaatst', " + getTotaalPrijsGerechten() + ")");
        for(MenuItem g : menuItems){
            gerechtLijst += g.getProductNr()+"/";
        }
     
        List<String> list = Arrays.asList(gerechtLijst.split("/"));
        Set<String> duplicates = new HashSet<>(list);
        
        for (String word : duplicates){
            db.VoerInsertQueryuit("INSERT INTO bestellingregel (bestellingNr,productNr, Aantal) VALUES (LAST_INSERT_ID(), '" + word + "', "+ Collections.frequency(list, word) + ")");
        }
        
        db.VoerUpdateQueryuit("UPDATE  `dehartigehap`.`bestelling` SET  `Totaalprijs` =  " + getTotaalPrijsGerechten() + " WHERE  `bestelling`.`Bestelnummer` = LAST_INSERT_ID()");
        
    }
    
    public void saveDrankjes(int tafelnummer){
      db.VoerInsertQueryuit("INSERT INTO bestelling (tafelNr, type, status, prijs) VALUES (" + tafelnummer + ", 'drank', 'geplaatst', " + getTotaalPrijsDrankjes() + ")");
        for(MenuItem d : menuItems){
            drankLijst += d.getProductNr() +"/";
            }
        List<String> list = Arrays.asList(gerechtLijst.split("/"));
        Set<String> duplicates = new HashSet<>(list);
        
        for (String word : duplicates){
            db.VoerInsertQueryuit("INSERT INTO bestellingregel (bestellingNr,productNr, Aantal) VALUES (LAST_INSERT_ID(), '" + word + "', "+ Collections.frequency(list, word) + ")");
        }
    }
    public ArrayList<MenuItem> getItems() {
        return menuItems;
    }
       
   public double getTotaalPrijsGerechten() {
		bedrag = 0;
		for (MenuItem g : menuItems) {
			bedrag = bedrag + g.getPrijs();
		}
		return bedrag;
	}
    
      public double getTotaalPrijsDrankjes() {
		bedrag = 0;
		for (MenuItem d : menuItems) {
			bedrag = bedrag + d.getPrijs();
		}
		return bedrag;
	}
    public void afrekenen(String updateQuery){
            db.VoerUpdateQueryuit(updateQuery);
        }
    
}