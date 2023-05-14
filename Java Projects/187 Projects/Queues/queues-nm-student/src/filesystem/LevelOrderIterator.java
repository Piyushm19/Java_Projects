package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import structures.Queue;

/**
 * An iterator to perform a level order traversal of part of a filesystem. A
 * level-order traversal is equivalent to a breadth- first search.
 */
public class LevelOrderIterator extends FileIterator<File> {

  private Queue<File> tempQ;

  /**
   * Instantiate a new LevelOrderIterator, rooted at the rootNode.
   * 
   * @param rootNode : node of the root.
   * @throws FileNotFoundException if the rootNode does not exist.
   */
  public LevelOrderIterator(File rootNode) throws FileNotFoundException {
    // TODO 1
    if (!rootNode.exists()) {
      throw new FileNotFoundException();
    }
    tempQ = new Queue<File>();
    tempQ.enqueue(rootNode);
  }

  @Override
  public boolean hasNext() {
    // TODO 2
    return !tempQ.isEmpty();
  }

  @Override
  public File next() throws NoSuchElementException {
    // TODO 3
    if (tempQ.isEmpty()) {
      throw new NoSuchElementException();
    }
    File temp = tempQ.dequeue();
    if (!temp.isDirectory()) {
      return temp;
    }
    File[] list = temp.listFiles();
    Arrays.sort(list);
    for (File example : list) {
      tempQ.enqueue(example);
    }
    return temp;
  }

  @Override
  public void remove() {
    // Leave this one alone.
    throw new UnsupportedOperationException();
  }

}
