package com.lowek.che.bdayhelper;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lowek.che.bdayhelper.adapter.TabsPagerFragmentAdapter;
import com.lowek.che.bdayhelper.database.DBHelper;
import com.lowek.che.bdayhelper.support_classes.DatabaseMethods;
import com.lowek.che.bdayhelper.support_classes.WorkaroundTabLayoutOnPageChangeListener;

public class MainActivity extends AppCompatActivity {
    public static final int LAYOUT = R.layout.activity_main;
    public static Resources applicationResources;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);

        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        initTabLayout();

        applicationResources = getResources();
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

        if (mToolbar != null){
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

        try{
            tabLayout.getTabAt(0).setIcon(R.drawable.ic_account_multiple);
            tabLayout.getTabAt(1).setIcon(R.drawable.ic_vk);
            tabLayout.getTabAt(2).setIcon(R.drawable.ic_facebook);
            tabLayout.getTabAt(3).setIcon(R.drawable.ic_google_plus);
        }catch (NullPointerException e){
            Log.d("LW", "MainActivity - set icon to tabs");
        }

    }

}
