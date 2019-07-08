package com.revolut.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int THREAD_POOL_SIZE = 20;

    public void start() {
        try (ServerSocket listener = new ServerSocket(8080)) {
            System.out.println("Banking server is running...");
            ExecutorService pool =
                    Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            while (true) {
                pool.execute(new ApiExecutor(listener.accept()));
            }
        } catch (IOException ioException) {
            System.out.println("Exception while starting server " + ioException.getMessage());
//            throw new BankingException(ioException.getMessage());
        }
    }
}
