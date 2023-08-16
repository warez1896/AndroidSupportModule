package com.ediwow.supportmodule.interfaces;

public interface AsyncTaskListenerObject {
    void onPreExecute();

    void onPostExecute(Object object);
}
