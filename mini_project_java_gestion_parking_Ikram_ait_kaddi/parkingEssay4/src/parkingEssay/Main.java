package parkingEssay;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;






public class Main {
	public static void main(String[] args) throws SQLException, IOException {
	    
	    
	    JFrame frame = new JFrame("Parking Simulator");
	     parking panel = new parking();
	    frame.setContentPane(panel);
	     panel.setLayout(null);
	     frame.setSize(1090, 600);
	     frame.setResizable(false);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setVisible(true);
	    
	             
	}
}
