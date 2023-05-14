import java.util.Scanner;
/* This application manages a collection of media items for a user. The items
 * are representeed by MediaItem objects. The items are managed by a MediaList object.
 * Their are currently three types of MediaItems: books (electronic format), movies, and songs.
 * Each type is modeled as a subclass of MediaItem.
 * A user has several options that are displayed in a menu format.
 * This class runs a console interface between a user and the mediaList
*/
import java.io.*;

public class LibraryMain {
       
   public static void main(String[] args) throws IOException{
      System.out.println("My Media Library");
      Scanner scan = new Scanner(System.in);
      MediaList mediaList = new MediaList();        
      boolean keepGoing = true;
      String userStr = "";
      // can use this if you wish   createListForTesting(mediaList);
      while(keepGoing) {
         System.out.println(" ");
         System.out.println("Main Menu:");
         System.out.println("Enter A to view all media items.");
         System.out.println("Enter C to know if an item exists in the library."); 
         System.out.println("Enter D to add a new item."); 
         System.out.println("Enter F to find all titles by an author.");        
         System.out.println("Enter R to remove a media item.");         
         System.out.println("Enter S to view a sorted list of authors");        
         System.out.println("Enter T to list all items with a specific title."); 
         System.out.println("Enter X to quit.");
         System.out.println("");
         userStr = scan.nextLine();
       // Check user's choice and execute it.
         //A: view all media items
         if (userStr.equalsIgnoreCase("A")){
            System.out.println("Your media: ");
            String[] items = mediaList.getItemListAsStringArray();
            if(items.length>0)
               printStringArray(items, true);
            else
               System.out.println("No items in library.");
         }
         //C: know if an item exists in the library
         else if (userStr.equalsIgnoreCase("C")){
            System.out.println("Enter the title:");
            String title = scan.nextLine();
            System.out.println("Enter the author:");
            String author = scan.nextLine();
            boolean result = mediaList.containsItem(title, author);
            if(result){
               System.out.println("Found item "+title+", by author "+author+" in the library.");
            }
            else{
               System.out.println("Could not find "+title+", by author "+author+" in the library.");
            }
         } 
         //D: add a new item        
         else if(userStr.equalsIgnoreCase("D")){ 
            MediaItem item = null;                
            System.out.println("Enter the title: ");
            String title = scan.nextLine();
            System.out.println("Enter the author/artist/director: ");
            String author = scan.nextLine();
            System.out.println("Enter the genre: ");
            String genre = scan.nextLine();
            System.out.println("What type of media? Enter B for book, M for movie, S for song, P for podcast: ");
            String type = scan.nextLine();
            if(type.equalsIgnoreCase("B")){
               System.out.println("Enter the number of pages: ");
               String pages = scan.nextLine();
               System.out.println("Enter the preferred font size: ");
               String font = scan.nextLine();
               item = new Book(title, author, genre, Integer.parseInt(pages), Double.parseDouble(font));
            }
            else if(type.equalsIgnoreCase("M")){
               System.out.println("Enter the playing time (minutes): ");
               String playTime = scan.nextLine();
               System.out.println("Enter the lead actor: ");
               String actor = scan.nextLine();
               System.out.println("Enter the release year YYYY: ");
               String year = scan.nextLine();
               item = new Movie(title, author, genre, Integer.parseInt(playTime), actor, year);
            }
            else if(type.equalsIgnoreCase("S")){
               System.out.println("Enter the playing time (minutes): ");
               String playTime = scan.nextLine();
               System.out.println("Enter the album: ");
               String album = scan.nextLine();
               System.out.println("Enter the label: ");
               String label = scan.nextLine();
               item = new Song(title, author, genre, Double.parseDouble(playTime), album, label);
            }
            else if(type.equalsIgnoreCase("P")){
               System.out.println("Enter description: ");
               String description = scan.nextLine();
               System.out.println("Enter the website address: ");
               String website = scan.nextLine();
               System.out.println("Enter the date: ");
               String date = scan.nextLine();
               System.out.println("Is this a video podcast? y/n: ");
               String ans = scan.nextLine();
               boolean isVideo = ans.equals("y") ? true : false;
               item = new Podcast(title, author, genre, description, website, date, isVideo);
            }
            else{
               System.out.println("Cannot add: unrecognized type.");
            }
            if(item != null){
               mediaList.addItem(item);
               System.out.println("New item has been added.");
            }
         }
         //F: find all titles by an author
         else if (userStr.equalsIgnoreCase("F")){ 
            System.out.println("Enter the author:");
            String author = scan.nextLine();
            String[] titles = mediaList.getAllItemsByAuthor(author);
            if(titles.length>0){
               printStringArray(titles, false);
            }
            else{
               System.out.println("Could not find any items by "+author+" in the library.");
            }
         }
         //R: remove a media item
         else if (userStr.equalsIgnoreCase("R")){
            System.out.println("Enter the title:");
            String title = scan.nextLine();
            System.out.println("Enter the author:");
            String author = scan.nextLine();
            boolean result = mediaList.removeItem(title, author);
            if(result){
               System.out.println("Item "+title+", by author "+author+" was removed from the library.");
            }
            else{
               System.out.println("Could not find "+title+" in the library, nothing removed.");
            }
         }         
         //S: view a sorted list of authors
         else if(userStr.equalsIgnoreCase("S")){
            System.out.println("Your authors are:");
            String[] authors = mediaList.getSortedListOfAuthors();
            for(int i = 0; i < authors.length; i++){
               System.out.println((i+1) + ": " + authors[i]);
            }
            if(authors.length == 0){
               System.out.println("No items in the library.");
            }
         }
         //T: list all items with a specific title
         else if (userStr.equalsIgnoreCase("T")){ 
            System.out.println("Enter the title:");
            String title = scan.nextLine();
            String[] titles = mediaList.getAllItemsByTitle(title);
            if(titles.length>0){
               printStringArray(titles, false);
            }
            else{
               System.out.println("Could not find items titled "+title+" in the library.");
            }
         }
         //X: quit the app
         else if(userStr.equalsIgnoreCase("X")){
            keepGoing = false;
         }
         else{// if we get here...
            System.out.println("Unrecognized input.");
         }            
      }
      System.out.println("Bye for now.");
      scan.close();
   }
    
