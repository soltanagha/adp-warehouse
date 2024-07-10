package ua.task.adp.warehouse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.task.adp.warehouse.util.UdpServerInitializer;
import ua.task.adp.warehouse.exception.AbstractExceptionHandler;
import ua.task.adp.warehouse.util.KafkaProducerManager;

import static org.mockito.Mockito.*;

public class UdpClientTest {

  @Mock
  private KafkaProducerManager producerManager;

  @Mock
  private UdpServerInitializer serverInitializer;

  @Mock
  private AbstractExceptionHandler exceptionHandler;

  @InjectMocks
  private UdpClient udpClient;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testStart() throws InterruptedException {
    doNothing().when(serverInitializer).start();

    udpClient.start();

    verify(serverInitializer).start();
    verify(producerManager).close();
  }

  @Test
  public void testStartException() throws InterruptedException {
    doThrow(new InterruptedException()).when(serverInitializer).start();

    udpClient.start();

    verify(exceptionHandler).handleException(any(InterruptedException.class));
    verify(producerManager).close();
  }
}