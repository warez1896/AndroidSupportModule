package com.ediwow.supportmodule.backend;

import com.ediwow.supportmodule.objectholder.URLQueryParams;

import java.util.ArrayList;

public class URLParamsAdapter extends ArrayList<URLQueryParams> {
    private final ArrayList<URLQueryParams.JoinedParams> arrJoinedParams;

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
