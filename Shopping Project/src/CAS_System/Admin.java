package CAS_System;

public class Admin extends User{ //Admin is a child of user. They can add stock.
	public String person_type; //Has 1 extra attribute which is the access level
	

	public Admin(int user_id, String username, String surname, int house_number, String postcode, String city) {
		super(user_id, username, surname, house_number, postcode, city);   //Creates an instance of an admin
		this.person_type="Admin";
	}
	
	public boolean add_to_stock(Mouse new_mouse) { //If the admin wants to add a mouse
		//START OF MOUSE VALIDATION//
		new_mouse.mouse_type=new_mouse.get_mouse_type().toLowerCase();//Changes to lower-case to match file
		new_mouse.connectivity=new_mouse.get_connectivity().toLowerCase();//Changes to lower-case to match file
		
		if( ((new_mouse.get_barcode().length()==6)&&(new_mouse.get_barcode().matches("-?\\d+")))//Checks length=6 and barcode is integer
		&& ((new_mouse.get_mouse_type().equals("standard"))||(new_mouse.get_mouse_type().equals("gaming")))//Checks type
		&& ((new_mouse.get_connectivity().equals("wired"))||(new_mouse.get_connectivity().equals("wireless")))//Checks connectivity	
		&& (new_mouse.get_stock()>0)//Checks quantity	
		&& ((new_mouse.get_retail_cost()>=0.0)&&(new_mouse.get_original_cost()>=0.0))//Checks the price of item is either free or more
		&& (new_mouse.get_button_num()>=0)//Checks there are 0 or more buttons on the mouse
		){	
			new_mouse.brand = new_mouse.get_brand().substring(0, 1).toUpperCase() + new_mouse.get_brand().substring(1).toLowerCase();//Changes brand to Capital case
			new_mouse.colour=new_mouse.get_colour().toLowerCase();//Changes colour to lower-case to match file
		//END OF MOUSE VALIDATION//
			
			boolean new_item=true;
			Product[] all_items=User.view_products();
			int i;
			for (i = 0; i <(all_items.length); i++) {//Checks if its a new product by checking the barcode
				Product item=all_items[i];
				if (item.barcode.equals(new_mouse.barcode)) { 
					new_item=false; //Sets false if it is not new
					break;
				}
			}
			if (new_item==false) {// If the product already exists, just update quantity
				(all_items[i]).quantity=(all_items[i]).quantity + new_mouse.quantity;
				File_Manager.add_stock_to_file(all_items); //Update the file with the new item
			}
			else {//If it's a new unrecognised item, add the item to the updated list
				Product[] updated_list= new Product [all_items.length+1];
				int j;
				for (j = 0; j <all_items.length; j++) {
					updated_list[j]=all_items[j];
				}
				updated_list[j]=new_mouse;
				File_Manager.add_stock_to_file(updated_list); //Update the file with the new item
			}
			return true;
		}
			
		return false;
	}
	
	public boolean add_to_stock(Keyboard new_keyboard) {	//If the admin wants to add a keyboard
		//START OF KEYBOARD VALIDATION//
		new_keyboard.keyboard_type=new_keyboard.get_keyboard_type().toLowerCase();//Changes to lower-case to match file
		new_keyboard.connectivity=new_keyboard.get_connectivity().toLowerCase();//Changes to lower-case to match file
		new_keyboard.keyboard_layout=new_keyboard.get_keyboard_layout().toUpperCase();//Changes to UPPER-case to match file
		
		if( ((new_keyboard.get_barcode().length()==6)&&(new_keyboard.get_barcode().matches("-?\\d+")))//Checks length=6 and barcode is integer
		&& ((new_keyboard.get_keyboard_type().equals("standard"))||(new_keyboard.get_keyboard_type().equals("gaming"))//Checks type 
		||	(new_keyboard.get_keyboard_type().equals("internet"))||(new_keyboard.get_keyboard_type().equals("flexible")))// is one of 4 types
		&& ((new_keyboard.get_connectivity().equals("wired"))||(new_keyboard.get_connectivity().equals("wireless")))//Checks connectivity	
		&& (new_keyboard.get_stock()>0)//Checks quantity is more than 0	
		&& ((new_keyboard.get_retail_cost()>=0.0)&&(new_keyboard.get_original_cost()>=0.0))//Checks the price of item is either free or more
		&& ((new_keyboard.get_keyboard_layout().equals("UK"))||(new_keyboard.get_keyboard_layout().equals("US")))//Checks the layout
		){	
			new_keyboard.brand = new_keyboard.get_brand().substring(0, 1).toUpperCase() + new_keyboard.get_brand().substring(1).toLowerCase();//Changes brand to Capital case
			new_keyboard.colour=new_keyboard.get_colour().toLowerCase();//Changes colour to lower-case to match file
		//END OF KEYBOARD VALIDATION//
			
			boolean new_item=true;
			Product[] all_items=User.view_products();
			int i;
			for (i = 0; i <(all_items.length); i++) {//Checks if its a new product by checking the barcode
				Product item=all_items[i];
				if (item.barcode.equals(new_keyboard.barcode)) { 
					new_item=false;//Sets false if it is not new
					break;
				}
			}
			if (new_item==false) {// If the product already exists, just update quantity
				(all_items[i]).quantity=(all_items[i]).quantity + new_keyboard.quantity;
				File_Manager.add_stock_to_file(all_items); //Update the file with the new item
			}
			else {//If it's a new unrecognised item, add the item to the updated list
				Product[] updated_list= new Product [all_items.length+1];
				int j;
				for (j = 0; j <all_items.length; j++) {
					updated_list[j]=all_items[j];
				}
				updated_list[j]=new_keyboard;
				File_Manager.add_stock_to_file(updated_list); //Update the file with the new item
			}
			return true;
		}
		return false;
	}
	
	public String get_person_type() {
		return(person_type);
	}
}