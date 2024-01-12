package com.ediwow.supportmodule.backend;

import com.ediwow.supportmodule.objectholder.Meta;

import java.net.MalformedURLException;
import java.net.URL;

public class Host2 {
    private final URL url;

    public Host2(String targetHost, int port, String... paths) throws MalformedURLException {
        String path = String.join("/", paths);
        this.url = new URL(Meta.PROTOCOL_HTTP, targetHost, port, path);
    }

    public URL getUrl() {
        return url;
    }
}
