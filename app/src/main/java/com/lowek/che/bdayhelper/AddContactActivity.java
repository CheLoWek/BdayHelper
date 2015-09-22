package com.lowek.che.bdayhelper;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class AddContactActivity extends AppCompatActivity{
    public static final int LAYOUT = R.layout.activity_add_contact;

    private Toolbar toolbar;
    private FrameLayout content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppNoDrawer);

        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_add_contact);
        setContentView(LAYOUT);

        content = (FrameLayout) findViewById(R.id.add_contact_content);

        initToolbar();

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar_add_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_save:
//                saveContact();
                Snackbar.make(content, "Saved", Snackbar.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
