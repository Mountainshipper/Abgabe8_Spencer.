/**
 * Autor: Samuel Spencer
 * This is the code for the student interface.
 * 06.06.2022
 */


package com.example.joanneumprojekt.ui.Student;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.joanneumprojekt.Current_Login;
import com.example.joanneumprojekt.R;
import com.parse.ParseUser;


public class INTERFACE_STUDENT extends AppCompatActivity implements View.OnClickListener{
    private Button btnProject, btnBachelor, btnMaster, read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_student);

        setTitle("Student Interface");


        btnProject = findViewById(R.id.Assigned_Students);
        btnBachelor = findViewById(R.id.changeSlot);
        btnMaster = findViewById(R.id.btnMaster);
        read = findViewById(R.id.Read_Button);

        read.setOnClickListener(this);
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
                startActivity(intent);
                break;

            case R.id.changeSlot:
                Current_Login current_user2 = (Current_Login) getIntent().getSerializableExtra("current_user");
                Intent intentAdmin = new Intent(INTERFACE_STUDENT.this, Bachelor.class).putExtra("current_user", current_user2);
                startActivity(intentAdmin);
                break;

            case R.id.btnMaster:
                Current_Login current_user3 = (Current_Login) getIntent().getSerializableExtra("current_user");
                Intent intentProf = new Intent(INTERFACE_STUDENT.this, Master.class).putExtra("current_user", current_user3);
                startActivity(intentProf);
                break;

            case R.id.Read_Button:
                Current_Login current_user4 = (Current_Login) getIntent().getSerializableExtra("current_user");
                Intent read = new Intent(INTERFACE_STUDENT.this, Read_Student.class).putExtra("current_user", current_user4);
                startActivity(read);
                break;
        }
    }
}
