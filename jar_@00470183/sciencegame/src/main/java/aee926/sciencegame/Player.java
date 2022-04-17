package aee926.sciencegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Player {
	private double x,y;
	private GraphicsContext gc;
	private Image img;
	private double dx=1, dy=1;
	private double width=30, height=30;
	protected Rectangle r;
	
	public Player(double x, double y, GraphicsContext gc) {
		super();
		this.x = x;
		this.y = y;
		this.gc = gc;
		r = new Rectangle(0,0,width,height);
		img = new Image(this.getClass().getResourceAsStream("virus.png"));
	}
	
	public void move() {
		gc.drawImage(img, x, y, width, height);
		updateRectangle();
	}
	
	public void moveUp() {
		y=y-10;
	}
	
	public void moveDown() {
		y=y+10;
	}
	
	public void moveLeft() {
		x=x-10;
	}
	
	public void moveRight() {
		x=x+10;
	}
	
	public void updateRectangle()
	{
		r.setX(x);
		r.setY(y);
		r.setWidth(width);
		r.setHeight(height);
	}
}
