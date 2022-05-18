/*
Dwayne Kirby
CS 110
Final Project
Create a grid class that creates and represents a 2 dimensonal grid
With height and width with all square type
Fill in with the selected amount of mines at random row and columns
Create function getNeighbors to see how many mines are around the selected square
Create function uncoverSquare to uncover the selected square and possible neighboring squares
Create function exposeMines to expose all the mines in the grid
Create function flagSquare to flag or unflag the current square
Create a toString method to display the grid in a row and column based array
*/

// Import random
import java.util.Random;

// Name class Grid
public class Grid
{
   // Declare instance varibles
   private Square[][] grid;
   private int width;
   private int height;
   private int numMines;
   private int numSquaresUncovered;
   
   /**
   Grid constuctor takes in 3 ints and sets them, sets numSquaresUncovered to 0, creates Squares objects
   and put them into an 2x2 array that assigns height * width squares with number amount of mines
   @param theHeight columns of the grid
   @param theWidth the rows of the grid
   @param theNumMines the amount of mines in the grid
   */
   public Grid(int theHeight, int theWidth, int theNumMines)
   {
      height = theHeight;
      width = theWidth;
      numMines = theNumMines;
      numSquaresUncovered = 0;
      
      // Set rowMine and ColMine as ints
      int rowMine;
      int colMine;
      
      // Create a grid that is of height and width
      grid = new Square[height][width];
      
      // Create randRow and randCol as random objects 
      Random randRow = new Random();
      Random randCol = new Random();
      
      // Use for loop to go through the height of the grid
      for (int col = 0; col < height; col++)
      {
         // Use for loop to go through width of the gird
         for(int row = 0; row < width; row++)
         {
            // Create a numberSquare object
            grid[col][row] = new NumberSquare(0, height, width);
         }
      }

      // Use a for loop to go through mines and create the number of mines
      for(int mine = 0; mine < numMines; mine++)
      {
         // Get a random integer for rowMine and ColMine
         rowMine = randRow.nextInt(width-1);
         colMine = randCol.nextInt(height-1);
         
         // Create a new MineSquare and replace that square
         grid[colMine][rowMine] = new MineSquare();
      }
      
      // Use for loop to go through the columns
      for (int col = 0; col < height; col++)
      {
         // Use for loop to go through the rows
         for(int row = 0; row < width; row++)
         {
            // If the square is not a mine
            if (grid[col][row].isMine() == false)
            {
               // Replace the square with a new NumberSquare with the neighbor of mines   
               grid[col][row] = new NumberSquare(getNeighbors(row, col), height, width);
            }
         }
      }

   }
   
   /**
   getNeighbors returns the amount of mines around the square covered
   @param row the row at which the square is
   @param col the column at which the square is
   @return minesAround the amount of mines
   */
   public int getNeighbors(int row, int col)
   {  
      // Create an int minesAround at 0
      int minesAround = 0;
      
      // Use if loops to go through go through a 3x3 grid and see if the squares around are mines
      // If so then increase minesAround by 1
      if (row > 0 && grid[col][row-1].isMine() == true)
      {
         minesAround += 1;
      }
      if (row < width-1 && grid[col][row+1].isMine() == true)
      {
         minesAround += 1;
      }
      if (col < height-1 && grid[col+1][row].isMine() == true)
      {
         minesAround += 1;
      }
      if (col > 0 && grid[col-1][row].isMine() == true)
      {
         minesAround += 1;;
      }
      if (row > 0 && col > 0 && grid[col-1][row-1].isMine() == true)
      {
         minesAround += 1;
      }
      if (row > 0 && col < height-1 && grid[col+1][row-1].isMine() == true)
      {
         minesAround += 1;
      }
      if (row < width-1 && col > 0 && grid[col-1][row+1].isMine() == true)
      {
         minesAround += 1;
      }
      if (row < width-1 && col < height-1 && grid[col+1][row+1].isMine() == true)
      {
         minesAround += 1;
      }   
      // Return minesAround
      return minesAround;
   }
   
