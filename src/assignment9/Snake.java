package assignment9;

import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class Snake {

	private static final double SEGMENT_SIZE = 0.03;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 0.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	
	public Snake() {
		segments = new LinkedList<>();
		segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));//FIXME - set up the segments instance variable
		deltaX = 0;
		deltaY = 0;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		BodySegment head = segments.getFirst();
		double newX = head.getX() + deltaX;
		double newY = head.getY() + deltaY;
		
		BodySegment newHead = new BodySegment(newX, newY, SEGMENT_SIZE);
		segments.addFirst(newHead);
		segments.removeLast(); 
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (int i = 1; i < segments.size(); i++) {
			segments.get(i).draw(); // Draw body segments first
		}

		// Draw head with face
		BodySegment head = segments.getFirst();
		head.draw();

		drawBigHead(head.getX(), head.getY());
		drawFace(head.getX(), head.getY());
		
		
//		
//		for (BodySegment segment : segments) {
//			segment.draw();
//		}//FIXME
	}

	private void drawFace(double x, double y) {
	    double eyeOffsetX = SEGMENT_SIZE * 0.33;
	    double eyeOffsetY = SEGMENT_SIZE * 0.33;
	    double eyeRadius = SEGMENT_SIZE * 0.12;

	    // Draw eyes
	    StdDraw.setPenColor(StdDraw.BLACK);
	    StdDraw.filledCircle(x - eyeOffsetX, y + eyeOffsetY, eyeRadius);
	    StdDraw.filledCircle(x + eyeOffsetX, y + eyeOffsetY, eyeRadius);

	    // Draw angry eyebrows (slanting inward)
	    double browLength = SEGMENT_SIZE * 0.3;
	    double browYOffset = eyeOffsetY + SEGMENT_SIZE * 0.1;
	    StdDraw.setPenRadius(0.002);

	    // Left eyebrow: slanting down toward the center
	    StdDraw.line(
	        x - eyeOffsetX - browLength / 2, y + browYOffset + 0.01,
	        x - eyeOffsetX + browLength / 2, y + browYOffset
	    );

	    // Right eyebrow: slanting down toward the center
	    StdDraw.line(
	        x + eyeOffsetX + browLength / 2, y + browYOffset + 0.01,
	        x + eyeOffsetX - browLength / 2, y + browYOffset
	    );

	 // Straight mouth
	    double mouthWidth = SEGMENT_SIZE * 0.5;
	    double frownRadius = SEGMENT_SIZE * 0.4;
	    double frownYOffset = SEGMENT_SIZE * 0.3;
	    StdDraw.setPenRadius(0.008);
	    StdDraw.line(x - mouthWidth / 2, y - frownYOffset, x + mouthWidth / 2, y - frownYOffset);
	 // Forked tongue
	    double tongueBaseY = y - SEGMENT_SIZE * 0.55;
	    double tongueTipY = tongueBaseY - SEGMENT_SIZE * 0.3;
	    double tongueOffsetX = SEGMENT_SIZE * 0.05;

	    StdDraw.setPenColor(StdDraw.RED);
	    StdDraw.setPenRadius(0.003);

	    // Center line
	    StdDraw.line(x, tongueBaseY, x, tongueTipY);

	    // Forks
	    StdDraw.line(x, tongueTipY, x - tongueOffsetX, tongueTipY - SEGMENT_SIZE * 0.1);
	    StdDraw.line(x, tongueTipY, x + tongueOffsetX, tongueTipY - SEGMENT_SIZE * 0.1);
	}
		
//		StdDraw.setPenColor(StdDraw.BLACK);
//		StdDraw.arc(x, y - 0.005, 0.006, 200, 340);
	
	
	private void drawBigHead(double x, double y) {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledCircle(x, y, SEGMENT_SIZE * 1.2); // slightly bigger than body
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		BodySegment head = segments.getFirst();
		double dx = head.getX() - f.getX();
		double dy = head.getY() - f.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);
	
		if (distance < SEGMENT_SIZE + f.getRadius()) {
			// Simulate growth by adding a segment (don’t remove tail in move next time)
			BodySegment tail = segments.getLast();
			segments.addLast(new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE));
			return true;
		}
		return false;
	
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		BodySegment head = segments.getFirst();
		double x = head.getX();
		double y = head.getY();
		return (x >= 0 && x <= 1 && y >= 0 && y <= 1);
		//return true;
	}
}
