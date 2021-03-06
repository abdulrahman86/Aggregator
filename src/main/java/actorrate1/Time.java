package actorrate1;

/**
 * Created by asattar on 2016-06-09.
 */
public class Time {

    private long count;
    private long time;
    private String message;

    public Time(String message) {
        count = 0;
        time = System.currentTimeMillis();
        this.message = message;
    }

    public void incCount() {
        count++;

        if (count % 5000 == 0) {

            System.out.println(message + ": " + (count * 1000) / (System.currentTimeMillis() - time));
        }
    }
}
