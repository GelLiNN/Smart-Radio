import java.awt.*;


public class Rectangle {
	private Graphics2D graphics;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean darkening;
	
	public Rectangle (int x, int y, int width, int height, Graphics2D graphics) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.graphics = graphics;

		int posNeg = 1;//(int) (Math.random() * 3);
		if (posNeg == 1) {
			darkening = true;
		} else {
			darkening = false;
		}
		
		graphics.drawRect(x, y, width, height);
		graphics.fillRect(x, y, width, height);
	}

	public void changeColor() {
		Color curColor = graphics.getColor();
		
		if (!darkening) {
			Color myCol = new Color((int) (curColor.getRGB() + .0001));
			graphics.setColor(myCol);
		} else if (darkening) {
			Color myCol = new Color((int) (curColor.getRGB() - .0001));
			graphics.setColor(myCol);
		}
		graphics.fillRect(x, y, width, height);
	}
	
	//public void move -- move rec by filling rec with background color, then making new one next to it
}
