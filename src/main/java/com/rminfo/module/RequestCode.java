package com.rminfo.module;

/**
 * Created by Admin on 2016/11/23.
 */

/**
 * 请求码
 */
public class RequestCode {
    private String length;
    private String carId;
    private String version;
    private String commandCode;
    private String commandId;
    private String reserved;
    private String content;

    @Override
    public String toString() {
        return length+carId+version+commandCode+commandId+reserved +content;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCommandCode() {
        return commandCode;
    }

    public void setCommandCode(String commandCode) {
        this.commandCode = commandCode;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
