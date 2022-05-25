package com.example.joanneumprojekt.ui.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joanneumprojekt.Admin.AdministratorLogin;
import com.example.joanneumprojekt.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class Project extends AppCompatActivity implements View.OnClickListener {

    private Button getbtnTitle, getbtnProfessor, saveAll;
    private TextView txtTitle, txtProfessor, txtChooseWork, txtChooseProfessor;
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
        txtChooseWork = findViewById(R.id.txt_Choose_Work);
        txtChooseProfessor = findViewById(R.id.txt_Choose_Professor);
        saveAll = findViewById(R.id.btn_Upload_ALL);


        saveAll.setOnClickListener(this);
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
                                    if (parseObject.get("Function").equals("Project") && (parseObject.get("User").equals("open"))) {
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
                ParseQuery<ParseObject> queryAllProfessor = ParseQuery.getQuery("New_User");
                queryAllProfessor.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if (e == null) {
                            if (objects.size() > 0 ) {
                                for (ParseObject parseObject : objects) {

                                    if (parseObject.get("ID").equals("Assistent")) {
                                        if (parseObject.get("Slots").equals("0")) {
                                            FancyToast.makeText(Project.this, "    " +parseObject.get("ID") ,FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();

//                                             if no spots left
                                        }else{

                                            txt_Professor = txt_Professor + parseObject.get("Username") + ".        Available slots" + parseObject.get("Slots") +"\n";
                                        txtProfessor.setText(txt_Professor);
                                        }

                                    }else{
                                        FancyToast.makeText(Project.this,parseObject.get("Slots")+ "  !!  " +parseObject.get("ID") ,FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();

                                    }
                                }
                            }
                        }
                    }
                });

                break;

            case R.id.btn_Upload_ALL:
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Saving");
                progressDialog.show();
//Works

                if (txtChooseWork.getText().toString().length() > 3 && txtChooseProfessor.toString().length() > 3) {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("All_Works");
                    query.whereEqualTo("Title", txtChooseWork.getText().toString());

                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {
                                object.put("User", "taken");
                                FancyToast.makeText(Project.this, "All Good", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                object.saveInBackground();
                            } else {
                                FancyToast.makeText(Project.this, "There are no available projects", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();
                        }
                    });

   //Assistent

                    ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Assistent");
                    query2.whereEqualTo("username", txtChooseProfessor.getText().toString());


                    query2.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {


                                if (object.getString("Slots").equals("0")) {
                                    FancyToast.makeText(Project.this, "No more Slots", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();


                                }else {
                                    int g = Integer.valueOf(object.getString("Slots"));

                                    g = g - 1;
                                    FancyToast.makeText(Project.this, "" + g, FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                    String h = Integer.toString(g);
                                    object.put("Slots", h);


                                    String user_temp = object.getString("users");
                                    user_temp = user_temp + txtChooseProfessor.toString() + "; ";
                                    object.put("user", "taken");

                                    FancyToast.makeText(Project.this, "All Good", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                    object.saveInBackground();
//
                                }
                            } else {
                                FancyToast.makeText(Project.this, "There are no available projects", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

                }else{
                    FancyToast.makeText(Project.this, "Please fill out the 'TextViews'. Not just one. Thanks :)", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                }






        }
    }


}