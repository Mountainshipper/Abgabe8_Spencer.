package com.example.joanneumprojekt.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joanneumprojekt.Assistent.ProfessorLogin;
import com.example.joanneumprojekt.R;
import com.example.joanneumprojekt.ui.Student.LoginActivity;
import com.shashank.sony.fancytoastlib.FancyToast;

public class ADMIN_INTERFACE extends AppCompatActivity implements View.OnClickListener{

    private Button btn_Add, btn_Work, btnMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_interface);


        setTitle("Interface Login");


        btn_Add = findViewById(R.id.interface_Add_Student);
        btn_Work = findViewById(R.id.interface_work);
        btnMaster = findViewById(R.id.interface_admin_Master);

        btn_Add.setOnClickListener(this);
        btn_Work.setOnClickListener(this);
        btnMaster.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.interface_Add_Student:

        Intent intent = new Intent(ADMIN_INTERFACE.this, Interface_ADD.class);
        FancyToast.makeText(ADMIN_INTERFACE.this, "Switching to Student Log In", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
        startActivity(intent);
        break;

            case R.id.interface_work:
        Intent intentAdmin = new Intent(ADMIN_INTERFACE.this, Interface_Work_ADD.class);
        FancyToast.makeText(ADMIN_INTERFACE.this, "Switching to Administrator Log In", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
        startActivity(intentAdmin);
        break;

            case R.id.btnMaster:
        Intent intentProf = new Intent(ADMIN_INTERFACE.this, ProfessorLogin.class);
        FancyToast.makeText(ADMIN_INTERFACE.this, "Switching to 'Professor' LOG IN", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
        startActivity(intentProf);
        break;
    }
    }
}