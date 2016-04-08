import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class TestChoiceContainer extends TestCase {
  ChoiceContainer container;
  protected void setUp(){
   container = new ChoiceContainer(); 
  }
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testAdd() {
    
    container.add(1);
    assertTrue(container.choice.get(0) == 1);
    
    container.add(2);
    assertTrue(container.choice.get(0) == 1);
    assertTrue(container.choice.get(1) == 2);
    
    
  }
  public void testGet(){
    
    container.add(1);
    
    assertTrue(container.get(0)==1);
    
    container.add(2);
    assertTrue(container.get(1)==2);
    assertTrue(container.get(0)==1);
  }
  public void testGetMin(){
    
    container.add(4);
    
    assertTrue(container.getMin()== 4);
    container.add(3);
    assertTrue(container.getMin()== 3);
    container.add(5);
    assertTrue(container.getMin()== 3);
    container.add(0);
    assertTrue(container.getMin()== 0);
  }
    
}
