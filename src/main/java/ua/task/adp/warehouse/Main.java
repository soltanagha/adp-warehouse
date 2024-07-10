package ua.task.adp.warehouse;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import ua.task.adp.warehouse.config.KafkaConfigManager;
import ua.task.adp.warehouse.util.UdpServerInitializer;
import ua.task.adp.warehouse.exception.AbstractExceptionHandler;
import ua.task.adp.warehouse.exception.ExceptionLogHandler;
import ua.task.adp.warehouse.service.UdpClient;
import ua.task.adp.warehouse.util.KafkaProducerManager;

public class Main {
  public static void main(String[] args) {
    Producer<String, String> producer = new KafkaProducer<>(KafkaConfigManager.getKafkaProperties());
    KafkaProducerManager producerManager = new KafkaProducerManager(producer);
    UdpServerInitializer serverInitializer = new UdpServerInitializer(producerManager);
    AbstractExceptionHandler exceptionHandler = new ExceptionLogHandler();

    UdpClient client = new UdpClient(producerManager, serverInitializer, exceptionHandler);
    client.start();
  }
}