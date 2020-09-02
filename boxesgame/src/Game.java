/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("DOTS AND BOXES");
        frame.setLocation(300, 300);
        
        final JTabbedPane tabs = new JTabbedPane();
        frame.add(tabs, BorderLayout.CENTER);
        
        final JPanel instructions_tab = new JPanel();
        tabs.addTab("Instructions", instructions_tab);
        
        final JTextArea instruct = new JTextArea("Dots and Boxes is a two player game where players"
        		+ "\n" + " take turns drawing lines between the grid of 5X5"
        		+ "\n" + " dots to complete boxes" + "\n" 
        		+ "1. Player A goes first and players alternate turns." + "\n"
        		+ "2. Player who draws the fourth line that completes"
        		+ "\n" + " a box gets a point and "
        		+ "takes another turn." + "\n"
        		+ "3. Game ends when all lines on grid are drawn. "
        		+ "\n" + "Player with most points wins."
        		+ "\n" + "Reset button: starts new game"
        		+ "\n" + "Undo button: undo previous move"
        		+ "\n" + "Save button: save a game"
        		+ "\n" + "Load button: load a saved game"
        		+ "\n" + "Note: undo is not supported for moves from"
        		+ "\n" + "loaded games.");
        instructions_tab.add(instruct);
        
        final JPanel game_tab = new JPanel(new BorderLayout());
        tabs.add("Game", game_tab);
        
        // Status panel
        final JPanel status_panel = new JPanel();
        game_tab.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
        game_tab.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        game_tab.add(control_panel, BorderLayout.NORTH);
        

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);
        
        //Function panel 
        final JPanel function_panel = new JPanel();
        function_panel.setLayout(new BoxLayout(function_panel, 1));
        game_tab.add(function_panel, BorderLayout.EAST);
        
        //undo button
        final JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		court.undo();
        	}
        });
        //function_panel.add(undo, BorderLayout.NORTH);
        function_panel.add(undo);
        
        //save button
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					court.save();
				} catch (IOException e1) {
					status.setText("Problem with saving file");
				}
        	}
        });
        function_panel.add(save);
        
        //load button
        final JButton load = new JButton("Load");
        load.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					court.load();
				} catch (IOException e1) {
					status.setText("Problem with loading file");
				}
        	}
        });
        //function_panel.add(load, BorderLayout.SOUTH);
        function_panel.add(load);
        
        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}