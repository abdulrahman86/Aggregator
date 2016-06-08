package session;

import akka.persistence.*;
import akka.actor.ReceiveTimeout;
import akka.actor.UntypedActor;
import akka.persistence.*;
import scala.concurrent.duration.Duration;

import javax.mail.Session;
import java.util.concurrent.TimeUnit;

public class SessionHandler extends UntypedPersistentActor{

    @Override
    public void onReceiveRecover(Object msg) throws Exception {

        if (msg instanceof SnapshotOffer) {
            eventCount = (Integer)((SnapshotOffer)msg).snapshot();
        } else {
            unhandled(msg);
        }
    }

    @Override
    public void onReceiveCommand(Object msg) throws Exception {

        if (msg instanceof ReceiveTimeout) {

            System.out.println("Timeout received:" + getSelf());

            saveSnapshot(eventCount);
        }
        else if (msg instanceof SaveSnapshotSuccess) {

            System.out.println("Save snapshot success:" + ((SaveSnapshotSuccess)msg).metadata());

            getContext().stop(self());
        }

        else if (msg instanceof SaveSnapshotFailure) {

            System.out.println("Save snapshot success:" + ((SaveSnapshotFailure)msg).metadata());

            saveSnapshot(eventCount);
        }

        else {
            eventCount++;

            saveSnapshot(eventCount);

            System.out.println("SessionHandler:" + msg);
        }
    }

    @Override
    public String persistenceId() {
        return getSelf().path().name();
    }

    public Integer eventCount = 0;

    public SessionHandler() {
        getContext().setReceiveTimeout(Duration.create(15, TimeUnit.SECONDS));
    }

}
