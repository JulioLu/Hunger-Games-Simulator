import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
	
	
	public Tile(int x, int y, Color c) {
		setWidth(GameGraphics.Tile_Size);
		setHeight(GameGraphics.Tile_Size);
		
		setFill(c);
		setStroke(Color.BLACK);
		
	}


}
