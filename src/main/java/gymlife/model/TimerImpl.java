package gymlife.model;

public class TimerImpl {
    private int milliseconds;
    private boolean running;
    private Thread timerThread;

    public TimerImpl(int milliseconds) {
        this.milliseconds = milliseconds;
        this.running = false;
    }

    public void start() {
        if (!running) {
            running = true;
            timerThread = new Thread(() -> {
                while (milliseconds > 0) {
                    try {
                        Thread.sleep(1); // Sleep for 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    milliseconds--;
                    System.out.print("\rMilliseconds left: " + milliseconds);
                }
                System.out.println("Stop!");
                running = false;
            });
            timerThread.start();
        } else {
            System.out.println("Timer is already running.");
        }
    }

}

