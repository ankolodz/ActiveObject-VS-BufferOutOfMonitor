package BufferOutOfMonitor;

import java.util.Random;

import static java.lang.Math.abs;

public class Producer extends Thread {
    private MyNewBuffer buffer;

    public Producer (MyNewBuffer buffer){
        this.buffer = buffer;
    }


    @Override
    public void run() {
        Random myRand =new Random();
        while(true){
            try {
                buffer.produce(abs(myRand.nextInt()%30));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
