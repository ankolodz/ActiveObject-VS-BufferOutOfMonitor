package ActiveObject;

public class Servant {
    private int bufferSize = 64;
    private int buffer = 0;
    private  int taskTime = 0;

    public Servant (int taskTime){
        this.taskTime = taskTime;
    }
    public void add (int part) throws Exception{
        if (buffer+part<=bufferSize){
            buffer+=part;
            Thread.sleep(taskTime);
        }

        else
            throw new Exception("Bufor pełny");
    }

    public void get (int part) throws Exception{
        if(buffer-part>=0){
            buffer-=part;
            Thread.sleep(taskTime);
        }
        else
            throw new Exception("Bufor pusty") ;

    }
    public int getActuallBufferSize (){return buffer;}
    public int getMaxSieze () {return bufferSize;}

}
