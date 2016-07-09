package mainStructure;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import connect.ConnectDB;
import connect.UserDBDAO;
import user.DataUser;
/**
 * Class for user authentication.
 * @author Kirash
 */
public class UserFrame extends JFrame implements ActionListener{

	private JPanel windowContent = new JPanel();
	private JTextField loginField;
	private JPasswordField passwordField;
	private JButton button1;
	private JButton button2;
/**
 * Constructor to initialize the data 
 * @param title
 */
	public UserFrame (String title) {
		super(title);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		 
        Font font = new Font("Verdana", Font.BOLD, 10);
        windowContent.setLayout(null);
        
        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setBounds(20, 20, 100, 21);
		windowContent.add(loginLabel);
		loginField = new JTextField(15);		
		loginLabel.setFont(font);
		
		loginField = new JTextField(15);
		loginField.setBounds(100, 20, 100, 21);
		windowContent.add(loginField);	
		loginField.setFont(font);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(20, 50, 100, 21);
		windowContent.add(passwordLabel);
		passwordField = new JPasswordField(15);
		passwordLabel.setFont(font);	
		
		passwordField = new JPasswordField(15);
		passwordField.setBounds(100, 50, 100, 21);
		windowContent.add(passwordField);	
		passwordField.setFont(font);
		
		 	button1 = new JButton("Ok");		 	
		 	button1.setBounds(20, 100, 70, 20);
			windowContent.add(button1);	        
	        button1.setFocusable(false);
	        button1.setFont(font);
	        
	        button2 = new JButton("Back");	        
	        button2.setBounds(140, 100, 70, 20);
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

		String pass = new String(passwordField.getPassword());

		if (loginField.getText().equals("")
				|| pass.equals("")) {
			JOptionPane.showConfirmDialog(null,
					"Empty field , enter data please", "Empty field",
					JOptionPane.OK_OPTION);
		} else {
			DataUser user = new DataUser();
			user.setLogin(loginField.getText());
			user.setPassword(pass);

			boolean check = false;
			try {
				ConnectDB connect = new ConnectDB();
				UserDBDAO pdb = new UserDBDAO (connect);
				check = pdb.checkUser(user);
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
						"Authentication is successful",
						"Successful operation", JOptionPane.OK_OPTION);
				 CatalogFrame frame = new CatalogFrame  ("Catalog", false, user.getLogin());
				 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             frame.setSize(250,300);   
	             frame.setVisible(true);
				 dispose();
			} else {
				JOptionPane.showConfirmDialog(null,
						"This user name and password is not found",
						"Not successful", JOptionPane.OK_OPTION);
				
			}
		}
		}
		else if (src == button2){
    		 LibraryFrame frame = new LibraryFrame ("Library");
             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             frame.setSize(340, 220);   
             frame.setVisible(true);
             this.dispose();
		}
		
		}}	

