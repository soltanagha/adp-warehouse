package ua.task.adp.warehouse.util;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
public class UdpServerInitializerTest {

  @Mock
  private UdpServerInitializer udpServerInitializer;

  @Test
  public void testStartServer() throws Exception {
    EventLoopGroup group = new NioEventLoopGroup();
    int port = 3344;

    Method startServerMethod = UdpServerInitializer.class.getDeclaredMethod("startServer", EventLoopGroup.class, int.class);
    startServerMethod.setAccessible(true);

    Channel channel = (Channel) startServerMethod.invoke(udpServerInitializer, group, port);

    assertNotNull(channel);
  }
}