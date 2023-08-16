package com.ediwow.supportmodule.backend;

import com.ediwow.supportmodule.objectholder.Constants;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ConnectionManager {
    private final HostManager hostManager;
    private final int connectTimeOut, readTimeOut;
    private String propertyName = "", propertyValue = "";
    public static final boolean DOWNLOAD = false, UPLOAD = true;

    public ConnectionManager(HostManager hostManager, int connectTimeOut, int readTimeOut) {
        this.hostManager = hostManager;
        this.connectTimeOut = connectTimeOut;
        this.readTimeOut = readTimeOut;
    }

    public ConnectionManager(HostManager hostManager, int connectTimeOut, int readTimeOut, String propertyName, String propertyValue) {
        this.hostManager = hostManager;
        this.connectTimeOut = connectTimeOut;
        this.readTimeOut = readTimeOut;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    private void checkConnection(HttpURLConnection conn) throws Exception {
        if (conn == null)
            throw new IOException("Unable to connect");
    }

    public JSONObject startConnection() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("resCode", Constants.RequestResponse.RES_BLANK);
        for (URL url : hostManager.getArrURLs()) {
            try {
                HttpURLConnection conn = getConnection(url);
                obj = downloadData(conn);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    //TODO: Increase modularity
    public JSONObject startConnection(URLParamsAdapter params) throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("resCode", Constants.RequestResponse.RES_BLANK);
        boolean success = false;
        SocketTimeoutException socketTimeoutException = null;
        for (URL url : hostManager.getArrURLs()) {
            try {
                HttpURLConnection conn = getConnection(url);
                obj = uploadData(conn, params);
                success = true;
                break;
            } catch (Exception e) {
                e.printStackTrace();
                socketTimeoutException = (e instanceof SocketTimeoutException) ? (SocketTimeoutException) e : null;
            }
        }
        if (!success && socketTimeoutException != null)
            obj.put("resCode", Constants.RequestResponse.RES_TIMEOUT);
        return obj;
    }

//    public JSONObject startConnection(byte[] bytes) throws Exception {
//        JSONObject obj = new JSONObject();
//        obj.put("resCode", Constants.RequestResponse.RES_BLANK);
//        boolean success = false;
//        SocketTimeoutException socketTimeoutException = null;
//        for (URL url : hostManager.getArrURLs()) {
//            try {
//                HttpURLConnection conn = getConnection(url);
//                obj = directWrite(conn, bytes);
//                success = true;
//                break;
//            } catch (Exception e) {
//                e.printStackTrace();
//                socketTimeoutException = (e instanceof SocketTimeoutException) ? (SocketTimeoutException) e : null;
//            }
//        }
//        if (!success && socketTimeoutException != null)
//            obj.put("resCode", Constants.RequestResponse.RES_TIMEOUT);
//        return obj;
//    }

    private HttpURLConnection getConnection(URL url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        checkConnection(conn);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setConnectTimeout(connectTimeOut);
        conn.setReadTimeout(readTimeOut);
        if (!this.propertyName.trim().isEmpty() && !this.propertyValue.trim().isEmpty())
            conn.setRequestProperty(propertyName, propertyValue);
        return conn;
    }

    private JSONObject uploadData(HttpURLConnection conn, URLParamsAdapter params) throws Exception {
        OutputStream out = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
        String urlParam = params.toWholeURLParamString();
        writer.write(urlParam);
        writer.flush();
        writer.close();
        System.out.println(urlParam);
        return downloadData(conn);
    }

//    private JSONObject directWrite(HttpURLConnection conn, byte[] bytes) throws Exception {
//        OutputStream out = conn.getOutputStream();
//        out.write(bytes);
//        out.flush();
//        out.close();
//        return downloadData(conn);
//    }

    private JSONObject downloadData(HttpURLConnection conn) throws Exception {
        JSONObject obj;
        StringBuilder sb = new StringBuilder();
        InputStream in = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String output = sb.toString();
        System.out.println(output);
        reader.close();
        in.close();
        conn.disconnect();
        if (output.isEmpty()) {
            obj = new JSONObject();
            obj.put("resCode", Constants.RequestResponse.RES_BLANK);
        } else {
            obj = new JSONObject(output);
            if (!obj.has("resCode"))
                obj.put("resCode", Constants.RequestResponse.RES_OK);
        }
        return obj;
    }
}
