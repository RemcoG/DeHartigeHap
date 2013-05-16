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
    private String naam, gerechtLijst, drankLijst = "";
    private double prijs, bedrag;
    private MenuItem gerecht, drank;
    private int nummer = 0;

    public Bestelling() {
        menuItems = new ArrayList<MenuItem>();
        db = new Database();
        db.Connectie();
    }

    /*
     * @param menucode
     */
    public void addMenuItem(int menucode) {
        naam = db.getMenuItem("SELECT productNaam FROM product WHERE barcode ='" + menucode % 1000 + "'");
        prijs = db.getPrijs("SELECT productPrijs FROM product WHERE barcode ='" + menucode % 1000 + "'");
        nummer = db.getProductNr("SELECT productNr FROM product WHERE barcode ='" + menucode % 1000 + "'");

        if (menucode / 100 % 10 == 1) {
            gerecht = new Gerecht(naam, prijs, nummer);
            menuItems.add(gerecht);

        } else {
            drank = new Drank(naam, prijs, nummer);
            menuItems.add(drank);
        }
    }

    public void clearMenuLijst(){
        for(int i=0; i<menuItems.size();i++){
            menuItems.remove(i);
        }
<<<<<<< HEAD
     
        List<String> list = Arrays.asList(gerechtLijst.split("/"));
        Set<String> duplicates = new HashSet<String>(list);
        
        for (String word : duplicates){
            db.VoerInsertQueryuit("INSERT INTO bestellingregel (bestellingNr,productNr, Aantal) VALUES (LAST_INSERT_ID(), '" + word + "', "+ Collections.frequency(list, word) + ")");
        }
        
        db.VoerUpdateQueryuit("UPDATE  `dehartigehap`.`bestelling` SET  `Totaalprijs` =  " + getTotaalPrijsGerechten() + " WHERE  `bestelling`.`Bestelnummer` = LAST_INSERT_ID()");
        
=======
>>>>>>> 55c5268e42a3e6563f312f463c3afdee202163aa
    }
    
    public void save(int tafelnummer) {
        
        gerechtLijst = "";
        for (MenuItem m : menuItems) {
            if (m instanceof Gerecht) {
                gerechtLijst += m.getProductNr() + "/";

            }
            if(!gerechtLijst.equals("")){
                db.VoerUpdateQueryuit("INSERT INTO bestelling (tafelNr, type, status, prijs, prijsExBTW) VALUES (" + tafelnummer + ", 'eten', 'geplaatst', " + getTotaalPrijsGerechten() +", " + getTotaalPrijsGerechten()*0.79 + ")");
            
        
        List<String> list = Arrays.asList(gerechtLijst.split("/"));
<<<<<<< HEAD
        Set<String> duplicates = new HashSet<String>(list);
        
        for (String word : duplicates){
            db.VoerInsertQueryuit("INSERT INTO bestellingregel (bestellingNr,productNr, Aantal) VALUES (LAST_INSERT_ID(), '" + word + "', "+ Collections.frequency(list, word) + ")");
=======
        Set<String> duplicates = new HashSet<>(list);

        for (String word : duplicates) {
            db.VoerUpdateQueryuit("INSERT INTO bestellingregel (bestellingNr,productNr, Aantal) VALUES (LAST_INSERT_ID(), '" + Integer.parseInt(word) + "', " + Collections.frequency(list, word) + ")");
        }
        }
        }
        drankLijst = "";
        for (MenuItem d : menuItems) {
            if(d instanceof Drank){
            drankLijst += d.getProductNr() + "/";
            }
        }
        System.out.println(drankLijst);
        if(!drankLijst.equals("")){
        db.VoerUpdateQueryuit("INSERT INTO bestelling (tafelNr, type, status, prijs, prijsExBTW) VALUES (" + tafelnummer + ", 'drank', 'geplaatst', " + getTotaalPrijsDrankjes() + ", " + getTotaalPrijsDrankjes()*0.79 +  ")");
        List<String> list1 = Arrays.asList(drankLijst.split("/"));
        Set<String> duplicates1 = new HashSet<>(list1);

        for (String word : duplicates1) {
            db.VoerUpdateQueryuit("INSERT INTO bestellingregel (bestellingNr,productNr, Aantal) VALUES (LAST_INSERT_ID(), '" + Integer.parseInt(word) + "', " + Collections.frequency(list1, word) + ")");
        }
>>>>>>> 55c5268e42a3e6563f312f463c3afdee202163aa
        }
    }

    public ArrayList<MenuItem> getItems() {
        return menuItems;
    }

    public double getTotaalPrijsGerechten() {
        bedrag = 0;
        for (MenuItem i : menuItems) {
            if (i instanceof Gerecht) {
                bedrag = bedrag + i.getPrijs();
            }
        }
        return bedrag;
    }

    public double getTotaalPrijsDrankjes() {
        bedrag = 0;
        for (MenuItem d : menuItems) {
            if (d instanceof Drank) {
                bedrag = bedrag + d.getPrijs();
            }
        }
        return bedrag;
    }

    public void afrekenen(String updateQuery) {
        db.VoerUpdateQueryuit(updateQuery);
    }
}