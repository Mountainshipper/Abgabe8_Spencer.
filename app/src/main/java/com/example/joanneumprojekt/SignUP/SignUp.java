/**
 * Class for signing up a new user
 */

package com.example.joanneumprojekt.SignUP;


import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.joanneumprojekt.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtUsername, edtPassword;
    private Button btnSignUp, btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        setTitle("Sign Up");

        edtEmail = findViewById(R.id.edtEnterEmail);
        edtPassword = findViewById(R.id.edtEnterPassword);

        // For enter to accept as enter
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int key, KeyEvent keyEvent) {
                if (key == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    // IF YES open
                    onClick(btnSignUp);
                }
                return false;
            }
        });



        edtUsername = findViewById(R.id.edtUsername);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btnSignUp:
                    // Checks if values are empty
                    if (edtEmail.getText().toString().equals("") ||
                            edtUsername.getText().toString().equals("") ||
                            edtPassword.getText().toString().equals("")) {

                        FancyToast.makeText(SignUp.this, "EMAIL, USERNAME, PASSWORD is required!",
                                FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();

                    } else if (edtEmail.getText().toString().length() > 5 && edtEmail.getText().toString().contains(".com") && edtEmail.getText().toString().contains("@")) {
                        if (edtPassword.getText().toString().length() > 4) {


                            final ProgressDialog progressDialog = new ProgressDialog(this);
                            progressDialog.setMessage("Signing up ");
                            progressDialog.show();

                            // Configure Query
                            ParseObject new_User = new ParseObject("New_User");
// Store an object
                            new_User.put("email", edtEmail.getText().toString());
                            new_User.put("password", edtPassword.getText().toString());
                            new_User.put("Username", edtUsername.getText().toString());
                            new_User.put("ID", "Admin");
                            // Saving object


                            new_User.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        FancyToast.makeText(SignUp.this, edtUsername.getText().toString() + ": is signed up",
                                                FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                        Intent intent = new Intent(SignUp.this, Login.class);
                                        startActivity(intent);
                                    } else {
                                        FancyToast.makeText(SignUp.this, "Error",
                                                FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                                    }
                                    progressDialog.dismiss();
                                }

                            });


                        } else {
                            FancyToast.makeText(SignUp.this, "Password to short",
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    } else {
                        FancyToast.makeText(SignUp.this, "please use a valid email",
                                FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                    }
                    break;

                case R.id.btnLogIn:
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                    break;
            }
    }



    // If tapped outside, keyboard goes away. Stackoverflow
    public void rootLayoutTapped (View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}