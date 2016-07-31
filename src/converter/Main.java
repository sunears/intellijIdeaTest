package converter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main {
	
	  private static JFrame frame;
	  private static JTextField txtPath;
	  private static JLabel label;
	  private static JLabel label2;
	  
	  private static float fps;
	  private static JComboBox<String> fpsBox;
	  
	  private static String destination;
	  
	  private static Converter converter;

	public static void main(String[] args)
	
	
	{
		
		destination = null;
		
		
		
		
		frame = new JFrame();
		frame.setTitle("Txt to srt file converter");
		    frame.setBounds(100, 100, 450, 300);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.getContentPane().setLayout(null);
		         
		    
		    String[] fpsList =  {"23","23.976","24","25","30"};
		fpsBox = new JComboBox<String>(fpsList);     
		fpsBox.setBounds(10, 130, 50, 20);
		frame.getContentPane().add(fpsBox);
		
	//	fps = Float.valueOf((String) fpsBox.getSelectedItem());
		
		fps = Float.valueOf( fpsBox.getSelectedItem().toString());
		    
		label = new JLabel("Select the file for conversion:");
		label.setBounds(10, 0, 300, 40);
		frame.getContentPane().add(label);
		
		label2 = new JLabel("Frames per second:");
		label2.setBounds(10, 100, 300, 40);
		frame.getContentPane().add(label2);
		
		    txtPath = new JTextField();
		    txtPath.setBounds(10, 40, 414, 21);
		    frame.getContentPane().add(txtPath);
		    txtPath.setColumns(10);
		    txtPath.setEditable(false);
		    
		    
		    
		    
		         
		    JButton btnBrowse = new JButton("Browse");
		    btnBrowse.setBounds(10, 71, 87, 23);
		    frame.getContentPane().add(btnBrowse);
		    
		    JButton btnConSav = new JButton("Convert & Save");
		    btnConSav.setBounds(287, 71, 137, 23);
		    frame.getContentPane().add(btnConSav);
		    
		    frame.setVisible(true);
		    
		    btnBrowse.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		 

		        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		        fileChooser.setAcceptAllFileFilterUsed(false);
		 
		        int rVal = fileChooser.showOpenDialog(null);
		        if (rVal == JFileChooser.APPROVE_OPTION) {
		          txtPath.setText(fileChooser.getSelectedFile().toString());
		        }
		      }
		    });
		    
		    
		    btnConSav.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			        JFileChooser fileChooser = new JFileChooser();
			 

			        fileChooser.setAcceptAllFileFilterUsed(true);
			 
			        int rVal = fileChooser.showSaveDialog(frame);
			        if (rVal == JFileChooser.APPROVE_OPTION) {
			          
			        	destination = fileChooser.getSelectedFile().getAbsolutePath();
			        	if(destination!=null && !txtPath.getText().isEmpty())
			        	{
			        		converter = new Converter(txtPath.getText(),destination);
			        		converter.setFps(fps);
			        		converter.readFile();
			        		converter.writeFile();
			        		
			        	}
			        	else
			        	{
			        		JOptionPane.showMessageDialog(frame,
			        			    "Proper source file for conversion has not been selected.",
			        			    "Conversion aborted.",
			        			    JOptionPane.WARNING_MESSAGE);
			        	}
			        	
			        }
			      }
			    });
		    
		    
		    fpsBox.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			    	
			  		fps = Float.valueOf( fpsBox.getSelectedItem().toString());
			    	  System.out.println(fps);
			    	   
			      }
			    });
		    

		
	};
	
	
}
