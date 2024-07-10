package ua.task.adp.warehouse.util;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerManager {
  private static final String TOPIC = "sensor_data";

  private final Producer<String, String> producer;

  public KafkaProducerManager(Producer<String, String> kafkaProducer) {
    this.producer = kafkaProducer;
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