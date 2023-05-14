import java.util.*;

public class StickWaterFireMain{

   public static void main(String[] args){
   
      StickWaterFireGame game = new StickWaterFireGame();
      // StickWaterFireGame game = new StickWaterFireGame(1234);
      Scanner scan = new Scanner(System.in);
      int rounds = -1;
   
      // Greet the user
      System.out.println("Welcome to Stick-Water-Fire!");
      System.out.println("Rules:\n");
      System.out.println("\tYou will play against the computer for the specified number of rounds.");
      System.out.println("\tYou will make a choice: 'S', 'W', or 'F' to represent 'Stick', 'Water', or 'Fire'.");
      System.out.println("\tThe computer will also make a choice, and the winner is chosen as follows:");
      System.out.println("\t\t Stick beats Water (it floats on top)");
      System.out.println("\t\t Water beats Fire (extinguishes the fire)");
      System.out.println("\t\t Fire beats Stick (burns the stick)");
      System.out.println("\tIn the event of a tie, there is no winner.");
      System.out.println("\tEach round, the winner will have their score incremented.");
      System.out.println("\tAt the end of play, the overall winner will be chosen based on their overall score.");
      System.out.println("\tGood luck!");
      
      // Make sure we are getting valid input and not simply crashing if we don't
      while(rounds < 0){
         System.out.println("Please enter the number of rounds you would like to play (greater than zero):");
         try{
            rounds = Integer.parseInt(scan.nextLine());
         } catch(Exception e){
            System.out.println("That's not a number! Please enter an integer.");
         }
      }
      
      // Loop to run each round
      for(int i = 0; i < rounds; i++){
         // First print game status
         System.out.println("Results so far:");
         System.out.println(game.getScoreReportStr());
         
         // Get status message
         if(game.isTie()){
            System.out.println("The game is tied!");
         } else if (game.playerWinning()) {
            System.out.println("You're winning, keep it up!");
         } else { // Computer won
            System.out.println("You're not doing so hot. Try to catch up!");
         }
         
         // Handle round of play
         System.out.println("Please enter your choice: 'S' for Stick, 'W' for Water, 'F' for Fire:");
         String playerChoice = scan.nextLine();
         game.playRound(playerChoice);
         String computerChoice = game.getComputerChoice();
         System.out.println("You chose " + playerChoice + " and the computer chose " + computerChoice);
      }
      // Print final scores
      System.out.println("Final score:");
      System.out.println(game.getScoreReportStr());
      // Determine winner
      if(game.isTie()){
         System.out.println("You tied!");
      } else if (game.playerWinning()) {
         System.out.println("You won! Nice job!");
      } else { // Computer won
         System.out.println("You lost. Better luck next time!");
      }
      System.out.println("Thanks for playing!");
   }

}