package com.example.joanneumprojekt.Admin;

/*
    Main und gugg bei rob_addbill
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.joanneumprojekt.R;
import com.example.joanneumprojekt.rob_addbill;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main extends AppCompatActivity implements View.OnClickListener{
    private TextView changetoolbarText;
    DrawerLayout drawerLayout;
    private static final String Tag = "MainActivity";
    private TextView displayDeadline, title, txt_Titel;
    private DatePickerDialog.OnDateSetListener dateListener, Listenerdate;
    //Date end; Checkbox star
    private CheckBox Private, Business;
    private Button setUpload;
    Date date2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rob_main);

//Date import
        displayDeadline = findViewById(R.id.txt_deadline);
        Private = findViewById(R.id.Private);
        Business = findViewById(R.id.Business);
        title = findViewById(R.id.Bill_Title);
        txt_Titel = findViewById(R.id.txt_Titel);
        setUpload = findViewById(R.id.btn_setWork);
        //end


        drawerLayout = findViewById(R.id.drawer_layout);
        changetoolbarText = findViewById(R.id.tv_toolbarText);
        changetoolbarText.setText("Bills");

        displayDeadline.setOnClickListener(this);
        Private.setOnClickListener(this);
        Business.setOnClickListener(this);
        setUpload.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.txt_deadline:
                deadline();
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
                Main.this,
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


    public void setWork() {
        //Check if Date is ok

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


            ParseObject Categorize = new ParseObject(role);

            if (count == 1) {
                if (Business.isChecked()) {
                    Categorize.put("Date", displayDeadline.getText().toString());
                    Categorize.put("Title", title.getText().toString());
                    Categorize.put("Price", txt_Titel.getText().toString());

                } else if (Private.isChecked()) {
                    Categorize.put("Date", displayDeadline.getText().toString());
                    Categorize.put("Title", title.getText().toString());
                    Categorize.put("Price", txt_Titel.getText().toString());

                }


                Categorize.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(Main.this, " Work has been uploaded", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            Intent intentAdmin = new Intent(Main.this, ADMIN_INTERFACE.class);
                            startActivity(intentAdmin);

                        } else {
                            FancyToast.makeText(Main.this, "Something went wrong", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            } else {
                FancyToast.makeText(Main.this, " Please check only one box", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
            }
        }
    }









    public void clickMenu(View view){
        openDrawer(drawerLayout);
    }
    // von der Seite soll der navigator kommen
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }


    //What should happen when you click on the logo in the drawer
    public void clickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //When drawer is open, then close it
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    // wenn ich home bin, und dann im navigator auf home erneut klicke - passiert halt ein refresh
    public void clickHome(View view){
        //Recreate the Rob_MainActivity
        recreate();
    }

    public void clickApplications(View view){
        //Redirect current activity to Applications activity
        // --> wenn ich auf add bill click, soll er zu add bill kommen
        redirectActivity(this, rob_addbill.class);
    }


    public void clickLogout(View view){
        //close app
        logout(this);
    }

    public static void logout(Activity activity) {
        //Initialize the alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.logout_alert_title);
        builder.setMessage(R.string.logout_alert_message);

        //Positive button clicked
        builder.setPositiveButton(R.string.logout_alert_responsePositive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finishAffinity();
                System.exit(0);
            }
        });

        //Negative button clicked
        builder.setNegativeButton(R.string.logout_alert_responseNegative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    public static void redirectActivity(Activity sourceActivity, Class classOfDestinationActivity) {
        Intent intent = new Intent(sourceActivity, classOfDestinationActivity);
        sourceActivity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}
