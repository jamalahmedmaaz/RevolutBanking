package com.revolut.server;

import com.revolut.util.JsonUtil;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * The type Api executor.
 */
public class ApiExecutor implements Runnable {

    private Socket socket;

    /**
     * Instantiates a new Api executor.
     *
     * @param socket the socket
     */
    public ApiExecutor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream);
            BufferedReader bufferedReader =
                    new BufferedReader(inputStreamReader);
            String apiName = readApiName(bufferedReader);
            StringBuilder payload = new StringBuilder();
            readPayLoad(bufferedReader, payload);
            Object response = invoke(payload.toString(), apiName);
            PrintWriter printWriter =
                    new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(JsonUtil.toJsonString(response));
        } catch (Exception e) {
            System.out.println("Exception while executing " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Exception while closing socket" + e.getMessage());
            }
        }
    }

    private void readPayLoad(BufferedReader bufferedReader,
                             StringBuilder payload) throws IOException {
        while (bufferedReader.ready()) {
            payload.append((char) bufferedReader.read());
        }
    }

    private String readApiName(BufferedReader bufferedReader) {
        try {
            StringBuilder headerPayLoad = new StringBuilder();
            String tmp;
            String methodName = null;
            while ((tmp = bufferedReader.readLine()) != null && !tmp.equals(
                    "")) {
                headerPayLoad.append(tmp);
                if (methodName == null && tmp.contains("POST") || tmp.contains(
                        "GET") || tmp.contains("DELETE") || tmp.contains("PUT"
                )) {
                    methodName = tmp.split(" ")[1].replace("/", "");
                }
            }
            return methodName;
        } catch (Exception e) {
            System.out.println("Exception while reading headers " + e.getMessage());
        }
        return null;
    }

    private Object invoke(String payload, String methodName) {
        Method method = ApiObjectContainer.getMethod(methodName);
        Object object = ApiObjectContainer.getObject(methodName);
        try {
            return method.invoke(object, payload);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
