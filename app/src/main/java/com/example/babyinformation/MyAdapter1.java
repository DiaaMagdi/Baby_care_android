package com.example.babyinformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {

    final private ListItemClickListener mOnClickListener;

    String data1[];
    int images[];

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public MyAdapter1(String s1[], int img[] , ListItemClickListener listener) {
        data1 = s1;
        images = img;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row1, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.myText.setText(data1[position]);
        holder.myImage.setImageResource(images[position]);
//        holder.mainLayout1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, funny.class);
//                intent.putExtra("data1", data1[position]);
//                intent.putExtra("myImage", images[position]);
//                context.startActivity(intent);
//            }
//
//        });

    }

    @Override
    public int getItemCount() {
        return images.length;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        TextView myText;
        ImageView myImage;
        ConstraintLayout mainLayout1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText = itemView.findViewById(R.id.myText);
            myImage = itemView.findViewById(R.id.myImageView1);
            mainLayout1 = itemView.findViewById(R.id.mainLayout1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickPosition);
        }
    }
}

