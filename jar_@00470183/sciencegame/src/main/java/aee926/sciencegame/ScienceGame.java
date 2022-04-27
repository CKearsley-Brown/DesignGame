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
	Stage primaryStage;
	Pane root, gamePane, losePane, winPane;
	Scene scene, gameScene, loseScene, winScene;
	Canvas canvas, gameCanvas, loseCanvas, winCanvas;
	GraphicsContext gc, gamegc, losegc, wingc;
	Label titleLabel = new Label();
	Label gameDescription = new Label();
	Button playButton = new Button();
	Label counter = new Label();
	int counterScore;
	Label winTitle = new Label();
	Label loseTitle = new Label();
	Button restart = new Button();
	Button exit = new Button();
	Player player;
	Map map;
	ArrayList<GameObject> list = new ArrayList<GameObject>();
	Factory factory;
	EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent> () {

		@Override
		public void handle(KeyEvent keyEvent) {
			if(keyEvent.getCode() == KeyCode.W && player.trapped == false) {
				map.checkPlayerMoveUp(player);
			}
			if(keyEvent.getCode() == KeyCode.S && player.trapped == false) {
				map.checkPlayerMoveDown(player);
			}
			if(keyEvent.getCode() == KeyCode.A && player.trapped == false) {
				map.checkPlayerMoveLeft(player);
			}
			if(keyEvent.getCode() == KeyCode.D && player.trapped == false) {
				map.checkPlayerMoveRight(player);
			}
		}};
	 AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				gamegc.setFill(Color.DARKRED);
				gamegc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				player.move();
				map.checkCollision(player);
				for(GameObject obj:list) 
				{
					obj.update();
					obj.intersects(player);
					counterScore = player.itemsCollected;
					counter.setText("Cells Infected: " + String.valueOf(counterScore) + "/4");
					if(obj instanceof Trap && ((Trap) obj).run != 1)
					{
						((Trap) obj).intersectsPlayer(player, gamePane, gameScene);
					}
					if(player.trapped == false)
						gameScene.setOnKeyPressed(keyHandler);
				}
				if(counterScore==4 && map.entrance(player)==true)
				{
					primaryStage.setScene(winScene);
				}
				if(player.dead == true)
					primaryStage.setScene(loseScene);
		}};
	EventHandler<ActionEvent> restartBHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				counterScore=0;
				player = new Player(1,271,gamegc);
				factory = new Factory(gamegc);
				list.add(factory.createProduct("cell", 22, 22));
				list.add(factory.createProduct("cell", 380, 452));
				list.add(factory.createProduct("cell", 710, 242));
				list.add(factory.createProduct("cell", 390, 142));
				primaryStage.setScene(gameScene);
			}};
	EventHandler<ActionEvent> exitBHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			System.exit(0);
		}};

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage=primaryStage;
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
		gamegc.fillRect(0,0,gameCanvas.getWidth(),gameCanvas.getHeight());
		
		//Game Page Design
		map = new Map();
		gamePane.getChildren().add(map.drawWalls());
		player = new Player(1,271,gamegc);
		gameScene.setOnKeyPressed(keyHandler);
		counter.setTextFill(Color.web("white"));
		counter.setLayoutX(630);
		counter.setLayoutY(0);
		counter.setFont(new Font("Arial", 20));
		gamePane.getChildren().add(counter);
		
		factory = new Factory(gamegc);
		list.add(factory.createProduct("cell", 22, 22));
		list.add(factory.createProduct("cell", 380, 452));
		list.add(factory.createProduct("cell", 710, 242));
		list.add(factory.createProduct("cell", 390, 142));
		list.add(factory.createProduct("mucus", 150, 210));
		list.add(factory.createProduct("mucus", 90, 410));
		list.add(factory.createProduct("mucus", 100, 500));
		list.add(factory.createProduct("mucus", 210, 190));
		list.add(factory.createProduct("mucus", 300, 110));
		list.add(factory.createProduct("mucus", 310, 350));
		list.add(factory.createProduct("mucus", 350, 500));
		list.add(factory.createProduct("mucus", 520, 20));
		list.add(factory.createProduct("mucus", 520, 160));
		list.add(factory.createProduct("mucus", 590, 320));
		list.add(factory.createProduct("mucus", 550, 430));
		list.add(factory.createProduct("mucus", 580, 500));
		list.add(factory.createProduct("mucus", 660, 80));
		list.add(factory.createProduct("mucus", 660, 180));
		list.add(factory.createProduct("mucus", 690, 500));
		list.add(factory.createProduct("mucus", 750, 130));
		list.add(factory.createProduct("enemy", 750, 130));
		list.add(factory.createProduct("enemy", 300, 110));
		list.add(factory.createProduct("enemy", 350, 500));
		list.add(factory.createProduct("enemy", 660, 80));
		list.add(factory.createProduct("enemy", 690, 500));
		timer.start();
		
		//Game Over Scene
		losePane = new Pane();
		loseScene = new Scene(losePane,800,600);
		loseCanvas = new Canvas(800,600);
		losegc = loseCanvas.getGraphicsContext2D();
		
		//Canvas Design
		losePane.getChildren().add(loseCanvas);
		losegc.setFill(Color.BLACK);
		losegc.fillRect(0, 0, loseCanvas.getWidth(), loseCanvas.getHeight());
		
		//Game Over Design
		loseTitle.setText("GAME OVER!");
		loseTitle.setLayoutX(scene.getWidth()/15);
		loseTitle.setLayoutY(scene.getHeight()/6);
		loseTitle.setFont(new Font("Arial", 45));
		loseTitle.setTextFill(Color.web("white"));
		losePane.getChildren().add(loseTitle);
		
		restart.setText("Restart");
		restart.setLayoutX(scene.getWidth()/4);
		restart.setLayoutY(scene.getHeight()/1.5);
		losePane.getChildren().add(restart);
		restart.setOnAction(restartBHandler);
		
		exit.setText("Exit");
		exit.setLayoutX(scene.getWidth()/1.35);
		exit.setLayoutY(scene.getHeight()/1.5);
		losePane.getChildren().add(exit);
		exit.setOnAction(exitBHandler);
		
		//Congratulations Scene
		winPane = new Pane();
		winScene = new Scene(winPane,800,600);
		winCanvas = new Canvas(800,600);
		wingc = winCanvas.getGraphicsContext2D();
		
		//Canvas Design
		winPane.getChildren().add(winCanvas);
		wingc.setFill(Color.BLACK);
		wingc.fillRect(0, 0, winCanvas.getWidth(), winCanvas.getHeight());
		
		//Congratulations Design
		winTitle.setText("CONGRATULATIONS! YOU WON!");
		winTitle.setLayoutX(scene.getWidth()/15);
		winTitle.setLayoutY(scene.getHeight()/6);
		winTitle.setFont(new Font("Arial", 45));
		winTitle.setTextFill(Color.web("white"));
		winPane.getChildren().add(winTitle);
		
		restart.setText("Restart");
		restart.setLayoutX(scene.getWidth()/4);
		restart.setLayoutY(scene.getHeight()/1.5);
		winPane.getChildren().add(restart);
		restart.setOnAction(restartBHandler);
		
		exit.setText("Exit");
		exit.setLayoutX(scene.getWidth()/1.35);
		exit.setLayoutY(scene.getHeight()/1.5);
		winPane.getChildren().add(exit);
		exit.setOnAction(exitBHandler);
	}

}