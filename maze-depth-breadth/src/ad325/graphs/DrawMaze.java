package ad325.graphs;
/*
 * @author Kellan Nealy
 * Assignment 8: Maze Generation
 * The DrawMaze class produces a populated maze using
 * a specific maze generation algorithm.
 */


import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * A simple class to support drawing the maze.
 * @author Dan
 */
public class DrawMaze extends JPanel {
    
    /**
     * The width of the maze given in the constructor
     */
    public final int width;
    /**
     * The height of the maze given in the constructor
     */
    public final int height;
    /**
     * The width of the maze halls, a constant
     */
    public static final int SIZE = 35;
    /**
     * The thickness of the maze walls, a constant
     */
    public static final int WALL = 5;
    /**
     * The thickness of the border around the maze, a constant
     */
    public static final int BORDER = 10;
    
    // storage for the walls that are added
    private Set<Wall> walls;
    
    /**
     * Construct a simple maze
     * @param w The width of the maze in "cells"
     * @param h The height of the maze in "cells"
     */
    public DrawMaze(int w, int h) {
        width = w;
        height = h;
        setPreferredSize(new Dimension(width * SIZE + 2 * BORDER + WALL, 
                                       height * SIZE + 2 * BORDER + WALL));
        walls = new HashSet<Wall>();
    }
 
    /**
     * Add a vertical wall to the maze.
     * @param x The horizontal offset for the wall
     * @param y The vertical offset for the wall
     * @param len The length of the wall
     * @return True, if the requested wall added to the set
     * of walls
     */
    public boolean addVerticalWall(int x, int y, int len) {
        if(x > width || y+len > height) 
            throw new IllegalArgumentException("Wall exceeds maze boundary");
        boolean added = false;
        for(int i = 0; i < len; i++) {
            if(addVerticalWall(x, y + i)) added = true;
        }
        return added;
    }
    
    /**
     * Add a vertical wall one cell long to the maze.
     * @param x The horizontal offset for the wall
     * @param y The vertical offset for the wall
     * @return True, if the requested wall added to the set
     * of walls
     */
    public boolean addVerticalWall(int x, int y) {
        if(x > width || y+1 > height) 
            throw new IllegalArgumentException("Wall exceeds maze boundary");
        return walls.add(new Wall(x, y, false));
    }
    
    /**
     * Add a horizontal wall to the maze.
     * @param x The horizontal offset for the wall
     * @param y The vertical offset for the wall
     * @param len The length of the wall
     * @return True, if the requested wall added to the set
     * of walls
     */
    public boolean addHorizontalWall(int x, int y, int len) {
        if(x+len > width || y > height) 
            throw new IllegalArgumentException("Wall exceeds maze boundary");
        boolean added = false;
        for(int i = 0; i < len; i++) {
            if(addHorizontalWall(x + i, y)) added = true;
        }
        return added;
    }
    
    /**
     * Add a horizontal wall one cell long to the maze.
     * @param x The horizontal offset for the wall
     * @param y The vertical offset for the wall
     * @return True, if the requested wall added to the set
     * of walls
     */
    public boolean addHorizontalWall(int x, int y) {
        if(x+1 > width || y > height) 
            throw new IllegalArgumentException("Wall exceeds maze boundary");
        return walls.add(new Wall(x, y, true));
    }
    
    /**
     * Display the maze in a JFrame
     */
    public void display() {
        JFrame win = new JFrame("My Maze");
        win.setLocation(25, 25);
        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        win.add(this);
        win.pack();
        win.setVisible(true);
    }
    
    /**
     * Paint the maze component
     * @param g The graphics object for rendering
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(WALL));
        int[] xs = {BORDER + WALL, BORDER, BORDER, BORDER + (width-1)*SIZE + WALL};
        int[] ys = {BORDER, BORDER, BORDER + height*SIZE, BORDER + height*SIZE};
        g2.drawPolyline(xs, ys, 4);
        int[] xs2 = {BORDER + SIZE - WALL, BORDER + width*SIZE, BORDER + width*SIZE, BORDER + width*SIZE - WALL};
        g2.drawPolyline(xs2, ys, 4);
        // code that was used to create the intermediate images
//        g2.setColor(Color.LIGHT_GRAY);
//        for(int i = 1; i < height; i++) {
//            int where = BORDER + i*SIZE;
//            g2.drawLine(BORDER + WALL, where, BORDER + width * SIZE - WALL, where);
//        }
//        for(int i = 1; i < width; i++) {
//            int where = BORDER + i*SIZE;
//            g2.drawLine(where, BORDER + WALL, where, BORDER + height * SIZE - WALL);
//        }
        g2.setColor(Color.blue);
        for(Wall wall : walls) {
            drawWall(g2, wall);
        }
    }
    
    // helper method to draw a horizontal wall
    void drawWall(Graphics g, Wall wall) {
        int x = wall.x;
        int y = wall.y;
        if(wall.horz) {
            g.drawLine(BORDER + x*SIZE, BORDER + y*SIZE, BORDER + (x+1)*SIZE, BORDER + y*SIZE);
        } else {
            g.drawLine(BORDER + x*SIZE, BORDER + y*SIZE, BORDER + x*SIZE, BORDER + (y+1)*SIZE);
        }
    }
    
    /**
     * Access to the set of walls
     * @return the set of walls
     */
    public Set<Wall> getWallSet() {
        return walls;
    }
    
