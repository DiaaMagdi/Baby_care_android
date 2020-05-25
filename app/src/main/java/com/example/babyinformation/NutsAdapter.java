package com.example.babyinformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NutsAdapter extends RecyclerView.Adapter<NutsAdapter.NutsViewHolder> {
    private List<Nuts_database> NutsList = new ArrayList<>();
    private OnNoteListener OnNoteListener;


    String data2[];
    String data3[];
    String data4[];
    String data5[];
    int images[];
    int icons[];
    //    int Line[];
    Context context;


    public NutsAdapter(Context ct, String s2[], String s3[], String s4[], String s5[], int img[], int Icons[], OnNoteListener nOnNoteListener) {


        context = ct;
        data2 = s2;
        data3 = s3;
        data4 = s4;
        data5 = s5;
        images = img;
        icons = Icons;
        OnNoteListener = nOnNoteListener;
//        Line = line;


    }


    @NonNull
    @Override
    public NutsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row1, parent, false);
        return new NutsViewHolder(view, OnNoteListener);

    }

    @Override
    public void onBindViewHolder(@NonNull NutsViewHolder holder, int position) {

        holder.nutsNames.setText(NutsList.get(position).getTypes());
//        holder.myText2.setText(data2[position]);
//        holder.myText3.setText(data3[position]);
//        holder.myText4.setText(data4[position]);
//        holder.myText5.setText(data5[position]);
        holder.im6.setImageResource(icons[Integer.parseInt(NutsList.get(position).getVal1())]);
        holder.im2.setImageResource(icons[Integer.parseInt(NutsList.get(position).getVal2())]);
        holder.im.setImageResource(icons[Integer.parseInt(NutsList.get(position).getVal3())]);
        holder.im7.setImageResource(icons[Integer.parseInt(NutsList.get(position).getVal4())]);
        Context context = holder.myImage.getContext();
        int id = context.getResources().getIdentifier(NutsList.get(position).getPhoto(), "drawable", context.getPackageName());
        holder.myImage.setImageResource(id);


    }

    @Override
    public int getItemCount() {
        return NutsList.size();
    }

    public void setList(List<Nuts_database> nutsList) {
        this.NutsList = nutsList;
        notifyDataSetChanged();
    }


    public class NutsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //        TextView nutsNames;
//          TextView tilteTV;
//          TextView useridTV;
//          TextView bodyTV;
        TextView nutsNames;
        TextView myText2;
        TextView myText3;
        TextView myText4;
        TextView myText5;
        ImageView myImage;
        ImageView im6;
        ImageView im2;
        ImageView im;
        ImageView im7;
        OnNoteListener onNoteListener;
//        ImageView myLine;
//        TextView userid , type ;

        public NutsViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
//            tilteTV = itemView.findViewById(R.id.titleTV);
//            useridTV = itemView.findViewById(R.id.userTV);
//            bodyTV = itemView.findViewById(R.id.bodyTV);
            nutsNames = itemView.findViewById(R.id.textView4);
            myText2 = itemView.findViewById(R.id.textView7);
            myText3 = itemView.findViewById(R.id.textView9);
            myText4 = itemView.findViewById(R.id.textView6);
            myText5 = itemView.findViewById(R.id.textView10);
            myImage = itemView.findViewById(R.id.imageView3);
            im6 = itemView.findViewById(R.id.imageView6);
            im2 = itemView.findViewById(R.id.imageView2);
            im = itemView.findViewById(R.id.imageView);
            im7 = itemView.findViewById(R.id.imageView7);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
//            myLine = itemView.findViewById(R.id.imageView8);


//            userid = itemView.findViewById(R.id.userTV);
//            type = itemView.findViewById(R.id.titleTV);

        }

        @Override
        public void onClick(View v) {
            onNoteListener.OnNoteClick(getAdapterPosition());

        }
    }

    public interface OnNoteListener {
        void OnNoteClick(int position);
    }


}
