  public class Movie extends MediaItem {
  
   private int  playTime; 
   private String leadActor;
   private String releaseYear;
   
   public Movie(String title, String author, String genre, int playTime, String leadActor, String releaseYear) {
      super(title, author, genre);
      this.playTime = playTime;
      this.leadActor = leadActor;
      this.releaseYear = releaseYear;
   }
   public int getPlayTime(){
      return playTime;
   }
   public String getLeadActor(){
      return leadActor;
   }
   public String getReleaseYear(){
      return releaseYear;
   }
  public String toString(){
     String tempToString = super.toString();
     return "Movie: " + tempToString + ", " + playTime + ", " + leadActor + ", " + releaseYear;
  }
}