package com.revolut.server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
        PrintWriter printWriter = null;
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedReader =
                    new BufferedReader(inputStreamReader);
            String apiName = readApiName(bufferedReader);
            StringBuilder payload = new StringBuilder();
            readPayLoad(bufferedReader, payload);
            Object response = invoke(payload.toString(), apiName);
            printWriter.println(JsonUtil.toJsonString(response));
        } catch (Exception e) {
            printWriter.println(JsonUtil.toJsonString(new ResponseObject(e.getLocalizedMessage())));
            System.out.println("Exception while executing " + e.getLocalizedMessage());
            e.printStackTrace();
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
            throw new RuntimeException(e.getTargetException().getLocalizedMessage());
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private class ResponseObject implements Serializable {
        private static final long serialVersionUID = 2227960462720122781L;
        private String message;

        /**
         * Instantiates a new Response object.
         *
         * @param localizedMessage the localized message
         */
        public ResponseObject(String localizedMessage) {
            this.message = localizedMessage;
        }

        /**
         * Gets message.
         *
         * @return the message
         */
        public String getMessage() {
            return message;
        }

        /**
         * Sets message.
         *
         * @param message the message
         */
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
