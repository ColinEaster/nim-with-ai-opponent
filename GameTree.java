import java.util.*;
/**
 * This class creates a game tree for a game of Nim.  
 */
public class GameTree {
  
  int startingPosition;
  int depth;
  ChoiceContainer choice;
  int minChoice; // so the recursive method doesn't call a method everytime it needs this
  Node root;
  /**
   * Constructor that takes the starting number of items, the depth of the search,
   * and the possible choices each player can make as parameters.
   */ 
  public GameTree(int startingPosition, int depth, ChoiceContainer choice){
    
    // save parameters to instance variables 
    this.startingPosition = startingPosition;
    this. depth = depth;
    this.choice = choice;
    
    minChoice = choice.getMin();
  }
  public void createTree(){
    // create root
    root = new Node(startingPosition, false);
    // recursively create children
    createChildren(root,0);
  }
  
  public void createChildren(Node parent, int currentDepth){
    
    // for each move option
    for(int i = 0; i< choice.size(); i++){
      
      // if the choice can be made, create a child and add it to the parent's child array
      // check for depth as well
      if(parent.position - choice.get(i) >= 0 && currentDepth < depth){
        Node child = new Node(parent.position - choice.get(i), !parent.player);
        createChildren(child, currentDepth + 1);
        assignWinValue(child);
        parent.child.add(child);
      }
    }
  }
  
  /**
   * This is a helper method to assign a "win value" to a node.
   * This follows the min/max game tree algorithm.
   * A 10 is a win for the computer, 5 is unknown, and 0 is a loss.
   *
   */ 
  private void assignWinValue(Node node){
    
    // the player is either the computer or the player at that position (the one who makes the move)
    if(node.player){
      // if the position is less than the min choice, the player loses
      if(node.position < minChoice){
        node.winValue = 10;
        return;
      }
      // if a node has no children, keep the default
      // value of 5 (unknown if win or loss)
      if(node.hasNoChildren()){return;}
      
      // if the node has children, look for the min win value of the children
      int minWinValue = 10;
      for(int j = 0; j < node.getNumberOfChildren(); j++){
        if(node.getChildWinValue(j) < minWinValue){
          minWinValue = node.getChildWinValue(j);
        }
      }
      
      // set the node's win value to the minWinValue of the children
      node.winValue = minWinValue;
      return;
    }
    
    // if the player is the computer
    if(!node.player){
      
      // if the position is less than the min choice,
      // the computer loses
      if(node.position < minChoice){
        node.winValue = 0;
        return;
      }
      
      // if a node has no children, keep the default of 5
      // unknown if win or loss
      if(node.hasNoChildren()){return;}
      
      // look for the maximum win value of the children
      int maxWinValue = 0;
      for(int n = 0; n < node.getNumberOfChildren(); n++){
        if(node.getChildWinValue(n) > maxWinValue){
          maxWinValue = node.getChildWinValue(n);      
        }
      }
      
      // set the node's win value to the max value among the children
      node.winValue = maxWinValue;
    }
  }
  /**
   * Returns the move the computer should make based on the game tree win values.
   * Returns the minimum choice if there are no children.
   */ 
  public int getMove(){
    if(root.hasNoChildren()){return minChoice;}
    
    // randomly choose from a list of choices that are equally the best
    ArrayList<Integer> list = getListOfBestChoices();
    Random rand = new Random();
    return list.get(rand.nextInt(list.size()));
  }
    
    
  /**
   * Helper method to get an arraylist of the best choices.
   * They all have the same max value (i.e. 10 if the computer can guarantee a win, 5 if unknown outcome)
   */ 
  private ArrayList<Integer> getListOfBestChoices(){
    int max = getMaxChildWinValue(root);
    ArrayList<Integer> list = new ArrayList<Integer>();
    for(int g = 0; g < root.getNumberOfChildren(); g++){
      if(root.child.get(g).winValue == max){list.add(startingPosition - root.child.get(g).position);}
    }
    return list;
  }
  /**
   * Returns the maximum win value among a node's children.
   */ 
  private int getMaxChildWinValue(Node node){
    int max = 0;
    for(int i = 0; i < node.getNumberOfChildren(); i++){
      if(node.child.get(i).winValue > max){max = node.child.get(i).winValue;}
    }
    return max;
  }
  
  
  
  
  
  // inner class
  class Node{
    
    public int position;
    public boolean player; // true indicates it is the player's turn at that position
    public int winValue = 5;
    
    // references to children
    public ArrayList<Node> child;     
    
    public Node right;
    
    // constructor
    Node(int positionValue, boolean player){
      
      position = positionValue;
      this.player = player;
      child = new ArrayList<Node>();
      
      
    }
    // empty constructor
    Node(){
    }
    /**
     * Method to set the win value of a node to the given value
     */ 
    public void setWinValue(int value){
      winValue = value;
    }
    /** Returns the winValue of a child
      */ 
    public int getChildWinValue(int index){
      return child.get(index).winValue;
    }
    /**
     * Method to see if a node has any children
     */ 
    public boolean hasNoChildren(){
      return child.isEmpty();
    }
    /**
     * Returns the number of children the node has
     */ 
    public int getNumberOfChildren(){
      return child.size();
    }
    /** Returns the child node at the given index.
      */ 
    public Node getChild(int index){
      return child.get(index);
    }
  }
}