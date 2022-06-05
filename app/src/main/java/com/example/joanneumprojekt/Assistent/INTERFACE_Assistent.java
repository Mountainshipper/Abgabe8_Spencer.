/**
 * Autor: Samuel Spencer
 * This is the interface for the assistant
 */

package com.example.joanneumprojekt.Assistent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joanneumprojekt.Admin.AdministratorLogin;
import com.example.joanneumprojekt.Current_Login;
import com.example.joanneumprojekt.R;
import com.example.joanneumprojekt.SignUP.Login_Interface;
import com.example.joanneumprojekt.ui.Student.LoginActivity;
import com.shashank.sony.fancytoastlib.FancyToast;

public class INTERFACE_Assistent extends AppCompatActivity implements View.OnClickListener {

    private Button assistantRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_assistent);

        setTitle("Assistant Interface");

        assistantRead = findViewById(R.id.Assigned_Students);



        assistantRead.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Assigned_Students:

                Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");

                Intent intent = new Intent(INTERFACE_Assistent.this, Read_assigned_Student.class).putExtra("current_user", current_user);;
                FancyToast.makeText(INTERFACE_Assistent.this, "Switching to Assistant - Interface", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                startActivity(intent);
                break;


        }
    }
}