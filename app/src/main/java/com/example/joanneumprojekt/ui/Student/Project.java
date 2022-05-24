package com.example.joanneumprojekt.ui.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joanneumprojekt.Admin.AdministratorLogin;
import com.example.joanneumprojekt.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class Project extends AppCompatActivity implements View.OnClickListener {

    private Button getbtnTitle, getbtnProfessor;
    private TextView txtTitle, txtProfessor;
    String txt_Work = "";
    String txt_Professor = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        txtTitle = findViewById(R.id.txt_GetTitle);
        getbtnTitle = findViewById(R.id.btn_GetText);
        txtProfessor = findViewById(R.id.txt_GetTitle2);
        getbtnProfessor = findViewById(R.id.btn_GetProfessor);



        getbtnProfessor.setOnClickListener(this);
        getbtnTitle.setOnClickListener(this);
    }
int n = 0;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_GetText:
                ParseQuery<ParseObject> queryAllWork = ParseQuery.getQuery("All_Works");
                queryAllWork.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if (e == null) {
                            if (objects.size() > 0 ) {
                                for (ParseObject parseObject : objects) {
                                    if (parseObject.get("Function").equals("Project")) {
                                        n++;
                                        txt_Work = txt_Work + parseObject.get("Title") + "\n";
                                        txtTitle.setText(txt_Work);
                                    }
                                }
                            } if (n == 0){
                                FancyToast.makeText(Project.this,"There are no available projects",FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();

                            }
                        }
                    }
                });
                break;

            case R.id.btn_GetProfessor:
                ParseQuery<ParseObject> queryAllProfessor = ParseQuery.getQuery("User");
                queryAllProfessor.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if (e == null) {
                            if (objects.size() > 0 ) {
                                for (ParseObject parseObject : objects) {

                                    if (parseObject.get("ID").equals("Assistent")) {
//                                        if (parseObject.getInt("Slots_available") >0){


                                            txt_Professor = txt_Professor + parseObject.get("username") + "\n";
                                        txtProfessor.setText(txt_Professor);
//                                        }

                                    }
                                }
                            } if (n == 0){
                                FancyToast.makeText(Project.this,"There are no available projects",FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();

                            }
                        }
                    }
                });
                break;
        }
    }


}