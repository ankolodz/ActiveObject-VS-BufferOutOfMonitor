package BufferOutOfMonitor;

import Time_and_Variables.GlobalsVariables;
import Time_and_Variables.TimeManager;

import java.util.Random;

import static java.lang.Math.abs;

public class Producer extends Thread {
    private MyNewBuffer buffer;
    private int id;
    private GlobalsVariables globalsVariables;

    public Producer (MyNewBuffer buffer,GlobalsVariables globalsVariables,int id){
        this.id = id;
        this.buffer = buffer;
        this.globalsVariables = globalsVariables;
    }


    @Override
    public void run() {
        Random myRand =new Random(globalsVariables.getProducerSeek()+id);
        for (int i=0;i<globalsVariables.getLifeTime();i++){
            try {
                TimeManager time = new TimeManager("P "+id);

                buffer.produce(abs(myRand.nextInt()%32));

                time.endSchredulerWork();

                Thread.sleep(globalsVariables.getExtraWork());

                time.endTask();
                time.getResult();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
