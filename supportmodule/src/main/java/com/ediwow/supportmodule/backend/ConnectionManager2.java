package com.ediwow.supportmodule.backend;

import androidx.annotation.NonNull;

import com.ediwow.supportmodule.objectholder.Constants;
import com.ediwow.supportmodule.objectholder.HTTPResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

public class ConnectionManager2 {
    @NonNull
    private final Host2 host2;
    private final int connectTimeout;
    private final int readTimeout;
    private String propertyName = "", propertyValue = "";

    public ConnectionManager2(@NonNull Host2 host2, int connectTimeout, int readTimeout) {
        //Connection configuration only, no opening of connection yet
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

    @NonNull
    public HTTPResponse startConnection() {
        HTTPResponse response;
        try {
            HttpURLConnection conn = getConnection();
            response = downloadData(conn);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            response = new HTTPResponse(Constants.RequestResponse.TIMEOUT);
        } catch (ConnectException e) {
            e.printStackTrace();
            response = new HTTPResponse(Constants.RequestResponse.NO_CONNECTION);
        } catch (JSONException e) {
            e.printStackTrace();
            response = new HTTPResponse(Constants.RequestResponse.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            response = new HTTPResponse(Constants.RequestResponse.UNKNOWN);
        }
        return response;
    }

    @NonNull
    public HTTPResponse startConnection(URLParamsAdapter params) {
        HTTPResponse response;
        try {
            HttpURLConnection conn = getConnection();
            response = uploadData(conn, params);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            response = new HTTPResponse(Constants.RequestResponse.TIMEOUT);
        } catch (ConnectException e) {
            e.printStackTrace();
            response = new HTTPResponse(Constants.RequestResponse.NO_CONNECTION);
        } catch (JSONException e) {
            e.printStackTrace();
            response = new HTTPResponse(Constants.RequestResponse.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            response = new HTTPResponse(Constants.RequestResponse.UNKNOWN);
        }
        return response;
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

    private HTTPResponse uploadData(HttpURLConnection conn, URLParamsAdapter params) throws Exception {
        OutputStream outputStream = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

        String urlParams = params.toWholeURLParamString();
        System.out.println("Input: " + urlParams);
        writer.write(urlParams);
        writer.flush();
        writer.close();
        return downloadData(conn);
    }

    private HTTPResponse downloadData(HttpURLConnection conn) throws Exception {
        HTTPResponse httpResponse;

        StringBuilder sb = new StringBuilder();
        InputStream inputStream = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null)
            sb.append(line);

        String output = sb.toString();
        System.out.println("Output: " + output);

        reader.close();
        inputStream.close();
        conn.disconnect();

        if (output.isEmpty())
            httpResponse = new HTTPResponse(Constants.RequestResponse.BLANK);
        else {
            JSONObject obj = new JSONObject(output);
            httpResponse = new HTTPResponse(Constants.RequestResponse.OK, obj);
        }
        return httpResponse;
    }
}
