import java.util.Timer;
import java.util.TimerTask;

public class CountDownTimer {
    Timer timer;

    public CountDownTimer(int minutes) {
        this.timer = new Timer();
        this.timer.schedule(new CountDownTask(), minutes * 60000); //convert ms to min
    }

    /* TO DO: must figure out to display time other than ending time */
    class CountDownTask extends TimerTask {
        @Override
        public void run() {
            int oneMinAlert = 1; //one min left
            int twoMinAlert = 2; //two min left
            CountDownTimer.this.timer.cancel(); //stop timer when minutes completed
        }
    }
}
