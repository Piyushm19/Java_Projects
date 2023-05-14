package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game.
 */
public class ArrayGame {

  // stores the next number to guess
  private int guess;
  private boolean ifGameOver;
  private int numGuesses;
  private boolean[] mainCandidates;
  private int[] prevGuesses;
  private int numRemoved;

  // TODO: declare additional data members, such as arrays that store
  // prior guesses, eliminated candidates etc.

  // NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
  // You MAY NOT use any Collection type (such as ArrayList) provided by Java.

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary, but DO NOT remove any
   * provided method, otherwise your code will fail the JUnit tests. Also DO NOT
   * create any new Java files, as they will be ignored by the autograder!
   *******************************************************/

  // ArrayGame constructor method
  public ArrayGame() {
    // TODO
    mainCandidates = new boolean[9000];
    prevGuesses = new int[18];
    reset();
  }

  /**
   * Resets data members and game state so we can play again.
   */
  public void reset() {
    // TODO
    for (int i = 0; i < mainCandidates.length; i++) {
      mainCandidates[i] = true;
    }
    ifGameOver = false;
    numGuesses = 0;
    guess = 1000;
    numRemoved = 0;
  }

  /**
   * Returns true if n is a prior guess; false otherwise.
   */
  public boolean isPriorGuess(int n) {
    // TODO
    for (int i = 0; i < prevGuesses.length; i++) {
      if (prevGuesses[i] == n) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the number of guesses so far.
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
   * <p>
   * A match is the same digit at the same location. For example: 1234 and 4321
   * have 0 match; 1234 and 1114 have 2 matches (1 and 4); 1000 and 9000 have 3
   * matches (three 0's).
   */
  public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
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
   * number has been correctly guessed or if all candidates have been eliminated.
   */
  public boolean isOver() {
    // TODO
    return ifGameOver;
  }

  /**
   * Returns the guess number and adds it to the list of prior guesses.
   */
  public int getGuess() {
    // TODO: add guess to the list of prior guesses.
    prevGuesses[numGuesses] = guess;
    numGuesses++;
    return guess;
  }

  /**
   * Updates guess based on the number of matches of the previous guess. If
   * nmatches is 4, the previous guess is correct and the game is over. Check
   * project description for implementation details.
   * 
   * <p>
   * Returns true if the update has no error; false if all candidates have been
   * eliminated (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
    // TODO
    if (nmatches == 4) {
      ifGameOver = true;
      return true;
    }

    for (int i = 0; i < mainCandidates.length; i++) {
      if (numMatches(guess, i + 1000) != nmatches && mainCandidates[i]) {
        mainCandidates[i] = false;
        numRemoved++;
      }
    }
    for (int i = 0; i < mainCandidates.length; i++) {
      if (mainCandidates[i] == true) {
        guess = i + 1000;
        break;
      }
    }
    if (numRemoved == 9000) {
      return false;
    }

    return true;
  }

  /**
   * Returns the list of guesses so far as an integer array. The size of the array
   * must be the number of prior guesses. Returns null if there has been no prior
   * guess
   */
  public int[] priorGuesses() {
    // TODO
    int[] tempArray = new int[numGuesses];
    for (int i = 0; i < numGuesses; i++) {
      tempArray[i] = prevGuesses[i];
    }
    if (numGuesses < 1) {
      return null;
    }
    return tempArray;
  }
}
