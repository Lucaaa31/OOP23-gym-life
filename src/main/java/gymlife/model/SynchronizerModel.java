package gymlife.model;

/**
 * This class provides a synchronization mechanism for coordinating
 * actions between multiple threads using a signal.
 */
public class SynchronizerModel {
    private boolean signalArrived;

    /**
     * Constructs a new SynchronizerModel with the initial state set to no signal.
     */
    public SynchronizerModel() {
        signalArrived = false;
    }

    /**
     * Waits for a signal to arrive. If no signal has arrived, the calling thread
     * will wait until a signal is received.
     * 
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    public synchronized void waitForSignal() throws InterruptedException {
        while (!signalArrived) {
            this.wait();
        }
        signalArrived = false;
    }

    /**
     * Sends a signal to waiting threads, notifying them that a signal has arrived.
     */
    public synchronized void signal() {
        signalArrived = true;
        notifyAll();
    }
}
