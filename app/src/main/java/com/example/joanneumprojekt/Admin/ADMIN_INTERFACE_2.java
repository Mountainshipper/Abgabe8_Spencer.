package com.example.joanneumprojekt.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joanneumprojekt.Assistent.ProfessorLogin;
import com.example.joanneumprojekt.R;
import com.shashank.sony.fancytoastlib.FancyToast;

public class ADMIN_INTERFACE_2 extends AppCompatActivity implements View.OnClickListener{

    private Button btn_User_delete, btn_Work_Delete, Interface3, Interface2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_interface2);




        setTitle("Admin Interface 1");


        btn_User_delete = findViewById(R.id.btn_Delete_User);
        btn_Work_Delete = findViewById(R.id.btn_Delete_Work);
        Interface3 = findViewById(R.id.Interface3);
        Interface2 = findViewById(R.id.Interface2);

        btn_User_delete.setOnClickListener(this);
        btn_Work_Delete.setOnClickListener(this);
        Interface3.setOnClickListener(this);
        Interface2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Delete_User:

                Intent intent = new Intent(ADMIN_INTERFACE_2.this, Admin_Delete_USER.class);
                FancyToast.makeText(ADMIN_INTERFACE_2.this, "Switching to Student Log In", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                startActivity(intent);
                break;

            case R.id.btn_Delete_Work:
                Intent intentAdmin = new Intent(ADMIN_INTERFACE_2.this, Interface_Work_ADD.class);
                FancyToast.makeText(ADMIN_INTERFACE_2.this, "Switching to Administrator Log In", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                startActivity(intentAdmin);
                break;

            case R.id.Interface3:
                Intent Interface3 = new Intent(ADMIN_INTERFACE_2.this, ProfessorLogin.class);
                FancyToast.makeText(ADMIN_INTERFACE_2.this, "Switching to 'Admin Interface 3'", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                startActivity(Interface3);
                break;

            case R.id.Interface2:
                Intent Interface1 = new Intent(ADMIN_INTERFACE_2.this, ADMIN_INTERFACE.class);
                FancyToast.makeText(ADMIN_INTERFACE_2.this, "Switching to 'Admin Interface'", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                startActivity(Interface1);
                break;
        }
    }
}