package com.example.joanneumprojekt.ui.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;



import com.example.joanneumprojekt.Current_Login;
import com.example.joanneumprojekt.R;
import com.example.joanneumprojekt.SignUP.Login_Interface;
import com.example.joanneumprojekt.SignUP.SignUp;
import com.parse.GetCallback;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import com.shashank.sony.fancytoastlib.FancyToast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{



    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignUpLoginActivity, btnReturnInterface;
    public Current_Login current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


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


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnLoginActivity:
                login();
                break;

            case R.id.btnSignUpLoginActivity:
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
                break;

            case R.id.btnStudent_toInterface:
                Intent intentInterface = new Intent(LoginActivity.this, Login_Interface.class);
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




    public void login () {

        // Checks if values are empty
        if (edtLoginEmail.getText().toString().equals("") ||
                edtLoginPassword.getText().toString().equals("")){

            FancyToast.makeText(LoginActivity.this,"EMAIL and PASSWORD is required!",
                    FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
        } else {



            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("logging in ");
            progressDialog.show();

            ParseQuery<ParseObject> query = ParseQuery.getQuery("New_User");
            query.whereEqualTo("email", edtLoginEmail.getText().toString());
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject user, ParseException e) {

                    if (e == null && edtLoginPassword.getText().toString().equals(user.getString("password"))) {
                        if (user.getString("ID").equals("Student")) {
                            progressDialog.dismiss();
                            FancyToast.makeText(LoginActivity.this, user.getString("Username") + " is logged in successfully!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();


                            current_user = new Current_Login(user.getString("Master"), user.getString("Bachelor"), user.getString("Projekt"), user.getString("password"),user.getString("email").toString());


                            Intent INTERFACE_STUDENT = new Intent(LoginActivity.this, INTERFACE_STUDENT.class).putExtra("current_user", current_user);
                            startActivity(INTERFACE_STUDENT);

                        }else{
                            FancyToast.makeText(LoginActivity.this, "This is the login for 'Students', please use the correct login. Thank you", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                        }
                    } else {
                        FancyToast.makeText(LoginActivity.this, "Password / Email wrong", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                    }
                    progressDialog.dismiss();
                }
            });
        }
    }
}