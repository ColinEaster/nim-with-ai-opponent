import java.util.*;
/**
 * This program is a game of Nim.  It the player inputs parameters and then
 * plays against the computer.  The computer tries to make the best move based
 * on a game tree.
 * 
 * @author Colin Easter
 */
public class NimGame{
  // instance variables
  int total = 39; // total number of items
  String first = "computer"; // human or computer goes first
  ChoiceContainer choice; // container for possible choices
  int depth = 1; // cutoff level for game tree
  int minChoice = 2;
  boolean gameOver = false;
  
  /**
   * Main method, initializes and starts the game
   */ 
  public static void main(String[] args){
    NimGame game = new NimGame();
    game.start();
  }
  /** Method to start the game.  It initializes variables and gets input from the user.
    * It then runs the game once the setup inputs have been accepted.
    */ 
  public void start(){
    
    initializeChoiceContainer();
    Scanner scan = new Scanner(System.in);
    
   // getSetUpParameters(scan);
    run(scan);
  }
  /**
   * Method to start the game by reading in input to establish game parameters
   */ 
  public void getSetUpParameters(Scanner input){
    
    
    System.out.println("Enter the starting number of marbles.");
    total = input.nextInt();
    input.nextLine();
    
    System.out.println("Enter human to choose first or computer to let the computer go first.");
    first = input.nextLine();
    
    System.out.println("Enter removal choice options as numbers separated by spaces.");
    String choices = input.nextLine();
    
    fillChoiceContainer(choices);
    minChoice = choice.getMin();
    
    System.out.println("Enter the depth/cutoff level for the computer's search.");
    depth = input.nextInt();
    

    
  }
  /** Method that takes the string input of numbers separated by spaces
    * and uses it to fill the ChoiceContainer object.
    * Most of this came from a post on dreamincode.net.
    */ 
  public void fillChoiceContainer(String input){
    
    String[] splitString;
    splitString = input.split(" ");
    for(int i = 0; i < splitString.length; i++){
      choice.add(Integer.parseInt(splitString[i]));
    }
  }
                 
      
  public void run(Scanner scan){
    gameOver = false;
    if(first.compareTo("human") == 0){
      playerTurn(scan);
    }
    while(!gameOver){
      computerTurn(scan);
      
      if(!gameOver){playerTurn(scan);}
      
    }    
    
  }
  /**
   * Method to handle the player's turn.
   */ 
  public void playerTurn(Scanner input){
    System.out.println("There are " + total + " marbles remaining.");
    if(total < minChoice){
      
      gameOver();
      input.close();
      return;
  }
    
    boolean validInput = false;
    int playerChoice = 0;
    while(!validInput){
      playerChoice = input.nextInt();
      if(choice.contains(playerChoice)){validInput = true;}
      else{System.out.println("Please enter a valid choice.");}
    }
    total = total - playerChoice;
  }
  /**
   * Method for the computer to make a turn.  It still takes a scanner because
   * it closes it if the game is lost.
   */ 
  public void computerTurn(Scanner input){
    if(total < minChoice){
    
      gameWon();
      input.close();
      return;
    }
    GameTree tree = new GameTree(total,depth,choice);
    tree.createTree();
    int move = tree.getMove();
    System.out.println("There are " + total + " marbles.");
    System.out.println("Your opponent took " + move + " away.");
    total = total - move;
    tree = null;
    
  }
  
  /**
   * Method to end the game if the player loses.
   */ 
  public void gameOver(){
    gameOver = true;
    System.out.println("You can't make any more moves. You lost the game.");
  }
  /**
   * Method to end the game if the player wins.
   */ 
  public void gameWon(){
    gameOver = true;
    System.out.println("Your opponent can't make any more moves.  You win!");
  }
  /**
   * Initializes the ChoiceContainer variable.  This is for testing purposes.
   */ 
  public void initializeChoiceContainer(){
    choice = new ChoiceContainer();
    choice.add(2);
    choice.add(3);
    choice.add(5);
    choice.add(7);
  }
    
}