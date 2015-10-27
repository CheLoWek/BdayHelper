package com.lowek.che.bdayhelper;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.lowek.che.bdayhelper.database.DBHelper;

public class AddContactActivity extends AppCompatActivity {
    public static final int LAYOUT = R.layout.activity_add_contact;

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private FrameLayout content;
    private ImageView userImage;
    private Uri imageURI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppNoDrawer);

        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_add_contact);
        setContentView(LAYOUT);

        content = (FrameLayout) findViewById(R.id.add_contact_content);
        userImage = (ImageView) findViewById(R.id.user_image);

        initToolbar();
        initFAB();
    }

    private void initFAB() {
        fab = (FloatingActionButton) findViewById(R.id.fab_add_contact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fab_add clicked
//                Toast.makeText(v.getContext(), "FAB CLICKED", Toast.LENGTH_SHORT).show();
                setContactPicture();
            }

            private void setContactPicture() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent, "Select picture"), 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1) {
            userImage.setImageURI(data.getData());
            imageURI = data.getData();
        } else {

        }
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

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                Snackbar.make(content, "Saved", Snackbar.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};

        //This method was deprecated in API level 11
        //Cursor cursor = managedQuery(contentUri, proj, null, null, null);

        CursorLoader cursorLoader = new CursorLoader(
                this,
                contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        int column_index =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void saveContact() {
        DBHelper dbHelper = new DBHelper(this);

        ContentValues cv = new ContentValues();

        EditText etName = (EditText) findViewById(R.id.etName);
        EditText etSurname = (EditText) findViewById(R.id.etSurname);
        EditText etBirthday = (EditText) findViewById(R.id.etBirthay);
        EditText etPhone = (EditText) findViewById(R.id.etPhone);

        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();

        String picturePath = "";
        if (imageURI != null) {
            picturePath = getRealPathFromURI(imageURI);
        }
        String birthday = etBirthday.getText().toString();


        String phone = etPhone.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        cv.put("name", name);
        cv.put("surname", surname);
        cv.put("birthday", birthday);
        //picture
        cv.put("picture", picturePath);

        cv.put("phone", phone);
        cv.put("origin", "user");


        long rowID = db.insert("bdayhelper_contacts", null, cv);
    }
}
