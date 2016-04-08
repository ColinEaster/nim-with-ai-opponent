import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class TestNimGame extends TestCase {
  NimGame game;
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  /**
   * Setup method
   */ 
  protected void setUp(){
    game = new NimGame();
  }
  /** Test filling the container with multiple choices.
    */ 
  public void testFillContainer() {
    
    game.initializeChoiceContainer();
    game.fillChoiceContainer("1 2 3 4 5");
    assertTrue(game.choice.get(0) == 1);
    assertTrue(game.choice.get(1) == 2);
    assertTrue(game.choice.get(2) == 3);
    assertTrue(game.choice.get(3) == 4);
    assertTrue(game.choice.get(4) == 5);
  }
  /**
   * Test filling the container with a single choice.
   */ 
  public void testFillContainerAgain() {
    // test case of a single choice
    
    game.initializeChoiceContainer();
    game.fillChoiceContainer("1");
    assertTrue(game.choice.get(0) == 1);
  }
  /**
   * Test the player's turn with an auto loss
    */ 
  public void testPlayerTurn(){
    game.total = 0;
    game.minChoice = 2;
    
    Scanner scan = new Scanner("1");
    game.playerTurn(scan);
    assertTrue(game.gameOver);
  }
  /**
   * Test the player's turn with a valid choice.
   */ 
  public void testPlayerTurnValid(){
    game.total = 10;
    game.minChoice = 2;
    game.initializeChoiceContainer();
    game.choice.add(2);
    game.choice.add(3);
    
    Scanner scan = new Scanner("2");
    game.playerTurn(scan);
    assertTrue(!game.gameOver);
    assertTrue(game.total == 8);
  }
  
  /**
   * Test the player's turn with an invalid choice and then a valid choice.
   */
  public void testPlayerTurnInValid(){
    game.total = 10;
    game.minChoice = 2;
    game.initializeChoiceContainer();
    game.choice.add(2);
    game.choice.add(3);
    
    Scanner scan = new Scanner("1\n2");
    game.playerTurn(scan);
    assertTrue(!game.gameOver);
    assertTrue(game.total == 8);
  }
  /**
   * Test computer with an auto loss
   */ 
  public void testComputerTurnLose(){
    game.total = 0;
    game.minChoice = 2;
    
    Scanner scan = new Scanner("1");
    game.computerTurn(scan);
    assertTrue(game.gameOver);
  }
  /**
   * Test when the computer makes a move on its turn.
   */ 
  public void testComputerTurnValid(){
    
    // set up known position where the computer will make a move of 2
    game.total = 12;
    game.minChoice = 2;
    game.initializeChoiceContainer();
    game.choice.add(2);
    game.choice.add(3);
    
    Scanner scan = new Scanner("1");
    game.computerTurn(scan);
    assertTrue(game.total == 10);
  
  }
  public void testGameOver(){
    game.gameOver();
    assertTrue(game.gameOver);
  }
  public void testGameWon(){
    game.gameWon();
    assertTrue(game.gameOver);
  }
  /**
   * getSetUpParameters gets the variables from console input.
   */ 
  public void testgetSetUpParameters(){
    
    game.initializeChoiceContainer();
    Scanner input = new Scanner("10\nhuman\n2 3\n30\n");
    game.getSetUpParameters(input);
    assertTrue(game.total == 10);
    assertTrue(game.first.compareTo("human") == 0);
    assertTrue(game.choice.get(0) == 2);
    assertTrue(game.choice.get(1) == 3);
    assertTrue(game.depth == 30);
    
    
  }
  /**
   * test getting parameters if only one choice is entered (not multiple with spaces)
   */ 
  public void testgetSetUpParametersWithOneChoice(){
    game.initializeChoiceContainer();
    Scanner input = new Scanner("10\nhuman\n2\n30");
    game.getSetUpParameters(input);
    assertTrue(game.total == 10);
    assertTrue(game.first.compareTo("human") == 0);
    assertTrue(game.choice.get(0) == 2);
    assertTrue(game.depth == 30);
  }
  /**
   * Testing by inputting some known trees.
   */ 
  public void testRun(){
    game.total = 10;
    game.first = "human";
    game.initializeChoiceContainer();
    game.choice.add(2);
    game.choice.add(3);
    game.depth = 30;
    
    Scanner input = new Scanner("3 3 3 3 3 3");
    
    // the game should end
    game.run(input);
    assertTrue(game.gameOver);
  }
  /**
   * The game should end with a the number of marbles being one.
   */ 
  public void testRunOneChoice(){
    
    game.total = 10;
    game.first = "human";
    game.initializeChoiceContainer();
    game.choice.add(3);
    game.depth = 30;
    game.minChoice = 3;
    Scanner input = new Scanner("3 3 3 3 3 3");
    
    // the game should end
    game.run(input);
    assertTrue(game.gameOver);
    assertTrue(game.total == 1);
  }
  /**
   * Same as above but with the computer choosing first
   */ 
  public void testRunOneChoiceComputerFirst(){
    
    game.total = 10;
    game.first = "computer";
    game.initializeChoiceContainer();
    game.choice.add(3);
    game.depth = 30;
    game.minChoice = 3;
    Scanner input = new Scanner("3 3 3 3 3 3");
    
    // the game should end
    game.run(input);
    assertTrue(game.gameOver);
    assertTrue(game.total == 1);
  }
  
  /**
   * Tests a run with prime numbers within the project's given range to see if it
   * will end.
   * This test has an invalid player choice included, which should be handled by the game.
   */ 
  public void testComplicatedRun(){
    game.total = 31;
    game.first = "human";
    game.initializeChoiceContainer();
    game.choice.add(3);
    game.choice.add(2);
    game.choice.add(5);
    
    game.depth = 40;
    game.minChoice = 2;
    Scanner input = new Scanner("5 5 2 2 2 2 2 2 2 2 1 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2");
    
    // the game should end
    game.run(input);
    assertTrue(game.gameOver);
    
  }
}
