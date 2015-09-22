package com.lowek.che.bdayhelper;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class AddContactActivity extends AppCompatActivity{
    public static final int LAYOUT = R.layout.activity_add_contact;

    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppNoDrawer);
        super.onCreate(savedInstanceState);

        setContentView(LAYOUT);

        initToolbar();

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
