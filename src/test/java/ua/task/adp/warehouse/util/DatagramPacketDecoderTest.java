package ua.task.adp.warehouse.util;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.buffer.ByteBuf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DatagramPacketDecoderTest {

  @Mock
  private KafkaProducerManager producerManager;

  @Mock
  private ChannelHandlerContext ctx;

  @Mock
  private DatagramPacket packet;

  @Mock
  private ByteBuf data;

  private DatagramPacketDecoder decoder;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    decoder = new DatagramPacketDecoder(producerManager, 3344);
  }

  @Test
  public void testDecode() throws Exception {
    when(packet.content()).thenReturn(data);
    when(data.readableBytes()).thenReturn(20);
    when(data.toString(StandardCharsets.UTF_8)).thenReturn("sensor_id=123; value=456");

    List<Object> out = mock(List.class);

    decoder.decode(ctx, packet, out);

    verify(producerManager).send("123", "456");
  }

  @Test
  public void testParseSensorData() {
    String data = "sensor_id=123; value=456";
    Map<String, String> result = decoder.parseSensorData(data);

    assertEquals("123", result.get("sensor_id"));
    assertEquals("456", result.get("value"));
  }
}