   /**This method prints the Strings in the array in as a numbered list.
    * Each String represents the data for a MediaItem.
   **/
   public static void printStringArray(String[] stringArr, boolean withNumbers){
      StringBuilder sb = new StringBuilder();
      for(int i=0; i<stringArr.length;i++){
         if(withNumbers)
            sb.append(i+1).append(" ").append(stringArr[i]).append("\n");
         else
            sb.append(stringArr[i]).append("\n");
      }
      System.out.println(sb.toString());
   }
   
   // Can use this for testing purposes: populate the array with 7 items.
   private static void createListForTesting(MediaList list){
       list.addItem(new Song("Money","Cardi B","hip hop", 3.03,"Tiger Woods","Atlantic"));
       list.addItem(new Movie("2001; A Space Odyssey","Kubrick","sci fi",142,"Keir Dullea","1968"));
       list.addItem(new Book("Snow Crash","Stephenson","sci fi",480,3.5));
       list.addItem(new Book("Goosebumps","R.L.Stine", "fiction",128,3.5));
       list.addItem(new Song("Humble","Kendrick Lamar","hip hop", 2.57,"Damn","Top Dawg"));
       list.addItem(new Song("Goosebumps","Travis Scott","trap",4.03,"Birds in the Trap Sing McKnight","Epic"));
       list.addItem(new Movie("Black Panther", "Coogler", "fantasy", 134, "Chadwick Boseman", "2018"));
       list.addItem(new Podcast("Bit Flip", "Simon Adler", "science", "Back in 2003, Belgium was holding a national election. One of their first where the votes would be cast and counted on computers. Thousands of hours of preparation went into making it unhackable. And when the day of the vote came, everything seemed to have gone well. That was, until a cosmic chain of events caused a single bit to flip and called the outcome into question.", "https://www.wnycstudios.org/podcasts/radiolab", "May 8, 2019", false));
       list.addItem(new Movie("The Shining","Kubrick","horror",146,"Jack Nicholson","1980"));
       list.addItem(new Movie("Slumdog Millionaire","Danny Boyle", "drama",123,"Dev Patel","2009"));
       list.addItem(new Book("The Three-Body Problem","Liu Cixin","sci fi",302,3.5));
       list.addItem(new Book("Oryx and Crake","Margaret Atwood","dystopian fiction",350,3.5));
   }
}