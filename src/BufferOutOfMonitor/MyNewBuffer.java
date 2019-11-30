package BufferOutOfMonitor;

import Time_and_Variables.GlobalsVariables;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyNewBuffer {
    int buffer =0;
    int limit;
    GlobalsVariables globalsVariables;
    Lock addLock = new ReentrantLock();
    Lock consumeLock = new ReentrantLock();
    Lock usingLock = new ReentrantLock();

    Condition usingLockCondition = usingLock.newCondition();



    public void setNewLimit (int size){limit=size;}
    public MyNewBuffer (int limit, GlobalsVariables globalsVariables){
        this.limit=limit;
        this.globalsVariables = globalsVariables;
    }


    public void produce (int part) throws InterruptedException {
        addLock.lock();
        usingLock.lock();

        while(part + buffer > limit){
            usingLockCondition.await();
        }
        buffer += part;
        Thread.sleep(globalsVariables.getTaskBuforTime());

        //System.out.println("Add + "+buffer);
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
        Thread.sleep(globalsVariables.getTaskBuforTime());

        //System.out.println("Produce "+buffer);
        usingLockCondition.signal();
        usingLock.unlock();
        consumeLock.unlock();


    }
}