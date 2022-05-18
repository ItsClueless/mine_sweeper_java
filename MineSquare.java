/*
Dwayne Kirby
CS 110
Final Project
Create a MineSquare class that extends Square
MineSquare represents a square that is a mine
Uses the abstract methods of Square only
Uncover sets the element to a mine and IsMine returns it is a mine
*/

// Create MineSquare class that extends Square class
public class MineSquare extends Square
{
   /**
   MineSquare is the default constructor that 
   calls the super constuctor call of x, false, and false
   */ 
   public MineSquare()
   {
      super("x", false, false);
   }
   
   /**
   Uncover calls super and sets the element to '*'
   @return true that it was uncovered
   */
   @Override
   public boolean uncover()
   {
      super.setElement("*");
      return true;
   }
   
   /**
   isMine returns that square is a mine
   @return true that the square is a mine
   */
   @Override
   public boolean isMine()
   {
      return true;
   }

}