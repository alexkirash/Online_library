package tableBooks;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import connect.*;
import mainStructure.*;
import tableUser.*;	 
import user.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Window to display the results upon request.
 *  Display books on a given category and receives data about the user who chose the book.
 * @author Kirash
 *
 */
public class DataBooksFrame extends JFrame implements ActionListener {
	 

	    static int i = 0;
	    private String currentUser;
	    private JButton subscription;
	    private JButton readingRoom;
	    private JButton back;
	    private JTextArea display;
	    private ArrayList<Books> books = new ArrayList<Books>();
	    private int currentSelectedRow;
	    static final long serialVersionUID = 2L;
	    private boolean check;
/**
 * Drawing window display the results table.
 * Receives as parameters the book to be searched and the current user.
 * @param book
 * @param currentUser
 */	 
	    public DataBooksFrame ( String currentUser, String category, boolean check){
	    	
	    	
	        super("Book search");
	        this.check = check;
	        this.currentUser = currentUser;	  	
	   
	        	
       	try{
       		ConnectDB cdb = new ConnectDB();
       		BooksDBDAO tdb = new BooksDBDAO(cdb);
       		books = tdb.showAll(category);
       		tdb.closeConnection();
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
       
       	if(books.size() != 0){
       	TableModel model = new TableModelBooks(books);
       	JTable table = new JTable(model);
       	table.setSelectionForeground(Color.BLUE);
       	
       	ListSelectionModel lm = new DefaultListSelectionModel();
           lm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
           table.setSelectionModel(lm);
           ListSelectionModel selModel = table.getSelectionModel();
           
           selModel.addListSelectionListener(new ListSelectionListener() {               
                public void valueChanged(ListSelectionEvent e) {
                     
                     currentSelectedRow = table.getSelectedRow();                    
                }               
           });
	        
	        subscription = new JButton("Subscription");
	        readingRoom = new JButton("Reading room");
	        back = new JButton("Back");
	        
	        JPanel panel = new JPanel();
	        TitledBorder tb = new TitledBorder("Actions");
	        panel.setBorder(tb);
	        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	        panel.add(Box.createHorizontalGlue());
	        subscription.setFocusable(false);
	        panel.add(subscription);
	        panel.add(Box.createHorizontalStrut(20));
      
	        readingRoom.setPreferredSize(subscription.getPreferredSize());
	        readingRoom.setFocusable(false);
	        readingRoom.setAlignmentY(Component.CENTER_ALIGNMENT);
	        panel.add( readingRoom);
	        panel.add(Box.createHorizontalStrut(20));
	        
	        back.setPreferredSize( readingRoom.getPreferredSize());
	        back.setFocusable(false);
	        panel.add(back);
	        panel.add(Box.createVerticalStrut(20));
	        
	        display = new JTextArea("Hello, dear friend! This is your display!");
	        display.setEditable(false);
	        JPanel panel2 = new JPanel();
	        TitledBorder tb2 = new TitledBorder("Actions:");
	        panel2.setBorder(tb2);
	        panel2.add(display);
	       
	        JPanel greatPanel = new JPanel();
	        greatPanel.setLayout(new BorderLayout());
	        greatPanel.add(new JScrollPane(table), BorderLayout.CENTER);
	        greatPanel.add(panel, BorderLayout.SOUTH);
	        greatPanel.add(panel2, BorderLayout.EAST);
	        add(greatPanel); 
	        
	        setPreferredSize(new Dimension(800, 500));
	        pack();
	        setVisible(true);
	        setLocationRelativeTo(null);
	        
	        subscription.addActionListener(this);
	        readingRoom.addActionListener(this);
	        back.addActionListener(this);
	    }}
/**
 * Processing of the user's choice.
 */	    
	    public void actionPerformed(ActionEvent e) {

			Object src = e.getSource();
			
			if(src == subscription){
				DataUser user= new DataUser();
				try {
					ConnectDB connect = new ConnectDB();
					UserDBDAO pdb = new UserDBDAO(connect);
					user = pdb.retUser(currentUser);
					pdb.closeConnection();
				} catch (SQLException sql) {
					JOptionPane.showConfirmDialog(null,
							"Error connecting to database",
							"Sorry just try again!", JOptionPane.YES_OPTION);
					sql.printStackTrace();
				} catch (ClassNotFoundException cnfe) {
					JOptionPane.showConfirmDialog(null,
							"Cannot find db driver classes",
							"Error with database connect",
							JOptionPane.OK_OPTION);
				}
				
				Books currentBook = books.get(currentSelectedRow);
				display.setText("You choose book - " + currentBook.getName() + 
						" \nYear of publication - " + currentBook.getYear() +
						" \nAuthor - " + currentBook.getAuthor() + 
						"\nSection - " + currentBook.getCategory() +  
						"\n\nYour account registered by:\nName: - " + user.getName() + 
						"\nSurname - " + user.getSurName() + "\nType of order - Subscription" +
						"\n\nPlease go to the librarian, she will give you a book.");
					
				 	Calendar calendar = new GregorianCalendar();
			        SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.yyyy");
			        String dateToday = formattedDate.format(calendar.getTime());
			        
			UserTookBook took = new UserTookBook (1, user.getLogin(), currentBook.getName(), dateToday);
				boolean error = false;
			try {
				ConnectDB connect = new ConnectDB();
				UserTableDBDAO pdb = new UserTableDBDAO(connect);
				pdb.write(took);
				pdb.closeConnection();
			} catch (SQLException sql) {
				JOptionPane.showConfirmDialog(null,
						"Error connecting to database",
						"Sorry just try again!", JOptionPane.YES_OPTION);
				error = true;
			} catch (ClassNotFoundException cnfe) {
				JOptionPane.showConfirmDialog(null,
						"Cannot find db driver classes",
						"Error with database connect",
						JOptionPane.OK_OPTION);
				error = true;
				
			}
			if (!error){
				JOptionPane.showConfirmDialog(null,
						"You have successfully entered into the databases",
						"successful operation",
						JOptionPane.OK_OPTION);
			}
			else {
				JOptionPane.showConfirmDialog(null,
						"You do not have successfully entered into the databases",
						"Not successful operation",
						JOptionPane.OK_OPTION);
			}
			
			}
			else if(src ==  readingRoom){
				DataUser user= new DataUser();
				try {
					ConnectDB connect = new ConnectDB();
					UserDBDAO pdb = new UserDBDAO(connect);
					user = pdb.retUser(currentUser);
					pdb.closeConnection();
				} catch (SQLException sql) {
					JOptionPane.showConfirmDialog(null,
							"Error connecting to database",
							"Sorry just try again!", JOptionPane.YES_OPTION);
					sql.printStackTrace();
				} catch (ClassNotFoundException cnfe) {
					JOptionPane.showConfirmDialog(null,
							"Cannot find db driver classes",
							"Error with database connect",
							JOptionPane.OK_OPTION);
				}
				
				Books currentBook = books.get(currentSelectedRow);
				display.setText("You choose book - " + currentBook.getName() + 
						" \nYear of publication - " + currentBook.getYear() +
						" \nAuthor -  " + currentBook.getAuthor() + 
						"\nSection -  " + currentBook.getCategory() +  
						"\n\nYour account registered by:\nName: - " + user.getName() + 
						"\nSurname - " + user.getSurName() + "\nType of order - Reading Room" +
						"\n\nPlease go to the librarian, she will give you a book.");
			}
			
			else if(src == back){
				 CatalogFrame frame = new CatalogFrame  ("Catalog", check, currentUser);
				 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             frame.setSize(250,300);   
	             frame.setVisible(true);
				dispose();

			}
	    }
		  
}
	

