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

                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser PrUser, ParseException e) {
                            if (PrUser != null && e == null) {
                                String studentID = PrUser.getString("ID");
                                if (studentID.equals("Assistent")){
                                    FancyToast.makeText(ProfessorLogin.this, PrUser.getUsername() + " is logged in successfully!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();


                                    //Temporary User New Database
                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("temprary_User_Pikka");
// Retrieve the object by id and overwrite it
                                    query.getInBackground("gH548pGCmO", new GetCallback<ParseObject>() {
                                        public void done(ParseObject appUser2, ParseException e) {
                                            if (e == null) {
                                                String username = PrUser.getString("username");
                                                appUser2.put("username", username);

                                                String email = PrUser.getString("email");
                                                appUser2.put("email", email);
                                                appUser2.put("ID", "Assistent");
                                                appUser2.put("Projekt", "");
                                                appUser2.put("Bachelore","");
                                                appUser2.put("Master", "");

                                                appUser2.saveInBackground();

                                            } else {
                                                FancyToast.makeText(ProfessorLogin.this, "temporary Login could not be generated", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                            }
                                        }
                                    });
//temporary Database en
                                } else {

                                    FancyToast.makeText(ProfessorLogin.this, PrUser.getUsername() + "Youre 'ACCOUNT' id linked with an other 'CATEGORY'.", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                                }
                            }else {

                                FancyToast.makeText(ProfessorLogin.this, "Email or Password is wrong", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
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

