package com.ediwow.supportmodule.backend;

import com.ediwow.supportmodule.objectholder.Host;
import com.ediwow.supportmodule.objectholder.Meta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HostManager {
    private final ArrayList<Host> arrHosts;
    private ArrayList<URL> arrURLs;

    public HostManager(ArrayList<Host> arrHosts) throws MalformedURLException {
        this.arrHosts = arrHosts;
        generateURLs();
    }

    public static JSONObject toJSON(String[] hosts, int priority, int port) throws JSONException {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        String priorityHost = hosts[priority].trim();
        if (!priorityHost.isEmpty())
            arr.put(new JSONObject().put("host", priorityHost).put("port", port));
        for (int i = 0; i < hosts.length; i++) {
            if (i != priority && !hosts[i].trim().isEmpty()) {
                arr.put(new JSONObject().put("host", hosts[i]).put("port", port));
            }
        }
        obj.put("hosts", arr);
        return obj;
    }

    public static ArrayList<Host> toArrayList(JSONObject object, String... paths) throws JSONException {
        JSONArray arr = object.getJSONArray("hosts");
        ArrayList<Host> arrHosts = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj2 = arr.getJSONObject(i);
            arrHosts.add(new Host(obj2.getString("host"), obj2.getInt("port"), paths));
        }
        return arrHosts;
    }

    public HostManager(JSONArray hostArray, String... paths) throws JSONException, MalformedURLException {
        this.arrHosts = new ArrayList<>();
        for (int i = 0; i < hostArray.length(); i++) {
            JSONObject obj = hostArray.getJSONObject(i);
            this.arrHosts.add(new Host(obj.getString("host"), obj.getInt("port"), paths));
        }
        if (arrHosts.size() > 0)
            generateURLs();
    }

    private void generateURLs() throws MalformedURLException {
        arrURLs = new ArrayList<>();
        for (Host host : arrHosts) {
            if (!host.getHostAddress().isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (String path : host.getPaths()) sb.append(path).append(File.separator);
                sb.setLength(sb.length() - 1);
                arrURLs.add(new URL(Meta.PROTOCOL_HTTP, host.getHostAddress(), host.getPort(), sb.toString()));
            }
        }
    }

    public ArrayList<URL> getArrURLs() {
        return this.arrURLs;
    }

}
