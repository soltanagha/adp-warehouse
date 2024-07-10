package ua.task.adp.warehouse.service;

import ua.task.adp.warehouse.exception.AbstractExceptionHandler;
import ua.task.adp.warehouse.util.KafkaProducerManager;
import ua.task.adp.warehouse.util.UdpServerInitializer;

public class UdpClient {
  private final KafkaProducerManager producerManager;
  private final UdpServerInitializer serverInitializer;
  private final AbstractExceptionHandler exceptionHandler;

  public UdpClient(KafkaProducerManager producerManager, UdpServerInitializer serverInitializer, AbstractExceptionHandler exceptionHandler) {
    this.producerManager = producerManager;
    this.serverInitializer = serverInitializer;
    this.exceptionHandler = exceptionHandler;
  }

  public void start() {
    try {
      serverInitializer.start();
    } catch (InterruptedException e) {
      exceptionHandler.handleException(e);
    } finally {
      producerManager.close();
    }
  }

}
