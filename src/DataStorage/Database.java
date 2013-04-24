/**
 * @author Remco
 *
 */
package DataStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import Presentation.Gui;

//public class Database extends Gui {
public class Database {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String queryTekst;
    Statement stmt, stmt1, stmt2;
    ResultSet rs2;
    public Connection con = null;

    //public Database(){
    //Database db = new Database();
    //}
    public void Connectie() {
        try {
            /* Connectie naar MySQL database leggen */
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dehartigehap", "root", "");
            //statements cre�ren
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();

            /*Succes melding als connectie naar MySQL database is gelukt */
            if (!con.isClosed()) {
                System.out.println("Successfully connected to "
                        + "MySQL server using TCP/IP...");
            }
        } catch (Exception e) {
            System.err.println("Melding1: " + e.getMessage());
        }
    }

    public void VoerInsertQueryuit(String sqlcommando) {
        try {
            stmt.executeUpdate(sqlcommando);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void VoerUpdateQueryuit(String updatecommando) {
        try {
            stmt1.executeUpdate(updatecommando);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet VoerSelectQueryuit(String selectcommando) {
        try {
            rs2 = stmt2.executeQuery(selectcommando);
            return rs2;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            return null;
        }
    }
}