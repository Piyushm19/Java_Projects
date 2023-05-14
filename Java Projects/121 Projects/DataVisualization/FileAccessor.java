import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintWriter;

/**
   Text file line processor. Reads a text file containing lines of data
   and processes each line.
*/
public abstract class FileAccessor{
  private String fileName; 
  Scanner scan;

  /* Takes a file path/name, attempts to open the file using a Scanner
   * (and a FileStream object).
   */
  public FileAccessor(String f) throws IOException{
    fileName = f;
    scan = new Scanner(new FileReader(fileName));
  }

  /* Call this method to read in the lines of text that have been loaded in the Scanner object.
   * Throws an exception if the data is not correctly processed.
   */
  public void processFile() throws DataFileProcessingException { 
    while(scan.hasNext()){
      processLine(scan.nextLine());
    }
    scan.close();
  }
  
  /* This method is called on each line that has been read into the Scanner object.
   * This method provides subclasses with a way to process the data in a text file.
   */
  protected abstract void processLine(String line) throws DataFileProcessingException;
  
  /* A untility method for writing a String of data to a file. */
  public void writeToFile(String data, String fileName) throws IOException{
		PrintWriter pw = new PrintWriter(fileName);
      pw.print(data);
      pw.close();
   }
}
