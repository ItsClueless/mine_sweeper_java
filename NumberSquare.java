/*
Dwayne Kirby
CS 110
Final Project
Create a NumberSquare class that extends Square representing a non mine
Takes in the mines around the square and the row and column
Use the abstract methods of isMine which returns false
And uncover which sets the selected square if not flagged to correct element
*/

// Create a NumberSquare class that extends Square
public class NumberSquare extends Square
{
   // Declare instance varibles
   private int neighborMines;
   private int myRow;
   private int myCol;
   
   /**
   NumberSquare constuctor takes in mines and rows and cols and sets them
   and calls super constructor with element x, false, false
   @param mines the amount of mines
   @param rows the row selected
   @param cols the column selected
   */
   public NumberSquare(int mines, int rows, int cols)
   {
      super("x", false, false);
      neighborMines = mines;
      myRow = rows;
      myCol = cols;
   }
   
   /**
   uncover calls the super and setUncovered and
   calls super and sets the element according to the mines around by calling neighborMines
   @return true if the square is uncovered
   */
   @Override
   public boolean uncover()
   {
      // If the square is not flagged
      if (super.isFlagged() == false)
      {
         // Uncover the square
         setUncovered();
         
         // Use if-else-if to set the neighborMines
         // to the amount of mines using super set call
         if (neighborMines == 0)
         {
            super.setElement("-");
         }
         else  if (neighborMines == 1)
         {
            super.setElement("1");
         }
         else if (neighborMines == 2)
         {
            super.setElement("2");
         }
         else if (neighborMines == 3)
         {
            super.setElement("3");
         }
         else if (neighborMines == 4)
         {
            super.setElement("4");
         }
         else if (neighborMines == 5)
         {
            super.setElement("5");
         }
         else if (neighborMines == 6)
         {
            super.setElement("6");
         }
         else if (neighborMines == 7)
         {
            super.setElement("7");
         }
         else if (neighborMines == 8)
         {   
            super.setElement("8");
         }
         return true;
      }
      else
         return false; 
   }
   
   /**
   getNeighborMines returns the mines
   @return neighborMines the amount of mines
   */
   public int getNeighborMines()
   {  
      return neighborMines;
   }
   
   /**
   isMine returns if the square is a mine
   @return false the square is not a mine
   */
   @Override
   public boolean isMine()
   {
      return false;
   }


}