package com.lowek.che.bdayhelper.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;

/**
 * Created by z11_000 on 05.10.2015.
 */
public class AlarmService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public AlarmService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}