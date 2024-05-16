package gymlife.model.statistics.api;

public class SynchronizerModel {
    private boolean signalArrived;

    public SynchronizerModel() {
        signalArrived = false;
    }

    public synchronized void waitForSignal() throws InterruptedException {
        while (!signalArrived) {
            this.wait();
        }
        signalArrived = false;
    }

    public synchronized void signal() {
        signalArrived = true;
        notifyAll();
    }
}
