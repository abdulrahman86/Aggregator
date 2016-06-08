package session;

import akka.actor.ReceiveTimeout;
import akka.actor.UntypedActor;
import scala.concurrent.duration.Duration;

import javax.mail.Session;
import java.util.concurrent.TimeUnit;

/**
 * Created by asattar on 2016-06-08.
 */
public class SessionHandler extends UntypedActor{

    public SessionHandler() {
        getContext().setReceiveTimeout(Duration.create(15, TimeUnit.SECONDS));
    }
    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof ReceiveTimeout) {

            System.out.println("Timeout received:" + getSelf());

            context().stop(self());
        }

        System.out.println("SessionHandler:" + message);
    }
}
