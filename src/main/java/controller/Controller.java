package controller;

import akka.actor.ActorRef;
import akka.actor.Props;
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


        System.out.println("hello " + message);

        ActorRef sessionEventHandlerActor = this.getContext().getChild(message.toString());

        if(sessionEventHandlerActor == null) {
            sessionEventHandlerActor = getContext().actorOf(Props.create(SessionHandler.class), message.toString());
        }

        sessionEventHandlerActor.tell(message, getSelf());

    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        super.preRestart(reason, message);
    }
}
