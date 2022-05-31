package com.example.joanneumprojekt.ui.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joanneumprojekt.Current_Login;
import com.example.joanneumprojekt.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class Master extends AppCompatActivity implements View.OnClickListener{

    private Button getbtnTitle, getbtnProfessor, saveAll;
    private TextView txtTitle, txtProfessor, txtChooseWork, txtChooseProfessor;
    String txt_Work = "";
    String txt_Professor = "";
    String User = "";
    String user_temp = "";
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        setTitle("Master");

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
                txt_Work = "";
                txtTitle.setText("");

                ParseQuery<ParseObject> queryAllWork = ParseQuery.getQuery("All_Works");
                queryAllWork.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        int count_Work = 0;

                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject parseObject : objects) {
                                    if (parseObject.get("Function").equals("Master") && (parseObject.get("User").equals("open"))) {
                                        n++;


                                        if (count_Work == 0) {
                                            txt_Work = txt_Work + "--------------\n" + parseObject.get("Title") + "\n";
                                            txtTitle.setText(txt_Work);
                                            ++count_Work;
                                        } else {
                                            txt_Work = txt_Work + parseObject.get("Title") + "\n";
                                            txtTitle.setText(txt_Work);
                                        }


                                    }
                                }
                            }
                            if (n == 0) {
                                FancyToast.makeText(Master.this, "There are no open master projects", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            }
                        }
                    }
                });
                break;

            case R.id.btn_GetProfessor:
                txt_Professor = "";
                txtProfessor.setText("");

                ParseQuery<ParseObject> queryAllProfessor = ParseQuery.getQuery("New_User");
                queryAllProfessor.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        int count_Assistent = 0;
                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject parseObject : objects) {

                                    if (parseObject.get("ID").equals("Assistent")) {
                                        if (parseObject.get("Slots").equals("0")) {
                                        }else {


                                            if (count_Assistent == 0){
                                                txt_Professor = txt_Professor + "--------------\n"+parseObject.get("Username") + ". \nAvailable slots" + parseObject.get("Slots") + "\n\n";
                                                txtProfessor.setText(txt_Professor);
                                                ++count_Assistent;
                                            }else{
                                                txt_Professor = txt_Professor + parseObject.get("Username") + ". \nAvailable slots" + parseObject.get("Slots") + "\n\n";
                                                txtProfessor.setText(txt_Professor);
                                            }

                                        }

                                    } else {

                                    }
                                }
                            }
                        }
                    }
                });

                break;


            case R.id.btn_Upload_ALL:

// local
                Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");
                if (current_user.getBachelorOB().equals("Yes")  ) {
                    if( counter <= 0 && current_user.getMasterOB().equals("Nein")){


                        final ProgressDialog progressDialog = new ProgressDialog(this);
                        progressDialog.setMessage("Saving");
                        progressDialog.show();


                        if (txtChooseWork.getText().toString().length() > 3 && txtChooseProfessor.toString().length() > 3) {



                            //User

                            ParseQuery<ParseObject> query2 = ParseQuery.getQuery("New_User");
                            query2.whereEqualTo("Username", txtChooseProfessor.getText().toString());


                            query2.getFirstInBackground(new GetCallback<ParseObject>() {
                                public void done(ParseObject object, ParseException e) {
                                    if (e == null) {


                                        if (object.getString("Slots").equals("0")) {
                                            FancyToast.makeText(Master.this, "No more Slots", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                                        } else {
                                            String h = "";
                                            int g = Integer.valueOf(object.getString("Slots"));

                                            g = g - 1;
                                            FancyToast.makeText(Master.this, "Professor has been booked" , FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                            h = Integer.toString(g);
                                            object.put("Slots", h);


                                            if (object.getString("Work").isEmpty()) {
                                                user_temp = ";" + current_user.getEmailOB();
                                            } else {
                                                user_temp = object.getString("Work");
                                            }

                                            user_temp = user_temp.concat(User + "; ");
                                            if (h.equals("0")){
                                                object.put("user", "taken");
                                            }

                                            object.put("Work", user_temp);

                                            FancyToast.makeText(Master.this, "All Good", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                            counter++;
                                            object.saveInBackground();
//
                                        }
                                    } else {
                                        FancyToast.makeText(Master.this, "There are no more master projects", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                    }
                                    progressDialog.dismiss();
                                }
                            });

//Works

                            ParseQuery<ParseObject> query = ParseQuery.getQuery("All_Works");
                            query.whereEqualTo("Title", txtChooseWork.getText().toString());

                            query.getFirstInBackground(new GetCallback<ParseObject>() {
                                public void done(ParseObject object, ParseException e) {
                                    if (e == null) {

                                        if (object.getString("User").equals("taken")){
                                            FancyToast.makeText(Master.this, "Work has already been booked", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                                        }else {
                                            object.put("User", "taken");
                                            FancyToast.makeText(Master.this, "Work has been booked", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                            object.saveInBackground();
                                        }
                                    } else {
                                        FancyToast.makeText(Master.this, "Work could not be found. ERROR", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                                    }

                                }
                            });





                        } else {
                            FancyToast.makeText(Master.this, "Please fill out the 'TextViews'. Not just one. Thanks :)", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                            progressDialog.dismiss();
                            break;
                        }


//Section to add to Student (No duplicate)


                        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("New_User");
                        query2.whereEqualTo("email", current_user.getEmailOB());


                        query2.getFirstInBackground(new GetCallback<ParseObject>() {
                            public void done(ParseObject object, ParseException e) {
                                if (e == null) {

                                    object.put("Master", "Yes");
                                    object.saveInBackground();
                                }
                            }
                        });
                    } else {
                        FancyToast.makeText(Master.this, "you cannot have multiple master projects ", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                    }
                } else {
                    FancyToast.makeText(Master.this, "Please finish your bachelor project first. THX ^^", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                }
        }
    }
}