package main.java.executorTask2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class SingleThreadWebServer {

    public static void main(String[] args) {
        final int PORT = 8080;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Web server is running on port " + PORT + "...");

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    handleRequest(clientSocket);
                } catch (IOException e) {
                    System.err.println("Error handling request: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Could not start server: " + e.getMessage());
        }
    }

    private static void handleRequest(Socket socket) throws IOException {
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            System.out.println("Start handle request...");
            Thread.sleep(5000);
            String httpResponse = """
                HTTP/1.1 200 OK\r
                Content-Type: text/html\r
                Content-Length: 46\r
                \r
                <html><body><h1>Hello from server!</h1></body></html>
                """;

            out.write(httpResponse);
            out.flush();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            socket.close();
        }
    }

}
