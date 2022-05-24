package com.example.joanneumprojekt.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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
import com.example.joanneumprojekt.SignUP.SignUp;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Calendar;

public class Interface_ADD extends AppCompatActivity implements View.OnClickListener{
    //Date

    private TextView email, username, password;
    //Date end; Checkbox star
    CheckBox studentBox, assistentBox, adminBox;
    private Button setUpload;
    // end


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_add);


        //Date import

        email = findViewById(R.id.txt_EMAIL_ADD);
        username = findViewById(R.id.txt_USERNAMR_ADD);
        studentBox = findViewById(R.id.check_Student);
        assistentBox = findViewById(R.id.check_Assistent);
        adminBox = findViewById(R.id.check_Admin);
        password = findViewById(R.id.txt_PASSWORD_ADD);
        setUpload = findViewById(R.id.addUser);
        //end




        studentBox.setOnClickListener(this);
        adminBox.setOnClickListener(this);
        assistentBox.setOnClickListener(this);
        setUpload.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {



            case R.id.addUser:


                final ParseUser appUser2 = new ParseUser();
                appUser2.setEmail(email.getText().toString());
                appUser2.setUsername(username.getText().toString());
                appUser2.setPassword(password.getText().toString());




                int count=0;
                if (assistentBox.isChecked()) {
                    count=count+1;
                }if (adminBox.isChecked()) {
                count=count+1;
            }if (studentBox.isChecked()) {
                count=count+1;}

                if (count == 1) {


                    if (studentBox.isChecked()){
                        appUser2.put("ID", "Student");

                    }else if (assistentBox.isChecked()){
                        appUser2.put("ID", "Assistent");

                    }else if (adminBox.isChecked()) {
                        appUser2.put("ID", "Admin");
                    }




                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up " + username.getText().toString());
                    progressDialog.show();


                    appUser2.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(Interface_ADD.this, appUser2.getUsername() + ": is signed up",
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();


                            } else {

                                FancyToast.makeText(Interface_ADD.this, "Error",
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }else { FancyToast.makeText(Interface_ADD.this, "Password / Email Error",
                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();}
                break;

        }
    }
}