package entity;

public class MenuItem {

	private String naam;
	private double prijs;
        private int productNr;

    public MenuItem(String naam, double prijs, int productNr) {

		this.naam = naam;
		this.prijs = prijs;
                this.productNr = productNr;
	}
	
	public String getNaam(){
		return naam;
	}
	
	public double getPrijs(){
		return prijs;
	}
        
        public int getProductNr(){
                return productNr;
        }
        
}
