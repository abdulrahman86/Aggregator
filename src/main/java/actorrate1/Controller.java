package actorrate1;

import akka.actor.UntypedActor;
import scala.Option;

import java.util.Properties;

//import kafka.producer.KeyedMessage;
//import kafka.javaapi.producer.Producer;
//import kafka.producer.ProducerConfig;

public class Controller extends UntypedActor{

    private Properties props = new Properties();
    private Time time = new Time("Consumer rate");

    public Controller() {

    }

    @Override
    public void onReceive(Object message) throws Exception {
        time.incCount();
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        super.preRestart(reason, message);
    }
}
