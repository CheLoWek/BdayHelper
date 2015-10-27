package com.lowek.che.bdayhelper.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lowek.che.bdayhelper.Contact;
import com.lowek.che.bdayhelper.MainActivity;
import com.lowek.che.bdayhelper.R;
import com.lowek.che.bdayhelper.database.DBHelper;
import com.lowek.che.bdayhelper.utils.DatabaseMethods;
import com.lowek.che.bdayhelper.utils.DateMethods;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<Contact> contacts;

    public CardAdapter(Context context) {
        super();
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor allContacts = DatabaseMethods.getDataNoSort(db, "bdayhelper_contacts");

        contacts = new ArrayList<Contact>();

        if (allContacts.moveToFirst()) {
            do {
                int nameColIndex = allContacts.getColumnIndex("name");
                String name = allContacts.getString(nameColIndex);

                int surnameColIndex = allContacts.getColumnIndex("surname");
                String surname = allContacts.getString(surnameColIndex);

                String picturePath = "";

                int pictureColIndex = allContacts.getColumnIndex("picture");


                try {
                    picturePath = allContacts.getString(pictureColIndex);
                } catch (NullPointerException e) {
                }
                if (picturePath == null) {
                    picturePath = "";
                }

                int birthdayColIndex = allContacts.getColumnIndex("birthday");
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                Calendar birthday = Calendar.getInstance();
                try {
                    Date date = df.parse(allContacts.getString(birthdayColIndex));
                    birthday.setTime(date);
                } catch (ParseException e) {
                    Log.d("CardAdapter", "Error trying to parse string to date");
                }


                int presentColIndex = allContacts.getColumnIndex("present");
                String present = allContacts.getString(presentColIndex);
                if (present == null)
                    present = "";

                Contact contact = new Contact(name,
                        surname,
                        picturePath,
                        birthday,
                        present);
                contacts.add(contact);
            } while (allContacts.moveToNext());
        }

        Collections.sort(contacts);

        allContacts.close();
    }


    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Contact contact = contacts.get(i);

        if (contact.isHaspresentIdea()) {
            viewHolder.imgPresentIdea.setImageResource(R.drawable.present_checked);
        } else {
            viewHolder.imgPresentIdea.setImageResource(R.drawable.present_unchecked);
        }

        viewHolder.tvFirstLastName.setText(contact.getName() + " " + contact.getLastName());


        if (contact.getPicturePath().equals("")) {
            viewHolder.imgContactPicture.setImageResource(R.drawable.user_image_default);
        } else {
            viewHolder.imgContactPicture.setImageBitmap(null);
            Bitmap bm = BitmapFactory.decodeFile(contact.getPicturePath());
            viewHolder.imgContactPicture.setImageBitmap(bm);
        }


        viewHolder.tvEvent.setText(R.string.birth_day);

        viewHolder.tvEventDate.setText(DateMethods.getStringDateMonth(contact.getBirthDate(), MainActivity.applicationResources.getStringArray(R.array.months)));

        // определить окончание для русского языка
        int lastDigit = contact.getDaysLeft() % 10;
        String days = "";
        if (lastDigit == 2 || lastDigit == 3 || lastDigit == 4) {
            days = MainActivity.applicationResources.getString(R.string.days2);
        } else {
            days = MainActivity.applicationResources.getString(R.string.days);
        }
        if (contact.getDaysLeft() == 0) {
            viewHolder.tvDaysLeft.setText(MainActivity.applicationResources.getString(R.string.today));
        } else if (contact.getDaysLeft() == 1) {
            viewHolder.tvDaysLeft.setText(MainActivity.applicationResources.getString(R.string.tomorrow));
        } else {
            viewHolder.tvDaysLeft.setText(MainActivity.applicationResources.getString(R.string.in) + " " + contact.getDaysLeft() + " " + days);
        }
//        Calendar nextBDay = DateMethods.nextBirthday(contact.getBirthDate());
        viewHolder.tvDay.setText(DateMethods.getDayOfWeek(contact.getNextBirthday(), MainActivity.applicationResources.getStringArray(R.array.days_of_week)));
//        viewHolder.tvDay.setText(DateMethods.getDayOfWeek(nextBDay, MainActivity.applicationResources.getStringArray(R.array.days_of_week)));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgContactPicture;
        public ImageView imgPresentIdea;
        public TextView tvFirstLastName;
        public TextView tvEvent;
        public TextView tvEventDate;
        public TextView tvDaysLeft;
        public TextView tvDay;

        public ViewHolder(View itemView) {
            super(itemView);
            imgContactPicture = (ImageView) itemView.findViewById(R.id.img_contact_picture);
            imgPresentIdea = (ImageView) itemView.findViewById(R.id.img_present_idea);
            tvFirstLastName = (TextView) itemView.findViewById(R.id.tv_first_last_name);
            tvEvent = (TextView) itemView.findViewById(R.id.tv_event);
            tvEventDate = (TextView) itemView.findViewById(R.id.tv_event_date);
            tvDaysLeft = (TextView) itemView.findViewById(R.id.tv_days_left);
            tvDay = (TextView) itemView.findViewById(R.id.tv_day_of_week);
        }
    }
}