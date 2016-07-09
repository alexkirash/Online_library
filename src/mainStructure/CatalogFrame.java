package mainStructure;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tableBooks.*;
/**
 * Class that implements the selection of books for certain categories.
 * @author Kirash
 */
public class CatalogFrame extends JFrame implements ActionListener {
	
			private String currentUser;
			private JButton button1;
			private JButton button2;
			private JButton button3;
			private JButton button4;
			private JButton button5;
			private JButton button6;
			private JButton button7;
			
			private boolean check;
	/**
	 * Constructor to initialize the data. 
	 * @param title
	 * @param check
	 * @param currentUser
	 */
			public CatalogFrame(String title, boolean check, String currentUser )  {
		 	super(title);
		 	
		 	this.check = check;
		 	this.currentUser = currentUser;
	      
		 	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	         
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        Font font = new Font("Verdana", Font.BOLD, 10);
	        
	        button1 = new JButton("Science");
	        panel.add(button1);
	        button1.setMaximumSize(new Dimension(250, 100));
	        button1.setFocusable(false);
	        button1.setFont(font);
	        
	        
	        button2 = new JButton("History");
	        panel.add(button2);
	        button2.setMaximumSize(new Dimension(250, 100));
	        button2.setFocusable(false);
	        button2.setFont(font);
	 
	        button3 = new JButton("Poetry");
	        panel.add(button3);
	        button3.setMaximumSize(new Dimension(250, 100));
	        button3.setFocusable(false);
	        button3.setFont(font);
	        
	        button4 = new JButton("Detective");
	        panel.add(button4);
	        button4.setMaximumSize(new Dimension(250, 100));
	        button4.setFocusable(false);
	        button4.setFont(font);
	        
	        button5 = new JButton("Prose");
	        panel.add(button5);
	        button5.setMaximumSize(new Dimension(250, 100));
	        button5.setFocusable(false);
	        button5.setFont(font);
	        
	        button6 = new JButton("Fantasy");
	        panel.add(button6);
	        button6.setMaximumSize(new Dimension(250, 100));
	        button6.setFocusable(false);
	        button6.setFont(font);
	        
	        button7 = new JButton("Back");
	        panel.add(button7);
	        button7.setMaximumSize(new Dimension(250, 100));
	        button7.setFocusable(false);
	        button7.setFont(font);
	        
	        add(panel); 
	        setPreferredSize(new Dimension(250, 220));
	        pack();
	        setLocationRelativeTo(null);
	        setVisible(true);
	        setResizable(false);
	        
	        button1.addActionListener(this);
	        button2.addActionListener(this);
	        button3.addActionListener(this);
	        button4.addActionListener(this);
	        button5.addActionListener(this);
	        button6.addActionListener(this);
	        button7.addActionListener(this);
			} 
/**
 * Event handler responds to the button and depending on the press fills the form.
 */
	            public void actionPerformed(ActionEvent e) {
	            	Object src = e.getSource();
	            	if (src== button1){
	            	 DataBooksFrame frame2 = new DataBooksFrame (currentUser, "Science", check);
	                 frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                 frame2.setSize(1000,500);
	                 frame2.setVisible(true);
	                 this.dispose();
	            	}
	            	
	            	else if (src==button2){
	            		DataBooksFrame frame2 = new DataBooksFrame (currentUser, "History", check);
		                 frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		                 frame2.setSize(1000,500);
		                 frame2.setVisible(true);
		                 this.dispose();
	            	}
	            	
	            	else if (src==button3){
	            		DataBooksFrame frame2 = new DataBooksFrame (currentUser, "Poetry", check);
		                 frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		                 frame2.setSize(1000,500);
		                 frame2.setVisible(true);
		                 this.dispose();
	            	}
	            	
	            	else if (src==button4){
	            		 DataBooksFrame frame2 = new DataBooksFrame (currentUser, "Detective", check);
		                 frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		                 frame2.setSize(1000,500);
		                 frame2.setVisible(true);
		                 this.dispose();
	            	}
	            	
	            	else if (src==button5){
	            		 DataBooksFrame frame2 = new DataBooksFrame(currentUser, "Prose", check);
		                 frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		                 frame2.setSize(1000,500); 
		                 frame2.setVisible(true);
		                 this.dispose();
	            	}
	            	
	            	else if (src==button6){
	            		  DataBooksFrame frame2 = new DataBooksFrame (currentUser, "Fantasy", check);
		                 frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		                 frame2.setSize(1000,500);   
		                 frame2.setVisible(true);
		                 this.dispose();
	            	}
	            	else if (src==button7){
	            		if (this.check == true){
	            			 AdminFunctions frame = new AdminFunctions ("Admin Panel",currentUser);
			                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			                 frame.setSize(330,220);   
			                 frame.setVisible(true);
			                 this.dispose();
	            		}
	            		else {	 
			                 UserFrame frame = new UserFrame ("User"); 
			                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			                 frame.setSize(250,200);   
			                 frame.setVisible(true);
			                 this.dispose();
	            		}
	            }
	            }}
   

 
