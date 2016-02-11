import java.awt.*;
import java.util.*;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
public class Visualization {
	private DrawingPanel panel;
	private ArrayList<Rectangle> recs;
	private int steps;
	private int vars = 0;
	private int placement = 1000;
	private Graphics2D graphics;
	public boolean running = false;
	
	public String[] paths = {"Tarrega, Recuerdos de la Alhambra.wav", "Claire De Lune.wav", "Dirt Road Anthem.wav", "Matilda.wav", "If I Were A Boy.wav",
			"Sunday Mornings.wav", "Over You.wav", "Background2.wav", "On My Own.wav", "Area Codes.wav", "Say Hello to Goodbye.wav", "Candy Shop.wav",
			"Kryptonite.wav", "T.N.T..wav", "Rooster.wav", "Bat Country.wav"};
	
	//classical & country
	public int[] inds = {0,1,2,3,4};
	//pop & dance
	public int[] inds2 = {5,6,7,8};
	//rap & hip-hop
	public int[] inds3 = {9,10,11};
	//rock & heavy rock
	public int[] inds4 = {12,13,14,15};
	

	public Visualization(){
		steps = 1;
        panel = new DrawingPanel(1200, 600);
        RadioFrame starter = new RadioFrame(this);
        
        
        //Create boxes and add components to them
//        Box boxy = Box.createVerticalBox();
//        Box box = Box.createHorizontalBox();
//        
//        JButton iconButton = new JButton();
//    	iconButton.setIcon(new ImageIcon("C:/Users/KellaN/workspace/Smart Radio/src/SMARTradio.png"));
//        iconButton.setVisible(true);
//        
//    	boxy.add(iconButton);
//        
//        boxy.add(panel.imagePanel);
		
		graphics = panel.getGraphics();
		panel.setBackground(Color.DARK_GRAY);
		drawRecs();
		
		this.run();
	}

	public void run() {
		while(running = true) {
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				//do nothing
			}
			
			setVars();
			//drawArcs(vars);
			colorRecs();
			
			Color curColor = graphics.getColor();
			Color myCol = new Color(curColor.getRGB() + steps);
			graphics.setColor(myCol);
			
			//Color;
			
			steps++;
			//this.run();
		}
	}
	
	public void drawArcs(int var) {
		System.out.println((int)Math.random() * placement + var);
		graphics.drawArc((int)Math.random() * placement + var, 200 - var^2, placement, (int)(Math.random() * placement + var*var), 30 + var, 30);
		graphics.drawArc((int)Math.random() * placement - var, 200, placement, (int)(Math.random() * placement + var*var), 30 - var, 20);
		graphics.drawArc((int)Math.random() * placement + var, 200, placement - var^3, (int)(Math.random() * placement + var*var), 30 + var^2, 50);
		
		graphics.drawArc((int)(Math.random() * placement - var), (int) (Math.random() * placement + var*var*var), placement, (int)(Math.random() * placement + var*var), 30 - var, 100);
		graphics.drawArc((int)(Math.random() * placement + var), (int) (Math.random() * placement + var), placement, (int)(Math.random() * placement + var*var), 30 + var, 60);
		graphics.drawArc((int)(Math.random() * placement - var), (int) (Math.random() * placement + var*var), placement, (int)(Math.random() * placement + var*var), 30 - var, 10);
		
		if (vars % 2 == 0) {
			graphics.drawArc((int)(Math.random() * placement - var), (int) (Math.random() * placement - var*var*var), placement, (int)(Math.random() * placement + var*var*var - var), 30 - var, 70 - var);
			graphics.drawArc((int)(Math.random() * placement + var), (int) (Math.random() * placement + var), placement, (int)(Math.random() * placement - var*var), 30 + var, 60 + var);
			graphics.drawArc((int)(Math.random() * placement - var), (int) (Math.random() * placement - var*var), placement, (int)(Math.random() * placement + var*var + var), 30 - var, 20 - var);
		}
			
	}
	
	public void drawRecs() {
		ArrayList<Rectangle> recs = new ArrayList<Rectangle>();
		int a = 0;
		for (int x = 0; x <= 1200; x += 50) {
			for (int y = 0; y <= 600; y += 50) {
				if (a == 1) {
					Rectangle rec = new Rectangle(x, y, 50, 50, graphics);
					recs.add(rec);
					a = 0;
				} else {
					a++;
				}
			}		
		}
		this.recs = recs;
	}
	
	public void colorRecs() {
		for(int x = 0; x < recs.size(); x++) {
			if (x % 2 == 0)
				recs.get(x).changeColor();
			else
				graphics.setBackground(Color.GRAY);
		}
	}
	
	public void setVars() {
		if (steps == 1)
			vars = steps;
		else if (vars == 800) {
			vars = 0;
			placement = 500;
			
		}
		else if (vars == 200)
			graphics.setColor(Color.RED);
		else if (vars == 300)
			graphics.setColor(Color.MAGENTA);
		vars++;
	}
	
	public DrawingPanel getPanel() {
		return this.panel;
	}
	
	public int getSteps() {
		return this.steps;
	}

}
