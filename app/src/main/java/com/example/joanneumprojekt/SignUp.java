package com.example.joanneumprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtUsername, edtPassword;
    private Button btnSignUp, btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(" Student Sign Up");

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

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnSignUp:
                // Checks if values are empty
                if (edtEmail.getText().toString().equals("") ||
                        edtUsername.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("")){

                    FancyToast.makeText(SignUp.this,"EMAIL, USERNAME, PASSWORD is required!",
                            FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                } else {

                    final ParseUser appUser2 = new ParseUser();
                    appUser2.setEmail(edtEmail.getText().toString());
                    appUser2.setUsername(edtUsername.getText().toString());
                    appUser2.setPassword(edtPassword.getText().toString());
                    appUser2.put("ID", "Student");
                    appUser2.put("Projekt", "");
                    appUser2.put("Bachelore", "");
                    appUser2.put("Master", "");

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up " + edtUsername.getText().toString());
                    progressDialog.show();


                    appUser2.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this, appUser2.getUsername() + ": is signed up",
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            } else {

                                FancyToast.makeText(SignUp.this, "Error",
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                break;

            case R.id.btnLogIn:

//                Intent intent = new Intent(SignUp.this, INTERFACE_ADMINISTRATOR.class);
//                FancyToast.makeText(SignUp.this,"Switching to Log In Interface",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
//                startActivity(intent);

                Intent intent = new Intent(SignUp.this, Login_Interface.class);
                FancyToast.makeText(SignUp.this,"Switching to Log In Interface",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
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
































//    public void helloWorldTapped(View view){
//        int len = 8;
//
//        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
//                +"lmnopqrstuvwxyz!@#$%&";
//        Random rnd = new Random();
//        StringBuilder sb = new StringBuilder(len);
//        for (int i = 0; i < len; i++)
//            sb.append(chars.charAt(rnd.nextInt(chars.length())));
//
//
//        ParseObject userNew = new ParseObject("UserNew");
//        userNew.put("Password", sb.toString());
//        userNew.put("Username", "Mountainshipper");
//
//        userNew.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if ((e == null)){
//
//                    Toast.makeText(SignUp.this, "Password has been set", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//