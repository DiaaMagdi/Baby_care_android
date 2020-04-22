package com.example.babyinformation;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomImageAdapter extends RecyclerView.Adapter<CustomImageAdapter.ViewHolder> {

    AlertDialog.Builder builder;
    List<GetSet> _data;
    private Random mRandom = new Random();
    Context context;


    public CustomImageAdapter(List<GetSet> getData) {
        _data = getData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutIdForListItem = R.layout.inflate_view;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return  new ViewHolder(view);
    }
    private boolean zoomOut =  false;
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(zoomOut) {
                    Toast.makeText(context, "NORMAL SIZE!", Toast.LENGTH_LONG).show();
                    holder.imageView .setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    holder.imageView.setAdjustViewBounds(true);
                    zoomOut =false;
                }else{
                    Toast.makeText(context, "FULLSCREEN!", Toast.LENGTH_LONG).show();
                    holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    zoomOut = true;
                }
            }
        });
        holder.imageView.setImageBitmap(_data.get(position).getImage());

        // need some calculaation from the real image going to be in the card
        holder.imageView.getLayoutParams().height = getRandomIntInRange(350,250);

        holder.removeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder = new AlertDialog.Builder(context);
                //Uncomment the below code to Set the message and title from the strings.xml file
                builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title)

                //Setting message manually and performing action on button click
//                builder.setMessage("Do you want to close this application ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // need some validation to check if user want to delete img
                                _data.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
//                alert.setTitle("AlertDialogExample");
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return _data.size();
    }

    public void setData(ArrayList<GetSet> getData) {
        _data = getData;
        notifyDataSetChanged();
    }

    protected int getRandomIntInRange(int max, int min){
        return mRandom.nextInt((max-min)+min)+min;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageButton removeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =  itemView.findViewById(R.id.imgPrv);
            removeImage = itemView.findViewById(R.id.cancel);
        }
    }
}


