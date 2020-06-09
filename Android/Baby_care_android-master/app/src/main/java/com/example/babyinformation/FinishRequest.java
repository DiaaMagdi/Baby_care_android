package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class FinishRequest extends AppCompatActivity {

    private Button sendrequest;
    EditText editText;

    //initialize variable
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_request);

        //assign variable
        editText = (EditText) findViewById(R.id.edittext);
        sendrequest = (Button) findViewById(R.id.sendrequest);

        //initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //add validation for address
        awesomeValidation.addValidation(this, R.id.edittext, RegexTemplate.NOT_EMPTY, R.string.invalid_note);

        sendrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check validation
                if (awesomeValidation.validate()) {
                    //on success
                    Toast.makeText(getApplicationContext() , "your request has sent",Toast.LENGTH_SHORT).show();
                    openActivity2();
                }
            }
        });

    }
    public void openActivity2() {
        Intent intent = new Intent(this, CancelRequest.class);
        startActivity(intent);
    }
}