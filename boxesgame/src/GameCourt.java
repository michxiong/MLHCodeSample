/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;
import java.io.*;
/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {
	 //state of game logic
	private Edge[][] hEdges = new Edge[5][4];
	private Edge[][] vEdges = new Edge[4][5];
	private Box[][] boxes = new Box[4][4];
	private Player a; 
	private Player b;
	private int boxesComplete;
	private JLabel status; // Current status text
	private LinkedList<Move> m;
	
	//game constants
	public static final int COURT_WIDTH = 420;
    public static final int COURT_HEIGHT = 420;
	
    //helper to know how many boxes on board are completed
    private int numBoxesComplete() {
    	int acc = 0;
    	for (int row = 0; row < boxes.length; row++) {
    		for (int col = 0; col < boxes[row].length; col++) {
    			if (boxes[row][col].isBoxComplete()) {
    				acc += 1;
    			}
    		}
    	}
    	return acc;
    }
	 public GameCourt(JLabel status) {
	     //add mouselistener for clicks on lines
	   	 addMouseListener(new MouseAdapter() {
	   		 public void mouseClicked(MouseEvent e) {
	   			 //horizontal lines
	   			 for (int row = 0; row < hEdges.length; row++) {
	   				 for (int col = 0; col < hEdges[row].length; col++) {
	   					 int lowerX = hEdges[row][col].getX1();
	   					 int upperX = hEdges[row][col].getX2();
	   					 //sensitivity range of +- 5 pixels added because had to click exactly
	   					 int lowerY = hEdges[row][col].getY1() - 5;
	   					 int upperY = hEdges[row][col].getY2() + 5;
	   					 //detect if click was on this edge
	   					 if (e.getX() >= lowerX && e.getX() <= upperX && e.getY() >= lowerY
	   							 && e.getY() <= upperY) {
	   						 //change edge to selected 
	   						 hEdges[row][col].setIsSelected(true);
	   						 //get number of new completed boxes
	   						 int boxesComplete2 = numBoxesComplete();
	   						 int boxesChange = boxesComplete2 - boxesComplete;
	   						 //identify whose player turn it is and update their profile
	   						 if (a.getMyTurn()) {
	   							//change color of line
	   							hEdges[row][col].setColor(a.getColor());
	   							//check if boxes were complete 
	   							if (boxesChange != 0) {
	   								//add points for player
	   								a.incrMyScore(boxesChange);
	   								//update overall box complete state
	   								boxesComplete = boxesComplete2;
	   								//player A takes another turn
	   								status.setText("Player A's Turn! Player A: " + 
			   								a.getMyScore() + " Player B: " + b.getMyScore());
	   							} else {
	   								//if no boxes are complete, switch turns
	   								a.myTurnSwitch();
	   								b.myTurnSwitch();
	   								status.setText("Player B's Turn! Player A: " + a.getMyScore()
	   								+ " Player B: " + b.getMyScore());
	   							}
	   							//add move to linked list 
	   							Move next = new Move(a.getMyTurn(), b.getMyTurn()
	   									, hEdges[row][col], boxesComplete,
	   									boxesChange, status);
	   							m.add(next);
	   						 } else if (b.getMyTurn()) {
	   							 //same updates happen for player b
		   							hEdges[row][col].setColor(b.getColor());
		   							//check if boxes were complete 
		   							if (boxesChange != 0) {
		   								//add points for player
		   								b.incrMyScore(boxesChange);
		   								//update overall box complete state
		   								boxesComplete = boxesComplete2;
		   								//player B takes another turn
		   								status.setText("Player B's Turn! Player A: "+ a.getMyScore()
		   								+ " Player B: " + b.getMyScore());
		   							} else {
		   								//if no boxes are complete, switch turns
		   								a.myTurnSwitch();
		   								b.myTurnSwitch();
		   								status.setText("Player A's Turn! Player A: " + 
		   								a.getMyScore() + " Player B: " + b.getMyScore());
		   							}
		   						//add move to linked list 
		   							Move next = new Move(a.getMyTurn(), b.getMyTurn(),
		   									hEdges[row][col], boxesComplete,
		   									boxesChange, status);
		   							m.add(next);
	   						 }
	   						 //check if game is over
	   						 if (boxesComplete == 16) {
	   							if (a.getMyScore() > b.getMyScore()) {
	   								status.setText("Player A wins!");
	   							} else if (a.getMyScore() < b.getMyScore()) {
	   								status.setText("Player B wins!");
	   							} else {
	   								status.setText("Player A and B tie!");
	   							}
	   						 }
	   						 repaint();
	   					 }
	   				 }
	   			 }
	   			 //vertical lines
	   			 for (int row = 0; row < vEdges.length; row++) {
	   				 for (int col = 0; col < vEdges[row].length; col++) {
	   					 int lowerX = vEdges[row][col].getX1() - 5;
	   					 int upperX = vEdges[row][col].getX2() + 5;
	   					 //sensitivity range of +- 5 pixels added because had to click exactly
	   					 int lowerY = vEdges[row][col].getY1();
	   					 int upperY = vEdges[row][col].getY2();
	   					if (e.getX() >= lowerX && e.getX() <= upperX && e.getY() >= lowerY
	   							 && e.getY() <= upperY) {
	   					//change edge to selected 
	   						 vEdges[row][col].setIsSelected(true);
	   						 //get number of new completed boxes
	   						 int boxesComplete2 = numBoxesComplete();
	   						 int boxesChange = boxesComplete2 - boxesComplete;
	   						 //identify whose player turn it is and update their profile
	   						 if (a.getMyTurn()) {
	   							//change color of line
	   							vEdges[row][col].setColor(a.getColor());
	   							//check if boxes were complete 
	   							if (boxesChange != 0) {
	   								//add points for player
	   								a.incrMyScore(boxesChange);
	   								//update overall box complete state
	   								boxesComplete = boxesComplete2;
	   								//Player A gets another turn
	   								status.setText("Player A's Turn! Player A: " + 
			   								a.getMyScore() + " Player B: " + b.getMyScore());
	   							} else {
	   								//if no boxes are complete, switch turns
	   								a.myTurnSwitch();
	   								b.myTurnSwitch();
	   								status.setText("Player B's Turn! Player A: "+ a.getMyScore()
	   								+ " Player B: " + b.getMyScore());
	   							}
	   						//add move to linked list 
	   							Move next = new Move(a.getMyTurn(), b.getMyTurn(),
	   									vEdges[row][col], boxesComplete,
	   									boxesChange, status);
	   							m.add(next);
	   						 } else if (b.getMyTurn()) {
	   							 //same updates happen for player b
		   							vEdges[row][col].setColor(b.getColor());
		   							//check if boxes were complete 
		   							if (boxesChange != 0) {
		   								//add points for player
		   								b.incrMyScore(boxesChange);
		   								//update overall box complete state
		   								boxesComplete = boxesComplete2;
		   								//Player B gets another turn
		   								status.setText("Player B's Turn! Player A: "+ a.getMyScore()
		   								+ " Player B: " + b.getMyScore());
		   							} else {
		   								//if no boxes are complete, switch turns
		   								a.myTurnSwitch();
		   								b.myTurnSwitch();
		   								status.setText("Player A's Turn! Player A: " + 
				   								a.getMyScore() + " Player B: " + b.getMyScore());
		   							}
		   						//add move to linked list 
		   							Move next = new Move(a.getMyTurn(), b.getMyTurn(), 
		   									vEdges[row][col], boxesComplete,
		   									boxesChange, status);
		   							m.add(next);
	   						 }
	   						 //check if game is over
	   						 if (boxesComplete == 16) {
	   							if (a.getMyScore() > b.getMyScore()) {
	   								status.setText("Player A wins!");
	   							} else if (a.getMyScore() < b.getMyScore()) {
	   								status.setText("Player B wins!");
	   							} else {
	   								status.setText("Player A and B tie!");
	   							}
	   						 }
	   						 repaint();
	   					 }
	   				 }
	   			 }
	  
	   		 }
	   	 });
	   	this.status = status;
	 }
	 
	 //(re)set to initial state
	 public void reset() {
		 status.setText("New Game: Player A's Turn!");
		 //fill horizontal lines
		 for (int row = 0; row < hEdges.length; row++) {
			 for (int col = 0; col < hEdges[row].length; col++) {
				 int x1 = col * 100 + 5;
				 int y1 = row * 100 + 5;
				 int x2 = x1 + 100;
				 int y2 = y1;
				 hEdges[row][col] = new Edge(x1, y1, x2, y2);
			 }
		 }
		 //fill vertical lines 
		 for (int row = 0; row < vEdges.length; row++) {
			 for (int col = 0; col < vEdges[row].length; col++) {
				 int x1 = col * 100 + 5;
				 int y1 = row * 100 + 5;
				 int x2 = x1;
				 int y2 = y1 + 100;
				 vEdges[row][col] = new Edge(x1, y1, x2, y2);
			 }
		 }
		 
		 //fill 2d array of box objects 
		  for(int row = 0; row < boxes.length; row++) {
			  for(int col = 0; col < boxes[row].length; col++) {
				  boxes[row][col] = new Box(hEdges[row][col], hEdges[row + 1][col],
						            vEdges[row][col], vEdges[row][col + 1]);
			  }
		  }
		//two players 
		  a = new Player(Color.BLUE, "A", true);
		  b = new Player(Color.RED, "B", false);
		  
		//boxes complete
		  boxesComplete = 0;
		  
		//linked list
		m = new LinkedList<Move>();
		
		repaint();
	 }
	 
	 public void undo() {
	    //empty list do nothing 
		if (m.peekLast() == null) {
			return;
		}
		//remove the last move 
		Move removed = m.removeLast();
		//restore state of game to that of the previous move
		Move prev = m.peekLast();
		//if there was only one move in list 
		if (prev == null) {
			this.reset();
		} else {
			//unselect edge
			removed.getSelected().setIsSelected(false);
			//change back color of edge
			removed.getSelected().setColor(Color.BLACK);
			//change back boxes complete count 
			this.boxesComplete = prev.getBoxesComplete();
			//update state for player whose turn it was 
			if (prev.getATurn()) {
			 a.decrMyScore(removed.getBoxesChange());
			 this.status.setText("Player A's Turn! Player A: " + 
						a.getMyScore() + " Player B: " + b.getMyScore());
			} else if (prev.getBTurn()) {
			 b.decrMyScore(removed.getBoxesChange());
			 this.status.setText("Player B's Turn! Player A: "+ a.getMyScore()
					+ " Player B: " + b.getMyScore());
			}
			//change back turns
			a.setMyTurn(prev.getATurn());
			b.setMyTurn(prev.getBTurn());
		}
		repaint();
	 }
	 
	 public void save() throws IOException {
		 File file = new File("files/game.txt");
		 file.createNewFile();
		 Writer out = new FileWriter(file);
		 for (int row = 0; row < hEdges.length; row++) {
			 for (int col = 0; col < hEdges[row].length; col++) {
				 if(hEdges[row][col].getIsSelected()) {
					 out.write("1");
				 } else {
					 out.write("0");
				 }
			 }
			 out.write("\n");
		 }
		 for (int row = 0; row < hEdges.length; row++) {
			 for (int col = 0; col < hEdges[row].length; col++) {
				 Color c = hEdges[row][col].getColor();
				 if (c.equals(Color.BLACK)) {
					 out.write("0");
				 } else if(c.equals(Color.BLUE)) {
					 out.write("1");
				 } else if (c.equals(Color.RED)) {
					 out.write("2");
				 }
			 }
			 out.write("\n");
		 }
		 for (int row = 0; row < vEdges.length; row++) {
			 for (int col = 0; col < vEdges[row].length; col++) {
				 if (vEdges[row][col].getIsSelected()) {
					 out.write("1");
				 } else {
					 out.write("0");
				 }
			 }
			 out.write("\n");
		 }
		 for (int row = 0; row < vEdges.length; row++) {
			 for (int col = 0; col < vEdges[row].length; col++) {
				 Color c = vEdges[row][col].getColor();
				 if (c.equals(Color.BLACK)) {
					 out.write("0");
				 } else if(c.equals(Color.BLUE)) {
					 out.write("1");
				 } else if (c.equals(Color.RED)) {
					 out.write("2");
				 }
			 }
			 out.write("\n");
		 }
		 //Player A
		 if (a.getMyTurn()) {
			 out.write("1");
		 } else {
			 out.write("0");
		 }
		 out.write("" + a.getMyScore());
		 out.write("\n");
		 
		 //Player B
		 if (b.getMyTurn()) {
			 out.write("1");
		 } else {
			 out.write("0");
		 }
		 out.write("" + b.getMyScore());
		 out.write("\n");
		 
		 //boxescomplete
		 out.write("" + boxesComplete);
		 out.write("\n");
		 
		 //status 
		 out.write(status.getText());
		 out.write("\n");
		 
		 out.flush();
		 out.close();
		
	 }
	 
	 public void load() throws IOException {
		 BufferedReader breader = new BufferedReader(new FileReader("files/game.txt"));
		 //iterate through horizontal edges for isSelected boolean
		 for (int row = 0; row < hEdges.length; row++) {
			 String l = breader.readLine();
			 for (int col = 0; col < hEdges[row].length; col++) {
				 //illegal argument exception thrown for charAt() already checks array format
				 if (l.charAt(col) - 48 == 0) {
					 hEdges[row][col].setIsSelected(false);
				 } else if (l.charAt(col) - 48 == 1) {
					 hEdges[row][col].setIsSelected(true);
				 } else {
					 breader.close();
					 throw new IllegalArgumentException();
				 }
			 }
		 }
		 //iterate through horizontal edges for color 
		 for (int row = 0; row < hEdges.length; row++) {
			 String l = breader.readLine();
			 for (int col = 0; col < hEdges[row].length; col++) {
				 if (l.charAt(col) - 48 == 0) {
					 hEdges[row][col].setColor(Color.BLACK);
				 } else if (l.charAt(col) - 48 == 1) {
					 hEdges[row][col].setColor(Color.BLUE);
				 } else if (l.charAt(col) - 48 == 2) {
					 hEdges[row][col].setColor(Color.RED);
				 } else {
					 breader.close();
					 throw new IllegalArgumentException();
				 }
			 }
		 }
		 //vertical edges 
		 for (int row = 0; row < vEdges.length; row++) {
			 String l = breader.readLine();
			 for (int col = 0; col < vEdges[row].length; col++) {
				 if (l.charAt(col) - 48 == 0) {
					 vEdges[row][col].setIsSelected(false);
				 } else if (l.charAt(col) - 48 == 1) {
					 vEdges[row][col].setIsSelected(true);
				 } else {
					 breader.close();
					 throw new IllegalArgumentException();
				 }
			 }
		 }
		 for (int row = 0; row < vEdges.length; row++) {
			 String l = breader.readLine();
			 for (int col = 0; col < vEdges[row].length; col++) {
				 if (l.charAt(col) - 48 == 0) {
					 vEdges[row][col].setColor(Color.BLACK);
				 } else if (l.charAt(col) - 48 == 1) {
					 vEdges[row][col].setColor(Color.BLUE);
				 } else if (l.charAt(col) - 48 == 2) {
					 vEdges[row][col].setColor(Color.RED);
				 } else {
					 breader.close();
					 throw new IllegalArgumentException();
				 }
			 }
		 }
		 
		 //Player A 
		 String playerA = breader.readLine();
		 if (playerA.charAt(0) - 48 == 0) {
			 a.setMyTurn(false);
		 } else if (playerA.charAt(0) - 48 == 1) {
			 a.setMyTurn(true);
		 } else {
			 breader.close();
			 throw new IllegalArgumentException();
		 }
		 String scoreA = "" + (playerA.charAt(1) - 48);
		 if (playerA.length() == 3) {
			 scoreA += playerA.charAt(2) - 48;
		 }
		 if (Integer.parseInt(scoreA) < 0 || Integer.parseInt(scoreA) > 16) {
			 breader.close();
			 throw new IllegalArgumentException();
		 }
		 a.setMyScore(Integer.parseInt(scoreA));
		 
		 
		 //Player B
		 String playerB = breader.readLine();
		 if (playerB.charAt(0) - 48 == 0) {
			 b.setMyTurn(false);
		 } else if (playerB.charAt(0) - 48 == 1) {
			 b.setMyTurn(true);
		 }
		 String scoreB = "" + (playerB.charAt(1) - 48);
		 if (playerB.length() == 3) {
			 scoreB += playerB.charAt(2) - 48;
		 }
		 if (Integer.parseInt(scoreB) < 0 || Integer.parseInt(scoreB) > 16) {
			 breader.close();
			 throw new IllegalArgumentException();
		 }
		 b.setMyScore(Integer.parseInt(scoreB));
		 
		 //boxes complete
		 String bc = breader.readLine();
		 String x = "" + (bc.charAt(0) - 48);
		 if (bc.length() == 2) {
			 x += bc.charAt(1) - 48;
		 }
		 if (Integer.parseInt(x) < 0 || Integer.parseInt(x) > 16) {
			 breader.close();
			 throw new IllegalArgumentException();
		 }
		 this.boxesComplete = Integer.parseInt(x);
		 
		 //status
		 String s = breader.readLine();
		 if (s == null) {
			 breader.close();
			 throw new IllegalArgumentException();
		 }
		 this.status.setText(s);
		 
		 repaint();
		 m.clear();
		 
		 breader.close();
	 }
	 
	 @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        //grid of dots 
	        for (int i = 0; i < 5; i++) {
	        	for (int j = 0; j < 5; j++) {
	        		g.fillOval(i*100, j*100, 10, 10);
	        	}
	        }
	        //horizontal edges
	        for (int row = 0; row < hEdges.length; row++) {
				 for (int col = 0; col < hEdges[row].length; col++) {
					 hEdges[row][col].draw(g);
				 }
			 }
	        //vertical edges
	        for (int row = 0; row < vEdges.length; row++) {
				 for (int col = 0; col < vEdges[row].length; col++) {
					 vEdges[row][col].draw(g);
				 }
			 }
	        //boxes 
	        for (int row = 0; row < boxes.length; row++) {
	        	for (int col = 0; col < boxes[row].length; col++) {
	        		boxes[row][col].draw(g);
	        	}
	        }
	    }
	 @Override
	    public Dimension getPreferredSize() {
	        return new Dimension(COURT_WIDTH, COURT_WIDTH);
	    }
	 
	//methods for testing 
	    public Edge[][] getHEdges() {
	    	return hEdges;
	    }
	    public Edge[][] getVEdges() {
	    	return vEdges;
	    }
	    public Box[][] getBoxes() {
	    	return boxes;
	    }
	    public Player getPlayerA() {
	    	return a;
	    }
	    public Player getPlayerB() {
	    	return b;
	    }
	    public int getBoxesComplete() {
	    	return boxesComplete;
	    }
	    public LinkedList<Move> getM() {
	    	return m;
	    }
}