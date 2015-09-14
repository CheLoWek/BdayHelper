package com.lowek.che.bdayhelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lowek.che.bdayhelper.Contact;
import com.lowek.che.bdayhelper.MainActivity;
import com.lowek.che.bdayhelper.R;
import com.lowek.che.bdayhelper.support_classes.DateMethods;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    private List<Contact> contacts;

    public CardAdapter() {
        super();

        contacts = new ArrayList<Contact>();

        Contact contact = new Contact("Ника", "Анисимкова", R.drawable.id4, new GregorianCalendar(1994, 5, 29), "Some present");
        contacts.add(contact);
        contact = new Contact("Женя", "Мишнева", R.drawable.id5, new GregorianCalendar(1988, 1, 9), "");
        contacts.add(contact);
        contact = new Contact("Лиза", "Рыженкова", R.drawable.id1, new GregorianCalendar(1993, 2, 27), "");
        contacts.add(contact);
        contact = new Contact("Маша", "Васюкович", R.drawable.id2, new GregorianCalendar(1993, 9, 14), "Some present");
        contacts.add(contact);
        contact = new Contact("Дима", "Чернявский", R.drawable.id3, new GregorianCalendar(1986, 8, 6), "");
        contacts.add(contact);
        contact = new Contact("Надя", "Чернявская", R.drawable.id6, new GregorianCalendar(1993, 10, 18), "Some present");
        contacts.add(contact);
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

        viewHolder.imgContactPicture.setImageResource(contact.getPicture());

        if (contact.isHaspresentIdea()){
            viewHolder.imgPresentIdea.setImageResource(R.drawable.present_checked);
        } else{
            viewHolder.imgPresentIdea.setImageResource(R.drawable.present_unchecked);
        }

        viewHolder.tvFirstLastName.setText(contact.getFirstName() + " " + contact.getLastName());
        viewHolder.tvEvent.setText(R.string.birth_day);

        viewHolder.tvEventDate.setText(DateMethods.getStringDateMonth(contact.getBirthDate(), MainActivity.applicationResources.getStringArray(R.array.months)));

        // определить окончание для русского языка
        int daysLeft = DateMethods.getDaysDifference(Calendar.getInstance(), DateMethods.nextBirthday(contact.getBirthDate()));
        int lastDigit = daysLeft%10;
        String days = "";
        if (lastDigit==2||lastDigit==3||lastDigit==4){
            days = MainActivity.applicationResources.getString(R.string.days2);
        } else{
            days = MainActivity.applicationResources.getString(R.string.days);
        }
        viewHolder.tvDaysLeft.setText(MainActivity.applicationResources.getString(R.string.in) + " " + daysLeft + " " + days);

        Calendar nextBDay = DateMethods.nextBirthday(contact.getBirthDate());
        viewHolder.tvDay.setText(DateMethods.getDayOfWeek(nextBDay, MainActivity.applicationResources.getStringArray(R.array.days_of_week)));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgContactPicture;
        public ImageView imgPresentIdea;
        public TextView tvFirstLastName;
        public TextView tvEvent;
        public TextView tvEventDate;
        public TextView tvDaysLeft;
        public TextView tvDay;

        public ViewHolder(View itemView) {
            super(itemView);
            imgContactPicture = (ImageView)itemView.findViewById(R.id.img_contact_picture);
            imgPresentIdea = (ImageView) itemView.findViewById(R.id.img_present_idea);
            tvFirstLastName = (TextView)itemView.findViewById(R.id.tv_first_last_name);
            tvEvent = (TextView)itemView.findViewById(R.id.tv_event);
            tvEventDate = (TextView) itemView.findViewById(R.id.tv_event_date);
            tvDaysLeft = (TextView)itemView.findViewById(R.id.tv_days_left);
            tvDay = (TextView)itemView.findViewById(R.id.tv_day_of_week);
        }
    }
}