package com.example.babyinformation;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

public class funny extends AppCompatActivity {

    CustomImageAdapter customImageAdapter;
    ArrayList<GetSet> getSets;
    RecyclerView rv;
    StaggeredGridLayoutManager mLayoutManager;
    FloatingActionButton capCam;

    int CAM_CODE = 0;
    int GALLERY_CODE = 1;
    // Temp save listItem position
    int position;
    String imageTempName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funny);

        rv =  findViewById(R.id.captureList);
        capCam = findViewById(R.id.cam_btn);
        getSets = new ArrayList<GetSet>();

        //        for (int i = 0; i < 3; i++) {
//
//            GetSet inflate = new GetSet();
//
//            // Global Values
//            inflate.setUid(String.valueOf(i));
//            inflate.setHaveImage(false);
//            inflate.setStatus(true);
//
//            getSets.add(inflate);
//        }
        rv.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(3 , LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(mLayoutManager);
        customImageAdapter = new CustomImageAdapter(getSets);
        rv.setAdapter(customImageAdapter);

        capCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(funny.this);
                builder.setTitle("Select Image")
                        .setItems(R.array.array, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                if(which == 0){
                                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(i,CAM_CODE);
                                }
                                else if(which == 1){
                                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    startActivityForResult(i,GALLERY_CODE);
                                }
                            }
                        }).show();
//                captureImage();
            }
        });
    }

    //check the SharedPreferences


    /**
     * Capture Image and save into database
     */

    public void captureImage() {

        position += 1;
        imageTempName = String.valueOf(position);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);
    }

    /**
     * Set capture image to database and set to image preview
     *
     * @param data
     */
    private void onCaptureImageResult(Bitmap data) {

//        Bundle extras = data.getExtras();
//        Bitmap imageBitmap = (Bitmap) extras.get("data");

        // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
//        Uri tempUri = getImageUri(getApplicationContext(), imageBitmap, imageTempName);
        //use it if you have database
//        String picturePath = getRealPathFromURI(tempUri);


        GetSet image = new GetSet();
        image.setImage(data);
        getSets.add(image);
        customImageAdapter.setData(getSets);

//        customImageAdapter.setImageInItem(position, imageBitmap, picturePath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            return;
        }
        if (requestCode == GALLERY_CODE) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    Toast.makeText(funny.this, "Image Saved :)", Toast.LENGTH_SHORT).show();
                    onCaptureImageResult(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(funny.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAM_CODE) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            onCaptureImageResult(thumbnail);
        }
    }
}