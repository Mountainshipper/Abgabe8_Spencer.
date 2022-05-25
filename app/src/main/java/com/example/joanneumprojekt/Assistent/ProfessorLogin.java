package com.example.joanneumprojekt.Assistent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.joanneumprojekt.R;
import com.example.joanneumprojekt.SignUP.Login_Interface;
import com.example.joanneumprojekt.SignUP.SignUp;
import com.example.joanneumprojekt.ui.Student.INTERFACE_STUDENT;
import com.example.joanneumprojekt.ui.Student.LoginActivity;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class ProfessorLogin extends AppCompatActivity implements View.OnClickListener{

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignUpLoginActivity, btnReturnInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_login);

        setTitle("Professor Login");



        edtLoginEmail = findViewById(R.id.edtProfEmail);
        edtLoginPassword = findViewById(R.id.edtProfPassword);

        // For enter to accept as enter
        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int number, KeyEvent keyEvent) {
                if (number == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    // IF YES open
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });


        btnLoginActivity = findViewById(R.id.btnProfLogin);
        btnSignUpLoginActivity = findViewById(R.id.btnProfSignUp);
        btnReturnInterface = findViewById(R.id.btnProf_toInterface);


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
            case R.id.btnProfLogin:

                // Checks if values are empty
                if (edtLoginEmail.getText().toString().equals("") ||
                        edtLoginPassword.getText().toString().equals("")){

                    FancyToast.makeText(ProfessorLogin.this,"EMAIL and PASSWORD is required!",
                            FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                } else {

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up ");
                    progressDialog.show();





                    ParseQuery<ParseObject> query = ParseQuery.getQuery("New_User");
                    query.whereEqualTo("email", edtLoginEmail.getText().toString());
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject user, ParseException e) {

                            if (e == null && edtLoginPassword.getText().toString().equals(user.getString("password"))) {
                                if (user.getString("ID").equals("Assistent")) {


                                    FancyToast.makeText(ProfessorLogin.this, user.getString("Username") + " is logged in successfully!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();



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
                                                FancyToast.makeText(ProfessorLogin.this, "Temporary Login could not be generated. But you can continue", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                                // Failed

                                            }

                                        }
                                    });

                                    Intent INTERFACE_STUDENT = new Intent(ProfessorLogin.this, Login_Interface.class);
                                    FancyToast.makeText(ProfessorLogin.this,"Switching to STUDENT INTERFACE",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                                    startActivity(INTERFACE_STUDENT);

                                }else{
                                    FancyToast.makeText(ProfessorLogin.this, "his is the login for 'Assistents', please use the correct login. Thank you", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                }
                            } else {
                                FancyToast.makeText(ProfessorLogin.this, "Password wrong", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();
                        }
                    });

                }
                break;

            case R.id.btnProfSignUp:
                Intent intent = new Intent(ProfessorLogin.this, SignUp.class);
                FancyToast.makeText(ProfessorLogin.this,"Switching to SIGN UP",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                startActivity(intent);

                break;

            case R.id.btnProf_toInterface:
                Intent intentInterface = new Intent(ProfessorLogin.this, Login_Interface.class);
                FancyToast.makeText(ProfessorLogin.this,"Switching to LOGIN INTERFACE",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
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

