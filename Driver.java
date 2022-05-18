/*
Dwayne Kirby
CS 110
Final Project
Create a driver class to run MineSweeper
Once game is over asks user if want to play again
Validate that it is a yes or no
Then run the game again or quit the program
*/

// Import Scanner
import java.util.Scanner;

// Name class Driver
public class Driver
{
   // Start execution
   public static void main(String []args)
   {
      // Create a keyboard scanner object
      Scanner keyboard = new Scanner (System.in);
      
      // Assign playGame as a string to Yes
      String playGame = "Yes";
      
      // Diplay welcome
      System.out.println("Welcome");
      
      // While playGame is yes
      while (playGame.equals("Yes"))
      {
         // Create a new Minesweeper game called game
         Minesweeper game = new Minesweeper();
         
         // Display if user wants to play again
         System.out.println("Would you like to play again?(Yes/No) ");
         
         // Get the user input
         playGame = keyboard.nextLine();
         
         // While playGame is not Yes or No
         while (playGame.equals("Yes") == false && playGame.equals("No") == false)
         {
            // Display enter only yes or no
            System.out.println("Would you like to play again? (Enter 'Yes' or 'No' only) ");
            
            // Get the user input
            playGame = keyboard.nextLine();
         } 
      }     
  }
}