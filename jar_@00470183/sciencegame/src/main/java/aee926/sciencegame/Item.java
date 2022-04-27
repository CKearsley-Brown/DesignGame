package aee926.sciencegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Item extends GameObject {
	
	public Item(GraphicsContext gc, double x, double y) 
	{
		super(gc, x, y);
		width = 26;
		height = 26;
		r = new Rectangle(0,0,0,0);
		img = new Image(this.getClass().getResourceAsStream("cell.png"));
		update();
		updateRectangle();
	}
	
	@Override
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
	}

	@Override
	public void intersects(Player player) {
		if(player.r.intersects(this.r.getX(),this.r.getY(),this.r.getWidth(), this.r.getHeight()))
		{
			System.out.println("Item collision");
			this.img = null;
			r.setX(0);
			r.setY(0);
			r.setWidth(0);
			r.setWidth(0);
			player.itemsCollected+=1;
		}
	}
}
