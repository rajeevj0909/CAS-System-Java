package CAS_System;

public class Mouse extends Product{ //Inherits a product
	public String mouse_type; // Mouse also has type attribute- Standard or gaming
	public int button_num; // Mouse also has buttons attribute-Integer larger than 0
	
	public Mouse (String barcode, String mouse_type, String brand, String colour, String connectivity, int quantity, float retail_cost, float original_cost, int button_num){
		super (barcode, brand, colour, connectivity, quantity, retail_cost, original_cost);//Inherits attributes from product
		this.mouse_type=mouse_type;//Gives extra 2 attributes to mouse
		this.button_num=button_num;
	}
	
	public String get_mouse_type() {	//Methods to get info about a mouse
		return(mouse_type);
	}
	
	public int get_button_num() {	
		return(button_num);
	}
}