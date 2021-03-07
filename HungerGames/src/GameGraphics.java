
/*NAME:TZOULIO LOULI
 *AEM:9567
 *PHONE:6972847108
 *email:julioluli15@gmail.com
 *         
 *NAME:KONSTANTINOS PETRIDIS
 *AEM:9403
 *PHONE:6978365745
 *email:petridkon@gmail.com
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameGraphics extends Application {
	public static final int Tile_Size =25;
	public static final int N =20;
	public static final int M =20;
	
	Player[] players = new Player[2];
	Button quit;
	Alert message, winnerMessage;
	
	Image[] playersImages;
	Image[] foodImages;
	Image[] weaponsImages;
	Image[] trapsImages;
	
	ImageView[] playersView;
	ImageView[] foodView ;
	ImageView[] weaponsView;
	ImageView[] trapsView;
	
	
	Label scorePlayer1=new Label();
	Label scorePlayer2=new Label();
	Label Round = new Label();
	
	
	private Group tileGroup = new Group();
	private int round;

	public GameGraphics(int round) {
		this.round = round;
	}

	public GameGraphics() {
		round = 0;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
	

	private Parent createConetent() {
		
		StackPane root = new StackPane();
		root.setPrefSize(N*Tile_Size+40,(M*Tile_Size)+100);
		root.getChildren().addAll(tileGroup);

		Board board = new Board(N, N, 6, 10, 8);

		int[][] wal = {
				{-2,-2},
				{2,-2},
				{2,2},
				{-2,2},
			};
		int[][] fal = {
				{-3,-3},
				{3,-3},
				{3,3},
				{-3,3},
		};
		int[][] tal = {
				{-4,-4},
				{4,-4},
				{4,4},
				{-4,4},
			};
		board.setWeaponAreaLimits(wal);
		board.setFoodAreaLimits(fal);
		board.setTrapAreaLimits(tal);

		board.createBoard();

		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if((i<=5 || i >=14 ) || (j <= 5 || j >= 14)) {
					Tile tile = new Tile(Tile_Size,Tile_Size,Color.GREEN);
					tile.setTranslateX(j*Tile_Size);
					tile.setTranslateY(i*Tile_Size);
					tileGroup.getChildren().add(tile);
				}

				else if((i<=6 || i >=13 ) || (j <= 6 || j >= 13)){
					Tile tile = new Tile(Tile_Size,Tile_Size,Color.BLUE);
					tile.setTranslateX(j*Tile_Size);
					tile.setTranslateY(i*Tile_Size);
					tileGroup.getChildren().add(tile);
				}

				else if((i<=7 || i >=12 ) || (j <= 7 || j >= 12)){
					Tile tile = new Tile(Tile_Size,Tile_Size,Color.GRAY);
					tile.setTranslateX(j*Tile_Size);
					tile.setTranslateY(i*Tile_Size);
					tileGroup.getChildren().add(tile);
				}

				else {
					Tile tile = new Tile(Tile_Size,Tile_Size,Color.WHITE);
					tile.setTranslateX(j*Tile_Size);
					tile.setTranslateY(i*Tile_Size);
					tileGroup.getChildren().add(tile);
				}
			}
		}
		
		
		ComboBox<String> comboBox1;
		comboBox1 = new ComboBox<>();
		comboBox1.setTranslateX(5);
		comboBox1.setTranslateY(520);
		comboBox1.getItems().addAll(null, "Random Player", "Heuristic Player");
		comboBox1.setPromptText("Player1 Type:");

		ComboBox<String> comboBox2;
		comboBox2 = new ComboBox<>();
		comboBox2.setTranslateX(355);
		comboBox2.setTranslateY(520);
		comboBox2.getItems().addAll(null, "Random Player", "Heuristic Player");
		comboBox2.setPromptText("Player2 Type:");

		Button generate_Board = new Button("Generate Board");
		generate_Board.setTranslateX(155);
		generate_Board.setTranslateY(520);
		players[0] = new Player(0, "Player0", board, 0, -N/2, -N/2, null, null, null);
		players[1] = new Player(0, "Player0", board, 0, N/2, N/2, null, null, null);
		generate_Board.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
					if(comboBox1.getValue()=="Random Player") {
						players[0] = new Player(0, "Player0", board, 0, -N/2, -N/2, null, null, null);
						
					}
					else if(comboBox1.getValue()=="Heuristic Player"){
						players[0] = new HeuristicPlayer(0, "heuristicPlayer", board, 0, -N/2, -N/2, null, null, null, 3);
					}
					
					if(comboBox2.getValue()=="Random Player") {
						players[1] = new Player(1, "Player1", board, 0, N/2, N/2, null, null, null);
					}
					else if(comboBox2.getValue()=="Heuristic Player"){
						players[1] = new HeuristicPlayer(1, "heuristicPlayer", board, 0, N/2, N/2, null, null, null, 3);
					}
					else {
						message = new Alert(Alert.AlertType.INFORMATION);
						message.setContentText(null);
						if(message.getContentText()==null) {
						message.setTitle("");
						message.setContentText("You Should choose the type for each Player");
						message.show();
						}
					}
					generate_Board.getBlendMode();
			}
			
		});

		Button play = new Button("Play");
		play.setTranslateX(260);
		play.setTranslateY(520);
		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(comboBox1.getValue()==null || comboBox2.getValue()==null) {
					message = new Alert(Alert.AlertType.INFORMATION);
					message.setContentText(null);
					if(message.getContentText()==null) {
					message.setTitle("");
					message.setContentText("You Should choose the type for each Player");
					message.show();
					}
				}
				else {
					round ++;
					if(comboBox1.getValue()=="Random Player" && comboBox2.getValue()=="Random Player") {
						players[0].move();
						players[1].move();

						for(int i=0; i<players.length; i++) {
							if(players[i].getX()>0) 
								playersView[i].setTranslateX(players[i].getX()*25+225);
							else
								playersView[i].setTranslateX(players[i].getX()*25+250);
							if(players[i].getY()>0) 
								playersView[i].setTranslateY(players[i].getY()*25+225);
							else
								playersView[i].setTranslateY(players[i].getY()*25+250);
						}
					}
					if(comboBox1.getValue()=="Heuristic Player" && comboBox2.getValue()=="Heuristic Player") {
						((HeuristicPlayer)players[0]).move(players[0]);
						((HeuristicPlayer)players[1]).move(players[1]);

						for(int i=0; i<players.length; i++) {
							if(players[i].getX()>0) 
								playersView[i].setTranslateX(players[i].getX()*25+225);
							else
								playersView[i].setTranslateX(players[i].getX()*25+250);
							if(players[i].getY()>0) 
								playersView[i].setTranslateY(players[i].getY()*25+225);
							else
								playersView[i].setTranslateY(players[i].getY()*25+250);
						}
					}
					if(comboBox1.getValue()=="Random Player" && comboBox2.getValue()=="Heuristic Player") {
						players[0].move();
						((HeuristicPlayer)players[1]).move(players[1]);

						for(int i=0; i<players.length; i++) {
							if(players[i].getX()>0) 
								playersView[i].setTranslateX(players[i].getX()*25+225);
							else
								playersView[i].setTranslateX(players[i].getX()*25+250);
							if(players[i].getY()>0) 
								playersView[i].setTranslateY(players[i].getY()*25+225);
							else
								playersView[i].setTranslateY(players[i].getY()*25+250);
						}
						
					}
					if(comboBox1.getValue()=="Heuristic Player" && comboBox2.getValue()=="Random Player") {
						((HeuristicPlayer)players[0]).move(players[0]);
						players[1].move();

						for(int i=0; i<players.length; i++) {
							if(players[i].getX()>0) 
								playersView[i].setTranslateX(players[i].getX()*25+225);
							else
								playersView[i].setTranslateX(players[i].getX()*25+250);
							if(players[i].getY()>0) 
								playersView[i].setTranslateY(players[i].getY()*25+225);
							else
								playersView[i].setTranslateY(players[i].getY()*25+250);
						}
						
					}
					

					if(board.getM() == 4 && board.getN() == 4){
						for(int i=0; i<players.length; i++) {
							if(players[0].getScore() > players[1].getScore()) {
								if(winnerMessage.getContentText()==null) {
									winnerMessage.setTitle("Winner");
									winnerMessage.setContentText("Player1 wins\nPlayer1 score:"+players[0].getScore()+"\nPlayer2 score:"+players[1].getScore());
									winnerMessage.show();
								}
							}
							else if(players[0].getScore() < players[1].getScore()) {
								if(winnerMessage.getContentText()==null) {
									winnerMessage.setTitle("Winner");
									winnerMessage.setContentText("Player2 wins\nPlayer1 score:"+players[0].getScore()+"\nPlayer2 score:"+players[1].getScore());
									winnerMessage.show();
								}
							}
							else {
								if(winnerMessage.getContentText()==null) {
									winnerMessage.setTitle("Winner");
									winnerMessage.setContentText("It's a DRAW"+"\nPlayer1 score:"+players[0].getScore()+"\nPlayer2 score:"+players[1].getScore());
									winnerMessage.show();
								}
							}
						}
					}

					winnerMessage = new Alert(Alert.AlertType.INFORMATION);
					winnerMessage.setContentText(null);
					boolean kill=false;
					if(comboBox1.getValue()=="Heuristic Player" && comboBox2.getValue()=="Heuristic Player") {
						kill=((HeuristicPlayer)players[0]).kill(players[1], players[0], 2);
						if(kill) {
							if(winnerMessage.getContentText()==null) {
								winnerMessage.setTitle("");
								winnerMessage.setContentText("Player2 kills Player1");
								winnerMessage.show();
							}
						}
						
						kill= false;
						kill=((HeuristicPlayer)players[1]).kill(players[0], players[1], 2);
						if(kill) {
							winnerMessage = new Alert(Alert.AlertType.INFORMATION);
							winnerMessage.setContentText(null);
							if(winnerMessage.getContentText()==null) {
								winnerMessage.setTitle("");
								winnerMessage.setContentText("Player1 kills Player2");
								winnerMessage.show();
							}
						}
					}
					kill=false;
					if(comboBox1.getValue()=="Heuristic Player" ) {
						kill=((HeuristicPlayer)players[0]).kill(players[0], players[1], 2);
						if(kill) {
							
							winnerMessage = new Alert(Alert.AlertType.INFORMATION);
							winnerMessage.setContentText(null);
							if(winnerMessage.getContentText()==null) {
								winnerMessage.setTitle("");
								winnerMessage.setContentText("Player1 kills Player2");
								winnerMessage.show();
							}
						}
						kill= false;
						kill=((HeuristicPlayer)players[0]).kill(players[1], players[0], 2);
						if(kill) {
							winnerMessage = new Alert(Alert.AlertType.INFORMATION);
							winnerMessage.setContentText(null);
							if(winnerMessage.getContentText()==null) {
								winnerMessage.setTitle("");
								winnerMessage.setContentText("Player2 kills Player1");
								winnerMessage.show();
							}
						
						}
					}
					kill=false;
					if(comboBox2.getValue()=="Heuristic Player" ) {
						
						kill=((HeuristicPlayer)players[1]).kill(players[1], players[0], 2);
						if(kill) {
							
							winnerMessage = new Alert(Alert.AlertType.INFORMATION);
							winnerMessage.setContentText(null);
							if(winnerMessage.getContentText()==null) {
								winnerMessage.setTitle("");
								winnerMessage.setContentText("Player2 kills Player1");
								winnerMessage.show();
							}
						}
						kill= false;
						kill=((HeuristicPlayer)players[1]).kill(players[0], players[1], 2);
						if(kill) {
							winnerMessage = new Alert(Alert.AlertType.INFORMATION);
							winnerMessage.setContentText(null);
							if(winnerMessage.getContentText()==null) {
								winnerMessage.setTitle("");
								winnerMessage.setContentText("Player1 kills Player2");
								winnerMessage.show();
							}
						
						}
					}
					

					if(round != 0 && round%3 == 0 && board.resizeBoardIsVaild(players[0], players[1]) ) {
						tileGroup.getChildren().remove(0, board.getM());
						tileGroup.getChildren().remove(board.getN()*board.getM()-2*board.getN(), board.getN()*board.getM()-board.getN());
						for(int i=0; i<board.getN()-2; i++) {
							tileGroup.getChildren().remove(board.getN()*i-i);
						}
						for(int i=0; i<board.getN()-2; i++) {
							tileGroup.getChildren().remove(board.getN()*i+board.getM()-2*(1+i));
						}
						board.resizeBoard(players[0], players[1]);
					}

					for(int i=0; i<board.getFood().length; i++)
						if(board.getFood()[i].getX()==0 || (board.getN()<6 && board.getM()<6))
							tileGroup.getChildren().remove(foodView[i]);
					
					for(int i=0; i<board.getWeapons().length; i++)
						if(board.getWeapons()[i].getX()==0)
							tileGroup.getChildren().remove(weaponsView[i]);
					
					for(int i=0; i<board.getTraps().length; i++)
						if(board.getN()<8 && board.getM()<8)
							tileGroup.getChildren().remove(trapsView[i]);
	
					scorePlayer1.setText("Player 1 score:"+Integer.toString(players[0].getScore()));
					scorePlayer1.setTranslateX(5);
					scorePlayer1.setTranslateY(545);
					scorePlayer2.setText("Player 2 score:"+Integer.toString(players[1].getScore()));
					scorePlayer2.setTranslateX(355);
					scorePlayer2.setTranslateY(545);
					Round.setText("ROUND"+Integer.toString(round));
					Round.setTranslateX(155);
					Round.setTranslateY(545);
				}
			}
		});
		

		quit = new Button("Quit");
		quit.setTranslateX(307);
		quit.setTranslateY(520);
		

		playersImages = new Image[2];
		playersView = new ImageView[2];

		playersImages[0] = new Image("Images/Player1.png");
		playersImages[1] = new Image("Images/Player2.png");
		
		for(int i=0; i<playersImages.length; i++) {
			playersView[i] = new ImageView();
			playersView[i].setImage(playersImages[i]);
			playersView[i].setFitWidth(25);
			playersView[i].setFitHeight(25);
			tileGroup.getChildren().add(playersView[i]);
			if(players[i].getX()>0) 
				playersView[i].setTranslateX(players[i].getX()*25+225);
			else
				playersView[i].setTranslateX(players[i].getX()*25+250);
			if(players[i].getY()>0) 
				playersView[i].setTranslateY(players[i].getY()*25+225);
			else
				playersView[i].setTranslateY(players[i].getY()*25+250);
		}
		
		foodImages = new Image[10];
		for(int i=0; i<10; i++) {
			if(i<2) 
				foodImages[i]=new Image("File:src/Images/Apple.png");
			else if(i<4)
				foodImages[i]=new Image("Images/Bananas.png");
			else if(i<7)
				foodImages[i]=new Image("Images/BlueBerries.png");
			else
				foodImages[i]=new Image("Images/Cherry.png");
		}
		
		foodView = new ImageView[10];
		for(int i=0; i<board.getFood().length; i++) {
			foodView[i]= new ImageView();
			foodView[i].setImage(foodImages[i]);
			foodView[i].setFitWidth(25);
			foodView[i].setFitHeight(25);
			tileGroup.getChildren().addAll(foodView[i]);
			
			if(board.getFood()[i].getX()>0)
				foodView[i].setTranslateX(25*board.getFood()[i].getX()+225);
			else
				foodView[i].setTranslateX(25*board.getFood()[i].getX()+250);
			
			if(board.getFood()[i].getY()>0) 
				foodView[i].setTranslateY(25*board.getFood()[i].getY()+225);
			else
				foodView[i].setTranslateY(25*board.getFood()[i].getY()+250);
		}
		
		weaponsImages = new Image[10];
		for(int i=0; i<board.getWeapons().length; i++) {
			if(board.getWeapons()[i].getType()=="pistol") 
				weaponsImages[i]=new Image("File:src/Images/Pistol.png");
			else if(board.getWeapons()[i].getType()=="bow")
				weaponsImages[i]=new Image("Images/Bow.png");
			else if(board.getWeapons()[i].getType()=="sword")
				weaponsImages[i]=new Image("Images/Sword.png");
		}
		
		weaponsView = new ImageView[10];
		for(int i=0; i<board.getWeapons().length; i++) {
			weaponsView[i]= new ImageView();
			weaponsView[i].setImage(weaponsImages[i]);
			weaponsView[i].setFitWidth(25);
			weaponsView[i].setFitHeight(25);
			tileGroup.getChildren().addAll(weaponsView[i]);
			
			if(board.getWeapons()[i].getX()>0)
				weaponsView[i].setTranslateX(25*board.getWeapons()[i].getX()+225);
			else
				weaponsView[i].setTranslateX(25*board.getWeapons()[i].getX()+250);
			
			if(board.getWeapons()[i].getY()>0) 
				weaponsView[i].setTranslateY(25*board.getWeapons()[i].getY()+225);
			else
				weaponsView[i].setTranslateY(25*board.getWeapons()[i].getY()+250);
		}
		
		trapsImages = new Image[10];
		for(int i=0; i<board.getTraps().length; i++) {
			if(board.getTraps()[i].getType()=="animals") 
				trapsImages[i]=new Image("File:src/Images/Lion.png");
			else if(board.getTraps()[i].getType()=="ropes")
				trapsImages[i]=new Image("Images/Trap.png");
		}
		
		trapsView = new ImageView[10];
		for(int i=0; i<board.getTraps().length; i++) {
			trapsView[i]= new ImageView();
			trapsView[i].setImage(trapsImages[i]);
			trapsView[i].setFitWidth(25);
			trapsView[i].setFitHeight(25);
			tileGroup.getChildren().addAll(trapsView[i]);
			
			if(board.getTraps()[i].getX()>0)
				trapsView[i].setTranslateX(25*board.getTraps()[i].getX()+225);
			else
				trapsView[i].setTranslateX(25*board.getTraps()[i].getX()+250);
			
			if(board.getTraps()[i].getY()>0) 
				trapsView[i].setTranslateY(25*board.getTraps()[i].getY()+225);
			else
				trapsView[i].setTranslateY(25*board.getTraps()[i].getY()+250);
		}
		// prosthetoume ta buttons
		tileGroup.getChildren().addAll(generate_Board, play, quit, comboBox1, comboBox2);

		tileGroup.getChildren().addAll(scorePlayer1,scorePlayer2,Round);
		
		return root;
	}

	@Override
	public void start (Stage primaryStage) throws Exception {
		Scene scene = new Scene(createConetent());
		primaryStage.setTitle("HUNGER GAME");
		primaryStage.setScene(scene);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		quit.setOnAction(e -> primaryStage.close());
	}
	public static void main(String[] args) {launch(args);}
}