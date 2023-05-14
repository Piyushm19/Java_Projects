import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.ArrayList;

public class CategoryDataModel {

   ArrayList<TempData> dataList;
   String seriesOneName;
   String seriesTwoName;

   public CategoryDataModel(ArrayList<TempData> data){
      data = dataList;
      seriesOneName = "";
      seriesTwoName = "";
   } 

   public CategoryDataModel(ArrayList<TempData> data, String sOneName, String sTwoName){
      dataList = data;
      seriesOneName = sOneName;
      seriesTwoName = sTwoName;
   }
   
    public CategoryDataset createCategoryDataset() {
       
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(TempData curData: dataList){
            dataset.addValue(curData.getTempOne(), seriesOneName, curData.getTime());
            dataset.addValue(curData.getTempTwo(), seriesTwoName, curData.getTime());
        }  
        return dataset; 
    }
}