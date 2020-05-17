package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Window.Type;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.Box;
import java.awt.Checkbox;
import java.awt.Cursor;

public class Start_1_GUI {
	private JFrame frmCasSystem;

	//Launch window
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start_1_GUI window = new Start_1_GUI(); //Creates the window and makes visible
					window.frmCasSystem.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Start_1_GUI() { //Start content
		initialize();
	}
	
	//Initialise frame
	private void initialize() {
		frmCasSystem = new JFrame();
		frmCasSystem.setBounds(100, 100, 546, 353);
		frmCasSystem.setBackground(Color.WHITE);
		frmCasSystem.setFont(new Font("Arial", Font.PLAIN, 12));
		frmCasSystem.getContentPane().setBackground(Color.WHITE);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCasSystem.dispose(); //Start button destroys current window and opens login page
				Login_Menu_2_GUI login_page=new Login_Menu_2_GUI();
				login_page.setVisible(true);
			}
		});
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 33));
		frmCasSystem.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextPane txtpnWelcome = new JTextPane();
		txtpnWelcome.setFont(new Font("Tahoma", Font.PLAIN, 67));
		txtpnWelcome.setForeground(Color.BLUE);
		txtpnWelcome.setEditable(false);
		txtpnWelcome.setText("Welcome");//Welcome message
		frmCasSystem.getContentPane().add(txtpnWelcome);
		frmCasSystem.getContentPane().add(btnNewButton);
		frmCasSystem.setForeground(Color.WHITE);
		frmCasSystem.setIconImage(Toolkit.getDefaultToolkit().getImage(Start_1_GUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		frmCasSystem.setTitle(" Computer Accessories Shop");
	}
}