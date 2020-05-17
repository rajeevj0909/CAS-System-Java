package CAS_System;

//Abstract class, admin and customer inherit methods and attributes
public abstract class User {//Class to define a user and their methods
	public int user_id;//Unique   
	public String username;//Unique
	public String surname;
	public int house_number;
	public String postcode;
	public String city;
	
	//Creates instance of a user, never directly accessed, only accessed through child classes
	public User(int user_id, String username, String surname, int house_number, String postcode, String city) {
		this.user_id=user_id;  
		this.username=username;
		this.surname=surname;
		this.house_number=house_number;
		this.postcode=postcode;
		this.city=city;
	}
	
	//Method to get all items that have quantity and are in order of quantity
	public static Product[] view_products() { 
		Product [] items=File_Manager.get_stock();//Gets the items from the file
		//-------------------Get rid of the stock with no quantity
		int n = items.length; 
		int size=0; //Finds the items with 0 quantity and finds how many there are
		Product [] items_updated=new Product [n];
		for (int i = 0; i < n; i++) {
			int stock=items[i].get_stock();
			if (stock!=0){
				size+=1;
				items_updated[i]=items[i];
			}
		}
		items=new Product [size];
		n=0;
		int x=0;//Adds all the items from the old list to the new list
		while (n<size) {
			if (items_updated[x]!=null) {
				items[n]=items_updated[x];
				n++;
			}
			x++;
		}
		//---------------Sort items into descending order
		n = items.length; 
		int quantity1;
		int quantity2; //Simple sort algorithm (bubble)
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) {
				quantity1=items[j].get_stock();
				quantity2=items[j+1].get_stock();
				if (quantity1 < quantity2){ 
					Product temp = items[j]; 
					items[j] = items[j+1]; 
					items[j+1] = temp;
				}
			}
		}
		return items;
	}
	
	public int get_user_id() { //Lots of methods to return a users attributes
		return(user_id);
	}
	
	public String get_username() {
		return(username);
	}
	
	public String get_surname() {
		return(surname);
	}
	
	public int get_house_number() {
		return(house_number);
	}
	
	public String get_postcode() {
		return(postcode);
	}
	
	public String get_city() {
		return(city);
	}
}