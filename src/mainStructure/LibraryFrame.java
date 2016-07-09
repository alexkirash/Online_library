package mainStructure;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Container;
/**
 * This frame draws the main window provides access to user registration,
 * user authentication and authorization admin. 
 * @author Kirash
 *
 */
public class LibraryFrame extends JFrame implements ActionListener{
	
	private JButton button1;
	private JButton button2;
	private JButton button3;
	/**
	 * This constructor creates an initial panel applications.
	 * @param title
	 */	
	public LibraryFrame(String title){
		super (title);

	        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	         
	        JPanel panel = new JPanel();
	        panel.setLayout (new BoxLayout(panel, BoxLayout.Y_AXIS));
  
	        
	        Font font = new Font ("Verdana", Font.PLAIN, 14);
	        
	        setContentPane(new ImagePanel());
	        Container cont = getContentPane();
	        
	        button1 = new JButton ("User");
	        panel.add(button1);
	        button1.setMaximumSize(new Dimension(250, 150));
	        button1.setFocusable(false);
	        button1.setFont(font);
	        
	        button2 = new JButton ("Registration");
	        panel.add(button2);
	        button2.setMaximumSize(new Dimension(250, 150));
	        button2.setFocusable(false);
	        button2.setFont(font);
	        
	        button3 = new JButton ("Admin");
	        panel.add(button3);
	        button3.setMaximumSize(new Dimension(250, 150));
	        button3.setFocusable(false);
	        button3.setFont(font);
	        
	        cont.add(button1);
	        cont.add(button2);
	        cont.add(button3);
	        
	        getContentPane().add(panel); 
	        setPreferredSize(new Dimension(340, 220));
	        pack();
	        setLocationRelativeTo(null);
	        setVisible(true);
	        setResizable(false);
	        
	        button1.addActionListener(this);
	        button2.addActionListener(this);
	        button3.addActionListener(this);
	        
	}
/**
 * Event handler responds to the button and depending on the press fills the form 
 */
	        public void actionPerformed(ActionEvent e) {
            	Object src = e.getSource();
            	if (src== button1){    

	                 UserFrame frame2 = new UserFrame("User");
	                 frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                 frame2.setSize(250,200);
	                 frame2.setVisible(true);
	                 this.dispose();
            	}else if (src==button2){

	                 RegistrationFrame frame2 = new RegistrationFrame ("Registration");
	                 frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                 frame2.setSize(250,250);  
	                 frame2.setVisible(true);
	                 this.dispose();
            	}else if (src==button3){

	                 AdminFrame frame2 = new AdminFrame("Admin");
	                 frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                 frame2.setSize(250,200);  
	                 frame2.setVisible(true);
	                 this.dispose();
	            }
	        	
	}
}

