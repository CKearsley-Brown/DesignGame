package aee926.sciencegame;

import javafx.scene.canvas.GraphicsContext;

public class Factory implements FactoryIF{
	GraphicsContext gc;

	public Factory(GraphicsContext gc) {
		super();
		this.gc = gc;
	}

	@Override
	public GameObject createProduct(String discrim, double x, double y) {
		if(discrim.equals("cell"))
			return new Item(gc,x,y);
		return null;
	}

}