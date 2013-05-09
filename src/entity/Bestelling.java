package entity;

import java.util.ArrayList;

import dataStorage.Database;

public class Bestelling {

	private ArrayList<MenuItem> menuItems;
	private Database db;

	private MenuItem item;

	public Bestelling() {
		menuItems = new ArrayList<>();
		db = new Database();
		db.Connectie();
        }

	/**
     *
     * @param menucode
     */	
        public void addMenuItem(String menucode) {
		String naam = db.getMenuItem("SELECT MenuItemNaam FROM menuitem WHERE MenuCode ='" + menucode + "'");
		double prijs = db.getPrijs("SELECT Prijs FROM menuitem WHERE MenuCode ='" + menucode + "'");
		item = new MenuItem(naam, prijs);

		menuItems.add(item);
	}



	public ArrayList<MenuItem> getItems() {
            return menuItems;
        }


	



}