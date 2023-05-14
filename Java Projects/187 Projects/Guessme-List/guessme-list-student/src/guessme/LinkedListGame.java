package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game.
 */
public class LinkedListGame {
  // TODO: declare data members as necessary
  int guess;
  private boolean gameOver;
  private LLIntegerNode headCandidates;
  private LLIntegerNode headGuesses;
  private LLIntegerNode tailGuesses;
  private int numGuesses;
  LLIntegerNode tempHead = null;
  LLIntegerNode tempTail = null;

  /********************************************************
   * NOTE: for this project you must use linked lists implemented by yourself. You
   * are NOT ALLOWED to use Java arrays of any type, or any class in the java.util
   * package (such as ArrayList).
   *******************************************************/

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary, but DO NOT remove any
   * provided method, and do NOT add new files (as they will be ignored by the
   * autograder).
   *******************************************************/

  // LinkedListGame constructor method
  public LinkedListGame() {
    // TODO
    reset();
  }

  /**
   * Resets data members and game state so we can play again.
   * 
   */
  public void reset() {
    // TODO
    numGuesses = 0;
    tailGuesses = null;
    headGuesses = null;
    tempHead = null;
    tempTail = null;
    gameOver = false;
    guess = 1000;
    headCandidates = new LLIntegerNode(1000, null);
    LLIntegerNode example = headCandidates;
    for (int i = 1001; i < 10000; i++) {
      LLIntegerNode next = new LLIntegerNode(i, null);
      example.setLink(next);
      example = next;
    }
  }

  /**
   * Returns true if n is a prior guess; false otherwise.
   * 
   */

  public boolean isPriorGuess(int n) {
    // TODO
    return contains(headGuesses, n);
  }

  /**
   * Returns the number of guesses so far.
   * 
   */
  public int numGuesses() {
    // TODO
    return numGuesses;
  }

  /**
   * Returns the number of matches between integers a and b. You can assume that
   * both are 4-digits long (i.e. between 1000 and 9999). The return value must be
   * between 0 and 4.
   * 
   * <p>A match is the same digit at the same location. For example: 1234 and 4321
   * have 0 match; 1234 and 1114 have 2 matches (1 and 4); 1000 and 9000 have 3
   * matches (three 0's).
   */
  public static int numMatches(int a, int b) {
    // TODO
    int match = 0;
    int digits = 4;
    int tempA = a;
    int tempB = b;
    for (int i = 0; i < digits; i++) {
      if (tempA % 10 == tempB % 10) {
        match++;
      }
      tempA = tempA / 10;
      tempB = tempB / 10;
    }
    return match;
  }

  /**
   * Returns true if the game is over; false otherwise. The game is over if the
   * number has been correctly guessed or if no candidate is left.
   */
  public boolean isOver() {
    // TODO
    return gameOver;
  }

  /**
   * Returns the guess number and adds it to the list of prior guesses. The
   * insertion should occur at the end of the prior guesses list, so that the
   * order of the nodes follow the order of prior guesses.
   */
  public int getGuess() {
    // TODO: add guess to the list of prior guesses.
    LLIntegerNode newGuess = new LLIntegerNode(guess, null);
    newGuess.setInfo(guess);
    if (tailGuesses == null) {
      headGuesses = newGuess;
    } else if (tailGuesses != null) {
      tailGuesses.setLink(newGuess);
    }
    tailGuesses = newGuess;
    numGuesses++;
    return guess;
  }

  /**
   * Updates guess based on the number of matches of the previous guess. If
   * nmatches is 4, the previous guess is correct and the game is over. Check
   * project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if no candidate is left
   * (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
    // TODO
    LLIntegerNode tempHead = null;
    LLIntegerNode tempTail = null;
    LLIntegerNode tempCandidates = headCandidates;
    if (nmatches == 4) {
      gameOver = true;
    }
    do {
      if (numMatches(guess, tempCandidates.getInfo()) == nmatches) {
        if (tempHead == null) {
          tempHead = new LLIntegerNode(tempCandidates.getInfo(), null);
          tempTail = tempHead;
        } else {
          LLIntegerNode temp = new LLIntegerNode(tempCandidates.getInfo(), null);
          tempTail.setLink(temp);
          tempTail = temp;
        }
      }
      tempCandidates = tempCandidates.getLink();
    } while (tempCandidates != null);
    headCandidates = tempHead;
    if (headCandidates == null) {
      return false;
    }
    guess = headCandidates.getInfo();
    return true;
  }

  /**
   * Returns the head of the prior guesses list. Returns null if there hasn't been
   * any prior guess
   */
  public LLIntegerNode priorGuesses() {
    // TODO
    return headGuesses;
  }

  /**
   * Returns the list of prior guesses as a String. For example, if the prior
   * guesses are 1000, 2111, 3222, in that order, the returned string should be
   * "1000, 2111, 3222", in the same order, with every two numbers separated by a
   * comma and space, except the last number (which should not be followed by
   * either comma or space).
   *
   * <p>Returns an empty string if here hasn't been any prior guess
   */
  public String priorGuessesString() {
    // TODO
    LLIntegerNode temp = new LLIntegerNode(0, null);
    temp = headGuesses;
    String emtString = "";
    while (temp != null) {
      if (temp.getLink() == null) {
        emtString += temp.getInfo();
        break;
      } else {
        emtString += temp.getInfo() + ", ";
        temp = temp.getLink();
      }
    }
    return emtString;
  }

  // Lecture code method
  public boolean contains(LLIntegerNode mainHead, int guess) {
    boolean check = false;
    while (mainHead != null) {
      if (mainHead.getInfo() == guess) {
        check = true;
      }
      mainHead = mainHead.getLink();
    }
    return check;
  }

}
