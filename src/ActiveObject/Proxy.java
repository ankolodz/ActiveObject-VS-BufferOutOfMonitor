package ActiveObject;

public class Proxy {
    private ActivationQueue queue;

    public Proxy(ActivationQueue q) {
        queue = q;
    }

    public Future put(int toAdd) {
        Future task = new Future(toAdd, TypeWork.PRODUCER);
        queue.put(task);
        return task;
    }

    public Future get(int toGet) {
        Future task = new Future(toGet, TypeWork.CONSUMENT);
        queue.put(task);
        return task;
    }
}
