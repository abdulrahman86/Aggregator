package actorrate3;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import java.util.Properties;

public class KafkaStream {

    public static void main(String[] args) throws  Exception{

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe");
        props.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG, "localhost:2181");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put("group.id", "1");
        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


        final ActorSystem system = ActorSystem.create("AggregationModule");
        ActorRef controller = system.actorOf(Props.create(Controller.class), "controller");

        Time time = new Time("Consumer rate");

        KStreamBuilder builder = new KStreamBuilder();
        builder.stream("test8").foreach((x, y) -> {
            controller.tell(y, controller.noSender());
        });

        KafkaStreams streams = new KafkaStreams(builder, props);
        streams.start();
    }
}
