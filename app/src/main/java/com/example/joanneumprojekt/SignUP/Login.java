/**
 * Class for logging in an user
 */

package com.example.joanneumprojekt.SignUP;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.joanneumprojekt.After_Login.Main;
import com.example.joanneumprojekt.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;





public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignUpLoginActivity, btnReturnInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);



        edtLoginEmail = findViewById(R.id.edtAdminEmail);
        edtLoginPassword = findViewById(R.id.edtAdminPassword);

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


        btnLoginActivity = findViewById(R.id.btnAdminLogin);
        btnSignUpLoginActivity = findViewById(R.id.btnAdminSignUp);
        btnReturnInterface = findViewById(R.id.btnAdmin_toInterface);


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
            case R.id.btnAdminLogin:

                // Checks if values are empty
                if (edtLoginEmail.getText().toString().equals("") ||
                        edtLoginPassword.getText().toString().equals("")){

                    FancyToast.makeText(Login.this,"EMAIL and PASSWORD is required!",
                            FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                } else {


                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Logging in ");
                    progressDialog.show();


                    ParseQuery<ParseObject> query = ParseQuery.getQuery("New_User");
                    query.whereEqualTo("email", edtLoginEmail.getText().toString());
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject user, ParseException e) {


                            if (e == null && edtLoginPassword.getText().toString().equals(user.getString("password"))) {
                                if (user.getString("ID").equals("Admin")) {

                                    progressDialog.dismiss();
                                    FancyToast.makeText(Login.this, user.getString("Username") + " is logged in successfully!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                    Intent INTERFACE_STUDENT = new Intent(Login.this, Main.class);
                                    startActivity(INTERFACE_STUDENT);

                                }else{
                                    FancyToast.makeText(Login.this, "Please use the correct login. Thank you", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                                }
                            } else {
                                FancyToast.makeText(Login.this, "Password wrong", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();
                        }
                    });

                }
                break;

            case R.id.btnAdminSignUp:
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                break;

            case R.id.btnAdmin_toInterface:
                Intent intentInterface = new Intent(Login.this, Login_Interface.class);
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