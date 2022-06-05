/**
 * Autor: Samuel Spencer
 * This is the second interface for the administrator
 */

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

    private Button btn_User_delete, btn_Change_user, Change_WORK, Interface2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_interface2);




        setTitle("Admin Interface 1");


        btn_User_delete = findViewById(R.id.btn_Delete_User);
        btn_Change_user = findViewById(R.id.btn_Change_user);
        Change_WORK = findViewById(R.id.Change_WORK);
        Interface2 = findViewById(R.id.Interface2);

        btn_User_delete.setOnClickListener(this);
        btn_Change_user.setOnClickListener(this);
        Change_WORK.setOnClickListener(this);
        Interface2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Delete_User:

                Intent intent = new Intent(ADMIN_INTERFACE_2.this, Admin_Delete_USER.class);
                startActivity(intent);
                break;

            case R.id.btn_Change_user:
                Intent intentAdmin = new Intent(ADMIN_INTERFACE_2.this, Change_DATA_1_User.class);
                startActivity(intentAdmin);
                break;

            case R.id.Change_WORK:
                Intent Interface3 = new Intent(ADMIN_INTERFACE_2.this, Change_DATA_WORK.class);
                startActivity(Interface3);
                break;

            case R.id.Interface2:
                Intent Interface1 = new Intent(ADMIN_INTERFACE_2.this, ADMIN_INTERFACE.class);
                startActivity(Interface1);
                break;
        }
    }
}