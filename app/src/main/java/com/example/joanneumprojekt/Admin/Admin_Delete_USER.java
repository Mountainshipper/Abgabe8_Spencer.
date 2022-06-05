package com.example.joanneumprojekt.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joanneumprojekt.Current_Login;
import com.example.joanneumprojekt.R;
import com.example.joanneumprojekt.ui.Student.Project;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.IllegalFormatCodePointException;
import java.util.List;

public class Admin_Delete_USER extends AppCompatActivity implements View.OnClickListener {

    private Button getWorks, getUser, Delete, Delete_WORK;
    private TextView txt_Display_Work, txt_Display_User, txt_Write_Work, txt_Write_User;
    String txt_Work = "";
    String txt_Professor = "";
    String Project = "";
    String email = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_user);


        Delete_WORK = findViewById(R.id.btn_Upload_DELETE_WORK);
        getWorks = findViewById(R.id.btnGetAllWORKS);
        getUser = findViewById(R.id.btn_Get_ALLUSERS);
        Delete = findViewById(R.id.btn_Upload_DELETE);
        txt_Display_Work = findViewById(R.id.txt_Display_Work);
        txt_Display_User = findViewById(R.id.txt_Display_User);
        txt_Write_Work = findViewById(R.id.txt_Write_Work);
        txt_Write_User = findViewById(R.id.txt_Write_User);


        getWorks.setOnClickListener(this);
        getUser.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Delete_WORK.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnGetAllWORKS:
                txt_Work = "";
                txt_Display_Work.setText("");

                ParseQuery<ParseObject> queryAllWork = ParseQuery.getQuery("All_Works");
                queryAllWork.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        int count_Work = 0;

                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject parseObject : objects) {


                                    if (count_Work == 0) {
                                        txt_Work = txt_Work + "--------------\n" + parseObject.get("Title") + "\n";
                                        txt_Display_Work.setText(txt_Work);
                                        ++count_Work;
                                    } else {
                                        txt_Work = txt_Work + parseObject.get("Title") + "\n";
                                        txt_Display_Work.setText(txt_Work);
                                    }
                                }
                            }
                        }
                    }
                });
                break;

            case R.id.btn_Get_ALLUSERS:
                txt_Professor = "";
                txt_Display_User.setText("");

                ParseQuery<ParseObject> queryAllProfessor = ParseQuery.getQuery("New_User");
                queryAllProfessor.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        int count_Assistent = 0;
                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject parseObject : objects) {


                                    if (count_Assistent == 0) {
                                        txt_Professor = txt_Professor + "--------------\n" + parseObject.get("Username") + ". \nAvailable slots" + parseObject.get("Slots") + "\n\n";
                                        txt_Display_User.setText(txt_Professor);
                                        ++count_Assistent;

                                    } else {
                                        txt_Professor = txt_Professor + parseObject.get("Username") + ". \nAvailable slots" + parseObject.get("Slots") + "\n\n";
                                        txt_Display_User.setText(txt_Professor);
                                    }

                                }
                            }
                        }
                    }

                });

                break;


            case R.id.btn_Upload_DELETE:
                Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");




                //User

                if (txt_Write_User.getText().toString().isEmpty()) {
                    FancyToast.makeText(Admin_Delete_USER.this, "Text View cannot be empty", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                }else {


                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Deleting Data");
                    progressDialog.show();

                    ParseQuery<ParseObject> deleteUser = ParseQuery.getQuery("New_User");

                    deleteUser.whereEqualTo("Username", txt_Write_User.getText().toString());
                    deleteUser.findInBackground(new FindCallback<ParseObject>() {
                        //No error?
                        @Override
                        public void done(final List<ParseObject> user, ParseException e) {
                            if (e == null) {

                                //for redundant data
                                redundant_User();

                                user.get(0).deleteInBackground(new DeleteCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            FancyToast.makeText(Admin_Delete_USER.this, "completed", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                                        } else {
                                            FancyToast.makeText(Admin_Delete_USER.this, "Failed to delete User", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                                        }
                                    }
                                });
                            } else {
                                FancyToast.makeText(Admin_Delete_USER.this, "Something went wrong (Database)", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();
                            Intent Interface2 = new Intent(Admin_Delete_USER.this, ADMIN_INTERFACE_2.class);
                            startActivity(Interface2);
                        }
                    });
                }
                break;
//Works


            case R.id.btn_Upload_DELETE_WORK:

                if (txt_Write_Work.getText().toString().isEmpty()) {
                    FancyToast.makeText(Admin_Delete_USER.this, "Text View cannot be empty", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                }else{


                    ParseQuery<ParseObject> work_delete = ParseQuery.getQuery("All_Works");


                    work_delete.whereEqualTo("Title", txt_Write_Work.getText().toString());
                    work_delete.findInBackground(new FindCallback<ParseObject>() {
                        @Override

                        //No error?
                        public void done(final List<ParseObject> work, ParseException e) {
                            if (e == null) {

                                    if (work.size() > 0) {

                                        redundant_Work();

                                        work.get(0).deleteInBackground(new DeleteCallback() {
                                            @Override
                                            public void done(ParseException e) {
                                                if (e == null) {
                                                    FancyToast.makeText(Admin_Delete_USER.this, "completed", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                                                } else {
                                                    FancyToast.makeText(Admin_Delete_USER.this, "Failed to delete 'Work", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                                }
                                            }
                                        });
                                    } else {
                                        FancyToast.makeText(Admin_Delete_USER.this, "Not available", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                    }
                            } else {
                                FancyToast.makeText(Admin_Delete_USER.this, "Something went wrong (Database)", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                            }
                        }
                    });
            }break;
        }
    }


    // Horror fix redundant data
    String bachelor;
    String master;

    public void redundant_User() {
        ParseQuery<ParseObject> update = ParseQuery.getQuery("New_User");
        update.whereEqualTo("Username", txt_Write_User.getText().toString());
        update.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    email = parseObject.getString("email");
                    Project = parseObject.getString("Project_txt");
                    bachelor = parseObject.getString("Bachelor_txt");
                    master = parseObject.getString("Master_txt");

                } else {
                    FancyToast.makeText(Admin_Delete_USER.this, "Wrong1", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                }
            }
        });


        //FOR REDUNDANT DATA
        ParseQuery<ParseObject> work = ParseQuery.getQuery("All_Works");
        work.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    for (ParseObject object : objects) {


                        if (object.get("Title").equals(Project) || object.get("Title").equals(bachelor) || object.get("Title").equals(master)) {
                            object.put("User", "open");
                        }
                        object.saveInBackground();
                        redundant_Work();
                    }
                }
            }
        });
    }


    public void redundant_Work() {

        ParseQuery<ParseObject> User = ParseQuery.getQuery("New_User");
        User.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
//                    FancyToast.makeText(Admin_Delete_USER.this, "" +txt_Write_Work.getText().toString(), FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                    for (ParseObject object : objects) {
                        if (object.getString("Project_txt").isEmpty()) {

                        } else {
                            if (object.getString("Project_txt").equals(txt_Write_Work.getText().toString())) {
                                object.put("Project_txt", "");
                            }
                        }
                        if (object.getString("Bachelor_txt").isEmpty()) {

                        } else {
                            if (object.getString("Bachelor_txt").equals(txt_Write_Work.getText().toString())) {
                                object.put("Bachelor_txt", "");
                            }
                        }
                        if (object.getString("Master_txt").isEmpty()) {

                        } else {
                            if (object.getString("Master_txt").equals(txt_Write_Work.getText().toString())) {
                                object.put("Master_txt", "");
                            }
                        }


                        if (email.isEmpty()) {

                        } else {
                            if (object.getString("Work").contains(email)) {
                                String hh = object.getString("Work");
                                String strNew = hh.replaceFirst(email, "");

                                object.put("Work", strNew);

                            }
                        }
                        object.saveInBackground();
                    }
                }
            }
        });
    }
}

