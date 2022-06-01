package com.example.joanneumprojekt.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import java.util.List;

public class Admin_Delete_USER extends AppCompatActivity implements View.OnClickListener{

    private Button getWorks, getUser, Delete, Delete_WORK;
    private TextView txt_Display_Work, txt_Display_User, txt_Write_Work, txt_Write_User;
    String txt_Work = "";
    String txt_Professor = "";
    String User = "";
    String user_temp = "";
    int counter = 0;


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

    int n = 0;

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

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Deleting Data");
                progressDialog.show();


                //User
try {


    ParseQuery<ParseObject> soccerPlayers = ParseQuery.getQuery("New_User");
// Query parameters based on the item name
    soccerPlayers.whereEqualTo("Username", txt_Write_User.getText().toString());
    soccerPlayers.findInBackground(new FindCallback<ParseObject>() {
        @Override
        public void done(final List<ParseObject> user, ParseException e) {
            if (e == null) {
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
        }


    });
    break;

}catch (Exception e){
    FancyToast.makeText(Admin_Delete_USER.this, "Something went wrong (True Error)", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

}

//Works



            case R.id.btn_Upload_DELETE_WORK:

try {


    ParseQuery<ParseObject> work_delete = ParseQuery.getQuery("All_Works");
// Query parameters based on the item name
    work_delete.whereEqualTo("Title", txt_Write_Work.getText().toString());
    work_delete.findInBackground(new FindCallback<ParseObject>() {
        @Override
        public void done(final List<ParseObject> player, ParseException e) {
            if (e == null) {
                player.get(0).deleteInBackground(new DeleteCallback() {
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
                FancyToast.makeText(Admin_Delete_USER.this, "Something went wrong (Database)", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

            }
        }


    });
    break;


}catch (Exception e){
    FancyToast.makeText(Admin_Delete_USER.this, "Something went wrong (true Error)", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

}
        }
    }
}

