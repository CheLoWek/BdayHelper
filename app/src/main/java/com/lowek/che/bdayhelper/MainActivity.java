package com.lowek.che.bdayhelper;

import android.content.res.Resources;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lowek.che.bdayhelper.fragment.RecyclerviewFragment;

public class MainActivity extends AppCompatActivity {
    public static final int LAYOUT = R.layout.activity_main;
    public static Resources applicationResources;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);

        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        initFragment();

        applicationResources = getResources();
    }

    private void initFragment() {
        if (findViewById(R.id.contentFrame) != null) {
            RecyclerviewFragment recyclerviewFragment = new RecyclerviewFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentFrame, recyclerviewFragment)
                    .commit();
        }
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

}
