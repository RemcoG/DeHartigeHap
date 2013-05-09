package mainpackage;

import java.text.DecimalFormat;

import presentation.BestelGui;
import logic.Manager;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DecimalFormat f = new DecimalFormat("##.00");
		Manager manager = new Manager(f);
		
		BestelGui gui = new BestelGui(manager, f);
		gui.setVisible(true);
	}

}
