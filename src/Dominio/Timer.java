package Dominio;

public abstract class Timer implements Runnable {

    private Thread hilo;
    private boolean activo;
    private int counter = 0;
    protected final Juego j;

    public Timer(int limite,Juego j) {
        this.counter = limite;
        this.j = j;
    }

    private static final int millis = 1000;

    public void start() {
        activo = true;
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        while (activo && counter > 0) {
            try {
                avisar();
                System.out.println(counter);
                Thread.sleep(millis);
                counter--;
                if(counter <= 0)
                     terminado();
            } catch (InterruptedException ex) {
            }
        }
    }

    public void stop() {
        pause();
        counter = 0;
    }

    public void pause() {
        activo = false;
        hilo.interrupt();
    }
    
    abstract void terminado();
    
    abstract void avisar();

    public int getCounter() {
        return counter;
    }
    
    
}
