package parkingEssay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class parking  extends JPanel {
	
	  ImageIcon image;
	     Car car;
	 	public ArrayList<Car> cars=new ArrayList<Car>();
	    
		public List<Car> listet = null;
		public  CarDAO CarDAO = null;
	 	
	  public parking() {   
	  }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
          image =new ImageIcon("src/img/parking.jpg");
          image.paintIcon(this, g, WIDTH,WIDTH);
          
             // button ajouter
	        JButton ajouter = new JButton();
	        ajouter.setBounds(700, 320, 190, 30);
	        ajouter.setText("Ajouter une voiture");
	        super.add(ajouter);
	        
	        // button lancer
	        JButton lancer = new JButton();
	        lancer.setBounds(200, 320, 190, 30);
	        lancer.setText("lancer");
	        super.add(lancer);
	        
		        
	        ajouter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Car et =null;
					try {
						et = new Car(0);
					
					} catch (NumberFormatException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				
			
				try {
					
					CarDAO	 CarDAO = new CarDAO();
					
					CarDAO.save(et);
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
						
				}
				});
	    
		try {
				CarDAO = new CarDAO();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
					
					try {
						listet = CarDAO.getAll();
					} catch (SQLException | IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				
			   for(Car c : listet)
			     {  
			         super.add(c);                                       
			      }
	   
	        lancer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
						
					   for(Car cr : listet)
					      { 
					          Thread t = new Thread(cr);
					          t.start();
					      }
				
				}
				});
	   
	   
	        String head [] = { "numéro de la voiture" } ;
			
			Object fill [][] =   { };
			
			
			DefaultTableModel mdodel = new DefaultTableModel(fill , head);
			  try {
				
			List<Car> list =  CarDAO.getAll();
			 
			 for(int i=0; i< list.size() ; i++) {
					
					
				 mdodel.addRow(new Object[] {list.get(i).getId()});
			 }	
					
				
			  } catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		        }

	        
	        JScrollPane scrollPane = new JScrollPane();
			 scrollPane.setBounds(200, 400, 391, 100);
			 super.add(scrollPane);

			JTable table = new JTable(mdodel);
			table.setBounds(200, 400, 391, 100);
			scrollPane.setViewportView(table);
         
    }
    
   



}