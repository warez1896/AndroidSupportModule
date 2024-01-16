package com.ediwow.supportmodule.backend;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class URLParamsAdapter extends ArrayList<String> {

    private void fromJSON(JSONObject paramsObj) throws Exception {
        JSONArray arrNames = paramsObj.names();
        if (arrNames != null) {
            for (int i = 0; i < arrNames.length(); i++) {
                String keyName = arrNames.getString(i);
                add(String.format("%s=%s", keyName, URLEncoder.encode(paramsObj.getString(keyName), StandardCharsets.UTF_8.name())));
            }
        }
    }

    private void fromHashMap(HashMap<String, String> map) throws Exception {
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet())
                add(String.format("%s=%s", entry.getKey(), URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name())));
        }
    }

    public boolean add(@NonNull String key, String value) throws Exception {
        return add(String.format("%s=%s", key, URLEncoder.encode(value, StandardCharsets.UTF_8.name())));
    }

    public URLParamsAdapter(JSONObject paramsObj) throws Exception {
        fromJSON(paramsObj);
    }

    public URLParamsAdapter(HashMap<String, String> map) throws Exception {
        fromHashMap(map);
    }

    public URLParamsAdapter() {
    }

    public String toWholeURLParamString() {
        return String.join("&", this);
    }
}
