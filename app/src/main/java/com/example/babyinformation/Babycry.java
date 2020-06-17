package com.example.babyinformation;

import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.babyinformation.pojo.ML_CryReason;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Babycry extends AppCompatActivity {
    private static final int RECORDER_BPP = 16;
    private static final String AUDIO_RECORDER_FILE_EXT_WAV = ".wav";
    private static final String AUDIO_RECORDER_FOLDER = "AudioRecorder";
    private static final String AUDIO_RECORDER_TEMP_FILE = "record_temp.raw";
    private static final int RECORDER_SAMPLERATE = 44100;
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_STEREO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    public int STORAGE_PERMISSION=1;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;
    private AudioRecord recorder = null;
    private int bufferSize = 0;
    private Thread recordingThread = null;
    private boolean isRecording = false;
    private ImageButton record;
    private ImageButton stop;
    private ProgressBar mprogressbar;
    private TextView mfinish;
    boolean isStart = true;
    private int mprogressstatus = 0;
    private String fileName = null;
    private static final String LOG_TAG = "Record_log";
    private Handler mhandler = new Handler();
    File file;
    File file1;
    public BabyCry_ViewModel CryingReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babycry);

//        cancel = findViewById(R.id.cancel);
        mfinish = findViewById(R.id.finish);
        mprogressbar = findViewById(R.id.progressbar);
        record = findViewById(R.id.btnStart);
        stop = findViewById(R.id.btnStop);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                diposeRecording();
//                record.setEnabled(true);
//            }
//        });

        CryingReason = ViewModelProviders.of(Babycry.this).get(BabyCry_ViewModel.class);
        CryingReason.cryReasonMutableLiveData.observe(this, new Observer<ML_CryReason>() {
            @Override
            public void onChanged(ML_CryReason ml_cryReason) {
                mfinish.setText(ml_cryReason.getBaby_crying_reason());
            }
        });

        setButtonHandlers();

        bufferSize = AudioRecord.getMinBufferSize(8000,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
    }

    private void setButtonHandlers() {
        ((ImageButton) findViewById(R.id.btnStart)).setOnClickListener(btnClick);
        ((ImageButton) findViewById(R.id.btnStop)).setOnClickListener(btnClick);
    }

    private void enableButton(int id, boolean isEnable) {
        ((ImageButton) findViewById(id)).setEnabled(isEnable);
    }

    private void enableButtons(boolean isRecording) {
        enableButton(R.id.btnStart, !isRecording);
        enableButton(R.id.btnStop, isRecording);
    }

    private String getFilename() {
        String filepath = Environment.getExternalStorageDirectory().getPath();
        file = new File(filepath, AUDIO_RECORDER_FOLDER);

        if (!file.exists()) {
            file.mkdirs();
        }
        // long timeMillis = System.currentTimeMillis();

        return (file.getAbsolutePath() + "/" + "Baby_cry_record" + AUDIO_RECORDER_FILE_EXT_WAV);
    }

    private String getTempFilename() {
        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filepath, AUDIO_RECORDER_FOLDER);

        if (!file.exists()) {
            file.mkdirs();
        }

        File tempFile = new File(filepath, AUDIO_RECORDER_TEMP_FILE);

        if (tempFile.exists())
            tempFile.delete();

        return (file.getAbsolutePath() + "/" + AUDIO_RECORDER_TEMP_FILE);
    }

    private void startRecording() {
        recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
                RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING, bufferSize);

        int i = recorder.getState();
        if (i == 1)
            recorder.startRecording();
//        stop.setVisibility(View.VISIBLE);

        isRecording = true;

        recordingThread = new Thread(new Runnable() {

            @Override
            public void run() {
                writeAudioDataToFile();
            }
        }, "AudioRecorder Thread");

        recordingThread.start();
