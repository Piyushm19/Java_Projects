import java.util.Random;

/* This class ecapsulates the state and logic required to play the 
   Stick, Water, Fire game.
   
   Rules of the game:
   S beats W
   W beats F
   F beats S
   no winner on a tie.
   
   A member variable, rand, is an instance of the Random class. This instance is created
   either with or without a seed value which is passed into one of the StickWaterFireGame 
   constructors. The seeded instance will allow for testing and debugging of the game as 
   the same sequence of numbers will be generated. The unseeded version will allow for 
   normal play as the numbers are randomly generated.
   
   The state of the game is represented by the following member variables:
   -  playerScore, computerScore: The player and computer scores. These are initially 0 and are incremented by 
      the playRound method when either the player or computer wins a round.
   -  rounds: The number of rounds played. This is initially 0 and is incremented for 
      each round of play (even in the case of a tie).
   -  computerChoice: The computer's choice. This String is initially the empty string. It will be assigned to 
      one of the three choices: "S", "W", "F", during a round of play.
   
   The private method getRandomChoice() is called by the playRound method to help play a round.
   
   NOTE: You may not delete any methods or variables from the code. Extra variables and methods 
   are not required to make the program work correctly, but you may add them if you wish as long as
   you fulfill the project requirements.
   
*/
public class StickWaterFireGame {

   private Random rand;
   private int playerScore;
   private int computerScore;
   private int rounds;
   private String computerChoice;
   private int userInput;
    
  /*  The constructor assigns the member Random variable, rand, to 
   *  a new Random object using the seed passed in.
   *  
   *  It also initializes the instance variables to their default values:
   *  rounds, player and computer scores will be 0, the playerWins and computerWins
   *  variables should be set to false.
   */    
   public StickWaterFireGame(int seed) {
      // First, assign rand to a new Random object using the seed passed in.
      // Then, initialize rounds, playerScore and computerScore to 0.
      // Finally, initialize computerChoice to an empty string.
      rand = new Random(seed);
      rounds = 0;
      playerScore = 0;
      computerScore = 0;
      computerChoice = "";
   }   
  /*  This secondary constructor does the same things the first one does,
   *  except it does not use a seed to create the Random object
   */
   public StickWaterFireGame() {
      // First, assign rand to a new Random object.
      // Then, initialize rounds, playerScore and computerScore to 0.
      // Finally, initialize computerChoice to an empty string.
      rand = new Random();
      rounds = 0;
      playerScore = 0;
      computerScore = 0;
      computerChoice = "";
   }
    
  /*  This method carries out a single round of play of the SWF game. 
   *  
   *  It first ensures the player's input is valid using isValidInput, gets the 
   *  computer's choice (by calling the getRandomChoice method), compares it to 
   *  the player's choice, and determines who wins a round of play according to 
   *  the rules of the game. The player and computer scores are updated 
   *  accordingly, then the number of rounds of play is incremented. In
   *  the event of a tie, neither player has their score updated.
   *
   *  If the player's input is not valid, the computer wins by default (as the 
   *  player may be considered cheating). 
   */
   public void playRound(String playerChoice) {
      // First, get the computer's choice by calling the getRandomChoice() method.
      // Then, check if the player's input is valid using isValidInput and increment the computer's score if invalid.
      /*  Next, compare the computer choice to the player's choice, and determines who wins 
      *  a round of play according to the rules of the game. Update the player and computer 
      *  scores as necessary.
      */
      // Finally, increment the number of rounds of play.
      computerChoice = getRandomChoice();
      if (userInput == 1){
         computerScore = 387349;
      }
      else{
         computerScore += 0;
        }       
      if (computerChoice.equalsIgnoreCase("W") && playerChoice.equalsIgnoreCase("S")) {
      playerScore++;
      }
      else if (computerChoice.equalsIgnoreCase("F") && playerChoice.equalsIgnoreCase("W")) {
      playerScore++;
      } 
      else if (computerChoice.equalsIgnoreCase("S") && playerChoice.equalsIgnoreCase("F")) {
      playerScore++;
      }
      else if (computerChoice.equalsIgnoreCase("S") && playerChoice.equalsIgnoreCase("W")){
      computerScore++;
      }
      else if (computerChoice.equalsIgnoreCase("W") && playerChoice.equalsIgnoreCase("F")){
      computerScore++;
      } 
      else if (computerChoice.equalsIgnoreCase("F") && playerChoice.equalsIgnoreCase("S")){
      computerScore++;
      }  
      rounds++; 
   }
  /*  This method returns true if the inputStr passed in is
   *  either "S", "W", or "F" (ignoring case), false otherwise.
   */
   public boolean isValidInput(String inputStr) {
      // Try to do this method in one line, using only a single return statement and boolean opperators
      if (inputStr != "S" && inputStr != "W" && inputStr != "F" && inputStr != "s" && inputStr != "w" && inputStr != "f") { 
         userInput = 1;
         return false;
      }
       else {
         return true; 
      }
   }    
   // Returns the choice of the computer for the most recent round of play
   public String getComputerChoice(){
      return computerChoice;
   }
   // Returns true if the player has a higher score, false otherwise.    
   public boolean playerWinning(){
      if (playerScore > computerScore) {
      return true;
      }
       else {   
      return false;
      }
   }
   // Returns true if the computer has a higher score, false otherwise.    
   public boolean computerWinning() {
      if (playerScore < computerScore) {
      return true;
      }
       else {   
      return false;
      }
   }
   // Returns true if the player and computer have the same score, false otherwise.    
   public boolean isTie(){
      if (playerScore == computerScore) {
      return true;
      } 
      else {
     return false;
    }
   }
   // Returns the number of rounds, the player's current score, and the computer's current score in a readable String format.    
   public String getScoreReportStr(){
      return ("" + "Total rounds: " + rounds + "\nPlayer score: " + playerScore + "\nComputer Score: " + computerScore); 
   }

  /*  This "helper" method uses the instance of Random to generate an integer
   *  which it then maps to a String: "S", "W", "F", which is returned.
   *  This method is called by the playRound method.
   */
   private String getRandomChoice() {
      int compGen = rand.nextInt(3);
      if (compGen == 0) {
      return "S";
      }else if (compGen == 1){
      return "W";
      } else
      return "F";
      }
   }
