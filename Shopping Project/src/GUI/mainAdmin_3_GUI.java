package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import CAS_System.Admin;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class mainAdmin_3_GUI extends JFrame {
	private JPanel contentPane;
	private JTextField txtBarcode;
	private JTextField txtName;
	private JTextField txtBrand;
	private JTextField txtColour;
	private JTextField txtConnectivity;
	private JTextField txtQuanity;
	private JTextField txtOriginalPrice;
	private JTextField txtRetail;
	private JTextField txtDeviceType;
	private JTextField txtBarcodetextbox;
	private JTextField txtNametextbox;
	private JTextField txtBrandtextbox;
	private JTextField txtColourtextbox;
	private JTextField txtConnectivity_1;
	private JTextField txtQuantitytextbox;
	private JTextField txtOriginaltextbox;
	private JTextField txtRetailtextbox;
	private JTextField txtDevicetextbox;
	private JTextField txtAdditionaltextbox;
	private JTextField txtAdditionalInfo;
	private JTable table;
	private JComboBox comboBox;
	public Admin admin_person; //This is the current user who can access all the methods of itself
	private DefaultTableModel all_products; //The model used inside the all items table
	private String brands[]; //Stores all the brands that are in the item list, there are no duplicates
	private String brand_chosen;//Variable to track if a brand was chosen
	private boolean uk_layout;//Variable to track if a layout was chosen
	private static DecimalFormat pounds = new DecimalFormat("0.00");//Prints prices to 2dp

	//Launches application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {//Creates an instance of the admin window
					mainAdmin_3_GUI frame = new mainAdmin_3_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainAdmin_3_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 903, 499);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(10, 55, 572, 394);
		contentPane.add(panel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane);
		
		table = new JTable();//Creates the table used for holding all the shopping
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		Admin admin_person=Login_Menu_2_GUI.get_current_admin();
		
		brand_chosen="All Items";//Default value when window launched
		uk_layout=false;//Default value when window launched
		all_products= view_all_products(brand_chosen,uk_layout);//Creates the table model to view all products with search working
		table.setModel(all_products);//Updates table
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Keyboards with UK Layout");
		chckbxNewCheckBox_1.setBackground(new Color(204, 255, 255));
		chckbxNewCheckBox_1.setForeground(Color.RED);
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		chckbxNewCheckBox_1.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {//When the checkbox is ticked
		    	uk_layout=chckbxNewCheckBox_1.isSelected();//Updates the filter of UK Layout
		    	all_products= view_all_products(brand_chosen,uk_layout);//Recreates the model
				table.setModel(all_products);//Updates table
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
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addGap(35)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
							.addComponent(chckbxNewCheckBox_1)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxNewCheckBox_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {//When a new brand is chosen
		    	brand_chosen=comboBox.getSelectedItem().toString(); //Updates the variable of brand chosen
		    	all_products= view_all_products(brand_chosen,uk_layout); //Recreates model
				table.setModel(all_products); //Updates the table
		    }
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 255));
		panel_1.setBounds(592, 55, 285, 394);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Item To Stock");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//When add item to stock is clicked
				//VALIDATE
				try {
					String barcode=txtBarcodetextbox.getText();   //6 Digit String E.g.004635
					String name =txtNametextbox.getText();		//Mouse OR Keyboard
					String brand=txtBrandtextbox.getText();		//String
					String colour=txtColourtextbox.getText();	//String
					String connectivity=txtConnectivity_1.getText();	//Wired or Wireless
					int quantity=Integer.parseInt(txtQuantitytextbox.getText()); //int of stock bigger than 0
					float original_cost=Float.parseFloat(txtOriginaltextbox.getText()); //float to 2 dp or less
					float retail_cost=Float.parseFloat(txtRetailtextbox.getText()); 	//float to 2 dp or less
					name=name.toLowerCase();//Validates name input
					if (name.equals("mouse")) { 
						String type=txtDevicetextbox.getText(); //Standard or gaming
						int no_of_buttons=Integer.parseInt(txtAdditionaltextbox.getText()); //int bigger than -1
						Mouse new_mouse = new Mouse (barcode, type, brand, colour, connectivity, quantity, retail_cost, original_cost, no_of_buttons); 
						boolean validated=admin_person.add_to_stock(new_mouse);//Gives the new instance to admin to add to stock and checks it's attributes
						if (validated){
							//Refreshes the all items list
							all_products= view_all_products(brand_chosen,uk_layout);
							table.setModel(all_products);
							//Confirmation message
							JOptionPane.showMessageDialog(null, "DONE");
						}
						else{
							
							JOptionPane.showMessageDialog(null, "Invalid Inputs");
						}
					}
					else if (name.equals("keyboard")) {
						String type=txtDevicetextbox.getText(); //Standard or Internet or gaming or flexible
						String layout=txtAdditionaltextbox.getText(); //US or UK layout
						Keyboard new_keyboard= new Keyboard (barcode, type, brand, colour, connectivity, quantity, retail_cost, original_cost, layout);
						boolean validated=admin_person.add_to_stock(new_keyboard);//Gives the new instance to admin to add to stock and checks it's attributes
						if (validated){
							//Refreshes the all items list
							all_products= view_all_products(brand_chosen,uk_layout);
							table.setModel(all_products);
							//Confirmation message
							JOptionPane.showMessageDialog(null, "DONE");
						}
						else{
							JOptionPane.showMessageDialog(null, "Invalid Inputs");
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Is the name \"mouse\" or \"keyboard\"? ");
					}
					
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Invalid Inputs"); //Ensures the admin has inputted correct info
				}
				
			}
		});
		btnNewButton.setForeground(Color.BLUE); //Below I initialise most items of the window, mostly the textboxes
		btnNewButton.setBounds(62, 349, 174, 16);
		panel_1.add(btnNewButton);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(204, 255, 255));
		panel_4.setBounds(0, 0, 299, 338);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		txtBarcode = new JTextField();
		txtBarcode.setText("Barcode");
		txtBarcode.setEditable(false);
		txtBarcode.setBounds(10, 11, 98, 20);
		panel_4.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setEditable(false);
		txtName.setBounds(10, 42, 98, 20);
		panel_4.add(txtName);
		txtName.setColumns(10);
		
		txtBrand = new JTextField();
		txtBrand.setText("Brand");
		txtBrand.setEditable(false);
		txtBrand.setBounds(10, 73, 98, 20);
		panel_4.add(txtBrand);
		txtBrand.setColumns(10);
		
		txtColour = new JTextField();
		txtColour.setText("Colour");
		txtColour.setEditable(false);
		txtColour.setBounds(10, 104, 98, 20);
		panel_4.add(txtColour);
		txtColour.setColumns(10);
		
		txtConnectivity = new JTextField();
		txtConnectivity.setText("Connectivity");
		txtConnectivity.setEditable(false);
		txtConnectivity.setBounds(10, 135, 98, 20);
		panel_4.add(txtConnectivity);
		txtConnectivity.setColumns(10);
		
		txtQuanity = new JTextField();
		txtQuanity.setText("Quanity");
		txtQuanity.setEditable(false);
		txtQuanity.setBounds(10, 168, 98, 20);
		panel_4.add(txtQuanity);
		txtQuanity.setColumns(10);
		
		txtOriginalPrice = new JTextField();
		txtOriginalPrice.setText("Original Cost");
		txtOriginalPrice.setEditable(false);
		txtOriginalPrice.setBounds(10, 199, 98, 20);
		panel_4.add(txtOriginalPrice);
		txtOriginalPrice.setColumns(10);
		
		txtRetail = new JTextField();
		txtRetail.setText("Retail Cost");
		txtRetail.setEditable(false);
		txtRetail.setBounds(10, 231, 98, 20);
		panel_4.add(txtRetail);
		txtRetail.setColumns(10);
		
		txtDeviceType = new JTextField();
		txtDeviceType.setText("Device Type");
		txtDeviceType.setEditable(false);
		txtDeviceType.setBounds(10, 262, 98, 20);
		panel_4.add(txtDeviceType);
		txtDeviceType.setColumns(10);
		
		txtBarcodetextbox = new JTextField();
		txtBarcodetextbox.setBounds(118, 11, 159, 20);
		panel_4.add(txtBarcodetextbox);
		txtBarcodetextbox.setColumns(10);
		
		txtNametextbox = new JTextField();
		txtNametextbox.setBounds(118, 42, 159, 20); 
		panel_4.add(txtNametextbox);
		txtNametextbox.setColumns(10);
		
		txtBrandtextbox = new JTextField();
		txtBrandtextbox.setBounds(118, 73, 159, 20);
		panel_4.add(txtBrandtextbox);
		txtBrandtextbox.setColumns(10);
		
		txtColourtextbox = new JTextField();
		txtColourtextbox.setBounds(118, 104, 159, 20);
		panel_4.add(txtColourtextbox);
		txtColourtextbox.setColumns(10);
		
		txtConnectivity_1 = new JTextField();
		txtConnectivity_1.setBounds(118, 135, 159, 20);
		panel_4.add(txtConnectivity_1);
		txtConnectivity_1.setColumns(10);
		
		txtQuantitytextbox = new JTextField();
		txtQuantitytextbox.setBounds(118, 168, 159, 20);
		panel_4.add(txtQuantitytextbox);
		txtQuantitytextbox.setColumns(10);
		
		txtOriginaltextbox = new JTextField();
		txtOriginaltextbox.setBounds(118, 199, 159, 20);
		panel_4.add(txtOriginaltextbox);
		txtOriginaltextbox.setColumns(10);
		
		txtRetailtextbox = new JTextField();
		txtRetailtextbox.setBounds(118, 231, 159, 20);
		panel_4.add(txtRetailtextbox);
		txtRetailtextbox.setColumns(10);
		
		txtDevicetextbox = new JTextField();
		txtDevicetextbox.setBounds(118, 262, 159, 20);
		panel_4.add(txtDevicetextbox);
		txtDevicetextbox.setColumns(10);
		
		txtAdditionaltextbox = new JTextField();
		txtAdditionaltextbox.setBounds(118, 293, 159, 22);
		panel_4.add(txtAdditionaltextbox);
		txtAdditionaltextbox.setColumns(10);
		
		txtAdditionalInfo = new JTextField();
		txtAdditionalInfo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtAdditionalInfo.setText("Additional Info");
		txtAdditionalInfo.setEditable(false);
		txtAdditionalInfo.setBounds(10, 293, 98, 20);
		panel_4.add(txtAdditionalInfo);
		txtAdditionalInfo.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 255, 255));
		panel_2.setBounds(10, 11, 867, 33);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Hi, view items & add to stock");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainAdmin_3_GUI.this.dispose(); //Destroys window and sends user back to login screen
				Login_Menu_2_GUI login_page=new Login_Menu_2_GUI();
				login_page.setVisible(true);
			}
		});
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		String username=admin_person.get_username();
		JLabel lblNewLabel_1 = new JLabel("Account: Admin "+username); //Includes username in title of window
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(4)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(btnNewButton_1)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
	}
	
	
	public DefaultTableModel view_all_products(String brand_chosen, Boolean UK_layout) { //Ran when there is a change in the page (filter or stock update)
		all_products= new DefaultTableModel(); //Creates a fresh model
		table.setDefaultEditor(Object.class, null);//Make table not editable
		all_products.setColumnIdentifiers(new Object[] {"Barcode", "Name", "Brand", "Colour", "Connectivity", "Quanitity", "Original Cost", "Retail Cost", "Device Type", "Additional Info (No.Buttons/Layout)"});
		all_products.setRowCount(0); //Recreates columns and sets row at the start
		Product[] all_items=admin_person.view_products(); //Pulls a list of all the items in the stock file as Product list
		brands=new String[all_items.length]; //Creates a list of brands that will hold the unique values
		brands[0]="All Items";
		int i=1;
		for (Product item : all_items) {//Gets information about every item
			String barcode=item.get_barcode();
			String brand=item.get_brand();
			String colour=item.get_colour();
			String connectivity=item.get_connectivity();
			int quantity=item.get_stock();
			float retail_cost=item.get_retail_cost();
			float original_cost=item.get_original_cost();
			String retail_cost_string=pounds.format(retail_cost);//Formats the price to 2dp
			String original_cost_string=pounds.format(original_cost);//Formats the price to 2dp
			//Checks if a brand has already been added
			Boolean unique_element=true;
			for (String brand_name : brands) {
				if (brand_name!=null) {//When it reaches end of list it stops
					if (brand_name.equals(brand)){
						unique_element=false; //Tells the next if statement that the item is already added
						break;
					}
				}
			}//If the brand has not been added, it gets added
			if (unique_element==true) {
				brands[i]=brand;
				i++;//Calculates how many unique brands there are
			}
			if (brand_chosen.equals("All Items")) {//This checks if a filter is on
				if (UK_layout.equals(false)) { // WHEN A BRAND IS NOT CHOSEN AND LAYOUT NOT CHOSEN
					if (item instanceof Mouse) {
						Mouse mouse_product = (Mouse) item; //Casts the product into a mouse
						String mouse_type=mouse_product.get_mouse_type(); 
						int button_num=mouse_product.get_button_num(); //Pulls the extra info of a mouse and creates the object for the table model
						Object[] item_info=new Object[] {barcode,"mouse",brand,colour,connectivity,quantity,retail_cost_string,original_cost_string,mouse_type,button_num};
						all_products.addRow(item_info); //Adds the new object to the table
					}
					else if (item instanceof Keyboard) {
						Keyboard keyboard_product = (Keyboard) item;//Casts the product into a keyboard
						String keyboard_type=keyboard_product.get_keyboard_type();
						String keyboard_layout=keyboard_product.get_keyboard_layout();//Pulls the extra info of a keyboard and adds the object to the table model
						Object[] item_info=new Object[] {barcode,"keyboard",brand,colour,connectivity,quantity,retail_cost_string,original_cost_string,keyboard_type,keyboard_layout};
						all_products.addRow(item_info);
					}
				}
				else {// WHEN A BRAND IS NOT CHOSEN AND LAYOUT IS CHOSEN
					if (item instanceof Keyboard) {
						Keyboard keyboard_product = (Keyboard) item;
						String keyboard_type=keyboard_product.get_keyboard_type();
						String keyboard_layout=keyboard_product.get_keyboard_layout();
						if (keyboard_layout.contentEquals("UK")) {
							Object[] item_info=new Object[] {barcode,"keyboard",brand,colour,connectivity,quantity,retail_cost_string,original_cost_string,keyboard_type,keyboard_layout};
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
							Object[] item_info=new Object[] {barcode,"mouse",brand,colour,connectivity,quantity,retail_cost_string,original_cost_string,mouse_type,button_num};
							all_products.addRow(item_info);
						}
						else if (item instanceof Keyboard) {
							Keyboard keyboard_product = (Keyboard) item;
							String keyboard_type=keyboard_product.get_keyboard_type();
							String keyboard_layout=keyboard_product.get_keyboard_layout();
							Object[] item_info=new Object[] {barcode,"keyboard",brand,colour,connectivity,quantity,retail_cost_string,original_cost_string,keyboard_type,keyboard_layout};
							all_products.addRow(item_info);
						}
					}
					else {// WHEN A BRAND IS CHOSEN AND LAYOUT IS CHOSEN
						if (item instanceof Keyboard) {
							Keyboard keyboard_product = (Keyboard) item;
							String keyboard_type=keyboard_product.get_keyboard_type();
							String keyboard_layout=keyboard_product.get_keyboard_layout();
							if (keyboard_layout.contentEquals("UK")) {
								Object[] item_info=new Object[] {barcode,"keyboard",brand,colour,connectivity,quantity,retail_cost_string,original_cost_string,keyboard_type,keyboard_layout};
								all_products.addRow(item_info);
							}
						}
					}
				}
			}
		}
		String [] temp=new String[i]; //This will hold the actual list of brands without null spaces
		for (int x = 0; x < i; x++) {//Goes through the brand list until it reaches a null space
			temp[x]=brands[x];
		}
		brands=temp;//Transfers all the brands across
		return all_products;//Sends back the new table model with updated stock/ filter added
	}
}