package CAS_System;

public class Customer extends User{//Customer is a child of user. They can add items to the shopping, buy items, cancel items and save items for later.
	public String person_type; //Has 2 extra attributes; access level and shopping list
	public static Product [] shopping_list;

	public Customer(int user_id, String username, String surname, int house_number, String postcode, String city, Product [] shopping_list) {
		super(user_id, username, surname, house_number, postcode, city);   //Creates an instance of a customer
		this.person_type="Customer";
		Customer.shopping_list=shopping_list;
	}
	
	public boolean add_mouse_to_shopping(Mouse mouse_product, int max_quantitiy) {//Add Mouse to shopping basket
		Product [] updated_list = new Product [shopping_list.length +1]; //Create room in a new list
		if  (shopping_list.length!=0) {//If the list isn't already empty
			boolean new_item=true;
			int i;
			for (i = 0; i <(shopping_list.length); i++) {//Checks if its a new product by checking the barcode
				Product item=shopping_list[i];
				if (item.barcode.equals(mouse_product.barcode)) { 
					new_item=false;//Sets false if it is not new
					break;
				}
			}
			if (new_item==false) {// If the product already exists, just update quantity
				if ((shopping_list[i]).get_stock() <(max_quantitiy)) {//Update the item with the new quantity
					(shopping_list[i]).quantity=(shopping_list[i]).quantity + mouse_product.quantity;
				}
				else{//Max quantity allowed
					return false;
				}
			}
			else {//If it's a new unrecognised item, add the item to the updated list on the end
				int j;
				for (j = 0; j <shopping_list.length; j++) {
					updated_list[j]=shopping_list[j];
				}
				updated_list[j]=mouse_product;
				shopping_list=updated_list;
			}
		}
		else {//First item is added to the list
			updated_list[0]=mouse_product;
			shopping_list=updated_list;
		}
		return true;
	}
	
	public boolean add_keyboard_to_shopping(Keyboard keyboard_product,int max_quantitiy) {//Add Mouse to shopping basket
		Product [] updated_list = new Product [shopping_list.length +1];//Create room in a new list
		if  (shopping_list.length!=0) {//If the list isn't already empty
			boolean new_item=true;
			int i;
			for (i = 0; i <(shopping_list.length); i++) {//Checks if its a new product by checking the barcode
				Product item=shopping_list[i];
				if (item.barcode.equals(keyboard_product.barcode)) { //Update existing entries
					new_item=false;//Sets false if it is not new
					break;
				}
			}
			if (new_item==false) {// If the product already exists, just update quantity
				if ((shopping_list[i]).get_stock() <(max_quantitiy)) {//Update the item with the new quantity
					(shopping_list[i]).quantity=(shopping_list[i]).quantity + keyboard_product.quantity;
				}
				else {//Max quantity allowed
					return false;
				}
			}
			else {//If it's a new unrecognised item, add the item to the updated list on the end
				int j;
				for (j = 0; j <shopping_list.length; j++) {
					updated_list[j]=shopping_list[j];
				}
				updated_list[j]=keyboard_product;
				shopping_list=updated_list;
			}
		}
		else {//First item is added to the list
			updated_list[0]=keyboard_product;
			shopping_list=updated_list;
		}
		return true;
	}
	
	
	public void buy_now(String payment_method) {//PayForItems- Ran from transaction screen, stock file not changed until they paid!
		//Remove from Stock
		File_Manager.update_stock(shopping_list);
		//LOG Activity
		File_Manager.purchase_activity(shopping_list, user_id, postcode,payment_method);
		//Empty basket
		empty_basket();
	}
	
	public void cancel_products() {//Cancel all products in basket
		//LOG Activity
		File_Manager.cancel_activity(shopping_list, user_id, postcode);
		//Empty basket
		empty_basket();
	}
	
	public void save_for_later() {//Save for later all products in basket
		//LOG Activity
		File_Manager.save_activity(shopping_list, user_id, postcode);
		//Empty basket
		empty_basket();
	}
	
	public void empty_basket() {//Empties user's shopping list
		Product [] empty_list = {};
		shopping_list=empty_list;
	}
	
	public static Product[] get_shopping() { //Returns user's shopping list
		return shopping_list;
	}
	
	public String get_person_type() { //Returns access level
		return(person_type);
	}
}