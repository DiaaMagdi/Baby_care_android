package com.example.babyinformation;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babycry);

        recorder = new MediaRecorder();

        mprogressbar =  findViewById(R.id.progressbar);
        mfinish = findViewById(R.id.finish);
        record =  findViewById(R.id.record);
        cancel = findViewById(R.id.cancel);
        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/baby_cry_record.mp3";


        record.setOnClickListener(new View.OnClickListener() {
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

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(fileName);
        recorder.setMaxDuration(10000); // 10 seconds
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
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
        recorder.release();
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
                            mfinish.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        }).start();
    }
}
