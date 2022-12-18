/**
 * Class for adding a new bill to the system
 */

package com.example.joanneumprojekt.Display_Interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.joanneumprojekt.After_Login.Main;
import com.example.joanneumprojekt.After_Login.activity_camera;
import com.example.joanneumprojekt.After_Login.show_delete_bill;
import com.example.joanneumprojekt.Pictures.GetPictures;
import com.example.joanneumprojekt.R;
import com.parse.ParseObject;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class New_Bill extends AppCompatActivity implements View.OnClickListener {
    private TextView changetoolbarText;
    DrawerLayout drawerLayout;
    private static final String Tag = "MainActivity";
    private TextView displayDeadline, title, price;
    private DatePickerDialog.OnDateSetListener dateListener;
    private CheckBox Private, Business, check20, check10, check13;
    private Button setUpload, openCamera;
    Date date2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbill);

        drawerLayout = findViewById(R.id.drawer_layout);
        changetoolbarText = findViewById(R.id.tv_toolbarText);
        //changetoolbarText.setText("ADD BILL");

        //Date import
        displayDeadline = findViewById(R.id.txt_deadline);
        Private = findViewById(R.id.Private);
        Business = findViewById(R.id.Business);
        title = findViewById(R.id.Bill_Title);
        price = findViewById(R.id.txt_Titel);
        setUpload = findViewById(R.id.btn_setWork);
        openCamera = findViewById(R.id.btn_camera);
        check20 = findViewById(R.id.check20);
        check10 = findViewById(R.id.check10);
        check13 = findViewById(R.id.check13);


        displayDeadline.setOnClickListener(this);
        Private.setOnClickListener(this);
        Business.setOnClickListener(this);
        setUpload.setOnClickListener(this);
        openCamera.setOnClickListener(this);
        check20.setOnClickListener(this);
        check10.setOnClickListener(this);
        check13.setOnClickListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.txt_deadline:
                deadline();
                break;

            case R.id.btn_setWork:
                setWork();
                break;

            case R.id.btn_camera:
                cameraOpen();
                break;

        }
    }


    @SuppressLint("SimpleDateFormat")
    public void deadline() {
        Calendar newCalender = Calendar.getInstance();
        int year = newCalender.get(Calendar.YEAR);
        int month = newCalender.get(Calendar.MONTH);
        int day = newCalender.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog newDialog = new DatePickerDialog(
                New_Bill.this,
                android.R.style.Theme_Black,
                dateListener,
                year, month, day);
        newDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        newDialog.show();


        dateListener = (datenew, year1, month1, day1) -> {
            month1 = month1 + 1;

            String date = month1 + "/" + day1 + "/" + year1;
            displayDeadline.setText(date);
            try {
                date2 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (java.text.ParseException ignored) {

            }
        };
    }


    public void setWork() {

        int count = 0;
        if (Business.isChecked()) {
            count = count + 1;
        }
        if (Private.isChecked()) {
            count = count + 1;
        }
        String role = "";
        if (count == 1) {
            if (Business.isChecked()) {
                role = "Business";

            } else if (Private.isChecked()) {
                role = "Private";
            }
        }


        double percent = 0;
        int checkPercent = 0;
        if (check10.isChecked()) {
            checkPercent = checkPercent + 1;
        }
        if (check13.isChecked()) {
            checkPercent = checkPercent + 1;
        }
        if (check20.isChecked()) {
            checkPercent = checkPercent + 1;
        }

        if (checkPercent == 1) {
            if (check10.isChecked())
                percent = 0.10;
            else if (check13.isChecked()) {
                percent = 0.13;
            } else if (check20.isChecked()) {
                percent = 0.20;
            }
        }

        boolean check = false;
        try {
            Integer.valueOf(price.getText().toString().length());
            check = true;
        } catch (Exception e) {
        }

        ParseObject Categorize = new ParseObject(role);
        if (title.getText().toString().length() > 3) {
            if (price.getText().toString().length() > 1 && check) {
                if (displayDeadline.getText().toString().length() > 5) {
                    if (count == 1 && checkPercent == 1) {
                        Categorize.put("Date", displayDeadline.getText().toString());
                        Categorize.put("Title", title.getText().toString());
                        Categorize.put("Price", price.getText().toString());
                        double calculation = Integer.parseInt(price.getText().toString()) * percent;
                        Categorize.put("Abrechnung", calculation);


                        Categorize.saveInBackground(e -> {
                            if (e == null) {
                                FancyToast.makeText(New_Bill.this, "Bill has been uploaded", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                Intent intent = new Intent(New_Bill.this, Main.class);
                                startActivity(intent);

                            } else {
                                FancyToast.makeText(New_Bill.this, "Something went wrong", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        });

                    } else {
                        FancyToast.makeText(New_Bill.this, " Please check only one box", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                } else {
                    FancyToast.makeText(New_Bill.this, "Please select a date.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
            } else {
                FancyToast.makeText(New_Bill.this, "The price has not been set / is malformed", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
            }
        } else {
            FancyToast.makeText(New_Bill.this, "The title has not been set / is malformed", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }

    public void clickMenu(View view) {
        Main.openDrawer(drawerLayout);

    }

    public void clickLogo(View view) {
        Main.closeDrawer(drawerLayout);
    }

    public void clickHome(View view) {
        //Redirect activity to MainActivity (Home)
        Main.redirectActivity(this, Main.class);
    }

    public void clickApplications(View view) {
        //Recreate the ApplicationsActivity
        recreate();
    }

    public void getBillsPng(View view) {
        //Redirect activity to Show_Pictures
        Main.redirectActivity(this, GetPictures.class);
    }

    public void deleteUser(View view) {
        //Redirect activity to delete_bill
        Main.redirectActivity(this, show_delete_bill.class);
    }


    public void clickLogout(View view) {
        //Close app
        Main.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Main.closeDrawer(drawerLayout);
    }


    private void cameraOpen() {
        Intent camera = new Intent(New_Bill.this, activity_camera.class);
        startActivity(camera);
    }
}