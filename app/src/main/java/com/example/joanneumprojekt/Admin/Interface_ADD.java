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

import com.example.joanneumprojekt.Assistent.ProfessorLogin;
import com.example.joanneumprojekt.R;
import com.example.joanneumprojekt.SignUP.Login_Interface;
import com.example.joanneumprojekt.SignUP.SignUp;
import com.example.joanneumprojekt.ui.Student.LoginActivity;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Calendar;

public class Interface_ADD extends AppCompatActivity implements View.OnClickListener {
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
                //


                // Checks if values are empty
                if (email.getText().toString().equals("") ||
                        username.getText().toString().equals("") ||
                        password.getText().toString().equals("")) {
//
                    FancyToast.makeText(Interface_ADD.this, "EMAIL, USERNAME, PASSWORD is required!",
                            FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
//

                } else if (email.getText().toString().length() > 5 && email.getText().toString().contains(".com") && email.getText().toString().contains("@")) {
                    if (password.getText().toString().length() > 4) {




//                        // Configure Query
                        ParseObject new_User = new ParseObject("New_User");
// Store an object
                        new_User.put("email", email.getText().toString());
                        new_User.put("Username", username.getText().toString());
                        new_User.put("password", password.getText().toString());


                        int count = 0;
                        if (assistentBox.isChecked()) {
                            count = count + 1;
                        }
                        if (adminBox.isChecked()) {
                            count = count + 1;
                        }
                        if (studentBox.isChecked()) {
                            count = count + 1;
                        }

                        if (count == 1) {
                            final ProgressDialog progressDialog = new ProgressDialog(this);
                            progressDialog.setMessage("Signing up ");
                            progressDialog.show();

                            if (studentBox.isChecked()) {
                                new_User.put("ID", "Student");

                            } else if (assistentBox.isChecked()) {
                                new_User.put("ID", "Assistent");


                            } else if (adminBox.isChecked()) {
                                new_User.put("ID", "Admin");

                            }


                            new_User.put("Bachelor", "Nein");
                            new_User.put("Projekt", "Nein");
                            new_User.put("Master", "Nein");
                            new_User.put("User", "open");

                            new_User.saveInBackground(new SaveCallback() {


                                @Override
                                public void done(ParseException e) {

                                    if (e == null) {
                                        FancyToast.makeText(Interface_ADD.this, username.getText().toString() + ": is signed up",
                                                FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                    } else {
                                        FancyToast.makeText(Interface_ADD.this, "Error",
                                                FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                                    }
                                    progressDialog.dismiss();
                                }

                            });

                        }else{
                            FancyToast.makeText(Interface_ADD.this, "One Checkbox should be checked!",
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


                        }

                        } else {
                                FancyToast.makeText(Interface_ADD.this, "Password to short",
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


                        }
                    } else {
                        FancyToast.makeText(Interface_ADD.this, "please use a valid email",
                                FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                    }
                    break;



        }
    }
}











