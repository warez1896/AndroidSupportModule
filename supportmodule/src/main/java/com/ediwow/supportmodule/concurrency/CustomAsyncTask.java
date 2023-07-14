package com.ediwow.supportmodule.concurrency;

import android.os.AsyncTask;

import com.ediwow.supportmodule.interfaces.AsyncTaskListener;

import org.json.JSONObject;

public abstract class CustomAsyncTask extends AsyncTask<JSONObject, String, JSONObject> {
    private AsyncTaskListener asyncTaskListener = null;

    @Override
    protected void onPreExecute() {
        if (asyncTaskListener != null)
            asyncTaskListener.onPreExecute();
    }

    public void destroyAsyncTaskListener() {
        asyncTaskListener = null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if (asyncTaskListener != null)
            asyncTaskListener.onPostExecute(jsonObject);
    }

    public void setAsyncTaskListener(AsyncTaskListener asyncTaskListener) {
        this.asyncTaskListener = asyncTaskListener;
    }
}
