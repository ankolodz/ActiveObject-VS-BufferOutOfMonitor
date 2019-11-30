package Time_and_Variables;

public class GlobalsVariables {
    private  int Consumer = 10;
    private  int Producer = 10;
    private  int TaskBuforTime = 1000;
    private  int extraWork = 2000;
    private int lifeTime = 10;
    private  int quark = 2;
//    private  int

    public  int getConsumer() {
        return Consumer;
    }

    public  int getProducer() {
        return Producer;
    }

    public  int getTaskBuforTime() {
        return TaskBuforTime;
    }

    public  int getExtraWork() {
        return extraWork;
    }
    public int getLifeTime(){
        return lifeTime;
    }
    public  int getQuark(){
        return quark;
    }
    public  void setVariables (int C, int P,int T,int E,int L){
        Consumer = C;
        Producer = P;
        TaskBuforTime = T;
        extraWork = E;
        lifeTime = L;
    }

}
