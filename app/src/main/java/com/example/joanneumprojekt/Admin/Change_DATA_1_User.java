package com.example.joanneumprojekt.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joanneumprojekt.Assistent.ProfessorLogin;
import com.example.joanneumprojekt.Current_Login;
import com.example.joanneumprojekt.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class Change_DATA_1_User extends AppCompatActivity implements View.OnClickListener {
    private Button btn_GET_ALL_INFO, btn_Upload_Info_ALL;
    private TextView txt_Get_ALL_INFO, txt_Admin_Choose_Username, txt_Admin_Choose_ID, txt_Admin_Choose_Password, txt_Admin_Choose_Email, txt_Admin_Choose_User, txt_Admin_Choose_Slots;
    String txt_All = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_data1_user);


        btn_GET_ALL_INFO = findViewById(R.id.btn_GET_ALL_INFO);
        btn_Upload_Info_ALL = findViewById(R.id.btn_Upload_Info_ALL);
        txt_Get_ALL_INFO = findViewById(R.id.txt_Get_ALL_INFO);
        txt_Admin_Choose_Username = findViewById(R.id.txt_Admin_Choose_Username);
        txt_Admin_Choose_ID = findViewById(R.id.txt_Admin_Choose_ID);
        txt_Admin_Choose_Password = findViewById(R.id.txt_Admin_Choose_Password);
        txt_Admin_Choose_Email = findViewById(R.id.txt_Admin_Choose_Email);
        txt_Admin_Choose_User = findViewById(R.id.txt_Admin_Choose_User);
        txt_Admin_Choose_Slots = findViewById(R.id.txt_Admin_Choose_Slots);


        btn_GET_ALL_INFO.setOnClickListener(this);
        btn_Upload_Info_ALL.setOnClickListener(this);


    }

    int n = 0;

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_GET_ALL_INFO:
                get_All_Data();
                break;


            case R.id.btn_Upload_Info_ALL:
                setUser();
                break;




// local

        }
    }


    public void get_All_Data() {

        txt_All = "";
        txt_Get_ALL_INFO.setText("");

        ParseQuery<ParseObject> queryAllProfessor = ParseQuery.getQuery("New_User");
        queryAllProfessor.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                int count_Assistent = 0;
                if (e == null) {
                    if (objects.size() > 0) {
                        for (ParseObject parseObject : objects) {
                            txt_All = txt_All + "--------------\nUsername: " + parseObject.get("Username") + ". \nID: " + parseObject.get("ID") + ". \nPassword:  " + parseObject.get("password") +
                                    ". \nEmail: " + parseObject.get("email")+ ". \nSlots: " + parseObject.get("Slots")  + "\n\n\n";
                            txt_Get_ALL_INFO.setText(txt_All);
                            ++count_Assistent;
                        }
                    }
                }
            }
        });
    }


    public void setUser() {
        if (txt_Admin_Choose_User.getText().toString().isEmpty()) {
            FancyToast.makeText(Change_DATA_1_User.this, "Please select a 'USER'", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

        } else {
            if (txt_Admin_Choose_Password.getText().toString().isEmpty() && txt_Admin_Choose_Email.getText().toString().isEmpty()) {
                set();
            }else if (txt_Admin_Choose_Password.getText().toString().length() > 4) {
                    if ((txt_Admin_Choose_Email.getText().toString().contains("@") || txt_Admin_Choose_Email.getText().toString().contains(".at") || txt_Admin_Choose_Email.getText().toString().contains(".com"))
                            && txt_Admin_Choose_Email.getText().toString().length() > 6) {
                        set();

                    } else {
                        FancyToast.makeText(Change_DATA_1_User.this, "Please wite a valid Email", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                    }
                } else {
                    FancyToast.makeText(Change_DATA_1_User.this, "Password to short", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                }


        }
    }

    public void set() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("UPDATING DATA ");
        progressDialog.show();

        Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("New_User");
        query2.whereEqualTo("Username", txt_Admin_Choose_User.getText().toString());


        query2.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    progressDialog.dismiss();

                    if (txt_Admin_Choose_Username.getText().toString().isEmpty()) {

                    } else {
                        object.put("Username", txt_Admin_Choose_Username.getText().toString());
                    }
                    if (txt_Admin_Choose_ID.getText().toString().isEmpty()) {

                    } else {
                        object.put("ID", txt_Admin_Choose_ID.getText().toString());
                    }
                    if (txt_Admin_Choose_Slots.getText().toString().isEmpty()) {

                    } else {
                        object.put("Slots", txt_Admin_Choose_Slots.getText().toString());
                    }
                    if (txt_Admin_Choose_Password.getText().toString().isEmpty()) {

                    } else {
                        object.put("password", txt_Admin_Choose_Password.getText().toString());
                    }
                    if (txt_Admin_Choose_Email.getText().toString().isEmpty()) {

                    } else {
                        object.put("email", txt_Admin_Choose_Email.getText().toString());
                    }





                    object.saveInBackground();
                    FancyToast.makeText(Change_DATA_1_User.this, "All Good", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                    Intent Interface3 = new Intent(Change_DATA_1_User.this, ADMIN_INTERFACE_2.class);
                    startActivity(Interface3);
//

                } else {
                    FancyToast.makeText(Change_DATA_1_User.this, "Professor could not be found. ERROR", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                }

            }
        });
    }
}