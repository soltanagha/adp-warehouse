package ua.task.adp.warehouse.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;

class KafkaProducerManagerTest {

  MockProducer<String, String> mockProducer =
      new MockProducer<>(true, new StringSerializer(), new StringSerializer());

  @Test
  void testSendSuccessfullyReceived() {
    KafkaProducerManager processor =
        new KafkaProducerManager(mockProducer);

    processor.send("t1", "30");
    processor.send("h1", "51");

    assertEquals(mockProducer.history().size(), 2);
  }

  @Test
  void testClose() {
    KafkaProducerManager processor =
        new KafkaProducerManager(mockProducer);

    processor.close();

    assertTrue(mockProducer.closed());
  }
}