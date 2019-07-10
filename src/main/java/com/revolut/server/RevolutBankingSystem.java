package com.revolut.server;

/**
 * The type Revolut banking system.
 */
public class RevolutBankingSystem {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        RevolutBankingServer revolutBankingServer = new RevolutBankingServer();
        revolutBankingServer.start();
    }
}
