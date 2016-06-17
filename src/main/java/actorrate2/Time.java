package actorrate2;

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

        if (count % 1000 == 0) {

            System.out.println(message + ": " + (count * 1000) / (System.currentTimeMillis() - time));
        }
    }

    public void incCount(int count) {
        this.count  = this.count + count;

        if (count % 1000 == 0) {

            System.out.println(message + ": " + (count * 1000) / (System.currentTimeMillis() - time));
        }
    }
}
