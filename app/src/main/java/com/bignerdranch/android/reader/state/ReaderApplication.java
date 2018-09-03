package com.bignerdranch.android.reader.state;

import android.app.Application;

public class ReaderApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        StateManager stateApp = StateManager.createStateManager();
    }
}
