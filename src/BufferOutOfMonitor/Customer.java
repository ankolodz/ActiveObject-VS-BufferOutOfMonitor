package BufferOutOfMonitor;

import Time_and_Variables.GlobalsVariables;
import Time_and_Variables.TimeManager;

import java.util.Random;

import static java.lang.Math.abs;

public class Customer extends Thread {
    private MyNewBuffer buffer;
    private GlobalsVariables globalsVariables;
    private int id;

    public Customer (MyNewBuffer buffer,GlobalsVariables globalsVariables,int id)
    {
        this.id = id;
        this.buffer = buffer;
        this.globalsVariables = globalsVariables;
    }

    @Override
    public void run() {
        Random myRand = new Random(globalsVariables.getCustomerSeek()+id);

        while(true) {
            try {
                TimeManager time = new TimeManager("C "+id);
                buffer.consume(abs(myRand.nextInt()%32));
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
