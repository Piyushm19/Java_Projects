import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class MediaList {
      private ArrayList<MediaItem> itemList;   
   
   public MediaList(){
      itemList = new ArrayList<MediaItem>();
   }
   public void addItem(MediaItem newItem){
      itemList.add(newItem);
   }
   public boolean containsItem(String targetTitle, String targetAuthor){
      boolean result = false;
      for (MediaItem currentItem : itemList){
       if(currentItem.getTitle().equalsIgnoreCase(targetTitle) && currentItem.getAuthor().equalsIgnoreCase(targetAuthor))         
         result = true;
     }
     return result;     
   }
   public boolean removeItem(String targetTitle, String targetAuthor){
      boolean removeResult = false;
     for (MediaItem currentItem : itemList){
       if(currentItem.getTitle().equalsIgnoreCase(targetTitle) && currentItem.getAuthor().equalsIgnoreCase(targetAuthor)){
         itemList.remove(currentItem);
         removeResult = true;
         break;
        }
     }
     return removeResult;
   }
   public String[] getItemListAsStringArray(){
      String[] itemListAsStringArray = new String[itemList.size()];
         for (int i = 0; i < itemList.size(); i++) {
            itemListAsStringArray[i] = itemList.get(i).toString();
         }
      return itemListAsStringArray;
   }  
   public String[] getAllItemsByTitle(String targetTitle){
      ArrayList<String> itemByTitle = new ArrayList<String>();
      int i = 0;
      for (MediaItem currentItem : itemList) {
         if (currentItem.getTitle().equals(targetTitle)) 
            itemByTitle.add(itemList.get(i).toString());
      }
      String[] itemTitles = new String[itemByTitle.size()];
      for (i = 0; i < itemByTitle.size(); ++i) {
         itemTitles[i] = itemByTitle.get(i);
      }
      return itemTitles;  
   }  
   public String[] getAllItemsByAuthor(String targetAuthor){
      ArrayList<String> itemByAuthor = new ArrayList<String>();
      int i = 0;
      for (MediaItem currentItem : itemList) {
         if (currentItem.getAuthor().equals(targetAuthor)) {
            itemByAuthor.add(itemList.get(i).toString());
         }
      }
      String[] matchAuthor = new String[itemByAuthor.size()];
      for (i = 0; i < itemByAuthor.size(); i++) {
         matchAuthor[i] = itemByAuthor.get(i);
      }
      return matchAuthor;     
   }
   public String[] getSortedListOfAuthors(){
      String[] sortedByAuthor = new String[itemList.size()];
      int i = 0;
      for (MediaItem currentItem : itemList) {
         sortedByAuthor[i] = itemList.get(i).getAuthor();
         String author = currentItem.getAuthor();
         i++;
      }
      Arrays.sort(sortedByAuthor);
      return sortedByAuthor;
   }
   public int getNumItems(){
      return itemList.size();
   }
   public boolean isEmpty(){
      boolean isEmpty = false;
      if (itemList.size() == 0) {
         return true;
      } else {
         return false;
         }
      }
   }
