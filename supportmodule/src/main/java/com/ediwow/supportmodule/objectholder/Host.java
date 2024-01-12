package com.ediwow.supportmodule.objectholder;

public class Host {
    private String hostAddress;
    private String[] paths;
   private int port;

    public Host(String hostAddress, int port, String... paths) {
        setHostAddress(hostAddress);
        setPort(port);
        setPaths(paths);
    }

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }
}
