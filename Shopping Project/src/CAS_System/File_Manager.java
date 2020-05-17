package CAS_System;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter; 
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class File_Manager {//Class to control reading and writing of any files

	//Converts contents of files into a list of User objects by instancing every Customer or Admin
	public static User[] get_users() {
		try {
			File UserAccountsFile= new File("UserAccounts.txt");
			Scanner scan_file= new Scanner(UserAccountsFile);//Opens a scanner on the file
			FileReader read_file=new FileReader(UserAccountsFile);//Opens a reader on the file
		    BufferedReader buffer_of_reader = new BufferedReader(read_file);//Buffer read operations for better performance     
		    int lines_in_file=0;
		    while((buffer_of_reader.readLine())!=null){//Used to count the amount of users there will be
		    	lines_in_file++;
		    }
		    User [] user_list= new User [lines_in_file];	
			int i=0;//Go through each line and convert the string into lists
			while (scan_file.hasNextLine()){
				String line = scan_file.nextLine();
				String lineSplit[] = line.split(", "); //Then converts these smaller lists into a Admin object or Customer object
				if (lineSplit[6].equals("admin")) {
					user_list[i]=new Admin (Integer.parseInt(lineSplit[0]), lineSplit[1], lineSplit[2], Integer.parseInt(lineSplit[3]), lineSplit[4], lineSplit[5]);
				}
				else if(lineSplit[6].equals("customer")) {
					Product[] empty_shopping_list = {};
					user_list[i]=new Customer (Integer.parseInt(lineSplit[0]), lineSplit[1], lineSplit[2], Integer.parseInt(lineSplit[3]), lineSplit[4], lineSplit[5], empty_shopping_list);
				}
				i=i+1;
			}
			scan_file.close();//Closes all the file libraries
			read_file.close();
			buffer_of_reader.close();
			return (user_list);//Returns a list of users
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return (null);
	}

	//Converts contents of files into a list of Product objects by instancing every Mouse or Keyboard
	public static Product[] get_stock() {
		try {
			File StockFile= new File("Stock.txt");
			Scanner scan_file= new Scanner(StockFile);//Opens a scanner on the file
			FileReader read_file=new FileReader(StockFile);//Opens a reader on the file
		    BufferedReader buffer_of_reader = new BufferedReader(read_file);//Buffer read operations for better performance  
		    int lines_in_file=0;
		    while((buffer_of_reader.readLine())!=null){//Used to count the amount of products there will be
		    	lines_in_file++;
		    }
		    Product [] product_list= new Product [lines_in_file];
			int i=0;//Go through each line and convert the string into lists
			while (scan_file.hasNextLine()){
				String line = scan_file.nextLine();
				String lineSplit[] = line.split(", ");//Then converts these smaller lists into a mouse object or keyboard object
				if (lineSplit[1].equals("mouse")) {
					product_list[i]=new Mouse (lineSplit[0], lineSplit[2], lineSplit[3], lineSplit[4], lineSplit[5], Integer.parseInt(lineSplit[6]), Float.parseFloat(lineSplit[7]), Float.parseFloat(lineSplit[8]), Integer.parseInt(lineSplit[9]));
				}
				else if(lineSplit[1].equals("keyboard")) {
					product_list[i]=new Keyboard (lineSplit[0], lineSplit[2], lineSplit[3], lineSplit[4], lineSplit[5], Integer.parseInt(lineSplit[6]), Float.parseFloat(lineSplit[7]), Float.parseFloat(lineSplit[8]), lineSplit[9]);
				}
				i=i+1;
			}
			scan_file.close();//Closes all the file libraries
			read_file.close();
			buffer_of_reader.close();
			return (product_list);//Returns a list of products
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return (null);
	}

	//Converts contents of files into a 2D array of Activities 
	public static String [] [] get_activity() {
		try {
			File ActivityFile= new File("ActivityLog.txt");
			Scanner scan_file= new Scanner(ActivityFile);//Opens a scanner on the file
			FileReader read_file=new FileReader(ActivityFile);//Opens a reader on the file
		    BufferedReader buffer_of_reader = new BufferedReader(read_file); //Buffer read operations for better performance     
		    int lines_in_file=0;
		    while((buffer_of_reader.readLine())!=null){//Used to count the amount of activities there will be
		    	lines_in_file++;
		    }
		    int activity_attributes=8;
		    String[] [] activity_list= new String [lines_in_file] [activity_attributes];	
			int i=0;//Go through each line and convert the string into lists
			while (scan_file.hasNextLine()){
				String line = scan_file.nextLine();
				String lineSplit[] = line.split(", ");//Then adds these smaller lists a larger list for the 2D array
				String [] activity_item= {lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3], lineSplit[4], lineSplit[5], lineSplit[6], lineSplit[7]};
				activity_list[i]=activity_item ;
				i=i+1;
			}
			scan_file.close();//Closes all the file libraries
			read_file.close();
			buffer_of_reader.close();
			return (activity_list);//Returns a list of users
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return (null);
	}

	//Method to add a Product object to a file, only used by an admin
	public static void add_stock_to_file(Product[] updated_list) {
		String items_to_add="";		//Updates the file by storing each product in a new line of the file
		for (Product item : updated_list) {//Goes through the file and turns each item into a string
			String barcode=item.get_barcode();
			String brand=item.get_brand();
			String colour=item.get_colour();
			String connectivity=item.get_connectivity();
			int quantity=item.get_stock();
			float retail_cost=item.get_retail_cost();
			float original_cost=item.get_original_cost();
			if (item instanceof Mouse) {
				Mouse mouse_product = (Mouse) item;
				String mouse_type=mouse_product.get_mouse_type();
				int button_num=mouse_product.get_button_num();
				items_to_add+= (barcode+", "+"mouse"+", "+mouse_type+", "+brand+", "+colour+", "+connectivity+", "+quantity+", "+retail_cost+", "+original_cost+", "+button_num+"\n");
			}
			else if (item instanceof Keyboard) {
				Keyboard keyboard_product = (Keyboard) item;
				String keyboard_type=keyboard_product.get_keyboard_type();
				String keyboard_layout=keyboard_product.get_keyboard_layout();
				items_to_add+= (barcode+", "+"keyboard"+", "+keyboard_type+", "+brand+", "+colour+", "+connectivity+", "+quantity+", "+retail_cost+", "+original_cost+", "+keyboard_layout+"\n");
			}
		}
		try {//Uses a library to write the variable to the file
			FileWriter fileWrite = new FileWriter("Stock.txt");
			fileWrite.write(items_to_add);
			fileWrite.close();
		}
		catch(IOException e) {
		    e.printStackTrace();
		}
	}

	//Update stock used when the Customer has bought an item and the quantity needs to be reduced
	public static void update_stock(Product[] shopping_list) {
		Product[] all_items=User.view_products();//Stores all the items into a list
		for (Product shopping_item:shopping_list) {//Cross references the shopping list with the all-items list
			for (Product item:all_items) {//When a item from the shopping list is found, the new quantity of the item is set
				if (shopping_item.get_barcode().equals(item.get_barcode())) {
					item.quantity=item.get_stock()-shopping_item.get_stock();
				}
			}
		}//Once all the new quantities have been updated in the product list
		String items_to_add="";
		for (Product item : all_items) {//Go through each product and turn its attributes into a string
			String barcode=item.get_barcode(); //Each string is a line in the file
			String brand=item.get_brand();
			String colour=item.get_colour();
			String connectivity=item.get_connectivity();
			int quantity=item.get_stock();
			float retail_cost=item.get_retail_cost();
			float original_cost=item.get_original_cost();
			if (item instanceof Mouse) {
				Mouse mouse_product = (Mouse) item;
				String mouse_type=mouse_product.get_mouse_type();
				int button_num=mouse_product.get_button_num();
				items_to_add+= (barcode+", "+"mouse"+", "+mouse_type+", "+brand+", "+colour+", "+connectivity+", "+quantity+", "+retail_cost+", "+original_cost+", "+button_num+"\n");
			}
			else if (item instanceof Keyboard) {
				Keyboard keyboard_product = (Keyboard) item;
				String keyboard_type=keyboard_product.get_keyboard_type();
				String keyboard_layout=keyboard_product.get_keyboard_layout();
				items_to_add+= (barcode+", "+"keyboard"+", "+keyboard_type+", "+brand+", "+colour+", "+connectivity+", "+quantity+", "+retail_cost+", "+original_cost+", "+keyboard_layout+"\n");
			}
		}
		try { //Writes the updated stock to the file
			FileWriter fileWrite = new FileWriter("Stock.txt");
			fileWrite.write(items_to_add);
			fileWrite.close();
		}
		catch(IOException e) {
		    e.printStackTrace();
		}
	}

	//Log Activity for PURCHASE
	public static void purchase_activity(Product[] shopping_list, int user_id, String postcode, String payment_method) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		String date=(String) dtf.format(now);//This gets the current date and formats it as dd-MM-yyyy
		String activity_to_add="";//Creates a string to add all the activity
		for (Product item: shopping_list) { //First adds the most recent activity to the string
			String barcode=item.get_barcode();//For each item, it adds the activity with the user's id, postcode and payment method
			float retail_cost=item.get_retail_cost();
			int quantity=item.get_stock();
			activity_to_add+= (user_id+", "+postcode+", "+barcode+", "+ retail_cost+", "+quantity+", purchased, "+payment_method+", "+date+"\n");
		}
		String[][] activity_list = get_activity();//Gets the 2D array of all the previous activity
		for (String[] line: activity_list) {//Goes through each element and adds it back the string
			for (int x = 0; x < line.length; x++) {
				String element=line[x];
				activity_to_add=activity_to_add+element;
				if (x != line.length-1){//For the last item, don't add a comma after it
					activity_to_add=activity_to_add+", ";
				}
			}
			activity_to_add+="\n";
		}
		try {//Writes the updated activity to the file
			FileWriter fileWrite = new FileWriter("ActivityLog.txt");
			fileWrite.write(activity_to_add);
			fileWrite.close();
		}
		catch(IOException e) {
		    e.printStackTrace();
		}
	}

	//Log Activity for CANCELLATION
	public static void cancel_activity(Product[] shopping_list, int user_id, String postcode) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		String date=(String) dtf.format(now);//This gets the current date and formats it as dd-MM-yyyy
		String activity_to_add="";//Creates a string to add all the activity
		for (Product item: shopping_list) {//First adds the most recent activity to the string
			String barcode=item.get_barcode();//For each item, it adds the activity with the user's id, postcode and cancellation status
			float retail_cost=item.get_retail_cost();
			int quantity=item.get_stock();
			activity_to_add+= (user_id+", "+postcode+", "+barcode+", "+ retail_cost+", "+quantity+", cancelled, , "+date+"\n");
		}
		String[][] activity_list = get_activity();//Gets the 2D array of all the previous activity
		for (String[] line: activity_list) {//Goes through each element and adds it back the string
			for (int x = 0; x < line.length; x++) {
				String element=line[x];
				activity_to_add=activity_to_add+element;
				if (x != line.length-1){//For the last item, don't add a comma after it
					activity_to_add=activity_to_add+", ";
				}
			}
			activity_to_add+="\n";
		}		
		try {//Writes the updated activity to the file
			FileWriter fileWrite = new FileWriter("ActivityLog.txt");
			fileWrite.write(activity_to_add);
			fileWrite.close();
		}
		catch(IOException e) {
		    e.printStackTrace();
		}
	}

	//Log Activity for SAVE FOR LATER
	public static void save_activity (Product[] shopping_list, int user_id, String postcode) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		String date=(String) dtf.format(now);//This gets the current date and formats it as dd-MM-yyyy
		String activity_to_add="";//Creates a string to add all the activity
		for (Product item: shopping_list) {//First adds the most recent activity to the string
			String barcode=item.get_barcode();//For each shopping item, it adds the activity with the user's id, postcode and save status
			float retail_cost=item.get_retail_cost();
			int quantity=item.get_stock();
			activity_to_add+= (user_id+", "+postcode+", "+barcode+", "+ retail_cost+", "+quantity+", saved, , "+date+"\n");
		}
		String[][] activity_list = get_activity();//Gets the 2D array of all the previous activity
		for (String[] line: activity_list) {//Goes through each element and adds it back the string
			for (int x = 0; x < line.length; x++) {
				String element=line[x];
				activity_to_add=activity_to_add+element;
				if (x != line.length-1){//For the last item, don't add a comma after it
					activity_to_add=activity_to_add+", ";
				}
			}
			activity_to_add+="\n";
		}		
		try {//Writes the updated activity to the file
			FileWriter fileWrite = new FileWriter("ActivityLog.txt");
			fileWrite.write(activity_to_add);
			fileWrite.close();
		}
		catch(IOException e) {
		    e.printStackTrace();
		}
	}
}