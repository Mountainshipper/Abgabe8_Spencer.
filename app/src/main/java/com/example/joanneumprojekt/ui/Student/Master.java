/**
 * Autor: Samuel Spencer
 * This is the code for students to book a master project
 */


package com.example.joanneumprojekt.ui.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

public class Master extends AppCompatActivity implements View.OnClickListener {

    private Button getbtnTitle, getbtnProfessor, saveAll;
    private TextView txtTitle, txtProfessor, txtChooseWork, txtChooseProfessor;
    String Date = "";
    int test_work, test_user;



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
               getWorks();
                break;

            case R.id.btn_GetProfessor:
               get_Met_Professor();
                break;


            case R.id.btn_Upload_ALL:

// local
                Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");
                if (current_user.getBachelorOB().equals("Yes")) {


                    if (current_user.getMasterOB().equals("Nein")) {
                        if (test_work == 0 || test_user ==0) {


                                //User
                                if (test_work == 0) {
                                    set_work();
                                } else {
                                    FancyToast.makeText(Master.this, "You have already selected a master project", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                }

                                if (test_user == 0) {
                                    setUser();
                                } else {
                                    FancyToast.makeText(Master.this, "You have already selected a professor", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                }


                        } else {
                            FancyToast.makeText(Master.this, "You can not have more then one master project", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }



                    } else {
                        FancyToast.makeText(Master.this, "you cannot have multiple master projects ", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                    }
                } else {
                    FancyToast.makeText(Master.this, "Please finish your bachelor project first. THX ^^", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                }
        }
    }
    String txt_Work;
    public void getWorks(){

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

    }
    String txt_Professor;
    public void get_Met_Professor(){
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
                                } else {


                                    if (count_Assistent == 0) {
                                        txt_Professor = txt_Professor + "--------------\n" + parseObject.get("Username") + ". \nAvailable slots" + parseObject.get("Slots") + "\n\n";
                                        txtProfessor.setText(txt_Professor);
                                        ++count_Assistent;
                                    } else {
                                        txt_Professor = txt_Professor + parseObject.get("Username") + ". \nAvailable slots" + parseObject.get("Slots") + "\n\n";
                                        txtProfessor.setText(txt_Professor);
                                    }

                                }

                            }
                        }
                    }
                }
            }
        });

    }

    public void set_work(){
        Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("All_Works");
        query.whereEqualTo("Title", txtChooseWork.getText().toString());

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {


                    if (object.getString("User").equals("taken")) {
                        FancyToast.makeText(Master.this, "Work has already been booked", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                    } else {
                        object.put("User", "taken");
                        Date = object.getString("Exam_Date");

                        FancyToast.makeText(Master.this, "Work has been booked", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                        test_work = 1;
                        object.saveInBackground();




                        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("New_User");
                        query2.whereEqualTo("email", current_user.getEmailOB());


                        query2.getFirstInBackground(new GetCallback<ParseObject>() {
                            public void done(ParseObject object, ParseException e) {
                                if (e == null) {
                                    object.put("Exam_Date", "Open");
                                    if (Date.isEmpty()) {
                                    } else {
                                        object.put("Exam_Date", Date);
                                    }

                                    test_work = 1;
                                    object.put("Master", "Yes");
                                    object.put("Master_txt", txtChooseWork.getText().toString());
                                    object.saveInBackground();
                                    FancyToast.makeText(Master.this, "All Good", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                                }
                            }
                        });
                    }
                } else {
                    FancyToast.makeText(Master.this, "Work could not be found. ERROR", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                }

            }
        });
    }

    String user_temp = "";
    public void setUser(){
        Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");

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
                        FancyToast.makeText(Master.this, "Professor has been booked", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                        h = Integer.toString(g);
                        object.put("Slots", h);
                        test_user=1;

                        user_temp = object.getString("Work");
                        if (object.getString("Work").isEmpty()) {
                            user_temp = "" + current_user.getEmailOB();
                        } else {
                            user_temp = user_temp +"; " + current_user.getEmailOB();
                        }
                        object.put("Work", user_temp);






                        object.saveInBackground();

//
                    }
                } else {
                    FancyToast.makeText(Master.this, "Professor could not be found. ERROR", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                }

            }
        });
    }


    public void rootLayoutTapped (View view) {
        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}