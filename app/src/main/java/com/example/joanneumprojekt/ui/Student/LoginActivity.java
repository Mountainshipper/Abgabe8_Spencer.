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


import com.example.joanneumprojekt.Assistent.ProfessorLogin;
import com.example.joanneumprojekt.R;
import com.example.joanneumprojekt.SignUP.Login_Interface;
import com.example.joanneumprojekt.SignUP.SignUp;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    public String Miau;
    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignUpLoginActivity, btnReturnInterface;

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

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("logging in ");
                    progressDialog.show();










                    ParseQuery<ParseObject> query = ParseQuery.getQuery("New_User");
                    query.whereEqualTo("email", edtLoginEmail.getText().toString());
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject user, ParseException e) {

                            if (e == null && edtLoginPassword.getText().toString().equals(user.getString("password"))) {
                                if (user.getString("ID").equals("Student")) {


                                    FancyToast.makeText(LoginActivity.this, user.getString("Username") + " is logged in successfully!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();



                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("temprary_User_Pikka");
// Retrieve the object by id
                                    query.getInBackground("gH548pGCmO", new GetCallback<ParseObject>() {
                                        public void done(ParseObject appUser2, ParseException e) {
                                            if (e == null) {
                                                String username = user.getString("Username");
                                                appUser2.put("username", username);

                                                String email = user.getString("email");
                                                appUser2.put("email", email);
                                                appUser2.put("ID", "Assistent");

                                                String project = user.getString("Projekt");
                                                appUser2.put("Projekt", project);

                                                String bachelore = user.getString("Bachelor");
                                                appUser2.put("Bachelor",bachelore);

                                                String master = user.getString("Master");
                                                appUser2.put("Master",master);
                                                appUser2.saveInBackground();



                                            } else {
                                                FancyToast.makeText(LoginActivity.this, "Temporary Login could not be generated. But you can continue", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                                // Failed

                                            }

                                        }
                                    });

                                    Intent INTERFACE_STUDENT = new Intent(LoginActivity.this, INTERFACE_STUDENT.class);
                                    FancyToast.makeText(LoginActivity.this,"Switching to STUDENT INTERFACE",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                                    startActivity(INTERFACE_STUDENT);

                                }else{
                                    FancyToast.makeText(LoginActivity.this, "This is the login for 'Students', please use the correct login. Thank you", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                }
                            } else {
                                FancyToast.makeText(LoginActivity.this, "Password wrong", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();
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