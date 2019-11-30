package Time_and_Variables;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

public class TimeManager {
    private long startTime;
    private long endTask;
    private long endSchredulerWork;
    //private boolean setSchredulerWork = false;
    private String id;

    public  TimeManager (String id){
        startTime = System.currentTimeMillis();
        this.id = id;
    }

    public void endTask (){
        endTask = System.currentTimeMillis();
    }

    public void endSchredulerWork(){
        endSchredulerWork = System.currentTimeMillis();
    }

    public void getResult (){
        System.out.println(id+" "+(System.currentTimeMillis()-startTime)+ " "+(endSchredulerWork-endTask));
        //setSchredulerWork = true;
    }
//
//    public boolean ServantFinished (){
//        return setSchredulerWork;
//    }
}
