import javax.swing.JLabel;

public class Move {
	private boolean aTurn;
	private boolean bTurn;
	private Edge selected; 
	private int boxesComplete;
	private int boxesChange;
	private JLabel status;
	
	public Move(boolean a, boolean b, Edge s, int bc, int change, JLabel status) {
		aTurn = a;
		bTurn = b;
		selected = s;
		boxesComplete = bc;
		boxesChange = change;
		this.status = status;
	}
	
	public boolean getATurn() {
		return aTurn;
	}
	public boolean getBTurn() {
		return bTurn;
	}
	public Edge getSelected() {
		return selected;
	}
	public int getBoxesComplete() {
		return boxesComplete;
	}
	public int getBoxesChange() {
		return boxesChange;
	}
	public JLabel getStatus() {
		return status;
	}
}