    /**
     * Sample application method, drawing a maze using this class
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        DrawMaze maze = new DrawMaze(20, 20);
        createMazeMinus(maze);
        maze.display();
    }
    
    /**
     * Adds walls to the maze using the algorithm for the minus version
     * @param maze the maze that will be constructed
     * @return the set of walls
     */
    public static void createMazeMinus(DrawMaze maze) {
        Set<Point> loose = new HashSet<Point>();
        Set<Point> source = new HashSet<Point>();
        
        maze.getLoosePoints(loose);
        maze.getSourcePoints(source);

        //Repeat until the loose collection is empty.
        while (!loose.isEmpty()) {
            //point randomly selected from the source collection
            //checked for adjacent points that are not connected
        	int size = source.size();
        	int item = new Random().nextInt(size);
        	int i = 0;
        	Point currentSourcePoint = null;
        	for(Point p : source) {
        	    if (i == item)
        	        currentSourcePoint = p;
        	    i = i + 1;
        	}
        	
        	ArrayList<Point> toChoose =
        			maze.getAdjacentLoosePoints(currentSourcePoint, loose);
        	
            //If there is exactly one loose adjacent point,
        	//the wall between the source point and the loose point is added to the maze
        	//source point can be removed from the source collection
        	if (toChoose.size() == 1) {
        		
        		Point drawTo = toChoose.get(0);
        		maze.connectWall(currentSourcePoint, drawTo);
        		loose.remove(drawTo);
        		source.remove(currentSourcePoint);
        		source.add(drawTo);
        	
            //else If there are more than one loose points adjacent to the source point,
        	//randomly select a direction for the wall, from the possibilities,
        	//and add that wall to the maze. In this case, do not remove the
        	//source point from the source collection.
        	} else if (toChoose.size() > 1) {
                
        		int rand = (int) (Math.random() * (toChoose.size() - 1)) / 1;
        		Point drawTo = toChoose.get(rand);
                maze.connectWall(currentSourcePoint, drawTo);
                loose.remove(drawTo);
                source.add(drawTo);
        	}
        }
    }
    
    /**
     * Connects wall between the passed points in this maze
     * @param p1 the point to connect from
     * @param p2 the point to connect to
     */
    public void connectWall(Point p1, Point p2) {
    	if (p1.getX() == p2.getX()) {
    		if (p1.getY() > p2.getY()) {
    			addVerticalWall((int) p2.getX(), (int) p2.getY());
    		} else {
    			addVerticalWall((int) p1.getX(), (int) p1.getY());
    		}
    	} else if (p1.getY() == p2.getY()) {
    		if (p1.getX() > p2.getX()) {
    			addHorizontalWall((int) p2.getX(), (int) p2.getY());
    		} else {
    			addHorizontalWall((int) p1.getX(), (int) p1.getY());
    		}
    	}
    }
    
    /**
     * Connects wall between the passed points in this maze
     * @param p1 the point to connect from
     * @param p2 the point to connect to
     */
    public void getLoosePoints(Set<Point> loose) {
    	for (int i = 1; i < width; i++) {
    		for (int j = 1; j < height; j++) {
    			loose.add(new Point(i, j));
    		}
    	}
    }
    
    /**
     * Adds points to the passed source collection to start
     * @param source the set of points to be populated with source points
     */
    public void getSourcePoints(Set<Point> source) {
    	for (int i = 1; i < width; i++) {
			source.add(new Point(i, 0));
			source.add(new Point(i, height));
    	}
		for (int j = 1; j < height; j++) {
			source.add(new Point(0, j));
			source.add(new Point(width, j));
		}
    }
    
    /**
     * Determines and returns a list of adjacent loose points by checking neighbors
     * @param p the point to find neighbors for and determine if they are loose
     * @param loose the set of loose points currently in the maze
     * @return List of points that are adjacent and also considered "loose"
     */
    public ArrayList<Point> getAdjacentLoosePoints(Point p, Set<Point> loose) {
    	ArrayList<Point> points = new ArrayList<Point>();
    	
    	// check point directly to the left
    	if (p.getX() + 1 < width) {
    		Point possible = new Point((int) p.getX() + 1, (int) p.getY());
    		if (loose.contains(possible)) {
    			points.add(possible);
    		}
    	}
    	//check point directly to the right
    	if (p.getX() - 1 > 0) {
    		Point possible = new Point((int) p.getX() - 1, (int) p.getY());
    		if (loose.contains(possible)) {
    			points.add(possible);
    		}
    	}
    	//check point directly above
    	if (p.getY() + 1 < height) {
    		Point possible = new Point((int) p.getX(), (int) p.getY() + 1);
    		if (loose.contains(possible)) {
    			points.add(possible);
    		}
    	}
    	//check point directly above
    	if (p.getY() - 1 > 0) {
    		Point possible = new Point((int) p.getX(), (int) p.getY() - 1);
    		if (loose.contains(possible)) {
    			points.add(possible);
    		}
    	}
    	return points;
    }
}