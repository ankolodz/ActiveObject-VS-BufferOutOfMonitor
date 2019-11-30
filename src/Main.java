import ActiveObject.*;
import BufferOutOfMonitor.BufferOutOfMonitor;
import Time_and_Variables.GlobalsVariables;

public class Main {
    public static void main(String[] args) throws Exception {

        GlobalsVariables globalsVariables = new GlobalsVariables();
    for (int bufforTask = 5;bufforTask<=25;bufforTask+=5)
        for (int threadTask = 5;threadTask<=25;threadTask+=5)
            for (int c=1;c<1025;c*=2){
                for (int p=1;p<1025;p*=2){
                    for (int i=0;i<10;i++) {
                        System.out.format("***ActiveObject bT %d tT %d C %d P %d%n", bufforTask, threadTask, c, p);

                        globalsVariables.setVariables(c, p, bufforTask, threadTask, 100);
                        ActiveObject activeObject = new ActiveObject(globalsVariables);
                        activeObject.start();

                        System.out.format("***BufferOutOfMonitor bT %d tT %d C %d P %d%n", bufforTask, threadTask, c, p);
                        BufferOutOfMonitor bufferOutOfMonitor = new BufferOutOfMonitor(globalsVariables);
                        bufferOutOfMonitor.start();
                    }
                }
            }



        }
    }

