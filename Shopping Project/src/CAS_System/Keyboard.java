package CAS_System;

public class Keyboard extends Product{//Inherits a product
	public String keyboard_type; // Keyboard also has type attribute- Standard or gaming or internet or flexible
	public String keyboard_layout; // Keyboard also has layout attribute-UK or US
	
	public Keyboard (String barcode, String keyboard_type, String brand, String colour, String connectivity, int quantity, float retail_cost, float original_cost, String keyboard_layout){
		super (barcode, brand, colour, connectivity, quantity, retail_cost, original_cost);
		this.keyboard_type=keyboard_type; //Gives extra 2 attributes to Keyboard
		this.keyboard_layout=keyboard_layout;
	}
	
	public String get_keyboard_type() {	//Methods to get info about a Keyboard
		return(keyboard_type);
	}
	
	public String get_keyboard_layout() {
		return(keyboard_layout);
	}
}