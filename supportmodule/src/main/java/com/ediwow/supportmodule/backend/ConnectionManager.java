package com.ediwow.supportmodule.backend;

import com.ediwow.supportmodule.objectholder.Constants;
import com.ediwow.supportmodule.objectholder.Meta;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ConnectionManager {
    private HttpURLConnection conn;

    public ConnectionManager(String hostAddress, int port, int connectTimeOut, int readTimeOut, String... paths) throws Exception {
        conn = null;
        if (paths.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String path : paths) sb.append(path).append(File.separator);
            sb.setLength(sb.length() - 1);
            String targetFile = sb.toString();
            URL url = new URL(Meta.PROTOCOL_HTTP, hostAddress, port, targetFile);
            this.conn = (HttpURLConnection) url.openConnection();
            checkConnection();
            this.conn.setRequestMethod("POST");
            this.conn.setDoOutput(true);
            this.conn.setDoInput(true);
            this.conn.setConnectTimeout(connectTimeOut);
            this.conn.setReadTimeout(readTimeOut);
        }
    }

    private void checkConnection() throws Exception {
        if (this.conn == null)
            throw new IOException("Unable to connect");
    }

    public JSONObject uploadData(URLParamsAdapter params) throws Exception {
        checkConnection();
        OutputStream out = this.conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
        String urlParam = params.toWholeURLParamString();
        writer.write(urlParam);
        writer.flush();
        writer.close();
        System.out.println(urlParam);
        return downloadData();
    }

    public JSONObject downloadData() throws Exception {
        StringBuilder sb = new StringBuilder();
        InputStream in = this.conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String output = sb.toString();
        System.out.println(output);
        reader.close();
        in.close();
        this.conn.disconnect();
        JSONObject obj;
        if (output.isEmpty()) {
            obj = new JSONObject();
            obj.put("resCode", Constants.RequestResponse.RES_BLANK);
        } else {
            obj = new JSONObject(output);
            obj.put("resCode", Constants.RequestResponse.RES_OK);
        }
        return obj;
    }
}
