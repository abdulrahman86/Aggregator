package actorrate2;

import akka.actor.ReceiveTimeout;
import akka.actor.UntypedActor;
import akka.persistence.SaveSnapshotFailure;
import akka.persistence.SaveSnapshotSuccess;
import akka.persistence.SnapshotOffer;
import akka.persistence.UntypedPersistentActor;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class SessionHandler extends UntypedActor{

    private Integer eventCount;


    private static Time time = new Time("Consumer rate");

    public SessionHandler() {
        getContext().setReceiveTimeout(Duration.create(1, TimeUnit.MINUTES));
        eventCount = 0;
    }


    @Override
    public void onReceive(Object msg) throws Exception {

        if (msg instanceof ReceiveTimeout) {
            Producer producer = new Producer("test100");
            producer.send(1);
            getContext().stop(self());
        }

        else {
            eventCount++;
            synchronized (time) {
                time.incCount();
            }

        }
    }




}
