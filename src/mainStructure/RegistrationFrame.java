package mainStructure;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import connect.*;
import user.DataUser;
/**
 * Class for user registration.
 * @author Kirash
 */
public class RegistrationFrame extends JFrame implements ActionListener{

	private JPanel windowContent = new JPanel();
	private JTextField loginField;
	private JTextField nameField;
	private JTextField surnameField;
	private JPasswordField passwordField;
	private JButton button1;
	private JButton button2;
/**
 * Constructor to initialize the data 	
 * @param title
 */
	public RegistrationFrame (String title) {
		super(title);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		Font font = new Font("Verdana", Font.BOLD, 10);
        windowContent.setLayout(null);
        
		JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 21);
		windowContent.add(nameLabel);
		nameField = new JTextField(15);		
		nameLabel.setFont(font);
		
		nameField = new JTextField(15);
		nameField.setBounds(100, 20, 100, 21);
		windowContent.add(nameField);	
		nameField.setFont(font);
		
		JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setBounds(20, 50, 100, 21);
		windowContent.add(surnameLabel);
		surnameField = new JTextField(15);		
		surnameLabel.setFont(font);
		
		surnameField = new JTextField(15);
		surnameField.setBounds(100, 50, 100, 21);
		windowContent.add(surnameField);	
		surnameField.setFont(font);
		
		JLabel loginLabel = new JLabel("Login:");
        loginLabel.setBounds(20, 80, 100, 21);
		windowContent.add(loginLabel);
		loginField = new JTextField(15);		
		loginLabel.setFont(font);
		
		loginField = new JTextField(15);
		loginField.setBounds(100, 80, 100, 21);
		windowContent.add(loginField);	
		loginField.setFont(font);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(20, 110, 100, 21);
		windowContent.add(passwordLabel);
		passwordField = new JPasswordField(15);
		passwordLabel.setFont(font);	
		
		passwordField = new JPasswordField(15);
		passwordField.setBounds(100, 110, 100, 21);
		windowContent.add(passwordField);	
		passwordField.setFont(font);
		
		 	button1 = new JButton("Ok");		 	
		 	button1.setBounds(20, 150, 70, 20);
			windowContent.add(button1);	        
	        button1.setFocusable(false);
	        button1.setFont(font);
	        
	        button2 = new JButton("Back");	        
	        button2.setBounds(140, 150, 70, 20);
			windowContent.add(button2);
	        button2.setFocusable(false);
	        button2.setFont(font);
	        
	        add(windowContent);
	        setPreferredSize(new Dimension(250, 220));
	        pack();
	        setLocationRelativeTo(null);
	        setVisible(true);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);

			button1.addActionListener(this);
			button2.addActionListener(this);
		
	}
/**
 * Event handler responds to the button and depending on the press fills the form.
 */	
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();
		if (src == button1) {

			if (nameField.getText().equals("")
					|| surnameField.getText().equals("")
					|| loginField.getText().equals("")
					|| passwordField.getPassword().equals("")		 
					 ) {
				JOptionPane.showConfirmDialog(null,
						"Empty field , enter data please", "Empty field",
						JOptionPane.YES_OPTION);
			} else {
				String pass = new String(passwordField.getPassword());
				boolean check = false;
				try {
					ConnectDB connect = new ConnectDB();
					UserDBDAO pdb = new UserDBDAO(connect);
					check = pdb.readCoincidence(loginField.getText());
					pdb.closeConnection();
				} catch (SQLException sql) {
					JOptionPane.showConfirmDialog(null,
							"Error connecting to database",
							"Sorry just try again!", JOptionPane.YES_OPTION);
				} catch (ClassNotFoundException cnf) {
					JOptionPane.showConfirmDialog(null,
							"Cannot find db driver classes",
							"Error with database connect",
							JOptionPane.OK_OPTION);
				}
				if (check) {
					JOptionPane.showConfirmDialog(null,
							"This username already exists",
							"Retype login, please", JOptionPane.OK_OPTION);
				} else {
					DataUser user = new DataUser(1, loginField.getText(),
							pass, nameField.getText(), surnameField.getText());
					boolean checkException = false;
					try {
						ConnectDB connect = new ConnectDB();
						UserDBDAO pdb = new UserDBDAO(connect);
						pdb.write(user);
						pdb.closeConnection();
					} catch (SQLException sql) {
						checkException = true;
						JOptionPane.showConfirmDialog(null,
										"Error connecting to database",
										"Sorry just try again!",
										JOptionPane.YES_OPTION);
						sql.printStackTrace();
					} catch (ClassNotFoundException cnf) {
						checkException = true;
						JOptionPane.showConfirmDialog(null,
								"Cannot find db driver classes",
								"Error with database connect",
								JOptionPane.OK_OPTION);
					}
					if (!checkException) {
						JOptionPane.showConfirmDialog(null,
								"Registration is successful",
								"Successful action", JOptionPane.OK_OPTION);
						UserFrame frame2 = new UserFrame("User");
						frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						dispose();
						
					} else {
						JOptionPane.showConfirmDialog(null,
								"Registration not successful",
								"Error record in database",
								JOptionPane.OK_OPTION);
						LibraryFrame frame2 = new LibraryFrame("Library");
						frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						dispose();
					}
				}}}

			
		else if (src == button2){
    		 LibraryFrame frame = new LibraryFrame ("Library");
             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             frame.setSize(340, 220);   
             frame.setVisible(true);
             this.dispose();
		}
		
		}}	