//        cancel.setVisibility(View.VISIBLE);

    }

    private void writeAudioDataToFile() {
        byte data[] = new byte[bufferSize];
        String filename = getTempFilename();
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

        int read = 0;

        if (null != os) {
            while (isRecording) {
                read = recorder.read(data, 0, bufferSize);

                if (AudioRecord.ERROR_INVALID_OPERATION != read) {
                    try {
                        os.write(data);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void stopRecording() {
        if (null != recorder) {
            isRecording = false;

            int i = recorder.getState();
            if (i == 1)
                recorder.stop();
            recorder.release();
            recorder = null;
            recordingThread = null;
        }

        copyWaveFile(getTempFilename(), getFilename());
        deleteTempFile();
        send();
    }

    private void deleteTempFile() {
        File file = new File(getTempFilename());

        file.delete();
    }

    private void copyWaveFile(String inFilename, String outFilename) {
        FileInputStream in = null;
        FileOutputStream out = null;
        long totalAudioLen = 0;
        long totalDataLen = totalAudioLen + 36;
        long longSampleRate = RECORDER_SAMPLERATE;
        int channels = 2;
        long byteRate = RECORDER_BPP * RECORDER_SAMPLERATE * channels / 8;

        byte[] data = new byte[bufferSize];
        try {
            in = new FileInputStream(inFilename);
            out = new FileOutputStream(outFilename);
            totalAudioLen = in.getChannel().size();
            totalDataLen = totalAudioLen + 36;

            AppLog.logString("File size: " + totalDataLen);

            WriteWaveFileHeader(out, totalAudioLen, totalDataLen,
                    longSampleRate, channels, byteRate);

            while (in.read(data) != -1) {
                out.write(data);
            }


            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void WriteWaveFileHeader(
            FileOutputStream out, long totalAudioLen,
            long totalDataLen, long longSampleRate, int channels,
            long byteRate) throws IOException {

        byte[] header = new byte[44];

        header[0] = 'R'; // RIFF/WAVE header
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        header[4] = (byte) (totalDataLen & 0xff);
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        header[12] = 'f'; // 'fmt ' chunk
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        header[16] = 16; // 4 bytes: size of 'fmt ' chunk
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        header[20] = 1; // format = 1
        header[21] = 0;
        header[22] = (byte) channels;
        header[23] = 0;
        header[24] = (byte) (longSampleRate & 0xff);
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        header[32] = (byte) (2 * 16 / 8); // block align
        header[33] = 0;
        header[34] = RECORDER_BPP; // bits per sample
        header[35] = 0;
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);

        out.write(header, 0, 44);
    }

    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnStart: {
                    if(CheckPermissions()) {
                        record.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                isStart = true;
                                startRecording();
                                progressbarfinish();
                            }
                        });
                    }
                    else
                    {
                        RequestPermissions();
                    }
                    AppLog.logString("Start Recording");

                    break;
                }
                case R.id.btnStop: {
                    AppLog.logString("Start Recording");

                    stopRecording();

                    break;
                }
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length> 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] ==  PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
    public boolean CheckPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }
    private void RequestPermissions() {
        ActivityCompat.requestPermissions(Babycry.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }


    private void send() {
        file1 = new File("/storage/emulated/0/AudioRecorder/Baby_cry_record" + AUDIO_RECORDER_FILE_EXT_WAV);

        RequestBody fbody = RequestBody.create(MediaType.parse("*/*"),
                file1);
        Log.v("CryCry, fbody", Boolean.toString(fbody == null));
        Log.v("CryCry, file", Boolean.toString(file1 == null));
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("baby_cry_record", file1.getName(), fbody);
        Log.v("CryCry: filePart", Boolean.toString(filePart == null));
        CryingReason.getCryReason(filePart);
    }


//    ///////////////////////// this method cancel record /////////////////////
//    private void diposeRecording() {
//        recorder.stop();
//        cancel.setVisibility(View.INVISIBLE);
//        mprogressstatus = 0;
//        isStart= false;
//
//    }

    ///////////// this method start progress bar For 10 seconds and visible finish result ////////////
    private void progressbarfinish() {
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
                    record.setEnabled(true);
                }
                /////////////////// visible finish result //////////////
                if (mprogressstatus == 100) {
                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            recorder.release();
                            mfinish.setVisibility(View.VISIBLE);
                            stopRecording();

                        }
                    });
                    record.setEnabled(false);
                }
            }
        }).start();
    }
}

/*
        recorder = new MediaRecorder();

        mprogressbar =  findViewById(R.id.progressbar);
        mfinish = findViewById(R.id.finish);
        record =  findViewById(R.id.record);
        cancel = findViewById(R.id.cancel);
        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/baby_cry_record.mp3";
        file = new File(fileName);

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
                            mfinish.setVisibility(View.VISIBLE);*/

    //   }
    //    });

    //   }
    //   }
    //   }).start();
    // }*/