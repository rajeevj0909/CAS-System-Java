package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CAS_System.Admin;
import CAS_System.Customer;
import CAS_System.File_Manager;
import CAS_System.User;

import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Scrollbar;
import java.awt.Window;
import java.awt.ScrollPane;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Login_Menu_2_GUI extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel account_data;
	public static String user_chosen;

	//Launches application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {//Creates instance of Login page
					Login_Menu_2_GUI frame = new Login_Menu_2_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Creates the frame
	public Login_Menu_2_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Creates a jTable model for the data to add to it
		account_data= new DefaultTableModel();
		account_data.setColumnIdentifiers(new Object[] {"Username", "Account Type"});//Column headers for model
		account_data.setRowCount(0);
		User [] accounts_list=File_Manager.get_users();//Fills model with all the users
		for (User person : accounts_list) {
			String username=person.get_username();
			if (person instanceof Admin ) {
				Object[] login_list=new Object[] {username, "Admin"};
				account_data.addRow(login_list);
			}
			else if (person instanceof Customer ) {
				Object[] login_list=new Object[] {username, "Customer"};
				account_data.addRow(login_list);
			}
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 98, 463, 274);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 752, 76);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Please Login");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
		
		JButton btnNewButton_1 = new JButton("QUIT");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login_Menu_2_GUI.this.dispose();//Closes system, this is the only exit point in the whole system
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 551, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(471, 98, 291, 274);
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setDefaultEditor(Object.class, null);//Make table not editable
		table.setModel(account_data);
		
		JButton btnNewButton = new JButton("Login");
		panel_2.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow()==-1) {//If there is no account selected
					JOptionPane.showMessageDialog(table, "Please choose an account to login with");
				}
				else {//If there is an account selected
					Login_Menu_2_GUI.this.dispose();
					int row_number=(table.getSelectedRow());
					Object user=(table.getValueAt(row_number,0 ));
					String user_chosen=user.toString();//Get the unique username of the user
					set_current_user(user_chosen);
					Object person_type=(table.getValueAt(row_number,1 ));//Gets the user type to find which window to open
					String type_chosen=person_type.toString();
					if (type_chosen=="Admin") {//Open admin page if admin
						mainAdmin_3_GUI main_page=new mainAdmin_3_GUI();
						main_page.setVisible(true);
					}
					else if (type_chosen=="Customer") {//Open Customer page if Customer
						Customer c=get_current_customer();
						mainCustomer_3_GUI main_page=new mainCustomer_3_GUI();
						main_page.setVisible(true);						
					}
				}
			}
		});
	}
	
	public static Admin get_current_admin() {//Ran by admin page to get the user logging in
		User [] accounts_list=File_Manager.get_users();//Pulls all the Users from the file
		for (User person : accounts_list) {
			String username=person.get_username();
			if (username.equals(user_chosen)) {
				Admin admin_user = (Admin) person;//Casts the user into an admin
				return(admin_user);
			}
		}
		return null;
	}
	
	public static Customer get_current_customer() {//Ran by customer page to get the user logging in
		User [] accounts_list=File_Manager.get_users();//Pulls all the Users from the file
		for (User person : accounts_list) {
			String username=person.get_username();
			if (username.equals(user_chosen)) {
				Customer customer_user = (Customer) person;//Casts the user into a customer
				return(customer_user);
			}
		}
		return null;
	}
	
	public void set_current_user(String new_user_chosen) {
		this.user_chosen=new_user_chosen; //When the login button is clicked, a user is chosen
	}
}