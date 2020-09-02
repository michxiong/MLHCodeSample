=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

Overview: I created the Box game for two players that lets them click edges to form boxes until board is filled and keeps track of their scores. Run Game.java file to play. All classes are found in the src folder. Game also includes undo and save/load game functionality.

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays
  I used 2D Arrays to store the state of all the possible edges on the board. This was an 
  appropriate use of the concept because the 2D Arrays helped me keep track of the location of each
  selected edge and determine when a box was complete. 
  2. Collections
  I used a LinkedList to store a collection of moves made in the game. This was used to implement
  the undo button functionality. To undo, a move was removed from list and game state updated 
  accordingly.
  3. File I/O
  I used File I/O to add save and load game functionality. The save method in GameCourt writes a 
  file that encodes the information for the game state. The load method reads a file with game 
  state information and updates.
  4. Testable Component
  I used JUnit testing to check that the state of the edges and boxes in the game were updating 
  correctly. I did this by creating an instance of GameCourt and then setting the game state to 
  specific scenarios. 


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  GameCourt: contains logic of game with MouseListener that updates state accordingly when
  user clicks on an edge of the board. Also contains reset, save, and load methods. 
  Edge: Object to store the coordinates of a line between two dots. Also stores color of line
  and boolean for whether it has been selected. Edges create the game board.
  Box: Object that stores four adjacent Edges. Contains method that checks if all four edges are 
  selected and then box becomes complete.
  Player: Stores a boolean for whether it is a players turn and also the player's score.
  Move: Stores information about the edge that was selected and player that took turn for each
  move for the undo functionality. 

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
The functionality of all the game logic is stored under the mouse click event which
could probably be separated better. I had two separate 2d Arrays for horizontal edges and 
vertical edges which made a lot of code repetitive. 
