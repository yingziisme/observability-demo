package com.demo.otel.server.one.tcp;


import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class DeviceCommandHandler {

    public void resetZero(byte[] cmd, int start, int end) {
        for (int i = start; i < end; i++) {
            cmd[i] = '0';
        }
    }

    public byte[] startChargingCommand(int deviceNumber, int chargingType, int chargingStrategy, int strategyParam, String bookingTime,
                                int timeout, String customId, int offlineFlag, int offlineEnergy, String orderId) {

        byte[] cmd = new byte[99];
        this.resetZero(cmd, 0, 4);
        cmd[5] = (byte) (deviceNumber & 0xFF);
        cmd[6] = (byte) (chargingType >> 24 & 0xFF);
        cmd[7] = (byte) (chargingType >> 16 & 0xFF);
        cmd[8] = (byte) (chargingType >> 8 & 0xFF);
        cmd[9] = (byte) (chargingType & 0xFF);
        this.resetZero(cmd, 10, 14);
        cmd[14] = (byte) (chargingStrategy >> 24 & 0xFF);
        cmd[15] = (byte) (chargingStrategy >> 16 & 0xFF);
        cmd[16] = (byte) (chargingStrategy >> 8 & 0xFF);
        cmd[17] = (byte) (chargingStrategy & 0xFF);

        cmd[18] = (byte) (strategyParam >> 24 & 0xFF);
        cmd[19] = (byte) (strategyParam >> 16 & 0xFF);
        cmd[20] = (byte) (strategyParam >> 8 & 0xFF);
        cmd[21] = (byte) (strategyParam & 0xFF);

        for (int i = 0; i < 8; i++) {

        }
        cmd[30] = (byte) (timeout & 0xFF);
        byte[] customIdBytes = customId.getBytes(StandardCharsets.UTF_8);
        for (int i = 31; i < 63; i++) {
            if (customIdBytes.length < (i - 31)) {
                cmd[i] = '0';
            } else {
                cmd[i] = customIdBytes[i - 31];
            }
        }
        cmd[63] = (byte) (offlineFlag & 0xFF);
        cmd[64] = (byte) (offlineEnergy >> 24 & 0xFF);
        cmd[65] = (byte) (offlineEnergy >> 16 & 0xFF);
        cmd[66] = (byte) (offlineEnergy >> 8 & 0xFF);
        cmd[67] = (byte) (offlineEnergy & 0xFF);

        byte[] orderIdBytes = orderId.getBytes(StandardCharsets.UTF_8);
        for (int i = 68; i < 100; i++) {
            if (orderIdBytes.length < (i - 31)) {
                cmd[i] = '0';
            } else {
                cmd[i] = orderIdBytes[i - 31];
            }
        }

        return cmd;
    }

}
