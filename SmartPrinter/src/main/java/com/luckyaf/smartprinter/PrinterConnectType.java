package com.luckyaf.smartprinter;

import org.jetbrains.annotations.NotNull;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/14
 */
public enum PrinterConnectType {

    // 设备自身
    LOCAL("LOCAL"),
    //蓝牙连接
    BLUETOOTH("BLUETOOTH"),
    //USB连接
    USB("USB"),
    //wifi连接
    WIFI("WIFI"),
    //串口连接
    SERIAL_PORT("SERIAL_PORT"),
    //服务后端
    ONLINE_SERVER("ONLINE_SERVER");

    /**
     * 打印机连接类型
     */
    private final String name;

    PrinterConnectType(String name) {
        this.name = name;
    }

    @NotNull
    @Override
    public String toString() {
        return this.name;
    }
}
