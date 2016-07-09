package tableUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import connect.ConnectDB;
import connect.UserTableDBDAO;
import mainStructure.*;
/**
 * Window to display the results upon request.
 * Show information about the user who took the season-ticket book.
 * @author Kirash
 *
 */
public class DataUserFrame extends JFrame implements ActionListener {

	    static int i = 0;
	    private String currentUser;
	    private JButton blacklist;
	    private JButton delete;	
	    private JButton show;	
	    private JButton update;
	    private JButton showBlacklist;
	    private JButton back;
	    private boolean whatUpdate;

	    private ArrayList<UserTookBook> date = new ArrayList<UserTookBook>();
	    private int currentSelectedRow;
	    static final long serialVersionUID = 2L;
		 
/**
 *  Drawing window display the results table.
 * Searches for information about the user chosen book and the current date.
 * @param currentUser
 * @param date
 * @param whatUpdate
 */
	    public DataUserFrame (String currentUser, ArrayList<UserTookBook> date, boolean whatUpdate){
	    	
	        super("User table");
	        this.currentUser = currentUser;	
	        this.date = date;
	        this.whatUpdate = whatUpdate;
      	
       	TableModel model = new TableModelUser(this.date);
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
       	
           	blacklist = new JButton("Blacklist");
           	delete = new JButton("Delete");
           	update = new JButton("Update");
           	show = new JButton("Show all");
	        back = new JButton("Back");
	        showBlacklist = new JButton("ShowBlacklist");
	        
	        JPanel panel = new JPanel();
	        TitledBorder tb = new TitledBorder("Actions");
	        panel.setBorder(tb);
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        blacklist.setFocusable(false);
	        show.setFocusable(false);
	        delete.setFocusable(false);
	        update.setFocusable(false);
	        showBlacklist.setFocusable(false);
	        
	        show.setPreferredSize(showBlacklist.getPreferredSize());
	        blacklist.setPreferredSize(show.getPreferredSize());
	        update.setPreferredSize(blacklist.getPreferredSize());
	        delete.setPreferredSize(update.getPreferredSize());

	        panel.add(show);
	        panel.add(Box.createVerticalStrut(20));
	        panel.add(blacklist);
	        panel.add(Box.createVerticalStrut(20));
	        panel.add(update);
	        panel.add(Box.createVerticalStrut(20));
	        panel.add(showBlacklist);
	        panel.add(Box.createVerticalStrut(20));
	        panel.add(delete);
	        panel.add(Box.createVerticalStrut(20));
	        back.setPreferredSize(delete.getPreferredSize());
	        back.setFocusable(false);
	        panel.add(back);
        	panel.add(Box.createVerticalStrut(20));   
	       
	        JPanel greatPanel = new JPanel();
	        greatPanel.setLayout(new BorderLayout());
	        greatPanel.add(new JScrollPane(table), BorderLayout.CENTER);
	        greatPanel.add(panel, BorderLayout.WEST);
	        add(greatPanel);
	        
	        show.addActionListener(this);
	        blacklist.addActionListener(this);
	        delete.addActionListener(this);
	        back.addActionListener(this);
	        update.addActionListener(this);
	        showBlacklist.addActionListener(this);
	        
	        setPreferredSize(new Dimension(800, 500));
	        pack();
	        setVisible(true);
	        setLocationRelativeTo(null);
	        
	        
	    }
/**
 * Processing of the user's choice.
 */    
	    public void actionPerformed(ActionEvent e) {

			Object src = e.getSource();
			
			if(src == show){

				try{
		       		ConnectDB cdb = new ConnectDB();
		       		UserTableDBDAO tdb = new UserTableDBDAO(cdb);
		       		this.date = tdb.showAll();
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
				whatUpdate = false;
				new DataUserFrame(currentUser, date, whatUpdate);
				dispose();
			}
			
			else if(src == blacklist){
				
				UserTookBook utb = date.get(currentSelectedRow);
				
				boolean error = false;
				try {
					ConnectDB connect = new ConnectDB();
					UserTableDBDAO pdb = new UserTableDBDAO(connect);
					pdb.writeInBlackList(utb);
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
							"User listed in the blacklist",
							"Successful operation",
							JOptionPane.OK_OPTION);
				}
				else {
					JOptionPane.showConfirmDialog(null,
							"The user is not listed in the blacklist",
							"Not successful operation",
							JOptionPane.OK_OPTION);
				}
				
				try {
					ConnectDB connect = new ConnectDB();
					UserTableDBDAO pdb = new UserTableDBDAO(connect);
					pdb.delete(utb);
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
			}
			else if (src == showBlacklist){
				
				try{
		       		ConnectDB cdb = new ConnectDB();
		       		UserTableDBDAO tdb = new UserTableDBDAO(cdb);
		       		date = tdb.showAllBlackList();
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
				whatUpdate = true;
				new DataUserFrame(currentUser, date, whatUpdate);
				dispose();

			}
			
			else if(src ==  delete){
				
				UserTookBook utb = date.get(currentSelectedRow);
				try {
					ConnectDB connect = new ConnectDB();
					UserTableDBDAO pdb = new UserTableDBDAO(connect);
					pdb.deleteBlacklist(utb);
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
			}
			
			else if (src == update){
				if(!whatUpdate){
					try {
					    ConnectDB cdb = new ConnectDB();
					    UserTableDBDAO tdb = new UserTableDBDAO(cdb);
					    date = tdb.showAll();
					    tdb.closeConnection();
					   } catch (SQLException sql) {
					    JOptionPane.showConfirmDialog(null,
					      "Error connecting to database",
					      "Sorry just try again!", JOptionPane.YES_OPTION);
					   } catch (ClassNotFoundException cnf) {
					    JOptionPane.showConfirmDialog(null,
					      "Cannot find db driver classes",
					      "Error with database connect", JOptionPane.OK_OPTION);
					   }

					   new DataUserFrame(currentUser, date, whatUpdate);
					   dispose();
				}
				else{
					try {
					    ConnectDB cdb = new ConnectDB();
					    UserTableDBDAO tdb = new UserTableDBDAO(cdb);
					    date = tdb.showAllBlackList();
					    tdb.closeConnection();
					   } catch (SQLException sql) {
					    JOptionPane.showConfirmDialog(null,
					      "Error connecting to database",
					      "Sorry just try again!", JOptionPane.YES_OPTION);
					   } catch (ClassNotFoundException cnf) {
					    JOptionPane.showConfirmDialog(null,
					      "Cannot find db driver classes",
					      "Error with database connect", JOptionPane.OK_OPTION);
					   }
					   new DataUserFrame(currentUser, date, whatUpdate);
					   dispose();
				}	
			}
			
			else if(src == back){
				 AdminFunctions frame = new AdminFunctions  ("Admin Panel", currentUser);
				 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 dispose();

			}
	    }

}
	

