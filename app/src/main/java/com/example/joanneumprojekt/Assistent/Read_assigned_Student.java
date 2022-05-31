package com.example.joanneumprojekt.Assistent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joanneumprojekt.Current_Login;
import com.example.joanneumprojekt.R;
import com.example.joanneumprojekt.ui.Student.Bachelor;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class Read_assigned_Student extends AppCompatActivity implements View.OnClickListener {
    private Button btn_GetStudent, btn_GetSlots, btn_setSlots;
    private TextView txt_ASSISTENT_Student, txt_ASSISTENT_Slots;
    String txt_Work;
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_assigned_student);


        btn_setSlots = findViewById(R.id.btn_Upload_Slots);
        btn_GetStudent = findViewById(R.id.btn_Assistent_getStudent);
        btn_GetSlots = findViewById(R.id.btn_Assistent_getSlots);
        txt_ASSISTENT_Student = findViewById(R.id.txt_Assistent_Student);
        txt_ASSISTENT_Slots = findViewById(R.id.txtSetSlots);


        btn_setSlots.setOnClickListener(this);
        btn_GetStudent.setOnClickListener(this);
        btn_GetSlots.setOnClickListener(this);
        txt_ASSISTENT_Student.setOnClickListener(this);
        txt_ASSISTENT_Slots.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.btn_Assistent_getStudent:
                txt_Work = "";
                txt_ASSISTENT_Student.setText("");

                ParseQuery<ParseObject> querySetStudent = ParseQuery.getQuery("New_User");
                querySetStudent.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        int count_Work = 0;
                        Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");
                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject parseObject : objects) {


                                    if (parseObject.get("email").equals(current_user.getEmailOB().toString())) {
                                        n++;


                                        if (count_Work == 0) {
                                            txt_Work = txt_Work + "--------------\n" + parseObject.get("Work") + "\n";
                                            txt_ASSISTENT_Student.setText(txt_Work);
                                            ++count_Work;
                                        } else {
                                            txt_Work = txt_Work + parseObject.get("Work") + "\n";
                                            txt_ASSISTENT_Student.setText(txt_Work);
                                        }


                                    }
                                }
                            }
                            if (n == 0) {
                                FancyToast.makeText(Read_assigned_Student.this, "There are no open bachelor projects", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            }
                        }
                    }
                });
                break;

            case R.id.btn_Assistent_getSlots:

                txt_Work = "";
                txt_ASSISTENT_Student.setText("");

                ParseQuery<ParseObject> queryReadSlots = ParseQuery.getQuery("New_User");
                queryReadSlots.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        int count_Work = 0;
                        Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");
                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject parseObject : objects) {


                                    if (parseObject.get("email").equals(current_user.getEmailOB().toString())) {
                                        n++;


                                        if (count_Work == 0) {
                                            txt_Work = txt_Work + "--------------\n" + parseObject.get("Slots") + "\n";
                                            txt_ASSISTENT_Slots.setText(txt_Work);
                                            ++count_Work;
                                        } else {
                                            txt_Work = txt_Work + parseObject.get("Slots") + "\n";
                                            txt_ASSISTENT_Slots.setText(txt_Work);
                                        }


                                    }
                                }
                            }
                            if (n == 0) {
                                FancyToast.makeText(Read_assigned_Student.this, "Slots is not set. This can cause an Error", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            }
                        }
                    }
                });
                break;


            case R.id.btn_Upload_Slots:

                Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");


                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Saving");
                progressDialog.show();



                    //User

                    ParseQuery<ParseObject> query2 = ParseQuery.getQuery("New_User");
                    query2.whereEqualTo("email", current_user.getEmailOB());


                    query2.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {


                                object.put("Work", "h");
                            }
                        }
                    });



        }
    }
}