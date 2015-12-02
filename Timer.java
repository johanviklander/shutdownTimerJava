package shutdownTimer;

/**
 *
 * @author Johan Viklander.
 */
public class Timer {

    private long stopTimeMillis = 0;
    private long durationSeconds = 0;
    private boolean timerIsSet = false;

    public Timer() {
    }

    public Timer(long durationSeconds) {
        this.durationSeconds = durationSeconds;
        stopTimeMillis = System.currentTimeMillis() + 1000 * durationSeconds;
        timerIsSet = true;
    }

    public void setTimer(long durationSeconds) {
        this.durationSeconds = durationSeconds;
        stopTimeMillis = System.currentTimeMillis() + 1000 * durationSeconds;
        timerIsSet = true;
    }

    public boolean isSet() {
        return timerIsSet;
    }

    public int startTimer() {
        if (!timerIsSet) {
            System.out.println("Error: Timer not set.");
            return 1;
        }

        System.out.println("Timer started at " + durationSeconds
                + " seconds.");

        while (System.currentTimeMillis() < stopTimeMillis) {
            // Let other processes kill this one if necessary.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
                return 1;
            }
        }

        return 0;
    }
}
