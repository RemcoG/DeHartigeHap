package BusinessEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.text.*;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import java.awt.EventQueue;

import DataStorage.Database;
//import Presentation.Gui;

//public class Bestelling extends Gui {
public class Bestelling {

      public int tafelnummer2 = 0;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    String finaloutput = "mislukt";

    /**
     *
     * @param menucode1
     * @param tafelnummer
     * @return
     */
    public String Bestel(String menucode1, int tafelnummer) {
        
        ResultSet rs3;
        String enkelgerecht = null;
        
        boolean first = true;
        String output1 = "";
        String output = "";
        String Lijst = "";
        String queryTekst, queryTekst4, queryTekst5, queryTekst0;
    
        int getal = 0;
        double Prijs = 0;
        double aantalprijs;
        double totaalprijs = 0;
        DecimalFormat f = new DecimalFormat("##.00");

        try {
            //haal tekst op uit tekstveld en plaats deze in een array
            Database db1 = new Database();
            db1.Connectie();

            //menucode = textField.getText();
            String[] array1 = menucode1.split(",");
            tafelnummer2 =  Integer.parseInt(array1[0]) / 1000;
                            
            for(int b = 0; b < array1.length ; b++){
               int getalInt = Integer.parseInt(array1[b]) %1000;
               String getalString = String.valueOf(getalInt);
               array1[b] = getalString;
            }
            
        
            // maak tupel aan in eintiteit bestelling
            db1.VoerInsertQueryuit("INSERT INTO bestelling (Tafelnummer) VALUES (" + tafelnummer2 + ")");

            //haal bijbehorende gerecht op van elke waarde in de array
            for (int a = 0; a < array1.length; a++) {
                String nummer = String.valueOf(array1[a]);
                queryTekst = "SELECT MenuItemNaam FROM menuitem WHERE MenuCode = '" + nummer + "'";
                ResultSet rs = db1.VoerSelectQueryuit(queryTekst);

                //haal uit de resultset de naam van het gerecht op  
                rs.next();
                if (first) {
                    first = false;
                    //enkelgerecht = rs.getString("MenuItemNaam");
                    //  maak voor het eerste gerecht een String met de naam
                    //Lijst = enkelgerecht;
                    Lijst = rs.getString("MenuItemNaam");
                } else {
                    //enkelgerecht = rs.getString("MenuItemNaam");
                    //plak hierna elke andere naam erachter in dezelfde String met steeds een nieuwe regel ertussen
                    Lijst = Lijst + "/" + rs.getString("MenuItemNaam");
                }
            }

            //splits de string in een array
            List<String> list = Arrays.asList(Lijst.split("/"));
            //tel dubbele waarden op
            Set<String> uniqueWords = new HashSet<String>(list);
            for (String word : uniqueWords) {

                queryTekst5 = "SELECT Prijs FROM menuitem WHERE MenuItemNaam = " + "'" + word + "'";
                rs3 = db1.VoerSelectQueryuit(queryTekst5);

                rs3.next();
                Prijs = rs3.getDouble("Prijs");
                aantalprijs = Prijs * Collections.frequency(list, word);
                output = Collections.frequency(list, word) + "x " + word + "      €" + f.format(aantalprijs);
                getal = Collections.frequency(list, word);
                output1 = output1 + output + "\n";
                totaalprijs = totaalprijs + aantalprijs;

                //plaats elk woord/gerecht 1 keer in de database
                queryTekst4 = "INSERT INTO bestelregel (bestelling_Bestelnummer, menuitem_MenuItemNaam, Aantal) VALUES (LAST_INSERT_ID(), '" + word + "', " + getal + ")";
                db1.VoerInsertQueryuit(queryTekst4);

                //voeg totaalprijs toe aan de eerder geplaatste bestelling
                queryTekst0 = "UPDATE  `dehartigehap`.`bestelling` SET  `Totaalprijs` =  " + totaalprijs + " WHERE  `bestelling`.`Bestelnummer` = LAST_INSERT_ID()";
                db1.VoerUpdateQueryuit(queryTekst0);
            }

            finaloutput =
                   "De volgende gerechten zijn besteld: \n"
                    + output1 + "\n Totaal:    €" + f.format(totaalprijs);
            System.out.println(tafelnummer2);
           } catch (SQLException o) {
            System.out.println(o.getMessage());
        }
        return finaloutput;
        
        
    }
    
    public int getTafelnummer(String menucode1){
        String[] array1 = menucode1.split(",");
        tafelnummer2 =  Integer.parseInt(array1[0]) / 1000;
        return tafelnummer2;
    }
}