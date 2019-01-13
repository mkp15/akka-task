package com.pandma.akka.task;


import java.util.ArrayList;
import java.util.List;

public class DeviceInfo {

    private final String ipAddress;

    private final String technology;

    private final List<String> supportedLinkChecks;

    public DeviceInfo(String ipAddress, String technology, List<String> supportedLinkChecks) {
        this.ipAddress = ipAddress;
        this.technology = technology;
        this.supportedLinkChecks = supportedLinkChecks;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getTechnology() {
        return technology;
    }

    public List<String> getSupportedLinkChecks() {
        return new ArrayList<>(supportedLinkChecks);
    }
}
