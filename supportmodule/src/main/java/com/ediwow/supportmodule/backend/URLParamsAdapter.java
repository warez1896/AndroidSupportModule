package com.ediwow.supportmodule.backend;

import com.ediwow.supportmodule.objectholder.URLQueryParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class URLParamsAdapter extends ArrayList<URLQueryParams> {
    private final ArrayList<URLQueryParams.JoinedParams> arrJoinedParams;

    private void fromJSON(JSONObject paramsObj, boolean urlEncoded) throws Exception {
        JSONArray arrNames = paramsObj.names();
        if (arrNames != null) {
            for (int i = 0; i < arrNames.length(); i++) {
                String keyName = arrNames.getString(i);
                add(new URLQueryParams(keyName, paramsObj.getString(keyName), urlEncoded));
            }
        }
    }

    private void fromHashMap(HashMap<String, String> map, boolean urlEncoded) throws Exception {
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet())
                add(new URLQueryParams(entry.getKey(), entry.getValue(), urlEncoded));
        }
    }

    public URLParamsAdapter(JSONObject paramsObj, boolean urlEncoded) throws Exception {
        arrJoinedParams = new ArrayList<>();
        fromJSON(paramsObj, urlEncoded);
    }

    public URLParamsAdapter(HashMap<String, String> map, boolean urlEncoded) throws Exception {
        arrJoinedParams = new ArrayList<>();
        fromHashMap(map, urlEncoded);
    }

    public URLParamsAdapter() {
        arrJoinedParams = new ArrayList<>();
    }

    public String toWholeURLParamString() {
        StringBuilder sb = new StringBuilder();
        for (URLQueryParams params : this)
            arrJoinedParams.add(new URLQueryParams.JoinedParams(params));

        for (URLQueryParams.JoinedParams joinedParams : arrJoinedParams)
            sb.append(joinedParams).append("&");
        if (sb.length() > 0)
            sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
