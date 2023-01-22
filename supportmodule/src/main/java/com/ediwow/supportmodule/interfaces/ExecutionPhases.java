package com.ediwow.supportmodule.interfaces;

import org.json.JSONObject;

public interface ExecutionPhases {
    void preExecute();

    JSONObject execute();

    void postExecute(JSONObject resultObj);
}
