package actorrate2;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

/**
 * Created by asattar on 2016-06-09.
 */
public class Producer {

    private Properties props = new Properties();
    private KafkaProducer producer = null;
    private Random random = new Random();
    private String topic;

    public static void main(String[] args) {
        Producer producer = new Producer("test7");

        Time time = new Time("Producer rate");

        for (long i = 0; i < 1000000; i++) {
            producer.send();
            time.incCount();
        }

        producer.close();
    }
    public Producer(String topic) {
        this.topic = topic;
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(props);
    }

    public void send() {
        ProducerRecord<String, String> msg = new ProducerRecord(topic, Math.abs(random.nextInt()) + "");
        producer.send(msg);
    }

    public void close() {
        producer.close();
    }

    public void send(int flag) {
        ProducerRecord<String, String> msg = new ProducerRecord(topic, Math.abs(random.nextInt()) + "");
        producer.send(msg);
        producer.close();
    }
}
