package ua.task.adp.warehouse.util;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import ua.task.adp.warehouse.config.KafkaConfigManager;

public class KafkaProducerManager {
  private static final String TOPIC = "sensor_data";

  private final KafkaProducer<String, String> producer;

  public KafkaProducerManager() {
    this.producer = new KafkaProducer<>(KafkaConfigManager.getKafkaProperties());
  }

  public void send(String sensor_id, String message) {
    producer.send(new ProducerRecord<>(TOPIC, sensor_id, message));
  }

  public void close() {
    if (producer != null) {
      producer.close();
    }
  }
}