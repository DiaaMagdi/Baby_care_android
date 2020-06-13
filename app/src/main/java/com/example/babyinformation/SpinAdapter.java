package com.example.babyinformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SpinAdapter extends ArrayAdapter<String> {

    String[] gender;
    Context context;

    public SpinAdapter(@NonNull Context context, int resource, String[] object) {
        super(context, resource, object);
        gender = object;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);

        View row=inflater.inflate(R.layout.custom_spinner_dropdown, parent, false);
        TextView label=(TextView)row.findViewById(R.id.text1);
        label.setText(gender[position]);


        return row;
    }
}
