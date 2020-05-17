package CAS_System;

//Abstract class, admin and customer inherit methods and attributes
public abstract class Product { 
	public String barcode;   //6 Digit Number E.g.004635
	public String brand;	// String not unique
	public String colour;	//String not unique
	public String connectivity; //Wired or wireless
	public int quantity;	//Integer larger than 0
	public float retail_cost; //Float to 2dp
	public float original_cost; //Float to 2dp
	
	//Creates instance of a product, never directly accessed, only accessed through child classes
	public Product (String barcode, String brand, String colour, String connectivity, int quantity, float retail_cost, float original_cost){
		this.barcode=barcode;   
		this.brand=brand;
		this.colour=colour;
		this.connectivity=connectivity;
		this.quantity=quantity;
		this.retail_cost=retail_cost;
		this.original_cost=original_cost;
	}

	public String get_barcode() { 		//Lots of get methods to get info about a product
		return(barcode);
	}
	
	public String get_brand() {
		return(brand);
	}
	
	public String get_colour() {
		return(colour);
	}
	
	public String get_connectivity() {
		return(connectivity);
	}
	
	public int get_stock() {
		return(quantity);
	}
	
	public float get_retail_cost() {
		return(retail_cost);
	}
	
	public float get_original_cost() {
		return(original_cost);
	}
}