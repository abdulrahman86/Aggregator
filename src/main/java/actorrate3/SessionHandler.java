package actorrate3;

import akka.actor.ReceiveTimeout;
import akka.persistence.SaveSnapshotFailure;
import akka.persistence.SaveSnapshotSuccess;
import akka.persistence.UntypedPersistentActor;
import scala.Option;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class SessionHandler extends UntypedPersistentActor{

    public Integer eventCount;

    private static actorrate2.Time time = new actorrate2.Time("Consumer rate");


    public SessionHandler() {
        getContext().setReceiveTimeout(Duration.create(2, TimeUnit.MINUTES));
        eventCount = 0;
    }



    @Override
    public void onReceiveRecover(Object msg)  {

    }



    @Override
    public void onReceiveCommand(Object msg) {

        if (msg instanceof ReceiveTimeout) {

            getContext().stop(self());

            saveSnapshot(eventCount);
        }

        else if (msg instanceof  SaveSnapshotSuccess || msg instanceof SaveSnapshotFailure) {

        }

        else {
            //saveSnapshot(50);
            synchronized (time) {
                time.incCount();
            }
            eventCount++;
        }
    }

    @Override
    public String persistenceId() {
        return getSelf().path().name();
    }



}
