package BufferOutOfMonitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyNewBuffer {
    int buffer =0;
    int limit;
    Lock addLock = new ReentrantLock();
    Lock consumeLock = new ReentrantLock();
    Lock usingLock = new ReentrantLock();

    Condition usingLockCondition = usingLock.newCondition();



    public void setNewLimit (int size){limit=size;}
    public MyNewBuffer (int limit){this.limit=limit;}

    public void produce (int part) throws InterruptedException {
        addLock.lock();
        usingLock.lock();

        while(part + buffer > limit){
            usingLockCondition.await();
        }
        buffer += part;

        System.out.println("Add + "+buffer);
        usingLockCondition.signal();
        usingLock.unlock();
        addLock.unlock();


    }
    public void consume (int part) throws InterruptedException {
        consumeLock.lock();
        usingLock.lock();

        while(buffer - part <0){
            usingLockCondition.await();
        }
        buffer -= part;

        System.out.println("Produce "+buffer);
        usingLockCondition.signal();
        usingLock.unlock();
        consumeLock.unlock();


    }
}