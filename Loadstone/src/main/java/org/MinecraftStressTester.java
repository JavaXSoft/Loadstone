package org;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MinecraftStressTester {

    private static final String CONFIG_FILE = "config.properties";

    public static void main(String[] args) {
        Properties config = loadConfig();
        if (config == null) {
            System.err.println("Failed to load configuration.");
            return;
        }

        String serverAddress = config.getProperty("server.address", "localhost");
        int serverPort = Integer.parseInt(config.getProperty("server.port", "25565"));
        int numBots = Integer.parseInt(config.getProperty("bots.count", "10"));

        ExecutorService botThreadPool = Executors.newFixedThreadPool(numBots);

        System.out.println("Starting overload with " + numBots + " bots...");

        for (int i = 0; i < numBots; i++) {
            String botName = "StressBot_" + (i + 1);
            BotTask botTask = new BotTask(serverAddress, serverPort, botName);
            botThreadPool.submit(botTask);
        }

        // Keep the main thread alive for a while to allow bots to connect
        try {
            Thread.sleep(60000); // Run for 1 minute
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Stress test duration finished. Shutting down bots...");
        botThreadPool.shutdownNow();
        try {
            if (!botThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("Some bot tasks did not terminate gracefully.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Loadstone stress test complete.");
    }

    private static Properties loadConfig() {
        Properties config = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            config.load(input);
            return config;
        } catch (IOException ex) {
            System.err.println("Error loading config file: " + ex.getMessage());
            System.err.println("Please ensure 'config.properties' exists in the project root.");
            return null;
        }
    }
}