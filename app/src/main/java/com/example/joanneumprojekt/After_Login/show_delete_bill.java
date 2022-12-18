/**
 * Class for removing bills from the app - including the reference in the database
 */

package com.example.joanneumprojekt.After_Login;

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

import com.example.joanneumprojekt.Display_Interface.New_Bill;
import com.example.joanneumprojekt.Pictures.GetPictures;
import com.example.joanneumprojekt.R;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class show_delete_bill extends AppCompatActivity implements View.OnClickListener{
    DrawerLayout drawerLayout;
    private TextView changetoolbarText;
    private Button Private_Button, Business_Button, Delete_Business, deletePrivate;
    private TextView txt_Display_Work, txt_Write_Work, txt_Write_User;
    String txt_Private, txtBusiness;
    String Project = "";
    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_delete_bill);
        drawerLayout = findViewById(R.id.drawer_layout);
        changetoolbarText = findViewById(R.id.tv_toolbarText);
        changetoolbarText.setText("REMOVE BILL");


        deletePrivate = findViewById(R.id.Delete_Private);
        Private_Button = findViewById(R.id.Private_Button);
        Business_Button = findViewById(R.id.Business_Button);
        Delete_Business = findViewById(R.id.Delete_Business);
        txt_Display_Work = findViewById(R.id.txt_Display_Work);
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
                                                "\nDate: "+  parseObject.get("Date")+ "\n \n";
                                        txt_Display_Work.setText(txt_Private);
                                        ++count_Work;
                                    } else {
                                        txt_Private = txt_Private + "Title: "+  parseObject.get("Title") + "\n" + "Price: "+  parseObject.get("Price") +
                                                "\nDate: "+  parseObject.get("Date")+ "\n \n";
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
                txt_Display_Work.setText("");

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
                                                "\nDate: "+  parseObject.get("Date")+ "\n \n";
                                        txt_Display_Work.setText(txtBusiness);
                                        ++count_Assistent;

                                    } else {
                                        txtBusiness = txtBusiness + "Title: "+  parseObject.get("Title") + "\n" + "Price: "+  parseObject.get("Price") +
                                                "\nDate: "+  parseObject.get("Date")+ "\n \n";
                                        txt_Display_Work.setText(txtBusiness);
                                    }

                                }
                            }
                        }
                    }

                });

                break;


            case R.id.Delete_Business:
                if (txt_Write_User.getText().toString().isEmpty()) {
                    FancyToast.makeText(show_delete_bill.this, "Text View cannot be empty", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
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
                                                FancyToast.makeText(show_delete_bill.this, "completed", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                                            } else {
                                                FancyToast.makeText(show_delete_bill.this, "Failed to delete 'Work", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                            }
                                        }
                                    });
                                } else {
                                    FancyToast.makeText(show_delete_bill.this, "Not available", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                }
                            } else {
                                FancyToast.makeText(show_delete_bill.this, "Something went wrong (Database)", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                }break;


//Works


            case R.id.Delete_Private:

                if (txt_Write_Work.getText().toString().isEmpty()) {
                    FancyToast.makeText(show_delete_bill.this, "Text View cannot be empty", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
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
                                                FancyToast.makeText(show_delete_bill.this, "completed", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                                            } else {
                                                FancyToast.makeText(show_delete_bill.this, "Failed to delete 'Work", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                            }
                                        }
                                    });
                                } else {
                                    FancyToast.makeText(show_delete_bill.this, "Not available", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                }
                            } else {
                                FancyToast.makeText(show_delete_bill.this, "Something went wrong (Database)", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                }break;
        }
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
        redirectActivity(this, Main.class);
    }

    public void clickApplications(View view){
        //Redirect current activity to Applications activity
        // --> wenn ich auf add bill click, soll er zu add bill kommen
        redirectActivity(this, New_Bill.class);
    }

    public void getBillsPng(View view) {
        //Recreate the getpictures
        Main.redirectActivity(this, GetPictures.class);
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





