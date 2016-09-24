package com.davidllorca.weatherapp.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WeatherAppSyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static WeatherAppSyncAdapter sSunshineSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("WeatherAppSyncService", "onCreate - WeatherAppSyncService");
        synchronized (sSyncAdapterLock) {
            if (sSunshineSyncAdapter == null) {
                sSunshineSyncAdapter = new WeatherAppSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sSunshineSyncAdapter.getSyncAdapterBinder();
    }
}