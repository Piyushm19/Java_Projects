import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.IOException;

public class DataFileAccessor extends FileAccessor{
   ArrayList<TempData> dataList;
   boolean firstLine;
   String seriesOneName; 
   String seriesTwoName; 

  public DataFileAccessor(String f, boolean hasHeader) throws IOException{
     super(f);
     dataList = new ArrayList<TempData>(0);
     firstLine = hasHeader;
     seriesOneName = "";
     seriesTwoName = "";
  }
  
  protected void processLine(String line) throws DataFileProcessingException {
     String arrayRow[] = line.split(",");
     
     if (arrayRow.length < 0) {
      throw new DataFileProcessingException("Incorrect line length: " + line);
     }
     if (firstLine) {
      seriesOneName = arrayRow[1];
      seriesTwoName = arrayRow[2];
      firstLine = false;
     } else {
      try {
         String monthName = arrayRow[0];
         int highestTemp = Integer.parseInt(arrayRow[1]);
         int lowestTemp = Integer.parseInt(arrayRow[2]);
         TempData newTemp = new TempData(monthName, highestTemp, lowestTemp);
         dataList.add(newTemp);
      }
       catch (Exception NumberFormatException) {
         throw new DataFileProcessingException("Encountered non-numeric data: " + line);
       }
     }
  }
  
   public ArrayList<TempData> getData(){
      return dataList;
   }
   public String getSeriesOneName(){
       return seriesOneName;
   } 
   public String getSeriesTwoName(){
       return seriesTwoName;
   }
}
