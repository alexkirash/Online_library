package mainStructure;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import tableUser.*;
/**
 * Class for function selection admin.
 * @author Kirash
 *
 */
	public class AdminFunctions extends JFrame implements ActionListener{

		private String currentUser;
		private JPanel windowContent = new JPanel();
		private JButton button1;
		private JButton button2;
		private JButton button3;
/**
 * Constructor to initialize the data 
 * @param title
 * @param currentUser
 */
		public AdminFunctions (String title,  String currentUser) {
			super(title);
			this.currentUser = currentUser;
			
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	        windowContent.setLayout(null);   
	        windowContent.setLayout(new BoxLayout(windowContent, BoxLayout.Y_AXIS));
	        Font font = new Font("Verdana", Font.BOLD, 11);
	        windowContent.setLayout(null);
	        
	        JLabel titleLabel = new JLabel("Select an action");
	        titleLabel.setBounds(110, 20, 150, 21);
			windowContent.add(titleLabel);		
			titleLabel.setFont(font);
					
			
			 	button1 = new JButton("Catalog books");		 	
			 	button1.setBounds(20, 70, 130, 30);
				windowContent.add(button1);	        
		        button1.setFocusable(false);
		        button1.setFont(font);
		        
		        button2 = new JButton("Catalog users");	        
		        button2.setBounds(170, 70, 130, 30);
				windowContent.add(button2);
		        button2.setFocusable(false);
		        button2.setFont(font);
		        
		        button3 = new JButton("Back");	        
		        button3.setBounds(120, 140, 80, 30);
				windowContent.add(button3);
		        button3.setFocusable(false);
		        button3.setFont(font);
		        
		        add(windowContent);
		        setPreferredSize(new Dimension(330, 220));
		        pack();
		        setLocationRelativeTo(null);
		        setVisible(true);
		        setDefaultCloseOperation(EXIT_ON_CLOSE);

				button1.addActionListener(this);
				button2.addActionListener(this);
				button3.addActionListener(this);
}
/**
 * Event handler responds to the button and depending on the press fills the form.
 */
		public void actionPerformed(ActionEvent e) {

			Object src = e.getSource();
			if (src == button1) {
				 CatalogFrame frame = new CatalogFrame  ("Catalog", true, currentUser);
	             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             frame.setSize(250,300);   
	             frame.setVisible(true);
	             this.dispose();
			}
			else if (src == button2){
				DataUserFrame frame2 = new DataUserFrame (currentUser, new ArrayList<UserTookBook> (), false );
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setSize(700,500);
                frame2.setVisible(true);
                this.dispose();
			}
			else if (src == button3){
				 AdminFrame frame = new AdminFrame ("Admin");
	             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             frame.setSize(250,200);   
	             frame.setVisible(true);
	             this.dispose();
			}
			}}