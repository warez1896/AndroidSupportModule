package com.ediwow.supportmodule.backend;

import androidx.annotation.NonNull;

import com.ediwow.supportmodule.objectholder.HTTPResponse;

import java.io.IOException;
import java.net.HttpURLConnection;

public class ConnectionManager2 {
    @NonNull
    private final Host2 host2;
    private final int connectTimeout;
    private final int readTimeout;
    private String propertyName = "", propertyValue = "";

    public ConnectionManager2(@NonNull Host2 host2, int connectTimeout, int readTimeout) {
        this.host2 = host2;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    public void setProperty(String propertyName, String propertyValue) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    private void checkConnection(HttpURLConnection conn) throws Exception {
        if (conn == null)
            throw new IOException("Unable to connect");
    }

    public HTTPResponse startConnection() throws Exception {
        HTTPResponse httpResponse;
        HttpURLConnection conn = getConnection();

        return httpResponse;
    }

    private HttpURLConnection getConnection() throws Exception {
        HttpURLConnection conn = (HttpURLConnection) host2.getUrl().openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setConnectTimeout(connectTimeout);
        conn.setReadTimeout(readTimeout);

        if (!this.propertyName.isEmpty() && !this.propertyValue.isEmpty())
            conn.setRequestProperty(propertyName, propertyValue);

        checkConnection(conn);
        return conn;
    }
}
