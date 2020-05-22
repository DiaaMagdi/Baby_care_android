package com.example.babyinformation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class editbaby extends AppCompatActivity {

    private Button newbaby;
    String name;
    int weight, length;
    String[] gender = {"Male", "Female"};
    TextView cal;
    Calendar mCurrentdDate;
    int day, month, year;

    EditText namebaby;
    EditText lengthbaby;
    EditText weightbaby;
    Button savebutton;
    Spinner Gender;
    TextView Dateofbirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editbaby);

        //////////////////////////////////////////////// start Babyname /////////////////////////////////////////////////

        namebaby = (EditText) findViewById(R.id.namebaby);
        lengthbaby = (EditText) findViewById(R.id.length);
        weightbaby = (EditText) findViewById(R.id.weight);


        //////////////// Intialization validation /////////////////////

        savebutton = (Button) findViewById(R.id.savebutton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveValidation();
            }
        });


        //////////////////////////////////////////////// End Baby name //////////////////////////////////////////////////


        //////////////////////////////////////////////// start spinner /////////////////////////////////////////////////
        Spinner spinner = findViewById(R.id.gender);

        SpinAdapter adapter = new SpinAdapter(this, R.layout.customspinner, gender);

        spinner.setAdapter(adapter);
        //////////////////////////////////////////////// End spinner /////////////////////////////////////////////////


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
                                       DatePickerDialog datePickerDialog = new DatePickerDialog(editbaby.this, new DatePickerDialog.OnDateSetListener() {
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
    }





    /////////////////////////// Validation ////////////////////////
    private void saveValidation() {
        if (namebaby.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Name is Required", Toast.LENGTH_SHORT).show();
            namebaby.setError("Name is required");
        }
        if ( (weightbaby.getText().toString().length() > 0) || (weightbaby.getText().toString().length() < 2) ) {
            weightbaby.setError("Weight Is Invalid");
            Toast.makeText(this, "Weight must be Entered in KG", Toast.LENGTH_LONG).show();
        }
        if (lengthbaby.getText().toString().length() < 2) {
            lengthbaby.setError("Length Is Invalid");
            Toast.makeText(this, "Length must be Entered in CM", Toast.LENGTH_LONG).show();
        } else {
            Intent save = new Intent(this, setting.class);
            startActivity(save);
            finish();
        }

    }

}
