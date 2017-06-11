package com.example.android.sugarormexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by simranjain1507 on 11/06/17.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private List<Person> personDetailses;
    Context context;
    int pos;
    public PersonAdapter(Context c,List<Person> personDetails) {
        this.context=c;
        this.personDetailses = personDetails;

    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, phn, gender, area;
        LinearLayout linearLayout;


        public PersonViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            phn = (TextView) itemView.findViewById(R.id.phn);
            area=(TextView) itemView.findViewById(R.id.area);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.layouttotal);

        }
    }


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, final int position) {
        holder.name.setText("NAME: " + personDetailses.get(position).getName());
        holder.email.setText("E-MAIL: " + personDetailses.get(position).getEmail());
        holder.phn.setText("PHONE: " + personDetailses.get(position).getPhone());
        holder.area.setText("AREA: " + personDetailses.get(position).getArea());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e ("Item selected: ", String.valueOf(personDetailses.get(position).getId()));
                Intent editIntent=new Intent(context, UpdateDeleteActivity.class);
                editIntent.putExtra("position_db",String.valueOf(personDetailses.get(position).getId()));
                editIntent.putExtra("name_db", personDetailses.get(position).getName());
                editIntent.putExtra("email_db", personDetailses.get(position).getEmail());
                editIntent.putExtra("phone_db", personDetailses.get(position).getPhone());
                editIntent.putExtra("area_db", personDetailses.get(position).getArea());

                context.startActivity(editIntent);


            }
        });
    }



    @Override
    public int getItemCount() {
        return personDetailses.size();
    }
}

