package com.lowek.che.bdayhelper;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lowek.che.bdayhelper.database.DBHelper;

public class AddContactActivity extends AppCompatActivity{
    public static final int LAYOUT = R.layout.activity_add_contact;

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private FrameLayout content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppNoDrawer);

        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_add_contact);
        setContentView(LAYOUT);

        content = (FrameLayout) findViewById(R.id.add_contact_content);

        initToolbar();
        initFAB();

    }

    private void initFAB() {
        fab = (FloatingActionButton) findViewById(R.id.fab_add_contact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fab_add clicked
                Toast.makeText(v.getContext(), "FAB CLICKED", Toast.LENGTH_SHORT).show();
                setContactPicture();
            }

            private void setContactPicture() {

            }
        });
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
                saveContact();
                Snackbar.make(content, "Saved", Snackbar.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveContact(){
        DBHelper dbHelper = new DBHelper(this);

        ContentValues cv = new ContentValues();

        EditText etName = (EditText) findViewById(R.id.etName);
        EditText etSurname = (EditText) findViewById(R.id.etSurname);
        EditText etBirthday = (EditText) findViewById(R.id.etBirthay);
        EditText etPhone = (EditText) findViewById(R.id.etPhone);

        String name = etName.getText().toString();
        String surname =etSurname.getText().toString();
        String birthday =etBirthday.getText().toString();
        String phone =etPhone.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        cv.put("name", name);
        cv.put("surname", surname);
        cv.put("birthday", birthday);
        //picture
        cv.put("phone", phone);
        cv.put("origin", "user");

        long rowID = db.insert("bdayhelper_contacts", null, cv);
    }
}
