package com.example.joanneumprojekt.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joanneumprojekt.Assistent.ProfessorLogin;
import com.example.joanneumprojekt.R;
import com.shashank.sony.fancytoastlib.FancyToast;

public class ADMIN_INTERFACE extends AppCompatActivity implements View.OnClickListener{

    private Button btn_Add, btn_Work, btnMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_interface);


        setTitle("Admin Interface 1");


        btn_Add = findViewById(R.id.interface_Add_Student);
        btn_Work = findViewById(R.id.interface_work);
        btnMaster = findViewById(R.id.btn_Interface2_ADMIN);

        btn_Add.setOnClickListener(this);
        btn_Work.setOnClickListener(this);
        btnMaster.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.interface_Add_Student:

        Intent intentUser = new Intent(ADMIN_INTERFACE.this, Interface_ADD.class);
        startActivity(intentUser);
        break;

            case R.id.interface_work:
        Intent intentWork = new Intent(ADMIN_INTERFACE.this, Interface_Work_ADD.class);
        startActivity(intentWork);
        break;

            case R.id.btn_Interface2_ADMIN:
        Intent Interface2 = new Intent(ADMIN_INTERFACE.this, ADMIN_INTERFACE_2.class);
        startActivity(Interface2);
        break;
    }
    }
}