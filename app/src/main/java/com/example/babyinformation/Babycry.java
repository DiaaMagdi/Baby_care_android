package com.example.babyinformation;

import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.babyinformation.pojo.ML_CryReason;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Babycry extends AppCompatActivity {

    private ProgressBar mprogressbar;
    private TextView mfinish;
    private ImageButton record;
    private ImageButton cancel;
    boolean isStart =true;
    private MediaRecorder recorder;
    private int mprogressstatus = 0;
    private String fileName = null;
    private static final String LOG_TAG = "Record_log";
    private Handler mhandler = new Handler();
    private BabyCry_ViewModel CryingReason;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babycry);

        CryingReason = ViewModelProviders.of(Babycry.this).get(BabyCry_ViewModel.class);
        CryingReason.cryReasonMutableLiveData.observe(this, new Observer<ML_CryReason>() {
            @Override
            public void onChanged(ML_CryReason ml_cryReason) {
                mfinish.setText(ml_cryReason.getBaby_crying_reason());
            }
        });

        recorder = new MediaRecorder();

        mprogressbar =  findViewById(R.id.progressbar);
        mfinish = findViewById(R.id.finish);
        record =  findViewById(R.id.record);
        cancel = findViewById(R.id.cancel);
        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/baby_cry_record.wav";

        record.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                isStart = true;
                progressbarfinish();
                startRecording();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diposeRecording();
                record.setEnabled(true);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startRecording() {
        recorder = new MediaRecorder();
        file = new File(fileName);
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //recorder.setOutputFile(fileName);
        recorder.setOutputFile(file);
        //// recorder.setAudioEncodingBitRate();  TODO
        recorder.setAudioSamplingRate(8000);
        recorder.setMaxDuration(10000); // 10 seconds
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        cancel.setVisibility(View.VISIBLE);
        record.setEnabled(false);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recorder.start();
    }
    ///////////////////////// this method
    private void diposeRecording() {
        recorder.stop();
        recorder = null;
        cancel.setVisibility(View.INVISIBLE);
        mprogressstatus = 0;
        isStart= false;

    }
    ///////////// this method start progress bar For 10 seconds and visible finish result ////////////
    private void progressbarfinish(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mprogressstatus < 100 && isStart) {
                    mprogressstatus++;
                    android.os.SystemClock.sleep(100);
                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mprogressbar.setProgress(mprogressstatus);
                        }
                    });
                }
                /////////////////// visible finish result //////////////
                if (mprogressstatus == 100) {
                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Babycry.this,"Record Saved", Toast.LENGTH_SHORT).show();
                            recorder.release();
                            mfinish.setVisibility(View.VISIBLE);
                            RequestBody fbody = RequestBody.create(MediaType.parse("*/*"),
                                    file);

                            MultipartBody.Part filePart = MultipartBody.Part.createFormData("baby_cry_record" ,file.getName(),fbody);
                            CryingReason.getCryReason(filePart);
//                            Toast.makeText(Babycry.this , file.exists(), Toast.LENGTH_SHORT).show();
//                            Map<String, Object> baby_cry_record_map = new HashMap<>();
//                            MediaType mediaType = MediaType.parse("/");
//                            RequestBody requestBody = RequestBody.create(mediaType, file);
//                            baby_cry_record_map.put("baby_cry_record", file);
//                            CryingReason.getCryReason(,"baby_cry_record", requestBody);

                        }
                    });
                }
            }
        }).start();
    }
}