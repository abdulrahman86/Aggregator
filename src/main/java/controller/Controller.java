package controller;

import akka.actor.UntypedActor;
//import kafka.producer.KeyedMessage;
//import kafka.javaapi.producer.Producer;
//import kafka.producer.ProducerConfig;
import scala.Option;

import java.util.Properties;

public class Controller extends UntypedActor{

    private Properties props = new Properties();
//    private ProducerConfig config = null;
//    private Producer producer = null;

    public Controller() {
//        config =  new ProducerConfig(props);
//        producer = new Producer<String, String>(config);
    }

    @Override
    public void onReceive(Object message) throws Exception {

        System.out.println("hello " + message);
//        KeyedMessage<String, String> data = new KeyedMessage<String, String>("test4", null, message.toString());
//        producer.send(data);

    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        super.preRestart(reason, message);
    }
}
