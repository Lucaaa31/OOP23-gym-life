package gymlife.model;

import gymlife.view.TimerView;

public class TimerImpl {
    private int milliseconds;
    private boolean running;
    private Thread timerThread;
    private int firstDigit;
    private int secondDigit;
    private int thirdDigit;
    private int forthDigit;

    

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
                    firstDigit = milliseconds/10000;
                    secondDigit = (milliseconds / 1000) % 10;
                    thirdDigit = (milliseconds / 100) % 10;
                    forthDigit = milliseconds % 10;
                    TimerView.update(firstDigit, secondDigit, thirdDigit, forthDigit);
                }
                System.out.println("Stop!");
                running = false;
            });
            timerThread.start();
        }
    }


}

