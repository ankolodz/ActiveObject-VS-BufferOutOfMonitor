package BufferOutOfMonitor;

import java.util.Random;

public class Main {


    public static void main(String[] args) {
        int bufferSize = 57;
        int customers = 5;
        int producer = 6;

        MyNewBuffer buffer = new MyNewBuffer(bufferSize);

        Customer[] customerThreads = new Customer[customers];
        Producer[] producerThreads = new Producer[producer];

        Random myRand = new Random();
        int j=0,k=0;
        for (int i=0;i<customers+producer;i++){
            if ((myRand.nextBoolean()==true) && customers>0){
                customerThreads[j] = new Customer(buffer);
                customerThreads[j].start();
                customers--;
                j++;
            }
            else{
                producerThreads[k] = new Producer(buffer);
                producerThreads[k].start();
                k++;
        }
//Układ zdarzeń blokujący program 1 klient 2 producentów
//    Rozmiar bufora  - 5 stan początkowy 0
//    Typ     Rozmiar     StanBufora Kolejka
//    P1       4           4           -
//    C1        5            4           C1
//    P2        3           4            C1 P2
//    P1        2           4            C1 P2 P1

        }
    }
}
