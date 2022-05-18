/*
Dwayne Kirby
CS 110
Final Project
Create an abstract Square class
Has setters and getters for each instance varible
Has a flagSquare method that flags that square
toString method that returns the element
And two abstract methods isMine and uncover
*/

// Name Square as an abstract class
public abstract class Square
{
   // Declare instance varibles
   private String element;
   private boolean flagged;
   private boolean uncovered;
   
   /**
   Square constructor takes in an element and 2 boolean values and sets them
   @param theElement the indicated square's element
   @param theFlagged the indicated square's flag status
   @param theUncovered the indicated square's uncover status
   */
   public Square(String theElement, boolean theFlagged, boolean theUncovered)
   {
      element = theElement;
      flagged = theFlagged;
      uncovered = theUncovered;
   }
   
   /**
   isFlagged returns the flagged value
   @return flagged if the square is flagged
   */
   public boolean isFlagged()
   {
      return flagged;
   }
   
   /**
   isUncovered returns the uncovered value
   @return uncovered if the square is uncovered
   */
   public boolean isUncovered()
   {
      return uncovered;
   }
   
   /**
   flagSqaure sets the flagged value to true and element to 'f'
   */
   public void flagSquare()
   {
      flagged = true;
      element = "f";
   }
   
   /**
   setUncovered sets the uncovered value to true
   */
   public void setUncovered()
   {
      uncovered = true;
   }
   
   /**
   setElements takes an element and sets it
   @param theElement the indicate Square's element
   */
   public void setElement(String theElement)
   {
      element = theElement;
   }
   
   /**
   toString returns a formatted string of the element
   @return element the indicate Square's element
   */
   @Override
   public String toString()
   {
      return element;
   }

   /**
   uncover is an abstract method that convers a square
   @return a boolean value if the square is uncovered
   */
   public abstract boolean uncover();
   
   /**
   isMine is an abstract method that determines the sqaure is a mine
   @return a boolean value if square is a mine
   */
   public abstract boolean isMine();
   
   
}