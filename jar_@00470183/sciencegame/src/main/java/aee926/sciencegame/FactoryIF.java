package aee926.sciencegame;

public interface FactoryIF {
	GameObject createGameObject(String discrim, double x, double y);
	GameObject createGameObject(String discrim, double x, double y, boolean d);
}
