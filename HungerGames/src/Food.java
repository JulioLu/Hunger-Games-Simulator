public class Food {
	
	private int id;
	private int x;
	private int y;
	private int points;
	
	public Food() {
		this.id = -1;
		this.x = 0;
		this.y = 0;
		this.points = 0;
	}
	
	public Food(int id, int x, int y, int points) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.points = points;
	}
	
	public Food(Food f) {
		this.id = f.id;
		this.x = f.x;
		this.y = f.y;
		this.points = f.points;
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
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
}
