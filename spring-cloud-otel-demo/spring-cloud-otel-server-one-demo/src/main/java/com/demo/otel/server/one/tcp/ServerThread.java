package com.demo.otel.server.one.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

@Slf4j
public class ServerThread extends Thread {

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            //0xAA0xF5, 0-0x8000,0x10,0-0xFF,CMD(2byte),data(n byte),check(1byte)

            int result = 0;
            byte a = (byte) 0xaa;
            byte b = (byte) 0xF5;
            while (result != -1) {
                byte[] startFlag = new byte[2];
                result = input.read(startFlag);
                if (result == -1) {
                    break;
                }

                log.info("text: {}, {}", Integer.toHexString(startFlag[0] & 0xFF), Integer.toHexString(startFlag[1] & 0xFF));
                if (startFlag[0] == a && startFlag[1] == b) {
                    byte[] lengthByte = new byte[2];
                     result = input.read(lengthByte);
                        if (result == -1) {
                            log.error("server deal failed with length ");
                            break;
                    }
                    log.info("text: {}, {}", Integer.toHexString(lengthByte[0] & 0xFF), Integer.toHexString(lengthByte[1] & 0xFF));

                    int high = (lengthByte[0] & 0xff) << 16;
                    int low = lengthByte[1] & 0xff;

                    int length = high + low;
                    log.info("cmd length: {}", length);

                    byte[] data = new byte[length - 4];
                    result = input.read(data);
                    if (result == -1) {
                        log.error("server deal failed with data ");
                        break;
                    }
                    log.info("data: {}", data);
                    String dataStr = "";
                    for (int i = 0; i<data.length; i++) {
                        dataStr = dataStr + Integer.toHexString(data[i]) + " ";
                    }
                    log.info("data hex: {}", dataStr);
                }
            }

            log.info("client closed: {}", System.currentTimeMillis());
            socket.close();
        } catch (IOException ex) {
            log.error("server deal failed: ", ex);
        }
    }
}
