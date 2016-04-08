import java.util.*;
/**
 * This class is a container for the choices a player can make.
 * It uses an arraylist.
 */
public class ChoiceContainer {
  
  ArrayList<Integer> choice;
  
  
  // constructor 
  public ChoiceContainer(){
    
    choice = new ArrayList<Integer>();
  }
  
  /**
   * Adds the given value to the container as a choice.
   */ 
  public void add(int value){
    choice.add(value);
  }
  
  /**
   * Retrieves the value at a given index.
   */ 
  public int get(int index){
    return choice.get(index);
  }
  /**
   * Returns the minimum value in the container
   */ 
  public int getMin(){
    int min = choice.get(0);
    for(int i = 0; i < choice.size(); i++){
      if(choice.get(i) < min){min = choice.get(i);}
    }
    return min;
  }
  /**
   * Returns the size of the container
   */ 
  public int size(){
    return choice.size();
  }
  /**
   * Returns whether the value is in the container or not (true or false).
   */ 
  public boolean contains(int value){
    return choice.contains(value);
  }
}



