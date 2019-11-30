import java.util.concurrent.LinkedBlockingQueue;

public class ActivationQueue {
    private LinkedBlockingQueue<Future> queue = new LinkedBlockingQueue<Future>();

    public void put (Future node){
        try {
            queue.put(node);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  Future get () throws  InterruptedException{
            return queue.take();
    }
}
