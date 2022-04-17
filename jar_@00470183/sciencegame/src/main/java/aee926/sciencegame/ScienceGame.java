package aee926.sciencegame;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ScienceGame extends Application {
	Pane root, gamePane;
	Scene scene, gameScene;
	Canvas canvas, gameCanvas;
	GraphicsContext gc, gamegc;
	Label titleLabel = new Label();
	Label gameDescription = new Label();
	Button playButton = new Button();
	Player player;
	Map map;
	EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent> () {

		@Override
		public void handle(KeyEvent keyEvent) {
			if(keyEvent.getCode() == KeyCode.W) {
				player.moveUp();
			}
			if(keyEvent.getCode() == KeyCode.S) {
				player.moveDown();
			}
			if(keyEvent.getCode() == KeyCode.A) {
				player.moveLeft();
			}
			if(keyEvent.getCode() == KeyCode.D) {
				player.moveRight();
			}
		}};
	 AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				gamegc.setFill(Color.DARKRED);
				gamegc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				player.move();
				map.checkCollision(player);
		}};

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Title Scene
		root = new Pane();
		scene = new Scene(root,800,600);
		canvas = new Canvas(800,600);
		gc = canvas.getGraphicsContext2D();
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//Canvas Design
		root.getChildren().add(canvas);
		gc.setFill(Color.DARKRED);
		gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
		
		//Title Page Design
		titleLabel.setText("Defeat The Immune System!");
		titleLabel.setLayoutX(scene.getWidth()/6);
		titleLabel.setLayoutY(scene.getHeight()/6);
		titleLabel.setFont(new Font("Arial", 45));
		titleLabel.setTextFill(Color.web("white"));
		root.getChildren().add(titleLabel);
		
		gameDescription.setText("Welcome! As a virus, you have successfully invaded the body. Now your mission \n is to attack the"
				+ " body. Watch out for the body's defences, the white blood cells and \n the mucous membrane. \n\n They will try to eradicate "
				+ "you. Good luck!");
		gameDescription.setMaxWidth(scene.getWidth()-20);
		gameDescription.setLayoutX(scene.getWidth()/20);
		gameDescription.setLayoutY(scene.getHeight()/3);
		gameDescription.setFont(new Font("Arial", 20));
		gameDescription.setTextAlignment(TextAlignment.CENTER);
		gameDescription.setTextFill(Color.web("white"));
		root.getChildren().add(gameDescription);
		
		playButton.setText("Enter The Game!");
		playButton.setLayoutX(scene.getWidth()/2.2);
		playButton.setLayoutY(scene.getHeight()/1.5);
		root.getChildren().add(playButton);
		playButton.setOnAction(e -> primaryStage.setScene(gameScene));
		
		//Game Scene
		gamePane = new Pane();
		gameScene = new Scene(gamePane,800,600);
		gameCanvas = new Canvas(800,600);
		gamegc = gameCanvas.getGraphicsContext2D();
		
		//Canvas Design
		gamePane.getChildren().add(gameCanvas);
		gamegc.setFill(Color.DARKRED);
		gamegc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
		
		//Game Page Design
		player = new Player(0,270,gamegc);
		map = new Map();
		gamePane.getChildren().add(map.drawWalls());
		timer.start();
		gameScene.setOnKeyPressed(keyHandler);
		//Game Over Scene
	}

}