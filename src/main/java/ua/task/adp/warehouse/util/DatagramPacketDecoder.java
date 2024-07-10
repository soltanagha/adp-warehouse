package ua.task.adp.warehouse.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DatagramPacketDecoder extends MessageToMessageDecoder<DatagramPacket> {
  private final KafkaProducerManager producerManager;
  private final int port;

  public DatagramPacketDecoder(KafkaProducerManager producerManager, int port) {
    this.producerManager = producerManager;
    this.port = port;
  }

  @Override
  protected void decode(ChannelHandlerContext ctx, DatagramPacket packet, List<Object> out) {
    ByteBuf data = packet.content();
    if (data.readableBytes() > 0) {
      String message = packet.content().toString(StandardCharsets.UTF_8);
      Map<String, String> parsedData = parseSensorData(message);

      // Extract sensor ID and value
      String sensorId = parsedData.get("sensor_id");
      String value = parsedData.get("value");
      System.out.println("Received on port " + port + ": sensor_id=" + sensorId + ", value=" + value);

      producerManager.send(sensorId, value);
      System.out.println("Received and sent to Kafka: " + message);
    }


  }
  public Map<String, String> parseSensorData(String data) {
    return Arrays.stream(data.split(";\\s*"))
        .map(entry -> entry.split("="))
        .filter(array -> array.length == 2)
        .collect(Collectors.toMap(
            arr -> arr[0].trim(),
            arr -> arr[1].trim()
        ));
  }
}