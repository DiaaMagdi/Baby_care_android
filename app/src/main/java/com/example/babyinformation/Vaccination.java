package com.example.babyinformation;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.babyinformation.TheVaccination.CHANNEL_1;

public class Vaccination extends AppCompatActivity {
    ImageView arrowBack;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ExampleItem> exampleList;

    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccination);



        notificationManagerCompat = NotificationManagerCompat.from(this);

        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.logo, "Vaccenation 1", "Ahmed", "Measles 1", "12 May"));
        exampleList.add(new ExampleItem(R.drawable.logo, "Vaccenation 2", "Mahmoud", "Measles 2", "15 May"));
        exampleList.add(new ExampleItem(R.drawable.logo, "Vaccenation 3", "Khaled", "Measles 3", "18 May"));
        exampleList.add(new ExampleItem(R.drawable.logo, "Vaccenation 4", "Reda", "Measles 4", "12 May"));
        exampleList.add(new ExampleItem(R.drawable.logo, "Vaccenation 5", "Hashim", "Measles 5", "15 May"));
        exampleList.add(new ExampleItem(R.drawable.logo, "Vaccenation 6", "Mohamed", "Measles 6", "18 May"));
        exampleList.add(new ExampleItem(R.drawable.logo, "Vaccenation 7", "Ayman", "Measles 7", "12 May"));
        exampleList.add(new ExampleItem(R.drawable.logo, "Vaccenation 8", "Shadi", "Measles 8", "15 May"));
        exampleList.add(new ExampleItem(R.drawable.logo, "Vaccenation 9", "Avramax", "Measles 9", "18 May"));

        recyclerView = findViewById(R.id.recyclarView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapder(exampleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void backActivity() {
        Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this,BackActivity.class);
        //startActivity(intent);
    }

    public void sendNotification(View v) {
        Toast.makeText(this, "HI", Toast.LENGTH_SHORT).show();
        int pointer = 2;
        String title = exampleList.get(pointer).getText1();
        String description = exampleList.get(pointer).getText2();
        Intent activityIntent = new Intent(this, Vaccination.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0,
                activityIntent,
                0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();
        notificationManagerCompat.notify(0, notification);
    }
}
