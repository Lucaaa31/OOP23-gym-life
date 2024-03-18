package gymlife.model;

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
                    firstDigit = milliseconds/1000;
                    secondDigit = (milliseconds / 100) % 10;
                    thirdDigit = (milliseconds / 10) % 10;
                    forthDigit = milliseconds % 10;
                    //System.out.print("\rMilliseconds left: " + milliseconds);
                }
                System.out.println("Stop!");
                running = false;
            });
            timerThread.start();
        } else {
            System.out.println("Timer is already running.");
        }
    }

    public int getFirstDigit() {
        return firstDigit;
    }

    public int getSecondDigit() {
        return secondDigit;
    }

    public int getThirdDigit() {
        return thirdDigit;
    }

    public int getForthDigit() {
        return forthDigit;
    }

}

