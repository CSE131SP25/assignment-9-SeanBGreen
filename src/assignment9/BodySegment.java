package assignment9;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class BodySegment {

	private double x, y, size;
	private Color color;
	
	public BodySegment(double x, double y, double size) {
		
			this.x = x;
			this.y = y;
			this.size = size;
			this.color = Color.GREEN; // Or use something from ColorUtils if you want variety
		//FIXME
		//See ColorUtils for some color options (or choose your own)
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	/**
	 * Draws the segment
	 */
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(x, y, size);//FIXME
	}
	
	public void setPosition(double newX, double newY) {
		this.x = newX;
		this.y = newY;
	}
}
