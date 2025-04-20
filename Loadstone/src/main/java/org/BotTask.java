package org;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class BotTask implements Runnable {
    private final String serverAddress;
    private final int serverPort;
    private final String botName;

    public BotTask(String serverAddress, int serverPort, String botName) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.botName = botName;
    }

    @Override
    public void run() {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(new InetSocketAddress(serverAddress, serverPort));
            System.out.println(botName + " connected to " + serverAddress + ":" + serverPort);

            // Keep the connection open for a while (no Minecraft protocol interaction yet)
            Thread.sleep(60000); // Keep connection for 1 minute
            System.out.println(botName + " - Connection kept alive for 1 minute.");

        } catch (IOException e) {
            System.err.println(botName + " - Error connecting: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}