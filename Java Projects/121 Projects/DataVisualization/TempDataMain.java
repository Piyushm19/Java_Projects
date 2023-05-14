import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import org.jfree.data.category.CategoryDataset;

public class TempDataMain {

   public static void main(String[] args)  {
      System.out.println("Data Visualizer");
      Scanner scan = new Scanner(System.in);
      DataFileAccessor dfa = null;
      boolean dataAcquired = false;

      System.out.println("Enter data file to read:");
      String fileName = scan.nextLine();  
      
      try {
         dfa = new DataFileAccessor(fileName, true);
      } catch (IOException ioex) {
         System.out.println("Error finding or opening the file: " + fileName);        
      }
      if(dfa != null) {
         try {
            dfa.processFile();
            dataAcquired = true;
         }
         catch(DataFileProcessingException ex) {
            System.out.print(ex);
         }
         if(dataAcquired) {
            
            ArrayList<TempData> tempData = dfa.getData();
            CategoryDataModel cdm = new CategoryDataModel(tempData, dfa.getSeriesOneName(), dfa.getSeriesTwoName());
      
            System.out.println("Enter the chart title:");
            String title = scan.nextLine();   
            System.out.println("Enter the horizontal axis label:");
            String horizAxisLabel = scan.nextLine();   
            System.out.println("Enter the vertical axis label:");
            String vertAxisLabel = scan.nextLine();    

            System.out.println("Enter type of plot: B for bar chart, L for Line Chart:");
            String plotType = scan.nextLine();
            if(plotType.equalsIgnoreCase("B")){
               BarChart chart = getBarChart(title, horizAxisLabel, vertAxisLabel, cdm.createCategoryDataset());
               chart.display(); 
            }
            else if(plotType.equalsIgnoreCase("L")) { 
               LineChart lineChart = getLineChart(title, horizAxisLabel, vertAxisLabel, cdm.createCategoryDataset());
               lineChart.display();
            }
            else 
               System.out.println("Unrecognized chart type.");
         }
      } 
   }
   
   public static BarChart getBarChart(String title, String hAxis, String vAxis, CategoryDataset ds){
      BarChart chart = new BarChart(title, hAxis, vAxis, ds);
      return chart;
   }
  
   public static LineChart getLineChart(String title, String hAxis, String vAxis, CategoryDataset ds){
       LineChart lineChart = new LineChart(title, hAxis, vAxis, ds);
       return lineChart;
  }
  }
  
