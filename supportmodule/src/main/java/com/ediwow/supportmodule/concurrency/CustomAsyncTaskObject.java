package com.ediwow.supportmodule.concurrency;

import android.os.AsyncTask;

import com.ediwow.supportmodule.interfaces.AsyncTaskListenerObject;

public class CustomAsyncTaskObject extends AsyncTask<Object, String, Object> {
    private AsyncTaskListenerObject asyncTaskListenerObject;

    @Override
    protected void onPreExecute() {
        if (asyncTaskListenerObject != null)
            asyncTaskListenerObject.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        if (asyncTaskListenerObject != null)
            asyncTaskListenerObject.onPostExecute(o);
    }

    @Override
    protected Object doInBackground(Object... objects) {
        return null;
    }

    public void setAsyncTaskListener(AsyncTaskListenerObject asyncTaskListenerObject) {
        this.asyncTaskListenerObject = asyncTaskListenerObject;
    }

    public void destroyAsyncTaskListener() {
        this.asyncTaskListenerObject = null;
    }
}
