package aee926.sciencegame;

public interface FactoryIF {
	GameObject createProduct(String discrim, double x, double y);
	GameObject createProduct(String discrim, double x, double y, boolean d);
}
