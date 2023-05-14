import org.junit.Assert;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.*;

//import com.gradescope.jh61b.grader.GradedTest;

public class MediaListTest {

   private MediaList mediaList;

   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
       mediaList = new MediaList();
   }
   
   /** works in case of zero length **/
   @Test
   public void getSortedListOfAuthorsTest1(){
      String[] authors = mediaList.getSortedListOfAuthors();
      Assert.assertEquals("Test 1: getSortedListOfAuthors returns an empty array if there are no elements", 0, authors.length);
   }
   
   /** all authors are present in list **/
   @Test
   public void getSortedListOfAuthorsTest2(){
      createListForTesting(mediaList);
      List<String> authors = Arrays.asList(mediaList.getSortedListOfAuthors());
      String[] correctList = {"Coogler", "Kendrick Lamar", "Kubrick", "R.L.Stine", "Stephenson", "Travis Scott"};
      boolean valid = true;
      for(int i = 0; i < 6; i++){
         if(!authors.contains(correctList[i])){
            valid = false;
            break;
         }
      }
      Assert.assertTrue("Test 2: getSortedListOfAuthors() returns an array containing all values", valid);
   }
   
   /** List is actually sorted **/
   @Test
   public void getSortedListOfAuthorsTest3(){
      createListForTesting(mediaList);
      String[] authors = mediaList.getSortedListOfAuthors();
      String[] correctList = {"Cardi B", "Coogler", "Danny Boyle", "Kendrick Lamar", "Kubrick", "Kubrick", "R.L.Stine", "Simon Adler", "Stephenson", "Stephenson", "Travis Scott"};
      boolean valid = true;
      for(int i = 0; i < 6; i++){
         if(!authors[i].equals(correctList[i])){
            valid = false;
            break;
         }
      }
      Assert.assertTrue("Test 3: getSortedListOfAuthors() returns the correctly sorted list", valid);
   }  
   
   /** Test that the list has been created. **/
   @Test 
   public void initListTest() {
      String[] items = mediaList.getItemListAsStringArray(); 
      assertNotEquals("Test 4: The array is null- list may not have been initialized.", null, items);
   }
   
   /** Test the addItem method. **/
   @Test 
   public void addOneItemTest() {
      MediaItem item = new Movie("Black Panther", "Coogler", "fantasy", 134, "Chadwick Boseman", "2018");
      mediaList.addItem(item);
      String[] items = mediaList.getItemListAsStringArray(); 
      String correctStr = item.toString();
      String studentStr = items[0];
      assertEquals("Test 5: The item was not added.", correctStr, studentStr);
   }
   
   /** Test the addItem method-length of array. **/
   @Test 
   public void addOneItemLenOneTest() {
      mediaList.addItem(new Movie("Black Panther", "Coogler", "fantasy", 134, "Chadwick Boseman", "2018"));
      String[] items = mediaList.getItemListAsStringArray(); 
      assertEquals("Test 6: Call to addItem- the list should contain one item.", 1, items.length);
   }
   
   /** Test the getNumberOfItems method on an empty list. **/
   @Test 
   public void initListSizeTest() {
      int len = mediaList.getNumItems(); 
      assertEquals("Test 7: The getNumItems method should return 0 on an empty list.", 0, len);
   }
   
   /** Test the isEmpty method on a list with one item. **/
   @Test 
   public void getItemListAsArrayEmptyTest() {
      String[] items = mediaList.getItemListAsStringArray(); 
      assertEquals("Test 8: The getItemListAsArray method should return a zero length array on an empty itemList.", 0, items.length);
   } 
   
   @Test 
   public void initListIsEmptyTest() {
      boolean empty = mediaList.isEmpty(); 
      assertEquals("Test 9: The isEmpty method should return true on an empty list.", true, empty);
   }

   @Test 
   public void oneItemListSizeTest() {
      mediaList.addItem(new Movie("Black Panther", "Coogler", "fantasy", 134, "Chadwick Boseman", "2018"));
      int len = mediaList.getNumItems(); 
      assertEquals("Test 10: The getNumItems method should return 1 on list with 1 item.", 1, len);
   }  

   @Test 
   public void listOneItemIsEmptyTest() {
      mediaList.addItem(new Movie("Black Panther", "Coogler", "fantasy", 134, "Chadwick Boseman", "2018"));
      boolean empty = mediaList.isEmpty(); 
      assertEquals("Test 11: The isEmpty method should return false on a list with one item.", false, empty);
   }
      
   @Test 
   public void getAllItemsByTitleZeroMatchesTest() {
      createListForTesting(mediaList);
      String[] items = mediaList.getAllItemsByTitle("No Title Matches This"); 
      assertEquals("Test 12: Test the getAllItemsByTitle method with zero matches.", 0, items.length);
   }

   @Test 
   public void getAllItemsByTitleTwoMatchesTest() {
      createListForTesting(mediaList);
      String[] items = mediaList.getAllItemsByTitle("Goosebumps"); 
      assertEquals("Test 13: Test the getAllItemsByTitle method with 2 matches.", 2, items.length);
   }

   @Test 
   public void getItemListAsArrayAfterAdding11ItemsTest() {
      mediaList = new MediaList();
      createListForTesting(mediaList);
      String[] items = mediaList.getItemListAsStringArray(); 
      assertEquals("Test 14: Test the getItemListAsStringArray method after adding 11 items.", 11, items.length);
   }
   
   @Test
   public void getTitleOnEmptyListTest() {
       String[] titleItems = mediaList.getAllItemsByTitle("Goosebumps");
       assertEquals("Test 15: getAllItemsByTitle on empty list should return array length 0.", 0, titleItems.length);
   }
   
   @Test
   public void getOneMatchTitleLengthTest() {
       mediaList.addItem(new Song("Humble","Kendrick Lamar","hip hop", 2.57,"Damn","Top Dawg"));
       String[] genreItems = mediaList.getAllItemsByTitle("Humble");
       assertEquals("Test 16: getAllItemsByTitle should return array length 1 with one match.", 1, genreItems.length);
   }
   
   @Test
   public void getOneMatchTitleTest() {
       Song item = new Song("Humble","Kendrick Lamar","hip hop", 2.57,"Damn","Top Dawg");
       mediaList.addItem(item);
       String[] genreItems = mediaList.getAllItemsByTitle("Humble");
       String correctStr = item.toString();
       String studentStr = genreItems[0];
       assertEquals("Test 16: getAllItemsByTitle should return the corect item.", correctStr, studentStr);
   }
   
   @Test
   public void toStringBookTest() {
       MediaItem book = new Book("Hairy Potter","Just Kidding Rowling","Fantasy", 200, 14.5);
       assertEquals("Test 17: book toString() works", "Book: Hairy Potter, Just Kidding Rowling, Fantasy, 200, 14.5", book.toString());
   }
   
   @Test
   public void toStringSongTest() {
       MediaItem song = new Song("Come And Get Your Love","Redborne","Rock", 3.75, "The Very Best Of Redborne", "SME");
       assertEquals("Test 18: song toString() works", "Song: Come And Get Your Love, Redborne, Rock, 3.75, The Very Best Of Redborne, SME", song.toString());
   }
   
   @Test
   public void toStringMovieTest() {
       MediaItem movie = new Movie("Guardians of the Galaxy","James Gunn","Sci-Fi", 125, "Crisp rat", "2014");
       assertEquals("Test 19: movie toString() works", "Movie: Guardians of the Galaxy, James Gunn, Sci-Fi, 125, Crisp rat, 2014", movie.toString());
   }
   
   @Test
   public void superclassMovieTest() {
       MediaItem movie = new Movie("Guardians of the Galaxy","James Gunn","Sci-Fi", 125, "Crisp rat", "2014");
       boolean isA = movie instanceof MediaItem;
       assertEquals("Test 20: Movie is subclass of MediaItem", true, isA);
   }
   
   @Test
   public void movieGetterTest() {
       Movie movie = new Movie("Guardians of the Galaxy","James Gunn","Sci-Fi", 125, "Crisp rat", "2014");
       String studentAtts = movie.getLeadActor()+movie.getReleaseYear()+movie.getPlayTime();
       String correctAtts ="Crisp rat"+"2014"+125;
       assertEquals("Test 21: movie get methods", correctAtts, studentAtts);
   }
   
   @Test
   public void toStringPodcastTest() {
       MediaItem pc = new Podcast("what", "auth", "gen", "desc", "https://whoa", "J, 2020", true);
       String studentStr = pc.toString();
       String correctStr = "Podcast: "+"what, "+ "auth, "+ "gen, "+ "desc, "+ "https://whoa, "+ "J, 2020, "+ "true";
       assertEquals("Test 22: podcast toString() works", correctStr, studentStr);
   }
   
   @Test
   public void superclassPodcastTest() {
       MediaItem pc = new Podcast("what", "auth", "gen", "desc", "https://whoa", "J, 2020", true);
       boolean isA = pc instanceof MediaItem;
       assertEquals("Test 23: Podcast is subclass of MediaItem", true, isA);
   }
   
   @Test
   public void podcastGetterTest() {
       Podcast pc = new Podcast("what", "auth", "gen", "desc", "https://whoa", "J, 2020", true);
       String studentAtts = pc.getDescription()+pc.getWebsite()+pc.getDate()+pc.isVideo();
       String correctAtts ="desc"+ "https://whoa"+ "J, 2020"+"true";
       assertEquals("Test 24: Podcast get methods", correctAtts, studentAtts);
   }
 
   @Test
   public void containsItemTest1() {
       createListForTesting(mediaList);
       boolean studentResult = mediaList.containsItem("Black Panther", "Coogler");
       boolean correctResult = true;
       assertEquals("Test 25: Test containsItem on a true result", correctResult, studentResult);
   }
   
   @Test
   public void containsItemTest2() {
       createListForTesting(mediaList);
       boolean studentResult = mediaList.containsItem("Diamond Age", "Stephenson");
       boolean correctResult = false;
       assertEquals("Test 26: Test containsItem on a false result", correctResult, studentResult);
   }

   @Test 
   public void removeItemFromEmptyListReturnTest() {
      boolean studentRes = mediaList.removeItem("Downton Abbey", "Fellows"); 
      assertEquals("Test 27: Test remove item from empty list returns false.", false, studentRes);
   }
   
   @Test 
   public void removeItemFromEmptyListTest() {
      mediaList.removeItem("Downton Abbey", "Fellows"); 
      String[] items = mediaList.getItemListAsStringArray(); 
      assertEquals("Test 28: Test remove item from empty list.", 0, items.length);
   }
   
   @Test 
   public void addOneRemoveOneItemTest() {
      MediaItem item = new Movie("Black Panther", "Coogler", "fantasy", 134, "Chadwick Boseman", "2018");
      mediaList.addItem(item);
      mediaList.removeItem("Black Panther", "Coogler");
      String[] items = mediaList.getItemListAsStringArray(); 
      assertEquals("Test 29: Add item, remove item equals empty list.", 0, items.length);
   }
   
   @Test 
   public void addOneRemoveOneItemUpdateSizeTest() {
      MediaItem item = new Movie("Black Panther", "Coogler", "fantasy", 134, "Chadwick Boseman", "2018");
      mediaList.addItem(item);
      mediaList.removeItem("Black Panther", "Coogler");
      int studentSize = mediaList.getNumItems(); 
      assertEquals("Test 30: Add item, remove item size is 0.", 0, studentSize);
   }
   
   @Test 
   public void addOneRemoveOneItemUpdateEmptyTest() {
      MediaItem item = new Movie("Black Panther", "Coogler", "fantasy", 134, "Chadwick Boseman", "2018");
      mediaList.addItem(item);
      mediaList.removeItem("Black Panther", "Coogler");
      boolean studentRes = mediaList.isEmpty(); 
      assertEquals("Test 31: Add item, remove item isEmpty is true.", true, studentRes);
   }
   
   @Test 
   public void addManyRemoveOneItemSuccessTest() {
      createListForTesting(mediaList);
      mediaList.removeItem("Bit Flip", "Simon Adler");
      String[] items = mediaList.getItemListAsStringArray(); 
      assertEquals("Test 32: Add 11, remove one.", 10, items.length);
   }
   
   @Test 
   public void addManyRemoveOneItemFailTest() {
      createListForTesting(mediaList);
      mediaList.removeItem("Whoa", "Skeezix");
      String[] items = mediaList.getItemListAsStringArray(); 
      assertEquals("Test 33: Add 11, remove non-extant item.", 11, items.length);
   }
   
   @Test 
   public void addManyRemoveManyItemSuccessTest() {
     mediaList.addItem(new Song("Money","Cardi B","hip hop", 3.03,"Tiger Woods","Atlantic"));
     mediaList.addItem(new Movie("2001; A Space Odyssey","Kubrick","sci fi",142,"Keir Dullea","1968"));
     mediaList.addItem(new Book("Snow Crash","Stephenson","sci fi",480,3.5));
     mediaList.removeItem("Money","Cardi B");
     mediaList.removeItem("2001; A Space Odyssey", "Kubrick");
     mediaList.removeItem("Snow Crash","Stephenson");
     String[] items = mediaList.getItemListAsStringArray(); 
     assertEquals("Test 34: Add three, remove three.", 0, items.length);
   }
   
   @Test 
   public void addManyRemoveManyMoreItemSuccessTest() {
     mediaList.addItem(new Song("Money","Cardi B","hip hop", 3.03,"Tiger Woods","Atlantic"));
     mediaList.addItem(new Movie("2001; A Space Odyssey","Kubrick","sci fi",142,"Keir Dullea","1968"));
     mediaList.addItem(new Book("Snow Crash","Stephenson","sci fi",480,3.5));
     mediaList.removeItem("Money","Cardi B");
     mediaList.removeItem("2001; A Space Odyssey", "Kubrick");
     mediaList.removeItem("Snow Crash","Stephenson");
     mediaList.removeItem("Sleigh Ride","Stephenson");
     String[] items = mediaList.getItemListAsStringArray(); 
     assertEquals("Test 35: Add three, remove four.", 0, items.length);
   }

   @Test 
   public void getItemsByAuthorZeroMatchesTest() {
      createListForTesting(mediaList);
      String[] items = mediaList.getAllItemsByAuthor("No Author Matches This"); 
      assertEquals("Test 36: Test the getAllItemsByAuthor method with zero matches.", 0, items.length);
   }

   @Test 
   public void getItemsByAuthorTwoMatchesTest() {
      createListForTesting(mediaList);
      String[] items = mediaList.getAllItemsByAuthor("Kubrick"); 
      assertEquals("Test 37: Test the getAllItemsByAuthor method with 2 matches.", 2, items.length);
   }

   @Test
   public void getItemsByAuthorOnEmptyListTest() {
       String[] titleItems = mediaList.getAllItemsByAuthor("Goosebumps");
       assertEquals("Test 38: getAllItemsByAuthor on empty list should return array length 0.", 0, titleItems.length);
   }
   
   @Test
   public void getOneMatchTitleByAuthorLengthTest() {
       mediaList.addItem(new Song("Money","Cardi B","hip hop", 3.03,"Tiger Woods","Atlantic"));
       String[] genreItems = mediaList.getAllItemsByAuthor("Cardi B");
       assertEquals("Test 39: getAllItemsByAuthor should return array length 1 with one match.", 1, genreItems.length);
   }
   
   @Test
   public void getOneMatchTitleByAuthorTest() {
       Song item = new Song("Humble","Kendrick Lamar","hip hop", 2.57,"Damn","Top Dawg");
       mediaList.addItem(item);
       String[] items = mediaList.getAllItemsByAuthor("Kendrick Lamar");
       String correctStr = item.toString();
       String studentStr = items[0];
       assertEquals("Test 40: getAllItemsByAuthor should return the corect item.", correctStr, studentStr);
   }

   // Helper method to populate a list of MediaItems.
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
       list.addItem(new Book("REAMDE","Stephenson","fiction",1044,3.5));
   }

}
