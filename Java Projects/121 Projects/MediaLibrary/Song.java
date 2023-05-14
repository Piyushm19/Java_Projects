 public class Song extends MediaItem {

  private double playTime; 
  private String album;
  private String label;
 
  public Song(String title, String author, String genre, double playTime, String album, String label){
      super(title, author, genre);
      this.playTime = playTime;
      this.album = album;
      this.label = label;
  }
  public double getPlayTime(){
     return playTime;
  }  
  public String getAlbum(){
     return album;
  }
  public String getLabel(){
     return label;
  }
  public String toString(){
     String tempToString = super.toString();
     return "Song: " + tempToString + ", " + playTime + ", " + album + ", " + label;
  }
}