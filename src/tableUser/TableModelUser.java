package tableUser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
/**
 * Model for storing data in our table with the search result Users.
 * @author Kirash
 *
 */
public class TableModelUser implements TableModel{

	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
/**
* List of storage for users who have a book on loan.
*/		
	private List<UserTookBook> date;
	
    public TableModelUser(List<UserTookBook> date) {
        this.date = date;

    }

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }
/**
 * JTable needs to know what data it should be displayed in a column.
 */
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 3;
    }
/**
 * Returns the column header to its index.
 */
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {

        case 0:
            return "Login";
        case 1:
            return "Name book";
        case 2:
            return "Date";

        }
        return "";
    }
/**
 * Returns the number of rows to be displayed in the table.
 */
    public int getRowCount() {
        return date.size();

    }
/**
 * Responsible for what data to JTable which cells will be displayed.
 */
    public Object getValueAt(int rowIndex, int columnIndex) {
        UserTookBook date1 = date.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return date1.getLogin();
        case 1:
            return date1.getNameBook();
        case 2:
            return date1.getDate();
        
        }
        return "";
    }
/**
 * Checks whether the cell is editable JTable row index and column is passed method as a parameter.
 */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {


    }
}

	

