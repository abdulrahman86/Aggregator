package actorrate3;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import scala.Option;

import java.util.Properties;

//import kafka.producer.KeyedMessage;
//import kafka.javaapi.producer.Producer;
//import kafka.producer.ProducerConfig;

public class Controller extends UntypedActor{

    private Properties props = new Properties();

    public Controller() {

    }

    @Override
    public void onReceive(Object message) throws Exception {


        if (message instanceof Terminated) {
            Terminated terminatedMsg = (Terminated) message;
        }

        else {

            ActorRef sessionEventHandlerActor = this.getContext().getChild(message.toString());

            if(sessionEventHandlerActor == null) {
                sessionEventHandlerActor = getContext().actorOf(Props.create(SessionHandler.class), message.toString());
            }

            sessionEventHandlerActor.tell(message, getSelf());

            getContext().watch(sessionEventHandlerActor);

        }
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        super.preRestart(reason, message);
    }
}
