package ua.task.adp.warehouse;

import ua.task.adp.warehouse.util.UdpServerInitializer;
import ua.task.adp.warehouse.exception.AbstractExceptionHandler;
import ua.task.adp.warehouse.exception.ExceptionLogHandler;
import ua.task.adp.warehouse.service.UdpClient;
import ua.task.adp.warehouse.util.KafkaProducerManager;

public class Main {
  public static void main(String[] args) {
    KafkaProducerManager producerManager = new KafkaProducerManager();
    UdpServerInitializer serverInitializer = new UdpServerInitializer(producerManager);
    AbstractExceptionHandler exceptionHandler = new ExceptionLogHandler();

    UdpClient client = new UdpClient(producerManager, serverInitializer, exceptionHandler);
    client.start();
  }
}