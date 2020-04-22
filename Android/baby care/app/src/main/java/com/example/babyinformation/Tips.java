package com.example.babyinformation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class Tips extends AppCompatActivity {
    public static final String TAG = "MyTag";
    private TextView mOutputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        mOutputText = findViewById(R.id.tv_output);

        if (getIntent() != null && getIntent().hasExtra("key1")) {
            mOutputText.setText("");

            for (String key : getIntent().getExtras().keySet()) {
                Log.d(TAG, "onCreate: Key: " + key + "Data: " + getIntent().getExtras().getString(key));
                mOutputText.append(getIntent().getExtras().getString(key) + "\n");
            }
        }

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {

                        if (task.isSuccessful()){
                            String token = task.getResult().getToken();
                            Log.d(TAG, "onComplete: Token: " + token);
                            mOutputText.setText("Token Generated");
                        }else {
                            mOutputText.setText("Token generation failed");
                        }
                    }
                });
    }
}
