/**
 * Autor: Samuel Spencer
 * This is the login inteface
 */


package com.example.joanneumprojekt.SignUP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.joanneumprojekt.Admin.AdministratorLogin;
import com.example.joanneumprojekt.Assistent.ProfessorLogin;
import com.example.joanneumprojekt.R;
import com.example.joanneumprojekt.ui.Student.LoginActivity;
import com.parse.ParseUser;

public class Login_Interface extends AppCompatActivity implements View.OnClickListener {


    private Button btnStudent, btnAdministrator, btnProfessor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_interface);


        setTitle("Interface Login");


        btnStudent = findViewById(R.id.btnStudentInt);
        btnAdministrator = findViewById(R.id.btnAdministratorInt);
        btnProfessor = findViewById(R.id.btnProfessorInt);

        btnStudent.setOnClickListener(this);
        btnAdministrator.setOnClickListener(this);
        btnProfessor.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStudentInt:

                Intent intent = new Intent(Login_Interface.this, LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.btnAdministratorInt:
                Intent intentAdmin = new Intent(Login_Interface.this, AdministratorLogin.class);
                startActivity(intentAdmin);
                break;

            case R.id.btnProfessorInt:
                Intent intentProf = new Intent(Login_Interface.this, ProfessorLogin.class);
                startActivity(intentProf);
                break;
        }
    }

}