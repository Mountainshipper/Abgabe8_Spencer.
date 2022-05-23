package com.example.joanneumprojekt;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.parse.ParseObject;
import com.shashank.sony.fancytoastlib.FancyToast;


public class SignUp extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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