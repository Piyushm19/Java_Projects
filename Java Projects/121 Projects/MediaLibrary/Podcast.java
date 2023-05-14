 public class Podcast extends MediaItem {
 
   private String description;
   private String website;
   private String date;
   private boolean isVideo;
   
   public Podcast(String title, String author, String genre, String description, String website, String date, boolean isVideo) {
      super(title, author, genre);
      this.description = description;
      this.website = website;
      this.date = date;
      this.isVideo = isVideo;
   }
   public String getDescription() {
      return description;
   } 
   public String getWebsite() {
      return website;
   } 
   public String getDate() { 
      return date;
   }
   public boolean isVideo() {
      return isVideo;
   }
  public String toString(){
     String tempToString = super.toString();
     return "Podcast: " + tempToString + ", " + description + ", " + website + ", " + date + ", " + isVideo;
  } 
}