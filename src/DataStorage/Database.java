/**
 * @author Remco
 *
 */
package dataStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private Statement stmt;
    private ResultSet rs;
    private Connection con = null;

    public void Connectie() {
        try {
            /* Connectie naar MySQL database leggen */
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DeHartigeHap",
	        "root", ""); 
//            con = DriverManager.getConnection("jdbc:mysql://www.vp4i2010b.aibbreda.nl/vp4i2010b_c1",
//                    "vp4i2010b_c1", "2change");
            stmt = con.createStatement();

            /*Succes melding als connectie naar MySQL database is gelukt */
            if (!con.isClosed()) {
                System.out.println("Successfully connected to "
                        + "MySQL server using TCP/IP...");
            }
        } catch (Exception e) {
            System.err.println("Melding1: " + e.getMessage());
        }
    }

    public void VoerUpdateQueryuit(String updatecommando) {
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(updatecommando);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getMenuItem(String selectcommando) {
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(selectcommando);
            rs.next();
            return rs.getString("productNaam");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            return null;
        }
    }

    public double getPrijs(String selectcommando) {
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(selectcommando);
            rs.next();
            return rs.getDouble("productPrijs");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            return 0;
        }
        
    }


    public int getProductNr(String selectcommando) {
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(selectcommando);
            rs.next();
            return rs.getInt("productNr");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            return 0;
        }
    }
}