/**
 * Class for User guidance screen
 */


package com.example.joanneumprojekt.SignUP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joanneumprojekt.Pictures.GetPictures;
import com.example.joanneumprojekt.Pictures.Show_Pictures;
import com.example.joanneumprojekt.R;
import com.parse.ParseUser;

public class Login_Interface extends AppCompatActivity implements View.OnClickListener {


    private Button Login, SignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_interface);


        setTitle("Interface Login");



        Login = findViewById(R.id.Login);
        Login.setOnClickListener(this);

        SignUp = findViewById(R.id.SignUp);
        SignUp.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Login:
                Intent intentAdmin = new Intent(Login_Interface.this, com.example.joanneumprojekt.SignUP.Login.class);
                startActivity(intentAdmin);
                break;

            case R.id.SignUp:
                Intent SignUp = new Intent(Login_Interface.this, com.example.joanneumprojekt.SignUP.SignUp.class);
                startActivity(SignUp);
                break;
        }
    }

}