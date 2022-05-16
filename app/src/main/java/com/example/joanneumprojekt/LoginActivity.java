package com.example.joanneumprojekt;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignUpLoginActivity, btnReturnInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        setTitle("Student Log In");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        // For enter to accept as enter
        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    // IF YES open
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });

        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpLoginActivity = findViewById(R.id.btnSignUpLoginActivity);
        btnReturnInterface = findViewById(R.id.btnStudent_toInterface);


        btnLoginActivity.setOnClickListener(this);
        btnSignUpLoginActivity.setOnClickListener(this);
        btnReturnInterface.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginActivity:

                // Checks if values are empty
                if (edtLoginEmail.getText().toString().equals("") ||
                        edtLoginPassword.getText().toString().equals("")){

                    FancyToast.makeText(LoginActivity.this,"EMAIL and PASSWORD is required!",
                            FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                } else {


                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser User, ParseException e) {
                            if (User != null && e == null) {
                                String studentID = User.getString("ID");
                                if (studentID.equals("Student")){
                                FancyToast.makeText(LoginActivity.this, User.getUsername() + " is logged in successfully!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
        //SWITCH TO INTERFACE
                                    Intent INTERFACE_STUDENT = new Intent(LoginActivity.this, INTERFACE_STUDENT.class);
                                    FancyToast.makeText(LoginActivity.this,"Switching to STUDENT INTERFACE",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                                    startActivity(INTERFACE_STUDENT);
                                    System.exit(-1);

         //
                            } else {
                                    FancyToast.makeText(LoginActivity.this, User.getUsername() + "You are not registered as a Student", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                                }
                            }else {
                                FancyToast.makeText(LoginActivity.this, "Email or Password is wrong", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                        }
                    });
                }
                break;

            case R.id.btnSignUpLoginActivity:
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                FancyToast.makeText(LoginActivity.this,"Switching to SIGN UP",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                startActivity(intent);

                break;

            case R.id.btnStudent_toInterface:
                Intent intentInterface = new Intent(LoginActivity.this, Login_Interface.class);
                FancyToast.makeText(LoginActivity.this,"Switching to LOG IN INTERFACE",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                startActivity(intentInterface);


        }
    }
    // If tapped outside, keyboard goes away. Stackoverflow
        public void loginLayoutTapped (View view) {
        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}