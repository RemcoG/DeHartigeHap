package entity;

public class MenuItem {

	private String naam;
	private double prijs;

	public MenuItem(String naam, double prijs) {

		this.naam = naam;
		this.prijs = prijs;
	}
	
	public String getNaam(){
		return naam;
	}
	
	public double getPrijs(){
		return prijs;
	}
}
