package ActiveObject;

public class Future {
    private boolean stateReady =false;
    private TypeWork typeWork;
    private int toDo;

    public Future(int toDo, TypeWork type){
        this.toDo = toDo;
        this.typeWork = type;
    }

    public  boolean isReady () {return stateReady;}
    public  synchronized void setReady (){
        stateReady = true;
        notifyAll();
    }
    public synchronized void getWork () throws InterruptedException {
        while(!isReady())  {
            wait();
        }

    }


    public int toWork (){ return toDo; }
    public TypeWork getTypeWork () {return typeWork;}

}
