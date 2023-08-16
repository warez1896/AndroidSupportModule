package com.ediwow.supportmodule.interfaces;

import org.json.JSONObject;

public interface AsyncTaskListener {
    void onPreExecute();

    void onPostExecute(JSONObject result);
}
