package com.demo.otel.server.one.tcp;

public class DeviceConstant {

    public static final int CMD_SERVER_STOP = 5;
    public static final int CMD_SERVER_REMOTE_START = 7;
    public static final int CMD_REPLY_HEARTBEAT = 101;
    public static final int CMD_REPLY_STATUS = 103;
    public static final int CMD_REPLY_SIGN = 105;
    public static final int CMD_REPLY_RECORD = 201;
    public static final int CMD_REPLY_NO_HISTORY_RECORD = 401;
    public static final int CMD_PRICE_CONFIG = 1103;


    public static final int CMD_CLIENT_SIGN = 106;
    public static final int CMD_CLIENT_STATUS = 104;
    public static final int CMD_CLIENT_HEARTBEAT = 102;
    public static final int CMD_CLIENT_RECORD = 202;




    public static final int CHARGING_TYPE_INSTANCE = 0;
    public static final int CHARGING_TYPE_TIMER = 1;
    public static final int CHARGING_TYPE_BOOKING = 2;


    public static final int CHARGING_STRATEGY_FULL = 0;
    public static final int CHARGING_STRATEGY_TIME = 1;
    public static final int CHARGING_STRATEGY_AMOUNT = 2;
    public static final int CHARGING_STRATEGY_ENERGY = 3;


    public static final int CHARGING_OFFLINE_DISABLE = 0;
    public static final int CHARGING_OFFLINE_ENABLE = 1;
}
