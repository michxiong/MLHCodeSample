import static org.junit.Assert.*;

import java.util.LinkedList;

import javax.swing.JLabel;

import org.junit.Test;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {
	JLabel status = new JLabel("running...");
    GameCourt court = new GameCourt(status);
    @Test
    public void testEdgesSelectedNoBoxesComplete() {
    	JLabel status = new JLabel("running...");
        GameCourt court = new GameCourt(status);
        court.reset();
        Edge[][] h = court.getHEdges();
        Edge[][] v = court.getVEdges();
        Box[][] b = court.getBoxes();
        
        //three edges of box 0,0 selected
        h[0][0].setIsSelected(true);
        h[1][0].setIsSelected(true);
        v[0][0].setIsSelected(true);
        
        assertFalse(b[0][0].isBoxComplete());
    }
    
    @Test
    public void testEdgeSelectedOneBoxComplete() {
    	JLabel status = new JLabel("running...");
        GameCourt court = new GameCourt(status);
        court.reset();
        Edge[][] h = court.getHEdges();
        Edge[][] v = court.getVEdges();
        Box[][] b = court.getBoxes();
        
        //all four edges of box 0,0 selected
        h[0][0].setIsSelected(true);
        h[1][0].setIsSelected(true);
        v[0][0].setIsSelected(true);
        v[0][1].setIsSelected(true);
        
        assertTrue(b[0][0].isBoxComplete());
    }
    @Test
    public void testEdgeSelectedTwoBoxesComplete() {
    	JLabel status = new JLabel("running...");
        GameCourt court = new GameCourt(status);
        court.reset();
        Edge[][] h = court.getHEdges();
        Edge[][] v = court.getVEdges();
        Box[][] b = court.getBoxes();
        
        //two adjacent boxes 1,1 and 1,2 all edges selected except the middle edge
        h[1][1].setIsSelected(true);
        h[1][2].setIsSelected(true);
        h[2][1].setIsSelected(true);
        h[2][2].setIsSelected(true);
        v[1][1].setIsSelected(true);
        v[1][3].setIsSelected(true);
        
        assertFalse(b[1][1].isBoxComplete());
        assertFalse(b[1][2].isBoxComplete());
        
        v[1][2].setIsSelected(true);
        
        assertTrue(b[1][1].isBoxComplete());
        assertTrue(b[1][2].isBoxComplete());
    }
    @Test
    public void testAllEdgesSelectedBoardComplete() {
    	JLabel status = new JLabel("running...");
        GameCourt court = new GameCourt(status);
        court.reset();
        Edge[][] h = court.getHEdges();
        Edge[][] v = court.getVEdges();
        Box[][] b = court.getBoxes();
        
        for (int i = 0; i < h.length; i++) {
        	for (int j = 0; j < h[i].length; j++) {
        		h[i][j].setIsSelected(true);
        	}
        }
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
            		v[i][j].setIsSelected(true);
            }
        }
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
            		assertTrue(b[i][j].isBoxComplete());
            }
        }
        
    }
    @Test
    public void testResetNoEdgesSelected() {
    	JLabel status = new JLabel("running...");
        GameCourt court = new GameCourt(status);
        court.reset();
        Box[][] b = court.getBoxes();
        
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
            		assertFalse(b[i][j].isBoxComplete());
            }
        }
    }
    
    @Test
    public void testUndoNoMoves() {
    	JLabel status = new JLabel("running...");
        GameCourt court = new GameCourt(status);
        court.reset();
        Edge[][] h = court.getHEdges();
        Edge[][] v = court.getVEdges();
        Box[][] b = court.getBoxes();
        LinkedList<Move> m = court.getM();
        
        court.undo();
        
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
            		assertFalse(b[i][j].isBoxComplete());
            }
        }
        
    }
    @Test
    public void testUndoOneMove() {
    	JLabel status = new JLabel("running...");
        GameCourt court = new GameCourt(status);
        court.reset();
        Edge[][] h = court.getHEdges();
        Edge[][] v = court.getVEdges();
        Box[][] b = court.getBoxes();
        LinkedList<Move> m = court.getM();
        
        h[0][0].setIsSelected(true);
        Move x = new Move(false, true, h[0][0], 0, 0, status);
        m.add(x);
        
        court.undo();
        assertFalse(h[0][0].getIsSelected());
        
    }
    
    @Test
    public void testUndoTwoMoves() {
    	JLabel status = new JLabel("running...");
        GameCourt court = new GameCourt(status);
        court.reset();
        Edge[][] h = court.getHEdges();
        Edge[][] v = court.getVEdges();
        Box[][] b = court.getBoxes();
        LinkedList<Move> m = court.getM();
        
        h[0][0].setIsSelected(true);
        Move x = new Move(false, true, h[0][0], 0, 0, status);
        m.add(x);
        
        v[0][0].setIsSelected(true);
        Move y = new Move(true, false, v[0][0], 0, 0, status);
        m.add(y);
        
        court.undo();
        assertFalse(v[0][0].getIsSelected());
        assertTrue(h[0][0].getIsSelected());
        
    }
    
    @Test
    public void testUndoAfterBoxComplete() {
    	JLabel status = new JLabel("running...");
        GameCourt court = new GameCourt(status);
        court.reset();
        Edge[][] h = court.getHEdges();
        Edge[][] v = court.getVEdges();
        Box[][] b = court.getBoxes();
        LinkedList<Move> m = court.getM();
        
        h[0][0].setIsSelected(true);
        Move x = new Move(false, true, h[0][0], 0, 0, status);
        m.add(x);
        
        v[0][0].setIsSelected(true);
        Move y = new Move(true, false, v[0][0], 0, 0, status);
        m.add(y);
        
        v[0][1].setIsSelected(true);
        Move z = new Move(false, true, v[0][1], 0, 0, status);
        m.add(z);
        
        h[1][0].setIsSelected(true);
        Move k = new Move(true, false, h[1][0], 1, 1, status);
        m.add(k);
        
        court.undo();
        assertEquals(0, court.getBoxesComplete());
        assertFalse(h[1][0].getIsSelected());
    }
}
