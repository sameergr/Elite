package com.innverse.elearn.test;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.innverse.elearn.config.Config;

public class ShowLogo {
	
	public static void main(String[] s){
		try{
			Image image = null;
			
		/*	// Read from a file  
            File file = new File("C:/Users/Sameer/Desktop/coin13.png");  
            Image image = ImageIO.read(file);  */
  
            // Read from an input stream  
            InputStream is = new BufferedInputStream(  
                new FileInputStream("C:/Users/Sameer/Desktop/coin13.png"));  
            image = ImageIO.read(is);
		
            JFrame frame = new JFrame();  
            JLabel label = new JLabel(new ImageIcon(image));  
            frame.getContentPane().add(label, BorderLayout.CENTER);  
            frame.pack();  
            frame.setVisible(true);
		
		
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("^^^^^^^"+e.getMessage());
		}
	}

}
