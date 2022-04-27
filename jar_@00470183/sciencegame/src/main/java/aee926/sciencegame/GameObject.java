package aee926.sciencegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class GameObject {
	protected Image img;
	protected double x,y,width,height;
	protected GraphicsContext gc;
	protected Rectangle r;
	
	public GameObject(GraphicsContext gc, double x, double y)
	{
		this.gc=gc;
		this.x=x;
		this.y=y;
	}
	
	public void update() 
	{
		if(img!=null)
			gc.drawImage(img, x, y,width,height);
	}
	
	public void updateRectangle()
	{
		r.setX(x);
		r.setY(y);
		r.setWidth(width);
		r.setHeight(height);
	}
	
	public void intersects(Player player) {
		
	}
}
