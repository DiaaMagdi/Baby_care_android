package com.example.babyinformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babyinformation.pojo.Vaccination_ChildrenData;

import java.util.ArrayList;

public class VaccinationAdapter extends RecyclerView.Adapter<VaccinationAdapter.MyViewHolder> {

    ArrayList<Vaccine> vaccinationList;
    Context context;

    public VaccinationAdapter(ArrayList<Vaccine> vaccinationList, Context context) {
        this.vaccinationList = vaccinationList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.vaccination_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Vaccine vaccine = vaccinationList.get(position);

        holder.childImage.setImageResource(R.drawable.logo);
        holder.vacc.setText("Vaccenation " + Integer.toString(position));
        holder.childName.setText(vaccine.childName);
        holder.vaccName.setText(vaccine.vaccName);
        holder.vaccDate.setText(vaccine.vaccDate);
    }

    @Override
    public int getItemCount() {
        return vaccinationList.size();
    }

    public void setList(ArrayList<Vaccine> vaccinationList){
        this.vaccinationList = vaccinationList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView vacc, childName, vaccName, vaccDate;
        ImageView  childImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            childImage = itemView.findViewById(R.id.childImage);
            vacc = itemView.findViewById(R.id.vacc);
            childName = itemView.findViewById(R.id.childName);
            vaccName = itemView.findViewById(R.id.vaccName);
            vaccDate = itemView.findViewById(R.id.vaccDate);
        }
    }
}
