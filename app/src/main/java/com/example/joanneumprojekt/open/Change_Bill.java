/**
 * Autor: Samuel Spencer
 * This is the code where a admin can change the data of a existing user
 * 06.06.2022
 */

package com.example.joanneumprojekt.open;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joanneumprojekt.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;
import java.util.List;

public class Change_Bill extends AppCompatActivity implements View.OnClickListener {
    private Button btn_GET_ALL_Work, btn_Upload_Work_ALL;
    private TextView txt_Get_ALL_WORK, txt_Admin_Choose_Work, txt_Admin_Choose_USER, txt_Admin_Choose_Title, txt_Admin_Choose_Function;
    String txt_All = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_bills);

        btn_GET_ALL_Work = findViewById(R.id.btn_GET_ALL_Work);
        btn_Upload_Work_ALL = findViewById(R.id.btn_Upload_Work_ALL);
        txt_Get_ALL_WORK = findViewById(R.id.txt_Get_ALL_WORK);
        txt_Admin_Choose_Work = findViewById(R.id.txt_Admin_Choose_Work);
        txt_Admin_Choose_USER = findViewById(R.id.txt_Admin_Choose_USER);
        txt_Admin_Choose_Title = findViewById(R.id.txt_Admin_Choose_Title);
        txt_Admin_Choose_Function = findViewById(R.id.txt_Admin_Choose_Function);


        btn_GET_ALL_Work.setOnClickListener(this);
        btn_Upload_Work_ALL.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_GET_ALL_Work:
                get_All_Data();
                break;


            case R.id.btn_Upload_Work_ALL:
                setUser();
                break;




// local

        }
    }


    public void get_All_Data() {

        txt_All = "";
        txt_Get_ALL_WORK.setText("");

        ParseQuery<ParseObject> queryAllProfessor = ParseQuery.getQuery("All_Works");
        queryAllProfessor.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                int count_Assistent = 0;
                if (e == null) {
                    if (objects.size() > 0) {
                        for (ParseObject parseObject : objects) {
                            txt_All = txt_All + "--------------\nTitle: " + parseObject.get("Title") + ". \nFunction: " + parseObject.get("Function") + ". \nAvailability:  " + parseObject.get("User") +
                                    ". \nDeadline: " + parseObject.get("Deadline") + ". \nExam Date: " + parseObject.get("Exam_Date") +  "\n\n\n";
                            txt_Get_ALL_WORK.setText(txt_All);
                            ++count_Assistent;
                        }
                    }
                }
            }
        });
    }


    public void setUser() {
        if (txt_Admin_Choose_Work.getText().toString().isEmpty()) {
            FancyToast.makeText(Change_Bill.this, "Please select a 'WORK'", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

        } else {

            set();
        }
    }

    public void set() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("UPDATING DATA ");
        progressDialog.show();

        Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("All_Works");
        query2.whereEqualTo("Title", txt_Admin_Choose_Work.getText().toString());


        query2.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    progressDialog.dismiss();


                    if (txt_Admin_Choose_USER.getText().toString().isEmpty()) {

                    } else {
                        object.put("User", txt_Admin_Choose_USER.getText().toString());
                    }

                    if (txt_Admin_Choose_Title.getText().toString().isEmpty()) {

                    } else {
                        object.put("Title", txt_Admin_Choose_Title.getText().toString());
                    }
                    if (txt_Admin_Choose_Function.getText().toString().isEmpty()) {

                    } else {
                        object.put("Function", txt_Admin_Choose_Function.getText().toString());
                    }





                    object.saveInBackground();
                    FancyToast.makeText(Change_Bill.this, "All Good", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

//

                } else {
                    FancyToast.makeText(Change_Bill.this, "Professor could not be found. ERROR", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                    progressDialog.dismiss();
                }

            }
        });
    }
}