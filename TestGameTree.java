import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class TestGameTree extends TestCase {
  private ChoiceContainer container;
  protected void setUp(){
    container = new ChoiceContainer();
  }
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testCreateTree() {
    
    container.add(3);
    container.add(2);
    GameTree tree = new GameTree(10, 30, container);
    tree.createTree();
    
    assertTrue(tree.root.child.get(0).winValue == 0);
    assertTrue(tree.root.child.get(1).winValue == 0);
  }
  /**
   * Making the node class an inner class of the game tree makes it impossible to test
   * most of the methods in the tree.  Therefore, instead of testing from the bottom up,
   * I'm testing from the top down.  I could have made the node class a separate public class,
   * which would enable testing of all of the methods.  That can be done if these top down
   * tests fail.
   */ 
  public void testCreatingAnEntireTree(){
    
    container.add(3);
    container.add(2);
    GameTree tree = new GameTree(10, 30, container);
    tree.createTree();
    assertTrue(tree.root.child.get(0).position == 7);
    assertTrue(tree.root.child.get(1).position == 8);
    
    // check under the 7
    assertTrue(tree.root.child.get(0).child.get(0).position == 4);
    assertTrue(tree.root.child.get(0).child.get(1).position == 5);
    assertTrue(tree.root.child.get(0).child.get(0).child.get(0).position == 1);
    assertTrue(tree.root.child.get(0).child.get(0).child.get(1).position == 2);
    assertTrue(tree.root.child.get(0).child.get(0).child.get(1).child.get(0).position == 0);
    
    // check under the 5 from before
    assertTrue(tree.root.child.get(0).child.get(1).child.get(0).position == 2);
    assertTrue(tree.root.child.get(0).child.get(1).child.get(1).position == 3);
    assertTrue(tree.root.child.get(0).child.get(1).child.get(0).child.get(0).position == 0);
    assertTrue(tree.root.child.get(0).child.get(1).child.get(1).child.get(0).position == 0);
    assertTrue(tree.root.child.get(0).child.get(1).child.get(1).child.get(1).position == 1);
    
    // check under the 8 from before
    assertTrue(tree.root.child.get(1).child.get(0).position == 5);
    assertTrue(tree.root.child.get(1).child.get(1).position == 6);
    
    // under the 5
    assertTrue(tree.root.child.get(1).child.get(0).child.get(0).position == 2);
    assertTrue(tree.root.child.get(1).child.get(0).child.get(1).position == 3);
    assertTrue(tree.root.child.get(1).child.get(0).child.get(0).child.get(0).position == 0);
    assertTrue(tree.root.child.get(1).child.get(0).child.get(1).child.get(0).position == 0);
    assertTrue(tree.root.child.get(1).child.get(0).child.get(1).child.get(1).position == 1);
    
    // under the 6
    assertTrue(tree.root.child.get(1).child.get(1).child.get(0).position == 3);
    assertTrue(tree.root.child.get(1).child.get(1).child.get(1).position == 4);
    
    assertTrue(tree.root.child.get(1).child.get(1).child.get(0).child.get(0).position == 0);
    assertTrue(tree.root.child.get(1).child.get(1).child.get(0).child.get(1).position == 1);
    
    assertTrue(tree.root.child.get(1).child.get(1).child.get(1).child.get(0).position == 1);
    assertTrue(tree.root.child.get(1).child.get(1).child.get(1).child.get(1).position == 2);
    assertTrue(tree.root.child.get(1).child.get(1).child.get(1).child.get(1).child.get(0).position == 0);
  }
  /**
   * This tree is limited by depth, so it should be cutoff and have some winValues of 5.
   */ 
  public void testCreatingTreeLimitedByDepth(){
    
    container.add(3);
    container.add(2);
    GameTree tree = new GameTree(10, 3, container);
    tree.createTree();
    
    // the win values should both be 5's at the top
    assertTrue(tree.root.child.get(0).winValue == 5);
    assertTrue(tree.root.child.get(1).winValue == 5);
    
    // it should be the same as the tree before, only cutoff sooner
                 
    assertTrue(tree.root.child.get(0).position == 7);
    assertTrue(tree.root.child.get(1).position == 8);
               
    // check under the 7
    assertTrue(tree.root.child.get(0).child.get(0).position == 4);
    assertTrue(tree.root.child.get(0).child.get(1).position == 5);
    assertTrue(tree.root.child.get(0).child.get(0).child.get(0).position == 1);
    assertTrue(tree.root.child.get(0).child.get(0).child.get(1).position == 2);
    
    // the child array of the last node should be empty because the depth limited it
    assertTrue(tree.root.child.get(0).child.get(0).child.get(1).child.size() == 0);
    
    // check under the 5 from before
    assertTrue(tree.root.child.get(0).child.get(1).child.get(0).position == 2);
    assertTrue(tree.root.child.get(0).child.get(1).child.get(1).position == 3);
    
    // check under the 8 from before
    assertTrue(tree.root.child.get(1).child.get(0).position == 5);
    assertTrue(tree.root.child.get(1).child.get(1).position == 6);
    
    // under the 5
    assertTrue(tree.root.child.get(1).child.get(0).child.get(0).position == 2);
    assertTrue(tree.root.child.get(1).child.get(0).child.get(1).position == 3);
    
    // under the 6
    assertTrue(tree.root.child.get(1).child.get(1).child.get(0).position == 3);
    assertTrue(tree.root.child.get(1).child.get(1).child.get(1).position == 4);
  }
  
  public void testGetMove(){
    
    container.add(3);
    container.add(2);
    GameTree tree = new GameTree(10, 3, container);
    tree.createTree();
    
    int moveValueOne = tree.getMove();
    // with this tree, both moves have the same win value of 5
    assertTrue(moveValueOne == 2 || moveValueOne == 3);
    
    // increased depth, both moves have the same win value of 0
    
    GameTree tree2 = new GameTree(10, 30, container);
    tree2.createTree();
    int moveValue = tree2.getMove();
    assertTrue(moveValue == 2 || moveValue == 3);
    
    // different tree, choosing two has a win value of 10, choosing 3 has a value of 0
    // 2 should be chosen every time
    GameTree tree3 = new GameTree(12, 30, container);
    tree3.createTree();
    System.out.println(tree3.getMove());
    assertTrue(tree3.root.getChild(0).winValue == 0);
    assertTrue(tree3.root.getChild(1).winValue == 10);
    assertTrue(tree3.getMove() == 2);
    
  }
  /**
   * Test getMove if the tree was built with 0 depth (it wasn't built)
   */ 
  public void testGetMoveZeroDepth(){
    container.add(3);
    container.add(2);
    GameTree tree = new GameTree(10,0,container);
    tree.createTree();
    assertTrue(tree.getMove() == 2);
  }
}
