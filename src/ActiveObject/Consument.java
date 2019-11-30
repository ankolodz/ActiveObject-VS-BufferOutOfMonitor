package ActiveObject;

import java.util.Random;

import Time_and_Variables.GlobalsVariables;
import Time_and_Variables.TimeManager;

import static java.lang.Math.abs;

public class Consument extends Thread {

    private Proxy proxy;
    private int maxPart;
    private Future futures;
    private GlobalsVariables globalVariables;
    private int id;

    public Consument(Proxy proxy, int maxPart, GlobalsVariables g, int id) {
        this.proxy = proxy;
        this.maxPart = maxPart;
        this.globalVariables = g;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Random rand = new Random();
            while(true) {
                TimeManager time = new TimeManager("C"+id);
                futures = proxy.get(abs(rand.nextInt() % maxPart + 1));
                boolean workReady=false;
                for (int j = 0; j < globalVariables.getExtraWork(); j += globalVariables.getQuark()) {
                    Thread.sleep(globalVariables.getQuark());
                    if (futures.isReady()&&!workReady) {
                        time.endSchredulerWork();
                        workReady = true;
                    }
                    //System.out.println("Consume! - wait");

                }
                time.endTask();
                futures.getWork();
                if (!workReady) time.endSchredulerWork();

            time.getResult();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
