package demo;

//imports commented out so that the package for each
//of the classes and interfaces used is clearer
//java.awt is the original GUI package from v1.0
//java.awt.event is the package for the event handling
// model introduced in v1.1
//javax.swing is the package added in a major overhaul
// of the standard library in v1.2

//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;

/**
 * A simple class for the discussion about threading.
 * 
 * @author Dan
 */
public class ThreadDemo extends javax.swing.JPanel 
implements java.awt.event.ActionListener, Runnable {

	// coordinates for the green circle
	private int x;
	private int y;

	// GUI elements
	private javax.swing.JFrame win;
	private javax.swing.JButton right;
	private javax.swing.JButton home;
	private javax.swing.JButton go;

	/**
	 * The constructor which sets everything up
	 */
	public ThreadDemo() {

		// create a window
		win = new javax.swing.JFrame("Thread Demo");
		win.setSize(400, 300);
		win.setLocation(50, 50);
		win.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		win.add(this);

		// set-up a tool bar
		javax.swing.JPanel tools = new javax.swing.JPanel();
		win.add(tools, java.awt.BorderLayout.SOUTH);
		right = new javax.swing.JButton("Move Right");
		home = new javax.swing.JButton("Home");
		go = new javax.swing.JButton("Go");
		tools.add(right);
		tools.add(home);
		tools.add(go);

		// set up the panel
		x = y = 10;
		setBackground(java.awt.Color.white);

		// set up event handler
		home.addActionListener(this);
		right.addActionListener(this);
		go.addActionListener(this);

		// make the window visible
		win.setVisible(true);
		win.repaint();
	}

	/**
	 * This method draws a green circle in the component.
	 * The circle's location is specified by the x and y
	 * fields of the class.
	 * 
	 * @param g The Graphics object used for drawing
	 */
	@Override
	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		g.setColor(java.awt.Color.green);
		g.fillOval(x, y, 50, 50);
		g.setColor(java.awt.Color.black);
		g.drawOval(x, y, 50, 50);
	}

	/**
	 * The application method that runs the program
	 * @param args The command-line arguments
	 */
	public static void main(String[] args) {
		new ThreadDemo().moveRight();
	}

	// method to reset the coordinates
	void home() {
		x = y = 10;
		win.repaint();
	}

	// method to move the circle to the right
	void moveRight() {
		try {
			while(x + 60 < getWidth()) {
				x += 10;
				win.repaint();
				Thread.sleep(100);
			}
		} catch(InterruptedException e) {
		}
	}

	/**
	 * Override of the ActionListener method. Called in response
	 * to a button click event in this program.
	 * @param e The event that triggered this handler call
	 */
	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		if(e.getSource() == home) {
			home();
		} else if(e.getSource() == right) {
			new Thread(this).start();
		} else if(e.getSource() == go) {
			// creation and instantiation of an anonymous 
			// inner class
			Runnable goThing = new Runnable() {
				@Override
				public void run() {
					go();
				}
			};
			// instantiation could occur as the argument for
			// the Thread constructor, but I believe the code
			// is more readable when the anonymous inner class
			// is instantiated outside of the method call.
			new Thread(goThing).start();
		}
	}

	/**
	 * The Runnable method that gives the actions that a 
	 * Thread object will perform when it is started.
	 */
	@Override
	public void run() {
		moveRight();
	}

	// method to move the circle around the component
	void go() {
		try {
			while(x + 60 < getWidth()) {
				x += 10;
				win.repaint();
				Thread.sleep(100);
			}
			while(y + 60 < getHeight()) {
				y += 10;
				win.repaint();
				Thread.sleep(100);
			}
			while(x > 10) {
				x -= 10;
				win.repaint();
				Thread.sleep(100);
			}
			while(y > 10) {
				y -= 10;
				win.repaint();
				Thread.sleep(100);
			}
		} catch(InterruptedException e) {
			System.out.println("Thread sleep interrupted");
		}
	}

	// an named inner class that was created for support of 
	// the Go button. It was replaced by an anonymous inner 
	// class created and instantiated in the actionPerformed
	// handler method.
	// class GoThing implements Runnable {

	// }

}
