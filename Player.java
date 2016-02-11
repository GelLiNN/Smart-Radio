import java.awt.Dimension;
import java.awt.Point;

import javax.swing.*;


public class Player {


	public Player() {
		JFrame musicplayer = new JFrame();
		musicplayer.setUndecorated(true);
        //Create boxes and add components to them
		Box boxy = Box.createVerticalBox();
    	Box box = Box.createHorizontalBox();
      
    	JButton iconButton = new JButton();
    	iconButton.setIcon(new ImageIcon("C:/Users/KellaN/workspace/Smart Radio/src/SMARTradio.png"));
    	iconButton.setVisible(true);
    	
    	JButton play = new JButton();
    	play.setIcon(new ImageIcon("C:/Users/KellaN/Pictures/temp/play.png"));
    	play.setSize(new Dimension(50,50));
    	play.setVisible(true);
    	
    	JButton pause = new JButton();
    	pause.setIcon(new ImageIcon("C:/Users/KellaN/Pictures/temp/pause.png"));
    	pause.setSize(new Dimension(50,50));
    	pause.setVisible(true);
    	
    	JButton up = new JButton();
    	up.setIcon(new ImageIcon("C:/Users/KellaN/Pictures/temp/up.png"));
    	up.setSize(new Dimension(50,50));
    	up.setVisible(true);
    	
    	JButton down = new JButton();
    	down.setIcon(new ImageIcon("C:/Users/KellaN/Pictures/temp/down.png"));
    	down.setSize(new Dimension(50,50));
    	down.setVisible(true);
    	
    	//boxy.add(iconButton);
    	box.add(play);
    	box.add(pause);
    
    	
    	box.add(up);
    	box.add(down);
    	
    	//boxy.setVisible(true);
    	box.setVisible(true);
    	
    	musicplayer.add(iconButton);
    	musicplayer.add(box);
    	//musicplayer.add(boxy);
    	
    	
    	musicplayer.pack();
    	musicplayer.setLocation(new Point(200,165));
    	musicplayer.setVisible(true);
    	
	}


}
