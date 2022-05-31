package com.example.joanneumprojekt.ui.Student;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joanneumprojekt.Current_Login;
import com.example.joanneumprojekt.R;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class INTERFACE_STUDENT extends AppCompatActivity implements View.OnClickListener{
    private Button btnProject, btnBachelor, btnMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_student);

        setTitle("Interface Login");


        btnProject = findViewById(R.id.Assigned_Students);
        btnBachelor = findViewById(R.id.btnBachelor);
        btnMaster = findViewById(R.id.btnMaster);

        btnProject.setOnClickListener(this);
        btnBachelor.setOnClickListener(this);
        btnMaster.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Assigned_Students:
                Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");



                Intent intent = new Intent(INTERFACE_STUDENT.this, Project.class).putExtra("current_user", current_user);
                FancyToast.makeText(INTERFACE_STUDENT.this, "Switching to projekt", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                startActivity(intent);
                break;

            case R.id.btnBachelor:
                Current_Login current_user2 = (Current_Login) getIntent().getSerializableExtra("current_user");
                Intent intentAdmin = new Intent(INTERFACE_STUDENT.this, Bachelor.class).putExtra("current_user", current_user2);
                FancyToast.makeText(INTERFACE_STUDENT.this, "Switching to Bachelor", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                startActivity(intentAdmin);
                break;

            case R.id.btnMaster:
                Current_Login current_user3 = (Current_Login) getIntent().getSerializableExtra("current_user");
                Intent intentProf = new Intent(INTERFACE_STUDENT.this, Master.class).putExtra("current_user", current_user3);
                FancyToast.makeText(INTERFACE_STUDENT.this, "Switching to Master", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                startActivity(intentProf);
                break;
        }
    }
}
