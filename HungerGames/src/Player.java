import java.util.Random;

public class Player {

	private int id;
	private String name;
	private Board board;
	private int score;
	private int x;
	private int y;
	private Weapon bow;
	private Weapon pistol;
	private Weapon sword;
	
	public Player() {
		id = -1;
		name = "";
		board = null;
		score = 0;
		x = 0;
		y = 0;
		bow = null;
		pistol = null;
		sword = null;
	}

	public Player(int id, String name, Board board, int score, int x, int y, Weapon bow, Weapon pistol, Weapon sword) {
		this.id = id;
		this.name = name;
		this.board = board;
		this.score = score;
		this.x = x;
		this.y = y;
		this.bow = bow;
		this.pistol = pistol;
		this.sword = sword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score += score;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Weapon getBow() {
		return bow;
	}

	public void setBow(Weapon bow) {
		this.bow = bow;
	}

	public Weapon getPistol() {
		return pistol;
	}

	public void setPistol(Weapon pistol) {
		this.pistol = pistol;
	}

	public Weapon getSword() {
		return sword;
	}

	public void setSword(Weapon sword) {
		this.sword = sword;
	}
	

	public int[] getRandomMove(int x, int y) {
		// Find number of possible moves
		int possibleMoves = 8;
		boolean isXEdge = x == -board.getN()/2 || x == board.getN()/2;
		boolean isYEdge = y == -board.getM()/2 || y == board.getM()/2;
		if(isXEdge && isYEdge) {
			possibleMoves = 3;
		} else if(isXEdge || isYEdge) {
			possibleMoves = 5;
		}
		
		// Get random die value
		Random r = new Random();
		int die = r.nextInt(possibleMoves);

		// Find next tile based on the die
		int moves[][] = {
				{0,-1},
				{1,-1},
				{1,0},
				{1,1},
				{0,1},
				{-1,1},
				{-1,0},
				{-1,-1},
		};
		int tiles[][] = new int[possibleMoves][2];
		int counter = 0;
		for(int i=0; i<8; i++) {
			int newx = x + moves[i][0];
			int newy = y + moves[i][1];
			// Skip row 0 and column 0
			if(newx == 0) {
				newx += moves[i][0];
			}
			if(newy == 0) {
				newy += moves[i][1];
			}
			if(board.isPositionValid(newx, newy)) {
				tiles[counter][0] = newx;
				tiles[counter][1] = newy;
				counter++;
			}
		}
		return tiles[die];
	}

	public int[] move() {
		int newPos[] = getRandomMove(x, y);
		int newx = newPos[0];
		int newy = newPos[1];
		Weapon w = null;
		Food f = null;
		Trap t = null;
		// Check if there is a weapon on the new tile
		for(int i=0; i<board.getWeapons().length; i++) {
			if(board.getWeapons()[i].getX() == newx && 
			   board.getWeapons()[i].getY() == newy &&
			   board.getWeapons()[i].getPlayerId() == id ) {
				w = board.getWeapons()[i];
				System.out.println("Found a weapon");
				if(w.getType() == "sword") {
					this.sword = w;
				} else if(w.getType() == "bow") {
					this.bow = w;
				} else if(w.getType() == "pistol") {
					this.pistol = w;
				}
				w.setX(0);
				w.setY(0);
			}
		}
		// Check if there is food on the new tile
		for(int i=0; i<board.getFood().length; i++) {
			if(board.getFood()[i].getX() == newx && board.getFood()[i].getY() == newy) {
				f = board.getFood()[i];
				System.out.println("Found food");
				this.score += f.getPoints();
				f.setX(0);
				f.setY(0);
			}
		}
		// Check if there is a trap on the new tile
		for(int i=0; i<board.getTraps().length; i++) {
			if(board.getTraps()[i].getX() == newx && board.getTraps()[i].getY() == newy) {
				t = board.getTraps()[i];
				boolean avoided = false;
				if(t.getType() == "ropes") {
					if(sword != null) {
						avoided = true;
					}
				} else if(t.getType() == "animals") {
					if(bow != null) {
						avoided = true;
					}
				}
				if(!avoided) {
					System.out.printf("Player %d (Trap) | prevscore %d, newscore %d\n", id, this.score, this.score+t.getPoints());
					this.score += t.getPoints();
				} else {
					System.out.printf("Player %d (Trap) | successfully avoided", id);
				}
			}
		}
		
		System.out.printf("Player %d (Move) | prevpos %d %d, newpos %d %d\n", id, this.x, this.y, newx, newy);
		this.x = newx;
		this.y = newy;
		return new int[] {newx, newy, w!=null?1:0, f!=null?1:0, t!=null?1:0};
	}
	
}
	
