
public class Weapon {

	private int id;
	private int x;
	private int y;
	private int playerId;
	private String type;
	
	public Weapon() {
		this.id = -1;
		this.x = 0;
		this.y = 0;
		this.playerId = -1;
		this.type = "";
	}
	
	public Weapon(int id, int x, int y, int playerId, String type) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.playerId = playerId;
		this.type = type;
	}
	
	public Weapon(Weapon w) {
		this.id = w.id;
		this.x = w.x;
		this.y = w.y;
		this.playerId = w.playerId;
		this.type = w.type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
