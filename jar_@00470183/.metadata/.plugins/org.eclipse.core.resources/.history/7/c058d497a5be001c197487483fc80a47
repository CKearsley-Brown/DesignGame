package aee926.sciencegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Item extends GameObject {
	private double x,y;
	private GraphicsContext gc;
	private Image img;
	private double width=28, height=28;
	protected Rectangle r;
	
	public Item(GraphicsContext gc, double x, double y) 
	{
		super(gc, x, y);
		this.x = x;
		this.y = y;
		this.gc = gc;
		r = new Rectangle(0,0,width,height);
		img = new Image(this.getClass().getResourceAsStream("cell.png"));
	}
	
	public void updateRectangle()
	{
		r.setX(x);
		r.setY(y);
		r.setWidth(width);
		r.setHeight(height);
	}

	@Override
	public void update() {
		super.update();
		this.gc = gc;
	}
}
