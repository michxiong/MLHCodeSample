import java.awt.Color;

public class Player {
	private Color color; 
	private String name;
	private boolean myTurn;
	private int myScore;
	
	public Player(Color c, String s, boolean b) {
		color = c;
		name = s;
		myTurn = b;
		myScore = 0; 
	}
	
	public Color getColor() {
		return color;
	}
	public boolean getMyTurn() {
		return myTurn;
	}
	public int getMyScore() {
		return myScore;
	}
	public String getName() {
		return name;
	}
	public void incrMyScore(int x) {
		myScore += x;
	}
	public void decrMyScore(int x) {
		myScore -= x;
	}
	public void setMyScore(int x) {
		myScore = x;
	}
	public void myTurnSwitch() {
		myTurn = !myTurn;
	}
	public void setMyTurn(boolean b) {
		myTurn = b;
	}
}