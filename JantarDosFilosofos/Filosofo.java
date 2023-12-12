import java.util.concurrent.locks.Lock;

class Filosofo extends Thread {
    private int id;
    private Lock esqGarfo, dirGarfo;
    private int jantou = 0;

    public Filosofo(int id, Lock esqGarfo, Lock dirGarfo, int jantou) {
        this.id = id;
        this.esqGarfo = esqGarfo;
        this.dirGarfo = dirGarfo;
        this.jantou = jantou;
    }

    private void pensando() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep(3000);
    }

    private void comer() throws InterruptedException {
        esqGarfo.lock();
        dirGarfo.lock();

        System.out.println("Filósofo " + id + " está comendo.");
        Thread.sleep(3000);

        dirGarfo.unlock();
        esqGarfo.unlock();

        jantou++;
    }

    @Override
    public void run() {
        try {
            while (jantou < 5) {
                pensando();
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
