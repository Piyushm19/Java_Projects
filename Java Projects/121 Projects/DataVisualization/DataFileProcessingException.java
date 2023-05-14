public class DataFileProcessingException extends Exception {

   String message;
   
   public DataFileProcessingException(String message){
      this.message = message;
   }
   
   public String getMessage(){
      return message;
   }
}