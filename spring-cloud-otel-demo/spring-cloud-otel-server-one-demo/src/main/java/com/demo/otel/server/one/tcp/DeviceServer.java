package com.demo.otel.server.one.tcp;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@Component
public class DeviceServer {

    private final int port = 10004;

    public DeviceServer() {
        this.startServer();
    }

    public void startServer() {
        Thread thread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {

                log.info("Server is listening on port " + port);

                while (true) {
                    Socket socket = serverSocket.accept();
                    log.info("New client coming: {}, {} ", socket.getInetAddress().getHostAddress(), socket.getPort());

                    new ServerThread(socket).start();
                }

            } catch (IOException ex) {
                log.error("Server exception: ", ex);
            }
        });
        thread.start();
        log.info("server thread start: {}", thread.getId());
    }
}
