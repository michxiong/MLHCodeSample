import java.awt.Color;
import java.awt.Graphics;

public class Box {
	private Edge l1;
	private Edge l2;
	private Edge l3;
	private Edge l4;
	private Color color;
	
	public Box (Edge l1, Edge l2, Edge l3, Edge l4) {
		this.l1 = l1;
		this.l2 = l2;
		this.l3 = l3;
		this.l4 = l4;
		color = Color.BLACK;
	}
	public boolean isBoxComplete() {
		boolean b = l1.getIsSelected() && l2.getIsSelected() &&
				l3.getIsSelected() && l4.getIsSelected();
		return b;
	}
	public void draw(Graphics g) {
        g.setColor(this.color);
        if (this.isBoxComplete()) {
        	g.drawLine(l1.getX1(), l1.getY1(), l4.getX2(), l4.getY2());
        	g.drawLine(l2.getX1(), l2.getY1(), l1.getX2(), l1.getY2());
        }
    }
}