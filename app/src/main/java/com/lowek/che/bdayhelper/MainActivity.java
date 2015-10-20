package com.lowek.che.bdayhelper;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lowek.che.bdayhelper.adapter.TabsPagerFragmentAdapter;
import com.lowek.che.bdayhelper.database.DBHelper;
import com.lowek.che.bdayhelper.receiver.AlarmReceiver;
import com.lowek.che.bdayhelper.service.NotificationService;
import com.lowek.che.bdayhelper.utils.WorkaroundTabLayoutOnPageChangeListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static final int LAYOUT = R.layout.activity_main;
    public static Resources applicationResources;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;

    private DBHelper dbHelper;


    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);

        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        initTabLayout();

        applicationResources = getResources();

        startNotifications();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        mToolbar.inflateMenu(R.menu.menu);

        setSupportActionBar(mToolbar);
    }

    private void initNavigationView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_menu);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mDrawerLayout.openDrawer(GravityCompat.START);

                }
            });
        }

        NavigationView view = (NavigationView) findViewById(R.id.navigationView);
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.drawerMenuAddContact:
                        Intent intent = new Intent(mViewPager.getContext(), AddContactActivity.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });
    }

    private void initTabLayout() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.clearOnPageChangeListeners();
        mViewPager.addOnPageChangeListener(new WorkaroundTabLayoutOnPageChangeListener(tabLayout));


        for(int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setIcon(R.drawable.ic_account_multiple);
            }
        }


    }

    private void startNotifications() {
        Intent myIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

//        calendar.set(Calendar.HOUR_OF_DAY, 4);
//        calendar.set(Calendar.MINUTE, 05);
//        calendar.set(Calendar.SECOND, 30); // first time
        calendar.add(Calendar.SECOND, 5);
        long frequency = 24 * 60 * 60 * 1000; // in ms
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), frequency, pendingIntent);
    }



}
