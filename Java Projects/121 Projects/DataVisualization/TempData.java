public class TempData {

      String timeUnit;
      int tempOne;
      int tempTwo;

    public TempData(String time, int t1, int t2){
        timeUnit = time;
        tempOne = t1;
        tempTwo = t2;
    }
    
    public String getTime(){
        return timeUnit;
    }
  
    public int getTempOne(){
        return tempOne;
    }
  
    public int getTempTwo(){
        return tempTwo;
    }

    public String toString(){
        return timeUnit+", "+tempOne+", "+tempTwo;
    }
}