   /**
   uncoverSquare uncovers the indicated square and the surrondings squares based
   on how many mines are around
   @param row the row of the indicated square
   @param col the column of the indicated square
   @return Status if there was a mine or the user is okay or has won
   */
   public Status uncoverSquare(int row, int col)
   {
      // Declare a Status varible
      Status stat;
      
      // If the selected square is flagged
      if (grid[col][row].isFlagged() == true)
      {
         // Return OK
         return Status.OK;
      }
      
      // If the selected sqaure is a mine
      else if (grid[col][row].isMine() == true)
      {
         // Expose all mines
         exposeMines();
         
         // Return MINE
         return Status.MINE;   
      }
      
      // If there are no mines around uncover a 5x5 grid of squares
      // Use if statements to make sure each square is not already uncovered
      // And make sure the square being uncovered is not a mine
      // And square being uncovered is in the grid
      // increases numSquaresUncovered by 1 each square that is uncovered 
      else if (getNeighbors(row, col) == 0 )
      { 
         if (grid[col][row].isUncovered() == false) 
         {
            grid[col][row].uncover(); 
            numSquaresUncovered += 1;
         }
         
         if (col > 0 && grid[col-1][row].isMine() == false && grid[col-1][row].isUncovered() == false)
         {
            grid[col-1][row].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col > 1 && grid[col-2][row].isMine() == false && grid[col-2][row].isUncovered() == false)
         {
            grid[col-2][row].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col < height-1 && grid[col+1][row].isMine() == false && grid[col+1][row].isUncovered() == false)
         {
            grid[col+1][row].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col < height-2 && grid[col+2][row].isMine() == false && grid[col+2][row].isUncovered() == false)
         {
            grid[col+2][row].uncover();
            numSquaresUncovered += 1;
         }
         
         if (row > 0 && grid[col][row-1].isMine() == false && grid[col][row-1].isUncovered() == false)
         {
            grid[col][row-1].uncover();
            numSquaresUncovered += 1;
         }
         
         if (row > 1 && grid[col][row-2].isMine() == false && grid[col][row-2].isUncovered() == false)
         {
            grid[col][row-2].uncover();
            numSquaresUncovered += 1;
         }
         
         if (row < width-1 && grid[col][row+1].isMine() == false && grid[col][row+1].isUncovered() == false)
         {
            grid[col][row+1].uncover();
            numSquaresUncovered += 1;
         }
         
         if (row < width-2 && grid[col][row+2].isMine() == false && grid[col][row+2].isUncovered() == false)
         {
            grid[col][row+2].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col > 0 && row > 0 && grid[col-1][row-1].isMine() == false && grid[col-1][row-1].isUncovered() == false)
         {
            grid[col-1][row-1].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col > 0 && row > 1 && grid[col-1][row-2].isMine() == false && grid[col-1][row-2].isUncovered() == false)
         {
            grid[col-1][row-2].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col > 1 && row > 0 && grid[col-2][row-1].isMine() == false && grid[col-2][row-1].isUncovered() == false)
         {
            grid[col-2][row-1].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col > 1 && row > 1 && grid[col-2][row-2].isMine() == false && grid[col-2][row-2].isUncovered() == false)
         {
            grid[col-2][row-2].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col < height-1 && row < width-1 && grid[col+1][row+1].isMine() == false && grid[col+1][row+1].isUncovered() == false)
         {
            grid[col+1][row+1].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col < height-2 && row < width-1 && grid[col+2][row+1].isMine() == false && grid[col+2][row+1].isUncovered() == false)
         {
            grid[col+2][row+1].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col < height-2 && row < width-2 && grid[col+2][row+2].isMine() == false && grid[col+2][row+2].isUncovered() == false)
         {
            grid[col+2][row+2].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col < height-1 && row < width-2 && grid[col+1][row+2].isMine() == false && grid[col+1][row+2].isUncovered() == false)
         {
            grid[col+1][row+2].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col < height-1 && row > 0 && grid[col+1][row-1].isMine() == false && grid[col+1][row-1].isUncovered() == false)
         {
            grid[col+1][row-1].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col < height-1 && row > 1 && grid[col+1][row-2].isMine() == false && grid[col+1][row-2].isUncovered() == false)
         {
            grid[col+1][row-2].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col < height-2 && row > 0 && grid[col+2][row-1].isMine() == false && grid[col+2][row-1].isUncovered() == false)
         {
            grid[col+2][row-1].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col < height-2 && row > 1 && grid[col+2][row-2].isMine() == false && grid[col+2][row-2].isUncovered() == false)
         {
            grid[col+2][row-2].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col > 0 && row < width-1 && grid[col-1][row+1].isMine() == false && grid[col-1][row+1].isUncovered() == false)
         {
            grid[col-1][row+1].uncover();
            numSquaresUncovered += 1;
         }
          
         if (col > 1 && row < width-1 && grid[col-2][row+1].isMine() == false && grid[col-2][row+1].isUncovered() == false)
         {
            grid[col-2][row+1].uncover();
            numSquaresUncovered += 1;
         }
         
         if (col > 0 && row < width-2 && grid[col-1][row+2].isMine() == false && grid[col-1][row+2].isUncovered() == false)
         {
            grid[col-1][row+2].uncover();
            numSquaresUncovered += 1;
         }
          
         if (col > 1 && row < width-2 && grid[col-2][row+2].isMine() == false && grid[col-2][row+2].isUncovered() == false)
         {
            grid[col-2][row+2].uncover();
            numSquaresUncovered += 1;
         }
      }
      
      // If there is 1 mines around uncover a 3x3 grid of squares
      // Use if statements to make sure each square is not already uncovered
      // And make sure the square being uncovered is not a mine
      // And square being uncovered is in the grid
      // increases numSquaresUncovered by 1 each square that is uncovered 
      else if (getNeighbors(row, col) == 1 )
      {
            if (grid[col][row].isUncovered() == false)
            {
               grid[col][row].uncover();
               numSquaresUncovered += 1;
            }
            
            if (row > 0 && grid[col][row-1].isMine() == false && grid[col][row-1].isUncovered() == false)
            {
               grid[col][row-1].uncover();
               numSquaresUncovered += 1;
            }
            
            if (row < width-1 && grid[col][row+1].isMine() == false && grid[col][row+1].isUncovered() == false)
            {
               grid[col][row+1].uncover();
               numSquaresUncovered += 1;
            }
            
            if (col > 0 && grid[col-1][row].isMine() == false && grid[col-1][row].isUncovered() == false)
            {
               grid[col-1][row].uncover();
               numSquaresUncovered += 1;
            }
            
            if (row > 0 && col > 0 && grid[col-1][row-1].isMine() == false && grid[col-1][row-1].isUncovered() == false)
            {
               grid[col-1][row-1].uncover();
               numSquaresUncovered += 1;
            }
            
            if (col < height-1 && row > 0 && grid[col+1][row-1].isMine() == false && grid[col+1][row-1].isUncovered() == false)
            {
               grid[col+1][row-1].uncover();
               numSquaresUncovered += 1;
            }
            
            if (col < height-1 && grid[col+1][row].isMine() == false && grid[col+1][row].isUncovered() == false)
            {
               grid[col+1][row].uncover();
               numSquaresUncovered += 1;
            }
            
            if (col < height-1 && row < width-1 && grid[col+1][row+1].isMine() == false && grid[col+1][row+1].isUncovered() == false)
            {
               grid[col+1][row+1].uncover();
               numSquaresUncovered += 1;
            }
            
            if (col > 0 && row < width-1 && grid[col-1][row+1].isMine() == false && grid[col-1][row+1].isUncovered() == false)
            {
               grid[col-1][row+1].uncover();
               numSquaresUncovered += 1;
            }
         }
      // If there are more than 1 mine around uncover the singluar square
      // Use if statements to make sure the square is not already uncovered
      // increases numSquaresUncovered by 1 each square that is uncovered            
      else
      {
         if (grid[col][row].isUncovered() == false)
         {
            grid[col][row].uncover();
            numSquaresUncovered += 1;
         }
      }
      // Determine if user won and if so return WIN
      if (numSquaresUncovered == ((height * width) - numMines))
      {
         return Status.WIN;
      }
      // If user has not won
      else
      {
         // Return OK
         return Status.OK;
      }
   }         
   
