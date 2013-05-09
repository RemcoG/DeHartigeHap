package logic;

import entity.Bestelling;
import entity.MenuItem;
import java.text.DecimalFormat;

public class Manager {

	private Bestelling bestelling = new Bestelling();
	private String output = "";
	private double bedrag;
	private DecimalFormat f;
    
	public Manager(DecimalFormat f) {
		this.f = new DecimalFormat("##.00");
	}

        public void addMenuItem(String menucode) {
		bestelling.addMenuItem(menucode);
	}

	public String getArray() {
		output = "";
		for (MenuItem item : bestelling.getItems()) {
			output = output + "€" + f.format(item.getPrijs()) + "     " + item.getNaam()+ "\n";
		}
		return output;
	}

	public double getTotaalPrijs() {
		bedrag = 0;
		for (MenuItem item : bestelling.getItems()) {
			bedrag = bedrag + item.getPrijs();
		}
		return bedrag;
	}

}