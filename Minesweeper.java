/*
Dwayne Kirby
CS 110
Final Project
Create a Minesweeper class to simulate 1 game of Minesweeper
Asks user what difficulty they want and validates it
Create the grid with the values to what the difficulty is
Validate user input on the row and column when user wants to uncover or flag
Valdiate the input is in range and formatted correctly
If user input does not match with U, F, or Q nothing happens
Keep going until user wins or quits
*/

// Import Scanner and util
import java.util.Scanner;
import java.util.*;

// Name Minesweeper Class
public class Minesweeper
{
   // MineSweeper Default Constructor
   public Minesweeper()
   {
      // Name difficulty as an empty String 
      String difficulty = "";
      
      // Create userinput as an empty string
      String userInput = "";
      
      // Create quit as an empty String
      String quit = "";
      
      // Create row int as 0
      int row = 0;
      
      // Create column int as 0
      int column = 0;
      
      // Create mine int as 0
      int mine = 0;
     
      // Create listInputs as an array of Strings
      String [] listInputs;
         
      // Create stat as equal to Status.OK
      Status stat = Status.OK;  
        
      // Create a game of grid object
      Grid game;
      
      // Create a keyboard scanner object
      Scanner keyboard = new Scanner (System.in);
      
      // Use while loop to make sure the difficulty is not anything else
      while (difficulty.equals("Intermediate") == false && difficulty.equals("Expert") == false
             && difficulty.equals("Beginner") == false)
      {
         // Display enter difficulty
         System.out.print("Enter the difficulty(Intermediate/Expert/Beginner): ");
       
         // Have keyboard get next line
         difficulty = keyboard.nextLine();
      }   
      
      // If user wants an intermediate level game
      if (difficulty.equals("Expert"))
      {
         // Set column as 16
         column = 16;
         
         // Set row as 20
         row = 20;
         
         // Set mine as 50
         mine = 50;
      }
      // If user wants expert level game
      else if (difficulty.equals("Intermediate"))  
      {
         // Set column as 10
         column = 10;
         
         // Set row as 12
         row = 12;
         
         // Set mine as 10
         mine = 10;
      }
      // If not expert and intermediate
      else
      {
        // Set column as 8
         column = 8;
         
         // Set row as 8
         row = 8;
         
         // Set mine as 8
         mine = 8;
      }
      
      // Create a new Grid with the set height, the set width and the set mines
      game = new Grid(column, row, mine);
      
      // Use while as long as user wants to keep the game going or user wins
      while (quit != "Q" && stat == Status.OK)
      {  
         // Display the game
         System.out.println(game.toString());
         System.out.println();
      
         // Display what's next
         System.out.println("What's next");
      
         // Display options to user
         System.out.println("Options:  (U)ncover r c, (F)lag r c, (Q)uit \n");
         
         // Get the user's input
         userInput = keyboard.nextLine();
         
         // Split the input into an array of lists
         listInputs = userInput.split("\\s+");
          
         // Get the first element is Q
         if (listInputs[0].equals("Q"))
         {
            // Quit the game
            quit = "Q";
         }
         // If the first element is F
         else if (listInputs[0].equals("F"))
         {
            // Use try statement
             try
            {
               // If the input is out of bounds of the the row and columns
               if (Integer.parseInt(listInputs[1]) >= 0 && Integer.parseInt(listInputs[2]) >= 0
                  && Integer.parseInt(listInputs[1]) <= column-1 && Integer.parseInt(listInputs[2]) <= row-1)
               {
                  // Flag the indicated sqaure
                  game.flagSquare(Integer.parseInt(listInputs[2]), Integer.parseInt(listInputs[1]));
               }
               // If they are out of bounds
               else
               {
               // Display invalid
               System.out.println("Invalid Input");
               }
            } 
            // Use catch incase out of bounds exception occurs
            catch (ArrayIndexOutOfBoundsException | NumberFormatException e)
            {
               // Display invalid
               System.out.println("Invalid Input");
            }
            
         }
         // If the first element is U
         else if (listInputs[0].equals("U"))
         {  
            // Use try statement
            try
            {
               // If the input is out of bounds of the the row and columns
               if (Integer.parseInt(listInputs[1]) >= 0 && Integer.parseInt(listInputs[2]) >= 0
                  && Integer.parseInt(listInputs[1]) <= column-1 && Integer.parseInt(listInputs[2]) <= row-1)
               {
                  // Uncover the indicated square and assign the resulting to stat
                  stat = game.uncoverSquare(Integer.parseInt(listInputs[2]), Integer.parseInt(listInputs[1]));
               
                  // If the stat is a MINe
                  if (stat == Status.MINE)
                  {
                     // Display the game ending
                     System.out.println(game.toString());
                  
                     // Display better luck next time
                     System.out.println("Better luck next time!");
                  }
                  // If stat is WIN
                  if (stat == Status.WIN)
                  {
                     // Expose all the mines
                     game.exposeMines();
                     
                     // Display the game
                     System.out.println(game.toString());
                     
                     // Display the user wins
                     System.out.println("You Win!");
                  }
               }
               // If they are out of bounds
               else
               {
                  // Display invalid
                  System.out.println("Invalid Input");
               }
            } 
            // Use catch incase out of bounds exception occurs
            catch (ArrayIndexOutOfBoundsException | NumberFormatException e)
            {
               // Display invalid
               System.out.println("Invalid Input");
            }     
         } 
      }   
   }
}