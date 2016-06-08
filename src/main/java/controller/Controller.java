package controller;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
//import kafka.producer.KeyedMessage;
//import kafka.javaapi.producer.Producer;
//import kafka.producer.ProducerConfig;
import akka.actor.dsl.Creators;
import scala.Option;
import session.SessionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Controller extends UntypedActor{

    private Properties props = new Properties();

    public Controller() {

    }

    @Override
    public void onReceive(Object message) throws Exception {


        if (message instanceof Terminated) {
            Terminated terminatedMsg = (Terminated) message;
            System.out.println("Actor terminated: " + terminatedMsg.actor());
        }

        else {

            System.out.println("hello " + message);

            ActorRef sessionEventHandlerActor = this.getContext().getChild(message.toString());

            if(sessionEventHandlerActor == null) {
                sessionEventHandlerActor = getContext().actorOf(Props.create(SessionHandler.class), message.toString());

                System.out.println("Actor created: " + sessionEventHandlerActor);
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
