package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    Button btnDate;
    RadioButton rbGender;
    String date, firstName, middleName, lastName, gender, barangay, municipality;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDate = findViewById(R.id.datePicker_button);
        Button btnSubmit = findViewById(R.id.submit_button);
        Button btnNext = findViewById(R.id.next_button);

        EditText edFirstName = findViewById(R.id.firstName_editText);
        EditText edMiddlename = findViewById(R.id.midleName_editText);
        EditText edLastName = findViewById(R.id.lastName_editText);
        EditText edBarangay = findViewById(R.id.barangay_editText);
        EditText edMunicipality = findViewById(R.id.municipality_editText);

        RadioGroup rgGender = findViewById(R.id.gender_radioGroup);

        sp = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        initDatePicker();
        btnDate.setText(getTodaysDate());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    firstName = edFirstName.getText().toString();
                    middleName = edMiddlename.getText().toString();
                    lastName = edLastName.getText().toString();
                    barangay = edBarangay.getText().toString();
                    municipality = edMunicipality.getText().toString();

                    int selectedGenderId = rgGender.getCheckedRadioButtonId();
                    rbGender = findViewById(selectedGenderId);
                    gender = rbGender.getText().toString();

                    date = btnDate.getText().toString();

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("firstName", firstName);
                    editor.putString("middleName", middleName);
                    editor.putString("lastName", lastName);
                    editor.putString("gender", gender);
                    editor.putString("date", date);
                    editor.putString("barangay", barangay);
                    editor.putString("municipality", municipality);
                    editor.commit();

                    Toast.makeText(MainActivity.this, "User Data has been saved", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Fill all the items first before saving", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String selectedDate = makeDateString(day, month, year);
                btnDate.setText(selectedDate);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}