package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import CAS_System.Admin;
import CAS_System.Customer;
import CAS_System.Keyboard;
import CAS_System.Mouse;
import CAS_System.Product;
import CAS_System.User;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class mainCustomer_3_GUI extends JFrame {
	private JPanel contentPane;
	private JTextField txtShppingList;
	private JComboBox comboBox;
	private JTable table; //This is the table for all the items
	private static JTable my_items_table; //This is the table for the shopping list
	private DefaultTableModel all_products; //Model to inside table
	private DefaultTableModel shopping_list; //Model to go inside my_items_table
	public static Customer customer_person; //The current Customer using the table
	private String brands[]; //Holds a list of all brands, this is a unique list
	private String brand_chosen; //Variable to track a filter
	private boolean uk_layout; //Variable to track a filter
	private static DecimalFormat pounds = new DecimalFormat("0.00");//Prints prices to 2dp
	
	
	//Launches application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainCustomer_3_GUI frame = new mainCustomer_3_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Creates the frame
	public mainCustomer_3_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 497);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(10, 55, 432, 403);
		contentPane.add(panel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane);
		
		//Creates the all items table
		table = new JTable();
		scrollPane.setViewportView(table);
		Customer customer_person=Login_Menu_2_GUI.get_current_customer();
		
		
		brand_chosen="All Items"; //Default value
		uk_layout=false; //Default value
		all_products= view_all_products(brand_chosen,uk_layout); //Loads the table model with all the products showing
		table.setModel(all_products); //Creates the model inside the table
		
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Keyboards with UK Layout");
		chckbxNewCheckBox_1.setBackground(new Color(204, 255, 255));
		chckbxNewCheckBox_1.setForeground(Color.RED);
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		chckbxNewCheckBox_1.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) { //When the checkbox is ticked
		    	uk_layout=chckbxNewCheckBox_1.isSelected();  //Gets the boolean value
		    	all_products= view_all_products(brand_chosen,uk_layout); //Recreates the table model with the filter on
				table.setModel(all_products);//Creates the model inside the table
		    }
		});
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox.setForeground(Color.RED);
		
		comboBox.setModel(new DefaultComboBoxModel(brands));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGap(20)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
							.addComponent(chckbxNewCheckBox_1)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxNewCheckBox_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {//When the new item is chosen
		    	brand_chosen=comboBox.getSelectedItem().toString();//Gets the string value
		    	all_products= view_all_products(brand_chosen,uk_layout); //Recreates the table model with the filter on
				table.setModel(all_products);//Creates the model inside the table
		    }
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 255));
		panel_1.setBounds(452, 55, 392, 403);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Item To Shopping");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// When the button is clicked
				if (table.getSelectedRow()==-1) { //If nothing is chosen, display error
					JOptionPane.showMessageDialog(my_items_table, "Please choose a product from the items on the left");
				}
				else {//Get the barcode and the type of item chosen
					int row_number=(table.getSelectedRow());
					Object barcode_object=(table.getValueAt(row_number,0 ));
					String barcode_number=barcode_object.toString();
					Object type_chosen_object=(table.getValueAt(row_number,1 ));
					String type_chosen=type_chosen_object.toString();
					if (type_chosen=="Mouse") {// If it's a mouse product
						Product[] all_items=User.view_products();
						for (Product item : all_items) {
							if (item.get_barcode().equals(barcode_number)) {
								Mouse mouse_product = (Mouse) item;
								int max_quantitiy=item.quantity;//Finds how many are available
								mouse_product.quantity=1;//Sets how many to take and adds the item to the shopping
								Boolean success=customer_person.add_mouse_to_shopping(mouse_product, max_quantitiy);
								if (success) {//If there is enough in stock, it will add to shopping
									//Recreate the user's shopping list
									shopping_list= view_shopping_list(true);
									my_items_table.setModel(shopping_list);
								}
								else {//If max quantity reached, display error message
									JOptionPane.showMessageDialog(null, "Max quantity reached for that item");
								}
							}
						}
					}
					else if (type_chosen=="Keyboard") {//If it's a Keyboard product
						Product[] all_items=User.view_products();
						for (Product item : all_items) {//Find the item in the list
							if (item.get_barcode().equals(barcode_number)) {
								Keyboard keyboard_product = (Keyboard) item;
								int max_quantitiy=item.quantity;//Finds how many are available
								keyboard_product.quantity=1;//Sets how many to take and adds the item to the shopping
								Boolean success=customer_person.add_keyboard_to_shopping(keyboard_product, max_quantitiy);
								if (success) {//If there is enough in stock, it will add to shopping
									//Recreate the user's shopping list 
									shopping_list= view_shopping_list(true);
									my_items_table.setModel(shopping_list);
								}
								else {//If max quantity reached, display error message
									JOptionPane.showMessageDialog(null, "Max quantity reached for that item");
								}
							}
						}					
					}
				}
			}
		});
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBounds(143, 0, 174, 23);
		panel_1.add(btnNewButton);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(204, 255, 255));
		panel_4.setBounds(0, 23, 382, 335);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 362, 313);
		panel_4.add(scrollPane_1);
		
		my_items_table = new JTable();
		scrollPane_1.setViewportView(my_items_table);
		shopping_list= view_shopping_list(false);//Shows the shopping list, false represents no new item added
		my_items_table.setModel(shopping_list);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//If items are cancelled
				Product[] shopping_products= Customer.get_shopping();
				if (shopping_products.length>0){//If there are items in the list
					customer_person.cancel_products();//Runs cancel method and updates the shopping list
					shopping_list= view_shopping_list(false);//Shows the shopping list, false represents no new item added
					my_items_table.setModel(shopping_list);
					JOptionPane.showMessageDialog(null, "Items Cancelled!");
				}
				else {//If there are no items, display error
					JOptionPane.showMessageDialog(null, "Add something to the basket");
				}
			}
		});
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setBounds(60, 369, 81, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Save For Later");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//If items are saved
				Product[] shopping_products= Customer.get_shopping();
				if (shopping_products.length>0){//If there are items in the list
					customer_person.save_for_later();//Runs saved method and updates the shopping list
					shopping_list= view_shopping_list(false);//Shows the shopping list, false represents no new item added
					my_items_table.setModel(shopping_list);
					JOptionPane.showMessageDialog(null, "Items Saved!");
				}
				else {//If there are no items, display error
					JOptionPane.showMessageDialog(null, "Add something to the basket");
				}
			}
		});
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.setBounds(143, 369, 134, 23);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("BUY");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//If items are bought
				Product[] shopping_products= Customer.get_shopping();
				if (shopping_products.length>0){//If there are items in the list
					JOptionPane.showMessageDialog(null, "Let's go buy the items!"); //Confirmation message
					Transaction_4_GUI buy_page=new Transaction_4_GUI();
					buy_page.set_user(customer_person);//Gives the user to the transaction page,
					//this will include the user's shopping list as it's an attribute
					mainCustomer_3_GUI.this.dispose();
					buy_page.setVisible(true);
				}
				else {//If there are no items, display error
					JOptionPane.showMessageDialog(null, "Add something to the basket");
				}
			}
		});
		btnNewButton_3.setForeground(Color.BLUE);
		btnNewButton_3.setBounds(281, 369, 74, 23);
		panel_1.add(btnNewButton_3);
		
		txtShppingList = new JTextField();
		txtShppingList.setBackground(new Color(204, 255, 255));
		txtShppingList.setForeground(Color.BLUE);
		txtShppingList.setText("Shopping List:");
		txtShppingList.setBounds(0, 1, 86, 20);
		panel_1.add(txtShppingList);
		txtShppingList.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 255, 255));
		panel_2.setBounds(10, 11, 834, 33);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Hi, start your shopping");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		String username=customer_person.get_username();
		JLabel lblNewLabel_1 = new JLabel("Account: Customer "+username);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnNewButton_4 = new JButton("Log Out");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//If button clicked
				mainCustomer_3_GUI.this.dispose();//Deletes window and goes back to login ignoring anything in the basket
				Login_Menu_2_GUI login_page=new Login_Menu_2_GUI();
				login_page.setVisible(true);
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addGap(118)
					.addComponent(btnNewButton_4)
					.addGap(118)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
	}
	
	public DefaultTableModel view_all_products(String brand_chosen, Boolean UK_layout) {//Ran when there is a change in the page (filters)
		all_products= new DefaultTableModel();//Creates a fresh model
		table.setDefaultEditor(Object.class, null);//Make table not editable
		all_products.setColumnIdentifiers(new Object[] {"Barcode", "Name", "Brand", "Colour", "Connectivity", "Quanitity", "Cost", "Device Type", "Additional Info (No.Buttons/Layout)"});
		all_products.setRowCount(0);//Recreates columns and sets row at the start
		Product[] all_items=User.view_products(); //Pulls a list of all the items in the stock file as Product list
		brands=new String[all_items.length];//Creates a list of brands that will hold the unique values
		brands[0]="All Items";
		int i=1;
		
		for (Product item : all_items) {//Gets information about every item
			String barcode=item.get_barcode();
			String brand=item.get_brand();
			String colour=item.get_colour();
			String connectivity=item.get_connectivity();
			int quantity=item.get_stock();
			float retail_cost=item.get_retail_cost();
			String retail_cost_string=pounds.format(retail_cost);//Formats the price to 2dp
			//Checks if a brand has already been added
			Boolean unique_element=true;
			for (String brand_name : brands) {
				if (brand_name!=null) {//When it reaches end of list it stops
					if (brand_name.equals(brand)){
						unique_element=false;//Tells the next if statement that the item is already added
						break;
					}
				}
			}//If the brand has not been added, it gets added
			if (unique_element==true) {
				brands[i]=brand;
				i++;//Calculates how many unique brands there are
			}
			if (brand_chosen.equals("All Items")) {//This checks if a filter is on
				if (UK_layout.equals(false)) {// WHEN A BRAND IS NOT CHOSEN AND LAYOUT NOT CHOSEN
					if (item instanceof Mouse) {
						Mouse mouse_product = (Mouse) item; //Casts the product into a mouse
						String mouse_type=mouse_product.get_mouse_type();
						int button_num=mouse_product.get_button_num();//Pulls the extra info of a mouse and creates the object for the table model
						Object[] item_info=new Object[] {barcode,"Mouse",brand,colour,connectivity,quantity,retail_cost_string,mouse_type,button_num};
						all_products.addRow(item_info);//Adds the new object to the table
					}
					else if (item instanceof Keyboard) {//Casts the product into a keyboard
						Keyboard keyboard_product = (Keyboard) item;
						String keyboard_type=keyboard_product.get_keyboard_type();
						String keyboard_layout=keyboard_product.get_keyboard_layout();//Pulls the extra info of a keyboard and adds the object to the table model
						Object[] item_info=new Object[] {barcode,"Keyboard",brand,colour,connectivity,quantity,retail_cost_string,keyboard_type,keyboard_layout};
						all_products.addRow(item_info);
					}
				}
				else {// WHEN A BRAND IS NOT CHOSEN AND LAYOUT IS CHOSEN
					if (item instanceof Keyboard) {
						Keyboard keyboard_product = (Keyboard) item;
						String keyboard_type=keyboard_product.get_keyboard_type();
						String keyboard_layout=keyboard_product.get_keyboard_layout();
						if (keyboard_layout.contentEquals("UK")) {
							Object[] item_info=new Object[] {barcode,"Keyboard",brand,colour,connectivity,quantity,retail_cost_string,keyboard_type,keyboard_layout};
							all_products.addRow(item_info);
						}
					}
				}
			}
			else {//Brand filter ON
				if (brand.equals(brand_chosen)) {
					if (UK_layout.equals(false)) {// WHEN A BRAND IS CHOSEN AND LAYOUT IS NOT CHOSEN
						if (item instanceof Mouse) {
							Mouse mouse_product = (Mouse) item;
							String mouse_type=mouse_product.get_mouse_type();
							int button_num=mouse_product.get_button_num();
							Object[] item_info=new Object[] {barcode,"Mouse",brand,colour,connectivity,quantity,retail_cost_string,mouse_type,button_num};
							all_products.addRow(item_info);
						}
						else if (item instanceof Keyboard) {
							Keyboard keyboard_product = (Keyboard) item;
							String keyboard_type=keyboard_product.get_keyboard_type();
							String keyboard_layout=keyboard_product.get_keyboard_layout();
							Object[] item_info=new Object[] {barcode,"Keyboard",brand,colour,connectivity,quantity,retail_cost_string,keyboard_type,keyboard_layout};
							all_products.addRow(item_info);
						}
					}
					else {// WHEN A BRAND IS CHOSEN AND LAYOUT IS CHOSEN
						if (item instanceof Keyboard) {
							Keyboard keyboard_product = (Keyboard) item;
							String keyboard_type=keyboard_product.get_keyboard_type();
							String keyboard_layout=keyboard_product.get_keyboard_layout();
							if (keyboard_layout.contentEquals("UK")) {
								Object[] item_info=new Object[] {barcode,"Keyboard",brand,colour,connectivity,quantity,retail_cost_string,keyboard_type,keyboard_layout};
								all_products.addRow(item_info);
							}
						}
					}
				}
			}
		}
		String [] temp=new String[i];//This will hold the actual list of brands without null spaces
		for (int x = 0; x < i; x++) {//Goes through the brand list until it reaches a null space
			temp[x]=brands[x];
		}
		brands=temp;//Transfers all the brands across
		return all_products;//Sends back the new table model with updated stock/ filter added
	}
	
	public DefaultTableModel view_shopping_list(Boolean add) {//Ran when there is a change in the page, usually any button
		shopping_list= new DefaultTableModel();//Creates a fresh model
		my_items_table.setDefaultEditor(Object.class, null);//Make table not editable
		shopping_list.setColumnIdentifiers(new Object[] {"Barcode", "Name", "Brand", "Colour", "Connectivity", "Quanitity", "Cost", "Device Type", "Additional Info (No.Buttons/Layout)"});
		shopping_list.setRowCount(0);//Recreates columns and sets row at the start
		if (add.equals(true)) {//If the add product button is clicked
			Product[] shopping_products= Customer.get_shopping();//Pulls a list of all the items in the users shopping basket
			for (Product item : shopping_products) {
				String barcode=item.get_barcode();
				String brand=item.get_brand();
				String colour=item.get_colour();
				String connectivity=item.get_connectivity();
				int quantity=item.get_stock();
				float retail_cost=item.get_retail_cost();
				if (item instanceof Mouse) { //Converts the Product into an object to add to the table model
					Mouse mouse_product = (Mouse) item;
					String mouse_type=mouse_product.get_mouse_type();
					int button_num=mouse_product.get_button_num();
					Object[] item_info=new Object[] {barcode,"Mouse",brand,colour,connectivity,quantity,retail_cost,mouse_type,button_num};
					shopping_list.addRow(item_info);
				}
				else if (item instanceof Keyboard) {//Converts the Product into an object to add to the table model
					Keyboard keyboard_product = (Keyboard) item;
					String keyboard_type=keyboard_product.get_keyboard_type();
					String keyboard_layout=keyboard_product.get_keyboard_layout();
					Object[] item_info=new Object[] {barcode,"Keyboard",brand,colour,connectivity,quantity,retail_cost,keyboard_type,keyboard_layout};
					shopping_list.addRow(item_info);
				}
			}
		}
		return shopping_list; //Returns the new shopping list, either with a new item added or nothing if the basket was emptied
	}
}