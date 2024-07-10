package ua.task.adp.warehouse.util;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class UdpServerInitializer {
  private static final int TEMPERATURE_PORT = 3344;
  private static final int HUMIDITY_PORT = 3355;
  private final KafkaProducerManager producerManager;

  public UdpServerInitializer(KafkaProducerManager producerManager) {
    this.producerManager = producerManager;
  }

  public void start() throws InterruptedException {
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      Channel tempChannel = startServer(group, TEMPERATURE_PORT);
      Channel humidityChannel = startServer(group, HUMIDITY_PORT);

      tempChannel.closeFuture().sync();
      humidityChannel.closeFuture().sync();
    } finally {
      group.shutdownGracefully();
      producerManager.close();
    }
  }

  private Channel startServer(EventLoopGroup group, int port) throws InterruptedException {
    Bootstrap b = new Bootstrap();
    b.group(group)
        .channel(NioDatagramChannel.class)
        .handler(new ChannelInitializer<NioDatagramChannel>() {
          @Override
          protected void initChannel(NioDatagramChannel ch) {
            ch.pipeline().addLast(new DatagramPacketDecoder(producerManager, port));
          }
        });

    return b.bind(port).sync().channel();
  }

}