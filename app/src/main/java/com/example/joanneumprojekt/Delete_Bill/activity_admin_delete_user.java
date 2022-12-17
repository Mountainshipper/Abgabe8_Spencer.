/**
 * Autor: Samuel Spencer
 * This is the code for deleting user and projects
 * 06.06.2022
 */

package com.example.joanneumprojekt.Delete_Bill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joanneumprojekt.After_Login.New_Bill;
import com.example.joanneumprojekt.After_Login.show_delete_bill;
import com.example.joanneumprojekt.R;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;
import java.util.List;

public class activity_admin_delete_user extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;
    private Button Private_Button, Business_Button, Delete_Business, deletePrivate;
    private TextView txt_Display_Work, txt_Display_User, txt_Write_Work, txt_Write_User;
    String txt_Private, txtBusiness;
    String Project = "";
    String email = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_user);


        deletePrivate = findViewById(R.id.Delete_Private);
        Private_Button = findViewById(R.id.Private_Button);
        Business_Button = findViewById(R.id.Business_Button);
        Delete_Business = findViewById(R.id.Delete_Business);
        txt_Display_Work = findViewById(R.id.txt_Display_Work);
        txt_Display_User = findViewById(R.id.txt_Display_User);
        txt_Write_Work = findViewById(R.id.txt_Write_Work);
        txt_Write_User = findViewById(R.id.txt_Write_User);


        Private_Button.setOnClickListener(this);
        Business_Button.setOnClickListener(this);
        Delete_Business.setOnClickListener(this);
        deletePrivate.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Private_Button:
                txt_Private = "";
                txt_Display_Work.setText("");

                ParseQuery<ParseObject> queryAllWork = ParseQuery.getQuery("Private");
                queryAllWork.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        int count_Work = 0;

                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject parseObject : objects) {


                                    if (count_Work == 0) {
                                        txt_Private = txt_Private + "--------------\n" + "Title: "+  parseObject.get("Title") + "\n" + "Price: "+  parseObject.get("Price") +
                                                "\n Date: "+  parseObject.get("createdAt")+ "\n \n";
                                        txt_Display_Work.setText(txt_Private);
                                        ++count_Work;
                                    } else {
                                        txt_Private = txt_Private + "Title: "+  parseObject.get("Title") + "\n" + "Price: "+  parseObject.get("Price") +
                                                "\n Date: "+  parseObject.get("createdAt")+ "\n \n";
                                        txt_Display_Work.setText(txt_Private);
                                    }
                                }
                            }
                        }
                    }
                });
                break;

            case R.id.Business_Button:
                txtBusiness = "";
                txt_Display_User.setText("");

                ParseQuery<ParseObject> queryAllProfessor = ParseQuery.getQuery("Business");
                queryAllProfessor.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        int count_Assistent = 0;
                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject parseObject : objects) {


                                    if (count_Assistent == 0) {
                                        txtBusiness = txtBusiness + "--------------\n" + "Title: "+  parseObject.get("Title") + "\n" + "Price: "+  parseObject.get("Price") +
                                                "\n Date: "+  parseObject.get("createdAt")+ "\n \n";
                                        txt_Display_User.setText(txtBusiness);
                                        ++count_Assistent;

                                    } else {
                                        txtBusiness = txtBusiness + "Title: "+  parseObject.get("Title") + "\n" + "Price: "+  parseObject.get("Price") +
                                                "\n Date: "+  parseObject.get("createdAt")+ "\n \n";
                                        txt_Display_User.setText(txtBusiness);
                                    }

                                }
                            }
                        }
                    }

                });

                break;


            case R.id.Delete_Business:
                if (txt_Write_User.getText().toString().isEmpty()) {
                    FancyToast.makeText(activity_admin_delete_user.this, "Text View cannot be empty", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                }else{


                    ParseQuery<ParseObject> work_delete = ParseQuery.getQuery("Business");
                    work_delete.whereEqualTo("Title", txt_Write_User.getText().toString());
                    work_delete.findInBackground(new FindCallback<ParseObject>() {
                        @Override

                        //No error?
                        public void done(final List<ParseObject> work, ParseException e) {
                            if (e == null) {

                                if (work.size() > 0) {

                                    work.get(0).deleteInBackground(new DeleteCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if (e == null) {
                                                FancyToast.makeText(activity_admin_delete_user.this, "completed", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                                            } else {
                                                FancyToast.makeText(activity_admin_delete_user.this, "Failed to delete 'Work", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                            }
                                        }
                                    });
                                } else {
                                    FancyToast.makeText(activity_admin_delete_user.this, "Not available", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                }
                            } else {
                                FancyToast.makeText(activity_admin_delete_user.this, "Something went wrong (Database)", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                }break;


//Works


            case R.id.Delete_Private:

                if (txt_Write_Work.getText().toString().isEmpty()) {
                    FancyToast.makeText(activity_admin_delete_user.this, "Text View cannot be empty", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                }else{


                    ParseQuery<ParseObject> work_delete = ParseQuery.getQuery("Private");


                    work_delete.whereEqualTo("Title", txt_Write_Work.getText().toString());
                    work_delete.findInBackground(new FindCallback<ParseObject>() {
                        @Override

                        //No error?
                        public void done(final List<ParseObject> work, ParseException e) {
                            if (e == null) {

                                    if (work.size() > 0) {

                                        work.get(0).deleteInBackground(new DeleteCallback() {
                                            @Override
                                            public void done(ParseException e) {
                                                if (e == null) {
                                                    FancyToast.makeText(activity_admin_delete_user.this, "completed", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                                                } else {
                                                    FancyToast.makeText(activity_admin_delete_user.this, "Failed to delete 'Work", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                                }
                                            }
                                        });
                                    } else {
                                        FancyToast.makeText(activity_admin_delete_user.this, "Not available", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                    }
                            } else {
                                FancyToast.makeText(activity_admin_delete_user.this, "Something went wrong (Database)", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
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
                    FancyToast.makeText(activity_admin_delete_user.this, "Wrong1", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
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
                    }
                }
            }
        });
    }






    public void clickMenu(View view){
        openDrawer(drawerLayout);
    }
    // von der Seite soll der navigator kommen
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }


    //What should happen when you click on the logo in the drawer
    public void clickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //When drawer is open, then close it
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    // wenn ich home bin, und dann im navigator auf home erneut klicke - passiert halt ein refresh
    public void clickHome(View view){
        //Recreate the Rob_MainActivity
        recreate();
    }

    public void clickApplications(View view){
        //Redirect current activity to Applications activity
        // --> wenn ich auf add bill click, soll er zu add bill kommen
        redirectActivity(this, New_Bill.class);
    }


    public void clickLogout(View view){
        //close app
        logout(this);
    }

    public void deleteUser(View view){
        //close app
        redirectActivity(this, show_delete_bill.class);
    }

    public static void logout(Activity activity) {
        //Initialize the alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.logout_alert_title);
        builder.setMessage(R.string.logout_alert_message);

        //Positive button clicked
        builder.setPositiveButton(R.string.logout_alert_responsePositive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finishAffinity();
                System.exit(0);
            }
        });

        //Negative button clicked
        builder.setNegativeButton(R.string.logout_alert_responseNegative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    public static void redirectActivity(Activity sourceActivity, Class classOfDestinationActivity) {
        Intent intent = new Intent(sourceActivity, classOfDestinationActivity);
        sourceActivity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}
