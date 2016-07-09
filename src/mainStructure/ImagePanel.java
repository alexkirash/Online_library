package mainStructure;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * Derive the background image of the main frame.
 * @author Kirash
 
 */
	public class ImagePanel extends JPanel{
		
	    public void paintComponent(Graphics g){
	        Image im = null;
	        try {
	            im = ImageIO.read(new File("C:\\Users\\SANYA\\Desktop\\zzzz.jpg"));
	        } catch (IOException e) {}
	        g.drawImage(im, 0, 0, null); 
	    }
	}