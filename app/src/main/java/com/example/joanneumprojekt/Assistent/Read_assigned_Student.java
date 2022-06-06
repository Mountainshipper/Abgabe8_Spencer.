/**
 * Autor: Samuel Spencer
 * This is the code where the assistant can see his assigned students, and where he can set the amount of students!
 * 06.06.2022
 */


package com.example.joanneumprojekt.Assistent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Read_assigned_Student extends AppCompatActivity implements View.OnClickListener {
    private Button btn_GetStudent, btn_GetSlots, btn_setSlots;
    private TextView txt_ASSISTENT_Student, txt_ASSISTENT_Slots, txt_Upload_SLOTS;
    String txt_Students;
    String txt_Slots;
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_assigned_student);
        setTitle("Assistant Slots");


        btn_setSlots = findViewById(R.id.btn_Upload_Slots);
        btn_GetStudent = findViewById(R.id.btn_Assistent_getStudent);
        btn_GetSlots = findViewById(R.id.btn_Assistent_getSlots);
        txt_ASSISTENT_Student = findViewById(R.id.txt_Assistent_Student);
        txt_ASSISTENT_Slots = findViewById(R.id.txtSetSlots);
        txt_Upload_SLOTS = findViewById(R.id.ChooseSlots);



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
                txt_Students = "";
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
                                            txt_Students = txt_Students + "--------------\n" + parseObject.get("Work") + "\n";
                                            txt_ASSISTENT_Student.setText(txt_Students);
                                            ++count_Work;
                                        } else {
                                            txt_Students = txt_Students + parseObject.get("Work") + "\n";
                                            txt_ASSISTENT_Student.setText(txt_Students);
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

                txt_Slots = "";
                txt_Upload_SLOTS.setText("");

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
                                            txt_Slots = txt_Slots + "--------------\n" + parseObject.get("Slots") + "\n";
                                            txt_ASSISTENT_Slots.setText(txt_Slots);
                                            ++count_Work;
                                        } else {
                                            txt_Slots = txt_Slots + parseObject.get("Slots") + "\n";
                                            txt_ASSISTENT_Slots.setText(txt_Slots);
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

try {


    String g = txt_Upload_SLOTS.getText().toString();
    int gg = Integer.parseInt(g);

                                if (gg < 11 && gg >= 0) {


                                FancyToast.makeText(Read_assigned_Student.this, "" +gg, FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                                object.put("Slots", txt_Upload_SLOTS.getText().toString());
                                    FancyToast.makeText(Read_assigned_Student.this, "Number of slots has been changed", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                    object.saveInBackground();

                                    Intent INTERFACE_Assistant = new Intent(Read_assigned_Student.this, INTERFACE_Assistent.class).putExtra("current_user", current_user);;
                                    FancyToast.makeText(Read_assigned_Student.this,"Switching to Assistant Interface",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                                    startActivity(INTERFACE_Assistant);


                                }else {
                                    FancyToast.makeText(Read_assigned_Student.this, "Minimum - maximum slot count is 0 - 10", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                                }
}catch (Exception d){
    FancyToast.makeText(Read_assigned_Student.this,"Not a Integer",FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();

}
                            }else{
                                FancyToast.makeText(Read_assigned_Student.this, "Something went wrong Sorry :(  (Cat Slot)", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();
                        }
                    });



        }
    }
}