   /**
   exposeMines searches for the mine in the grid array and uncovers it
   */
   public void exposeMines()
   {
      // Use for loop to go through the height of the grid
      for(int col = 0; col < height; col++)
      {
         // Use for loop to go through the width of the grid
         for(int row = 0; row < width; row++)
         {
            // If square is a mine
            if (grid[col][row].isMine() == true)
            {
               // Uncover the square
               grid[col][row].uncover();   
            }
         }
      }
   }
   
   /**
   flagSquare takes in the row and column of the sqaure and flags it or unflags it if already flagged
   @param row the indicated square's row
   @param col the indicated square's column
   */
   public void flagSquare(int row, int col)
   {
      // If the square is already flagged then unflag it
      if (grid[col][row].isFlagged() == true)
      {
         // If the square is not a mine create new NumberSquare
         if(grid[col][row].isMine() == false)
         {
            grid[col][row] = new NumberSquare(getNeighbors(row, col), height, width);
         }
         else 
         {          
            // If it is then create a new MineSquare
            grid[col][row] = new MineSquare();
         }
      }
      // Else flag the square
      else
      {
         grid[col][row].flagSquare();
      }
   }
   
   /**
   toString creates a string representation of grid and gets the numbers and columns of the rows
   while displaying each element of the grid
   @return str the formatted represented string
   */
   @Override
   public String toString()
   {
      // Create a str
      String str = "   ";
      
      // Use for loop to go though the width of the grid
      for(int row = 0; row < width; row++)
      {
         // Add the row number to the string
         str += String.format("%3d",row);
      }
      
      // Use for loop to go through the height of the column
      for(int col = 0; col < height; col++)
      {
         // Add the column number to the string
         str += String.format("\n%3d", col);
         
         // Use for loop to go through the row
         for(int row = 0; row < width; row++)
         {
            // Add the row element to the string
            str += String.format("%3s",grid[col][row].toString());
         }     
      } 
      // Return the string
      return str;
   }
}