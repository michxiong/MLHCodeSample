import java.awt.Color;
import java.awt.Graphics;

public class Edge {
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	private boolean isSelected;
	private Color color;
	
	public Edge(int px1, int py1, int px2, int py2) {
		x1 = px1;
		y1 = py1;
		x2 = px2;
		y2 = py2;
		isSelected = false;
		color = Color.BLACK;
	}
	
	public int getX1() {
		return x1;
	}
	public int getX2() {
		return x2;
	}
	public int getY1() {
		return y1;
	}
	public int getY2() {
		return y2;
	}
	public boolean getIsSelected() {
		return isSelected;
	}
	public Color getColor() {
		return color;
	}
	public void setIsSelected(boolean b) {
		isSelected = b;
	}
	public void setColor (Color c) {
		color = c;
	}
	public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawLine(x1, y1, x2, y2);
    }
}