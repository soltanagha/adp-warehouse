package ua.task.adp.warehouse.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class KafkaConfigManager {
  public static final String KAFKA_SERVER = "localhost:9092";

  public static Properties getKafkaProperties() {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,new StringSerializer());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, new StringSerializer());
    return props;
  }
}