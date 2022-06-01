package com.example.joanneumprojekt.ui.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joanneumprojekt.Current_Login;
import com.example.joanneumprojekt.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class Read_Student extends AppCompatActivity implements View.OnClickListener {

    private Button Button;
    private TextView setText;
    String txt_Work = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_student);


        setText = findViewById(R.id.Display_TXT);
        Button = findViewById(R.id.GetTXT);
        Button.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.GetTXT:
                txt_Work = "";
                setText.setText("");


                Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");


                if (current_user.getProjektOB().equals("Nein")) {
                    FancyToast.makeText(Read_Student.this, "No available works", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                } else {
                    txt_Work = txt_Work + "You currently have a Project" + "\n\n\n\n" + "more features coming soon!";
                }

                if (current_user.getBachelorOB().equals("Nein")) {
                } else {
                    txt_Work = "You have a Projekt, as well as a Bachelor." + "\n\n\n\n" + "more features coming soon!";
                }

                if (current_user.getMasterOB().equals("Nein")) {
                } else {
                    txt_Work = "You have a Projekt, as well as a Bachelor and a Master. Impressive." + "\n\n\n\n" + "more features coming soon!";
                }
                setText.setText(txt_Work);


        }
    }


}
