/**
* This program creates a game called "Snap, Crackle, Pop, " 
* there are 3 players and they get to choose the number of rounds.
* @author Piyush Makkapati
* @version 1.0
*/
public class SnapCracklePop { 
   /**
   * Storage is created for the value of snap
   */
   private int snap;
   
   /**
   * Storage is created for the value of crackle
   */
   private int crackle;
   
   /**
   * Storage is created for the value of pop
   */
   private int pop;
   
   /**
   * Assigns S to int snap, C to int crackle, P to int pop
   * @param S is the value of snap inputted by the user
   * @param C is the value of crackle inputted by the user
   * @param P is the value of pop inputted by the user
   */
   public SnapCracklePop (int S, int C, int P) {
      snap = S;
      crackle = C;
      pop = P;
   }
   
   /**
   * Uses the current number in the round and determines if it could be divisible by the values of Snap, Crackle, Pop.
   * @param currentRound is the number that is used to be divided during the round
   * @return a value, either the value of snap, crackle, pop, or a number
   */ 
   private String playRound(int currentRound) {
      String answer = "";
      if ((currentRound % snap != 0) && (currentRound % crackle != 0) && (currentRound % pop != 0)) {
         answer = answer.concat("" + currentRound);
      } 
      if (currentRound % snap == 0) {
         answer += "Snap";
      } 
      if (currentRound % crackle == 0) {
         answer += "Crackle";
      }
      if (currentRound % pop == 0) {
         answer += "Pop";
      }
      return answer;
   }
     
   /**
   * This method simulates the game using playRound, and then uses the number during the round and 
   * then returns the number and also round number
   * @param totRounds represents the number of rounds the user wants to play
   * @return gives the outcome of the round and the round number
   */   
   public String playGame(int totRounds){
      String result = "";
      for (int r = 1; r <= totRounds; ++r) {
         result += "Round " + r + ": " + playRound(r) + "\n";
      }
      return result;
   }
   
   /**
   * returns the value of snap
   * @return a integer value that is snap
   */
   public int getSnap() {
      return snap;
   } 
   
   /**
   * returns the value of crackle
   * @return a integer value that is crackle
   */ 
   public int getCrackle() {
      return crackle;
   } 
     
   /**
   * returns the value of pop
   * @return a integer value that is pop
   */ 
   public int getPop() {
      return pop;
   } 
}  