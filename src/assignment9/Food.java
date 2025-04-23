package assignment9;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Food {

	public static final double FOOD_SIZE = 0.03;
	private double x, y;
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getRadius() {
		return FOOD_SIZE;
	}

	
	/**
	 * Creates a new Food at a random location
	 */
	

	
	public Food() {
		this.x = Math.random() * (1 - 2 * FOOD_SIZE) + FOOD_SIZE;
		this.y = Math.random() * (1 - 2 * FOOD_SIZE) + FOOD_SIZE;
	}
	
	/**
	 * Draws the Food
	 */
	public void draw() {
		StdDraw.setPenColor(Color.RED);
		StdDraw.filledCircle(x, y, FOOD_SIZE);//FIXME
	}
	
}

