package actorrate1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by asattar on 2016-06-09.
 */
public class Producer {

    private Properties props = new Properties();
    private KafkaProducer producer = null;

    public static void main(String[] args) {
        Producer producer = new Producer();

        Time time = new Time("Producer rate");

        for (long i = 0; i < 1000000; i++) {
            producer.send();
            time.incCount();
        }
    }
    public Producer() {
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(props);
    }

    public void send() {
        ProducerRecord<String, String> msg = new ProducerRecord("test7", "1");
        producer.send(msg);
    }
}
