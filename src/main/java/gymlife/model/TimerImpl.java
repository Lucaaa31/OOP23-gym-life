package gymlife.model;

import java.util.List;

import gymlife.view.TimerView;

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
                    TimerView.update(List.of(milliseconds/10000, (milliseconds / 1000) % 10, (milliseconds / 100) % 10, milliseconds % 10));
                }
                running = false;
            });
            timerThread.start();
        }
    }


}

