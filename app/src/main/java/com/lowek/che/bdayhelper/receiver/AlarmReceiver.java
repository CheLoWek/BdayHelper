package com.lowek.che.bdayhelper.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lowek.che.bdayhelper.service.NotificationService;

/**
 * Created by z11_000 on 05.10.2015.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent service1 = new Intent(context, NotificationService.class);
        context.startService(service1);

    }
}
