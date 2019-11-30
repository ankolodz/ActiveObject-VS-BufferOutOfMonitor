package ActiveObject;

import Time_and_Variables.GlobalsVariables;

import java.util.LinkedList;
import java.util.Queue;

public class Schreduler extends Thread {
    ActivationQueue queue = new ActivationQueue();
    Queue <Future> isWait = new LinkedList<>();
    Servant servant;
    GlobalsVariables globalsVariables;

    public Schreduler (GlobalsVariables g){
        this.globalsVariables =g;
        this.servant = new Servant(g.getTaskBuforTime());
    }
    @Override
    public void  run ()  {
        System.out.println("schreduler - start");
        while (true){
            //System.out.println("schreduler try get mainQueueu: "+isWait.size());
            Future task;
            TypeWork flag = TypeWork.NONE;
            if (isWait.size()!=0){
                task= isWait.peek();
                flag = task.getTypeWork();
                try {
                    if (sendToServant(task)) {
                        isWait.remove();
                        task.setReady();
                        continue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                try {
                    task = queue.get();
                    if(flag != TypeWork.NONE) {
                        isWait.add(task);
                        continue;
                    }
                    if (!sendToServant(task)) isWait.add(task);
                    else task.setReady();
                }
                catch(Exception e){
                    e.printStackTrace();
                }

        }
    }
    private boolean sendToServant (Future task) throws Exception {
        if (task.getTypeWork()==TypeWork.PRODUCER && servant.getActuallBufferSize()+task.toWork()<=servant.getMaxSieze()){
            servant.add(task.toWork());
            return true;
        }
        else if (task.getTypeWork()==TypeWork.CONSUMENT && servant.getActuallBufferSize()-task.toWork()>=0){
            servant.get(task.toWork());
            return true;
        }
        return false;

    }
    public ActivationQueue getQueue (){return queue;}
}
