package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    String date, firstName, middleName, lastName, gender, barangay, municipality;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btnReturn = findViewById(R.id.return_button);

        Button btnDate = findViewById(R.id.datePicker_button);
        EditText edFirstName = findViewById(R.id.firstName_editText);
        EditText edMiddlename = findViewById(R.id.midleName_editText);
        EditText edLastName = findViewById(R.id.lastName_editText);
        EditText edBarangay = findViewById(R.id.barangay_editText);
        EditText edMunicipality = findViewById(R.id.municipality_editText);
        RadioButton rbnMale = findViewById(R.id.male_radioButton);
        RadioButton rbnFemale = findViewById(R.id.female_radioButton);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        firstName = sp.getString("firstName", "");
        middleName = sp.getString("middleName", "");
        lastName = sp.getString("lastName", "");
        gender = sp.getString("gender", "");
        date = sp.getString("date", "");
        barangay = sp.getString("barangay", "");
        municipality = sp.getString("municipality", "");

        edFirstName.setText(firstName);
        edMiddlename.setText(middleName);
        edLastName.setText(lastName);
        edBarangay.setText(barangay);
        edMunicipality.setText(municipality);
        btnDate.setText(date);
        if(gender.equals("Male")){
            rbnMale.setChecked(true);
            rbnFemale.setChecked(false);
        }else{
            rbnFemale.setChecked(true);
            rbnMale.setChecked(false);
        }

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}