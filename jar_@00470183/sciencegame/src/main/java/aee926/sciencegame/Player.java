package aee926.sciencegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {
	double x,y;
	GraphicsContext gc;
	Image img;
	
	public Player(double x, double y, GraphicsContext gc) {
		super();
		this.x = x;
		this.y = y;
		this.gc = gc;
		img = new Image(this.getClass().getResourceAsStream("virus.png"));
	}
	
	public void move() {
		x++;
		gc.drawImage(img, x, y, 50, 50);
	}
}
