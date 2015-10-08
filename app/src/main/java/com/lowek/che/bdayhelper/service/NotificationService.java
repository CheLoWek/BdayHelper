package com.lowek.che.bdayhelper.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.lowek.che.bdayhelper.Contact;
import com.lowek.che.bdayhelper.MainActivity;
import com.lowek.che.bdayhelper.R;
import com.lowek.che.bdayhelper.database.DBHelper;
import com.lowek.che.bdayhelper.utils.DatabaseMethods;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class NotificationService extends Service {

    final String LOG_TAG = "myLogs";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }

    private void someTask() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor allContacts = getData();
                List<Contact> contacts = getContactsList(allContacts);
                allContacts.close();


                Iterator iterator = contacts.iterator();
                while (iterator.hasNext()) {
                    Contact current = (Contact) iterator.next();

                    if (current.getDaysLeft() == 0) {
                        String contactName = current.getName() + " " + current.getLastName();
                        showNotification(contactName, "Has a birthday today!");
                    }
                }
                stopSelf();
            }
        }).start();

    }

    private Cursor getData() {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor allInformation = DatabaseMethods.getDataNoSort(db, "bdayhelper_contacts");
        Log.d("Service", "getData");
        return allInformation;
    }

    private List<Contact> getContactsList(Cursor data) {
        List<Contact> contacts = new ArrayList<Contact>();
        if (data.moveToFirst()) {
            do {
                int nameColIndex = data.getColumnIndex("name");
                String name = data.getString(nameColIndex);

                int surnameColIndex = data.getColumnIndex("surname");
                String surname = data.getString(surnameColIndex);

                String picturePath = "";

                int birthdayColIndex = data.getColumnIndex("birthday");
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                Calendar birthday = Calendar.getInstance();
                try {
                    Date date = df.parse(data.getString(birthdayColIndex));
                    birthday.setTime(date);
                } catch (ParseException e) {
                    Log.d("CardAdapter", "Error trying to parse string to date");
                }

                int presentColIndex = data.getColumnIndex("present");
                String present = data.getString(presentColIndex);
                if (present == null)
                    present = "";

                Contact contact = new Contact(name,
                        surname,
                        picturePath,
                        birthday,
                        present);
                contacts.add(contact);
            } while (data.moveToNext());
        }

        Collections.sort(contacts);
        Log.d("Service", "making contact list");
        return contacts;
    }

    private void showNotification(String title, String text) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.present_checked)
                        .setContentTitle(title)
                        .setContentText(text);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        new Intent(this, MainActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        int mNotificationId = 001;
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
