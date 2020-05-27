package com.example.babyinformation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.Calendar;

public class detailsChildern extends AppCompatActivity {

    private Button buttonContinue;
    TextView cal;
    Calendar mCurrentdDate;
    int day, month, year;
    EditText editText;
    RadioGroup radiochild;

    //initialize variable
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_childern);

        buttonContinue = (Button) findViewById(R.id.buttonContinue);

        //////////////////////////////////////////////// start Date /////////////////////////////////////////////////
        cal = (TextView) findViewById(R.id.Date);
        mCurrentdDate = Calendar.getInstance();

        day = mCurrentdDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentdDate.get(Calendar.MONTH);
        year = mCurrentdDate.get(Calendar.YEAR);
        month = month + 1;
        cal.setText(day + "/" + month + "/" + year);
        cal.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       DatePickerDialog datePickerDialog = new DatePickerDialog(detailsChildern.this, new DatePickerDialog.OnDateSetListener() {
                                           @Override
                                           public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                               monthOfYear = monthOfYear + 1;
                                               cal.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                                           }
                                       }, year, month, day);
                                       datePickerDialog.show();
                                   }
                               }
        );
        //////////////////////////////////////////////// End Date /////////////////////////////////////////////////

        //assign variable
        editText = (EditText) findViewById(R.id.edittext);

        //initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //add validation for address
        awesomeValidation.addValidation(this, R.id.edittext,RegexTemplate.NOT_EMPTY,R.string.invalid_address);

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check validation
                if(awesomeValidation.validate()){
                    //on success
                    openActivity2();
                }
            }
        });

    }
    public void openActivity2() {
        Intent intent = new Intent(this, FinishRequest.class);
        startActivity(intent);
    }

    public void onSelcted(View view) {
        RadioGroup radiochild = findViewById(R.id.radiochild);
        switch (radiochild.getCheckedRadioButtonId()){
            case R.id.radio1111:
                break;
            case R.id.radio2222:
                break;
            case R.id.radio3333:
                break;
            case R.id.radio4444:
                break;
        }
    }
}
