package com.ediwow.supportmodule.objectholder;

import org.json.JSONObject;

public class HTTPResponse {
    private Constants.RequestResponse responseCode;
    private JSONObject obj;

    public HTTPResponse(Constants.RequestResponse responseCode, JSONObject obj) {
        this.responseCode = responseCode;
        this.obj = obj;
    }

    public Constants.RequestResponse getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Constants.RequestResponse responseCode) {
        this.responseCode = responseCode;
    }

    public JSONObject getObj() {
        return obj;
    }

    public void setObj(JSONObject obj) {
        this.obj = obj;
    }
}
