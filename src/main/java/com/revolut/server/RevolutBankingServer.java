package com.revolut.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Revolut banking server.
 */
public class RevolutBankingServer {

    private static final int PORT = 8080;
    private final ExecutorService FIXED_THREAD_POOL =
            Executors.newCachedThreadPool();

    /**
     * Instantiates a new Revolut banking server.
     */
    public RevolutBankingServer() {
        ApiObjectContainer.instantiateAPIObjects();
    }

    /**
     * Start.
     */
    public void start() {
        System.out.println("Starting Revolut Banking RevolutBankingServer");
        try (ServerSocket listener = new ServerSocket(PORT)) {
            System.out.println("Accepting HTTP request on Banking server on " +
                    "Port : " + PORT);
            while (true) {
                Socket socket = listener.accept();
                FIXED_THREAD_POOL.execute(new ApiExecutor(socket));
            }
        } catch (IOException ioException) {
            System.out.println("Exception while starting server " + ioException.getMessage());
        }
    }
}
