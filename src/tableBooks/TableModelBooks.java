package tableBooks;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
/**
 * Model for storing data in our table with the search result Books.
 * @author Kirash
 *
 */ 
public class TableModelBooks implements TableModel {
 
	
	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
/**
* List for storage all selected books.
*/	 
    private List<Books> books;

    public TableModelBooks(List<Books> books) {
        this.books = books;
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
/**
 * Returns the number of columns to be displayed in the table.
 */
    public int getColumnCount() {
        return 6;
    }
/**
 * Returns the column header to its index.
 */
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return "Number";
        case 1:
            return "Name";
        case 2:
            return "Year";
        case 3:
            return "Author";
        case 4:
            return "Category";
        case 5:
            return "Quantity";
        
        }
        return "";
    }
/**
 * Returns the number of rows to be displayed in the table.
 */
    public int getRowCount() {
        return books.size();
    }
/**
 * Responsible for what data to JTable which cells will be displayed.
 */
    public Object getValueAt(int rowIndex, int columnIndex) {
        Books book = books.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return book.getNamber();
        case 1:
            return book.getName();
        case 2:
            return book.getYear();
        case 3:
            return book.getAuthor();
        case 4:
            return book.getCategory();
        case 5:
            return book.getQuantity();
        
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
