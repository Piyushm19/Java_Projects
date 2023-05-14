/**
 * This class encapsulates the data required to represent a MediaItem.
 * The attributes common to all MediaItems are: title, author and genre.
 * Subclasses should override the toString method.
 **/
 public class MediaItem {

  private String title;
  private String author;
  private String genre;
  
  /* Subclasses may add specific parameters to their constructor's
   * parameter lists.
  */
  public MediaItem(String title, String author, String genre){
    this.title = title;
    this.author = author; 
    this.genre = genre;
  }
  // get method for the title
  public String getTitle(){
     return title;
  }
  // get method for the author
  public String getAuthor(){
     return author;
  }
  // get method for the genre
  public String getGenre(){
     return genre;
  }
  
  // Subclasses should override.
  public String toString(){
     return title+", "+author+", "+genre;
  }
}