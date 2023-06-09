package guessme;

/**
 * This class defines a linked list node storing an integer.
 * Use primitive type int (do not use wrapper class Integer)
 * You must provide the following methods:
 * - a constructor
 * - a setInfo method and a getInfo method
 * - a setLink method and a getLink method
 */
public class LLIntegerNode {
  // TODO
  private int data;
  private LLIntegerNode link;
  
  public LLIntegerNode(int tempInt, LLIntegerNode tempNode) {
    data = tempInt;
    link = tempNode;
  }
 
  public void setInfo(int data) {
    this.data = data;
  }
  public int getInfo() {
    return data;
  }
  public void setLink(LLIntegerNode link) {
    this.link = link;
  }
  public LLIntegerNode getLink() {
    return link;
  }
}


