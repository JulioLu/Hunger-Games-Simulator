
public class Trap {

	private int id;
	private int x;
	private int y;
	private String type;
	private int points;
	
	public Trap() {
		this.id = -1;
		this.x = 0;
		this.y = 0;
		this.type = "";
		this.points = 0;
	}
	
	public Trap(int id, int x, int y, String type, int points) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.type = type;
		this.points = points;
	}
	
	public Trap(Trap t) {
		this.id = t.id;
		this.x = t.x;
		this.y = t.y;
		this.type = t.type;
		this.points = t.points;
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
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
}
