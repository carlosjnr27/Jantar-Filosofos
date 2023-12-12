import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JantarDosFilosofos {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Filosofo[] Filosofos = new Filosofo[numFilosofos];
        Lock[] garfos = new ReentrantLock[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            garfos[i] = new ReentrantLock();
        }

        for (int i = 0; i < numFilosofos; i++) {
            Filosofos[i] = new Filosofo(i, garfos[i], garfos[(i + 1) % numFilosofos], i);
            Filosofos[i].start();
        }

        for (Filosofo Filosofo : Filosofos) {
            try {
                Filosofo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println("Todos os filósofos jantaram 5 vezes. Fim do jantar.");
    }
}