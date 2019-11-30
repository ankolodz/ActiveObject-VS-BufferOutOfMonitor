package BufferOutOfMonitor;

import Time_and_Variables.GlobalsVariables;

public class BufferOutOfMonitor {
    GlobalsVariables globalsVariables;

    public BufferOutOfMonitor (GlobalsVariables globalsVariables){
        this.globalsVariables = globalsVariables;
    }

    public void start() throws InterruptedException {
        int bufferSize = 64;
        MyNewBuffer buffer = new MyNewBuffer(bufferSize, globalsVariables);

        Customer[] customerThreads = new Customer[globalsVariables.getConsumer()];
        Producer[] producerThreads = new Producer[globalsVariables.getProducer()];

        for (int i=0;i<globalsVariables.getConsumer();i++){
            customerThreads[i] = new Customer(buffer,globalsVariables,i);
            customerThreads[i].start();
        }
        for (int i=0;i<globalsVariables.getProducer();i++){
            producerThreads[i] = new Producer(buffer,globalsVariables,i);
            producerThreads[i].start();
        }
        Thread.sleep(1000*30);
        for (int i = 0; i < globalsVariables.getConsumer(); i++)
            customerThreads[i].join(1);
        for (int i = 0; i < globalsVariables.getProducer(); i++)
            producerThreads[i].join(1);

    }
}
