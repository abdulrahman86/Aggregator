package actorrate3;

import actorrate2.*;
import akka.actor.ReceiveTimeout;
import akka.persistence.SaveSnapshotFailure;
import akka.persistence.SaveSnapshotSuccess;
import akka.persistence.SnapshotOffer;
import akka.persistence.UntypedPersistentActor;
import scala.Option;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class SessionHandler extends UntypedPersistentActor{

    public Integer eventCount;

    private static actorrate2.Time time = new actorrate2.Time("Consumer rate");


    public SessionHandler() {
        getContext().setReceiveTimeout(Duration.create(5, TimeUnit.MINUTES));
        eventCount = 0;
    }


    @Override
    public void onRecoveryFailure(Throwable cause, Option<Object> event){

    }

    @Override
    public void onReceiveRecover(Object msg) throws Exception {

    }

    @Override
    public void onReceiveCommand(Object msg) throws Exception {

        time.incCount(eventCount);
        getContext().stop(self());

        if (msg instanceof ReceiveTimeout) {

            getContext().stop(self());

//            persistAsync(eventCount, x -> {
//                time.incCount(eventCount);
//                getContext().stop(self());
//            });
        }


        else {
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
