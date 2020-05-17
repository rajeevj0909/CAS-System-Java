package GUI;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CAS_System.Customer;
import CAS_System.File_Manager;
import CAS_System.Payment;
import CAS_System.Product;

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
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class Transaction_4_GUI extends JFrame {
	private JPanel contentPane;
	private JTextField txtCardNumber;
	private JTextField txtCvc;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField txtEmailAddress;
	private JTextField textField_1;
	private JButton btnPay;
	private JTextField txtPaypal;
	private JTextField txtDebitCreditCard;
	public Customer customer_person; //Gets the info about the user like their shopping list
	public float total; //Keeps track of the user's total amount to pay
	private static DecimalFormat pounds = new DecimalFormat("0.00");//Prints total as normal price

	//Launches application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transaction_4_GUI frame = new Transaction_4_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Creates the frame
	public Transaction_4_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 425);
		
		Product[] shopping_products= Customer.get_shopping();//Gets the user's shopping list and goes through it to find the total
		for (Product item : shopping_products) {
			float retail_cost=item.get_retail_cost();
			int quantity=item.get_stock();
			total=total+ (retail_cost*quantity); //Calculates the user's total by adding all the retail costs multiplied by the quantity
		}
		String total_string=pounds.format(total);//Converts total to 2dp
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(10, 55, 309, 300);
		contentPane.add(panel);
		
		txtEmailAddress = new JTextField();
		txtEmailAddress.setText("Email Address:");
		txtEmailAddress.setEditable(false);
		txtEmailAddress.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//If the Paypal button is clicked
				//Gets email from text box and validates it
				String email=textField_1.getText();
				Boolean validated=Payment.pay_by_paypal(email);//Checks if the email is valid
				if	(validated){ 
					customer_person.buy_now("paypal");//Updates the customers details
					Transaction_4_GUI.this.dispose();//Shows a confirmation message and sends user back to login page
					JOptionPane.showMessageDialog(null, "Paid £"+total_string+"  via PayPal    \nSee you soon");
					Login_Menu_2_GUI login_page=new Login_Menu_2_GUI();
					login_page.setVisible(true);
				}
				else {//If not validated, displays error
					JOptionPane.showMessageDialog(null, "I need a valid email");
				}
			}
		});
		btnPay.setForeground(Color.BLUE);
		
		txtPaypal = new JTextField();
		txtPaypal.setEditable(false);
		txtPaypal.setText("PayPal");
		txtPaypal.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtEmailAddress, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(68, Short.MAX_VALUE)
					.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGap(67))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(111)
					.addComponent(txtPaypal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(112, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(30)
					.addComponent(txtPaypal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEmailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
					.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(88))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 255));
		panel_1.setBounds(329, 55, 295, 300);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(204, 255, 255));
		panel_4.setBounds(0, 0, 299, 289);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		txtCardNumber = new JTextField();
		txtCardNumber.setText("Card Number");
		txtCardNumber.setEditable(false);
		txtCardNumber.setBounds(22, 73, 86, 20);
		panel_4.add(txtCardNumber);
		txtCardNumber.setColumns(10);
		
		txtCvc = new JTextField();
		txtCvc.setText("CVC");
		txtCvc.setEditable(false);
		txtCvc.setBounds(22, 104, 86, 20);
		panel_4.add(txtCvc);
		txtCvc.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setBounds(118, 73, 159, 20);
		panel_4.add(textField_11);
		textField_11.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setBounds(118, 104, 159, 20);
		panel_4.add(textField_12);
		textField_12.setColumns(10);
		
		JButton btnNewButton = new JButton("Pay");
		btnNewButton.setBounds(70, 198, 174, 16);
		panel_4.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//If the Card pay button is clicked
				//VALIDATE Card
				String card_number=textField_11.getText();
				String cvc=textField_12.getText();
				Boolean validated=Payment.pay_by_card(card_number,cvc);//Checks if the card details are validated
				if	(validated){ 
					customer_person.buy_now("Credit Card");//Updates the customers details
					Transaction_4_GUI.this.dispose();//Shows a confirmation message and sends user back to login page
					JOptionPane.showMessageDialog(null, "Paid £"+total_string+"   via Card    \nSee you soon");
					Login_Menu_2_GUI login_page=new Login_Menu_2_GUI();
					login_page.setVisible(true);
				}
				else {//If not validated, displays error
					//Uses Luhn Formula for validation
					JOptionPane.showMessageDialog(null, "I need a real valid card number\nE.g. \"4556737586899855\"  and \"123\" ");
				}
			}
		});
		btnNewButton.setForeground(Color.BLUE);
		
		txtDebitCreditCard = new JTextField();
		txtDebitCreditCard.setEditable(false);
		txtDebitCreditCard.setText("Debit/ Credit Card");
		txtDebitCreditCard.setBounds(70, 25, 134, 20);
		panel_4.add(txtDebitCreditCard);
		txtDebitCreditCard.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 255, 255));
		panel_2.setBounds(10, 11, 614, 33);
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Payment    TOTAL=£"+total_string);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("  Pay via card or Paypal?   ");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_1);
	}

	public void set_user(Customer current_user) {//This is set by the previous page so that when it loads, the user's info is already here
		customer_person=current_user;
	}
}