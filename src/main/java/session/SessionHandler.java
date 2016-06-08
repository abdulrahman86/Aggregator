package session;

import akka.actor.UntypedActor;

/**
 * Created by asattar on 2016-06-08.
 */
public class SessionHandler extends UntypedActor{
    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println("SessionHandler:" + message);
    }
}
