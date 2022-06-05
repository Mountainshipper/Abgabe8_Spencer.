package com.example.joanneumprojekt.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joanneumprojekt.Current_Login;
import com.example.joanneumprojekt.R;
import com.example.joanneumprojekt.ui.Student.Project;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class Assign_Project_to_Student extends AppCompatActivity implements View.OnClickListener {
    private Button admin_btn_get_Works, admin_get_all_users, admin_upload_all;
    private TextView Admin_txt_Display_Works, admin_txt_Display_Users, admin_Txt_Write_professor, admin_Txt_Write_work, admin_txt_wite_Users;


int code1 = 0, code2 = 0, code3 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_project_to_student);
        setTitle("Add assignment");


        Admin_txt_Display_Works = findViewById(R.id.Admin_txt_Display_Works);
        admin_txt_Display_Users = findViewById(R.id.admin_txt_Display_Users);
        admin_Txt_Write_professor = findViewById(R.id.admin_Txt_Write_professor);
        admin_txt_wite_Users = findViewById(R.id.admin_txt_wite_Users);
        admin_Txt_Write_work = findViewById(R.id.admin_Txt_Write_work);

        admin_upload_all = findViewById(R.id.admin_upload_all);
        admin_btn_get_Works = findViewById(R.id.admin_btn_get_Works);
        admin_get_all_users = findViewById(R.id.admin_get_all_users);
//
        admin_upload_all.setOnClickListener(this);
        admin_get_all_users.setOnClickListener(this);
        admin_btn_get_Works.setOnClickListener(this);

    }

    int n = 0;

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.admin_btn_get_Works:

                all_Works();
                break;


            case R.id.admin_get_all_users:
                all_Users();
                break;

            case R.id.admin_upload_all:
                set_work();
                break;
        }
    }

    String txt_Professor;
    String set_User;

    public void all_Users() {
        txt_Professor = "";
        admin_txt_Display_Users.setText("");

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
                                    set_User = "Assistent: ";
                                }
                                if (count_Assistent == 0) {
                                    txt_Professor = txt_Professor + "------------------------------------------------------------------------\nRole: " + set_User + "\nUsername: " + parseObject.get("Username") + ". \nAvailable slots" + parseObject.get("Slots") + "\n\n";
                                    admin_txt_Display_Users.setText(txt_Professor);
                                    ++count_Assistent;
                                } else {
                                    txt_Professor = txt_Professor + "Role: " + set_User + "\nUsername: " + parseObject.get("Username") + ". \nAvailable slots" + parseObject.get("Slots") + "\n\n";
                                    admin_txt_Display_Users.setText(txt_Professor);
                                }
                            }
                        }
                        //SORTED
                        count_Assistent = 0;
                        for (ParseObject parseObject : objects) {
                            if (parseObject.get("ID").equals("Student")) {
                                set_User = "Student: ";
                            }

                            if (count_Assistent == 0) {
                                txt_Professor = txt_Professor + "------------------------------------------------------------------------\nUsername: " + parseObject.get("Username") + "\nRole: " + set_User + ". \nAvailable slots" + parseObject.get("Slots") + "\n\n";
                                admin_txt_Display_Users.setText(txt_Professor);
                                ++count_Assistent;
                            } else {
                                txt_Professor = txt_Professor + "Username: " + parseObject.get("Username") + "\nRole: " + set_User + ". \nAvailable slots" + parseObject.get("Slots") + "\n\n";
                                admin_txt_Display_Users.setText(txt_Professor);
                            }
                        }
                    }
                }
            }
        });
    }


    String txt_Work;
    String set_Work;

    public void all_Works() {

        txt_Work = "";
        Admin_txt_Display_Works.setText("");

        ParseQuery<ParseObject> queryAllWork = ParseQuery.getQuery("All_Works");
        queryAllWork.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {


                if (e == null) {
                    if (objects.size() > 0) {
                        for (ParseObject parseObject : objects) {
                            n++;

                            if (parseObject.get("User").equals("open")) {
                                if (parseObject.get("User").equals("open") && parseObject.get("Function").equals("Project")) {
                                    set_Work = "Category 'Project'";
                                } else if (parseObject.get("Function").equals("Bachelor")) {
                                    set_Work = "Category 'Bachelor'";
                                } else if (parseObject.get("Function").equals("Master")) {
                                    set_Work = "Category 'Master'";
                                }


                                txt_Work = txt_Work + "--------------\nTitle: " + parseObject.get("Title") + "\n" + set_Work + "\nDeadline: " + parseObject.get("Deadline")
                                        + "\nExam Date: " + parseObject.get("Exam_Date") + "\n\n";
                                Admin_txt_Display_Works.setText(txt_Work);


                            }
                        }
                    }
                    if (n == 0) {
                        FancyToast.makeText(Assign_Project_to_Student.this, "There are no available projects", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                    }
                }
            }
        });
    }


    int test_work;
    String Rank;

    public void set_work() {


            ParseQuery<ParseObject> query = ParseQuery.getQuery("All_Works");
            query.whereEqualTo("Title", admin_Txt_Write_work.getText().toString());

            query.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {



                            object.put("User", "taken");

                            Rank = object.getString("Function");
                            FancyToast.makeText(Assign_Project_to_Student.this, "Work has been booked", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                            test_work = 1;
                            object.saveEventually();
                            set_user();


                    } else {
                        FancyToast.makeText(Assign_Project_to_Student.this, "Work could not be found. ERROR", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                    }

                }
            });


    }


    String email;

    public void set_user() {

        ParseQuery<ParseObject> student = ParseQuery.getQuery("New_User");
        student.whereEqualTo("Username", admin_txt_wite_Users.getText().toString());

        student.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {


                    email = object.getString("email");


                    if (Rank.equals("Project")) {
                        object.put("Projekt", "Yes");

                    } else if (Rank.equals("Bachelor")) {
                        object.put("Bachelor", "Yes");

                    } else if (Rank.equals("Master")) {
                        object.put("Master", "Yes");
                    }


                    object.put("Project_txt", admin_Txt_Write_work.getText().toString());

                    object.saveEventually();


                } else {
                    FancyToast.makeText(Assign_Project_to_Student.this, "User not found", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                }
            }
        });


        ParseQuery<ParseObject> assistant = ParseQuery.getQuery("New_User");
        assistant.whereEqualTo("Username", admin_Txt_Write_professor.getText().toString());
        assistant.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {

                    //Professor


                    String user_temp;


                    user_temp = object.getString("Work");
                    if (object.getString("Work").isEmpty()) {
                        user_temp = "" + email;
                        FancyToast.makeText(Assign_Project_to_Student.this, "" + email, FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                    } else {
                        FancyToast.makeText(Assign_Project_to_Student.this, "" + email, FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                        user_temp = user_temp + "; " + email;

                    }
                    object.put("Work", user_temp);
                    object.saveInBackground();


//Student
                    if (object.get("ID").equals("Student") && object.get("Username").equals(admin_txt_wite_Users.getText().toString())) {

                    }

                } else {
                    FancyToast.makeText(Assign_Project_to_Student.this, "Professor could not be found. ERROR", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                }
            }
        });
    }
}




