package com.example.joanneumprojekt.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.joanneumprojekt.R;
import com.example.joanneumprojekt.SignUP.Login_Interface;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Interface_Work_ADD extends AppCompatActivity implements View.OnClickListener {

    //Date
    private static final String Tag = "MainActivity";
    private TextView displayDeadline, displayExamDate, title;
    private DatePickerDialog.OnDateSetListener dateListener, Listenerdate;
    //Date end; Checkbox star
    private CheckBox projectBox, bachelorBox, masterBox;
    private Button setUpload;
    String dateM;
    Date date1;
    Date date2;
    // end


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_work_add);


        //Date import

        displayDeadline = findViewById(R.id.txt_deadline);
        displayExamDate = findViewById(R.id.txtExamDate);
        projectBox = findViewById(R.id.chack_Project);
        bachelorBox = findViewById(R.id.check_Bachelor);
        masterBox = findViewById(R.id.check_Master);
        title = findViewById(R.id.txt_Titel);
        setUpload = findViewById(R.id.btn_setWork);
        //end


        displayDeadline.setOnClickListener(this);
        displayExamDate.setOnClickListener(this);
        projectBox.setOnClickListener(this);
        masterBox.setOnClickListener(this);
        bachelorBox.setOnClickListener(this);
        setUpload.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.txt_deadline:
                deadline();
                break;

            case R.id.txtExamDate:
               examDate();
                break;

            case R.id.btn_setWork:
                setWork();

        }
    }

    public void deadline() {
        Calendar newCalender = Calendar.getInstance();
        int year = newCalender.get(Calendar.YEAR);
        int month = newCalender.get(Calendar.MONTH);
        int day = newCalender.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog newDialog = new DatePickerDialog(
                Interface_Work_ADD.this,
                android.R.style.Theme_Black,
                dateListener,
                year, month, day);
        newDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        newDialog.show();


        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datenew, int year, int month, int day) {
                month = month + 1;

                String date = month + "/" + day + "/" + year;
                displayDeadline.setText(date);
                try {
                    date2 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                } catch (java.text.ParseException e) {

                }
            }
        };
    }




    public void examDate(){
        Calendar calender1 = Calendar.getInstance();
        int year1 = calender1.get(Calendar.YEAR);
        int month1 = calender1.get(Calendar.MONTH);
        int day1 = calender1.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datedialog = new DatePickerDialog(
                Interface_Work_ADD.this,
                android.R.style.Theme_Black, Listenerdate,
                year1, month1, day1);
        datedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datedialog.show();

        Listenerdate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePick, int year, int month, int day) {
                month = month + 1;  //starts at 0


                String dateM = month + "/" + day + "/" + year;
                displayExamDate.setText(dateM);
                try {
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateM);
                } catch (java.text.ParseException e) {

                }
            }

        };
    }


    public void setWork() {
        //Check if Date is ok

        java.util.Date date = new java.util.Date();


        ParseObject work = new ParseObject("All_Works");
        int number = 0;
        if (masterBox.isChecked()) {
            if (date.after(date1)) {
                number = 1;
            }
        }
        if (date.before(date2) && number == 0) {


            work.put("Deadline", displayDeadline.getText().toString());
            work.put("Title", title.getText().toString());
            work.put("User", "open");


            int count = 0;
            if (bachelorBox.isChecked()) {
                count = count + 1;
            }
            if (masterBox.isChecked()) {
                count = count + 1;
            }
            if (projectBox.isChecked()) {
                count = count + 1;
            }

            if (count == 1) {
                if (bachelorBox.isChecked()) {
                    work.put("Function", "Bachelor");

                } else if (projectBox.isChecked()) {
                    work.put("Function", "Project");

                } else if (masterBox.isChecked()) {
                    work.put("Function", "Master");
                }
                if (masterBox.isChecked()) {
                    work.put("Exam_Date", displayExamDate.getText().toString());
                }


                work.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(Interface_Work_ADD.this, " Work has been uploaded", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            Intent intentAdmin = new Intent(Interface_Work_ADD.this, ADMIN_INTERFACE.class);
                            startActivity(intentAdmin);

                        } else {
                            FancyToast.makeText(Interface_Work_ADD.this, "Something went wrong", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            } else {
                FancyToast.makeText(Interface_Work_ADD.this, " Please check only one box", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
            }
        } else {
            FancyToast.makeText(Interface_Work_ADD.this, "Date has already expired\n" + "Please choose anly dates after :\n" + date, FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
}