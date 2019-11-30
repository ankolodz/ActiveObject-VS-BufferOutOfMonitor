package ActiveObject;

import Time_and_Variables.GlobalsVariables;

public class ActiveObject {
    int maxPart = 32;
    GlobalsVariables globalsVariables;

    public ActiveObject (GlobalsVariables globalsVariables){
        this.globalsVariables = globalsVariables;
    }

    public void start() throws InterruptedException {
        Consument[] customerThreads = new Consument[globalsVariables.getConsumer()];
        Producer[] producerThreads = new Producer[globalsVariables.getProducer()];

        Schreduler schreduler = new Schreduler(globalsVariables);
        schreduler.start();
        schreduler.setPriority(10);
        Proxy proxy = new Proxy(schreduler.getQueue());


        for (int i = 0; i < globalsVariables.getConsumer(); i++) {
            customerThreads[i] = new Consument(proxy, maxPart, globalsVariables, i);
            customerThreads[i].start();

        }
        for (int i = 0; i < globalsVariables.getProducer(); i++) {
            producerThreads[i] = new Producer(proxy, maxPart, globalsVariables, i);
            producerThreads[i].start();
        }
        Thread.sleep(1000*1);
        for (int i = 0; i < globalsVariables.getConsumer(); i++)
            customerThreads[i].stop();
        for (int i = 0; i < globalsVariables.getProducer(); i++)
            producerThreads[i].stop();
        System.out.println("Threads died");
        schreduler.stop();
        System.out.println("Schreduler died");

    }
}
