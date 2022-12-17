package com.example.joanneumprojekt.After_Login;

/*

 */

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

import com.example.joanneumprojekt.Pictures.GetPictures;
import com.example.joanneumprojekt.Pictures.Show_Pictures;
import com.example.joanneumprojekt.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Main extends AppCompatActivity implements View.OnClickListener {
    private TextView changetoolbarText;
    DrawerLayout drawerLayout;
    private static final String Tag = "MainActivity";
    private Button Private_Button, Business_Button;
    private TextView txt_Display_Work;
    String txt_Private, txtBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);


        Private_Button = findViewById(R.id.Main_b_private);
        Business_Button = findViewById(R.id.Main_b_Business);
        txt_Display_Work = findViewById(R.id.Display_Info);

        drawerLayout = findViewById(R.id.drawer_layout);
        changetoolbarText = findViewById(R.id.tv_toolbarText);
        changetoolbarText.setText("BILLS");

        Private_Button.setOnClickListener(this);
        Business_Button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Main_b_private:
                txt_Private = "";
                txt_Display_Work.setText("");

                ParseQuery<ParseObject> queryAllWork = ParseQuery.getQuery("Private");
                queryAllWork.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        int count_Work = 0;

                        double sum = 0;
                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject parseObject : objects) {

                                    if (count_Work == 0) {
                                        txt_Private = txt_Private + "--------------\n" + "Title: " + parseObject.get("Title") + "\n" + "Price: " + parseObject.get("Price") + "€"+
                                                "\nDate: " + parseObject.get("Date") + "\nTaxes: " + parseObject.get("Abrechnung") + "€"+ "\n \n";
                                        sum = sum + (double) parseObject.get("Abrechnung");

                                    } else {
                                        txt_Private = txt_Private + "Title: " + parseObject.get("Title") + "\n" + "Price: " + parseObject.get("Price") + "€"+
                                                "\nDate: " + parseObject.get("Date") + "\nTaxes: " + parseObject.get("Abrechnung") + "€"+ "\n \n";
                                        sum = sum + (double) parseObject.get("Abrechnung");
                                    }
                                }
                            }
                            String total = "Total sum of taxes: " + sum +"€\n";
                            txt_Display_Work.setText(total + "\n" + txt_Private);
                        }
                    }
                });
                break;

            case R.id.Main_b_Business:
                txtBusiness = "";
                txt_Display_Work.setText("");


                ParseQuery<ParseObject> queryAllProfessor = ParseQuery.getQuery("Business");
                queryAllProfessor.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        int count_Assistent = 0;
                        double sum = 0;
                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject parseObject : objects) {


                                    if (count_Assistent == 0) {
                                        txtBusiness = txtBusiness + "--------------\n" + "Title: " + parseObject.get("Title") + "\n" + "Price: " + parseObject.get("Price") + "€"+
                                                "\nDate: " + parseObject.get("Date") + "\nTaxes: " + parseObject.get("Abrechnung") + "€"+ "\n \n";
                                        ++count_Assistent;
                                        sum = sum + (double) parseObject.get("Abrechnung");

                                    } else {
                                        txtBusiness = txtBusiness + "Title: " + parseObject.get("Title") + "\n" + "Price: " + parseObject.get("Price") + "€"+
                                                "\nDate: " + parseObject.get("Date") + "\nTaxes: " + parseObject.get("Abrechnung") + "€"+ "\n \n";
                                        sum = sum + (double) parseObject.get("Abrechnung");
                                    }

                                }
                                String total = "Total sum of taxes: " + sum +"€\n";
                                txt_Display_Work.setText(total + "\n\n" + txt_Private);
                            }
                        }
                    }

                });

                break;
        }
    }

    public void clickMenu(View view) {
        openDrawer(drawerLayout);
    }

    // von der Seite soll der navigator kommen
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }


    //What should happen when you click on the logo in the drawer
    public void clickLogo(View view) {
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //When drawer is open, then close it
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    // wenn ich home bin, und dann im navigator auf home erneut klicke - passiert halt ein refresh
    public void clickHome(View view) {
        //Recreate the Rob_MainActivity
        recreate();
    }

    public void clickApplications(View view) {
        //Redirect current activity to Applications activity
        // --> wenn ich auf add bill click, soll er zu add bill kommen
        redirectActivity(this, New_Bill.class);
    }


    public void getBillsPng(View view) {
        //Recreate the getpictures
        redirectActivity(this, GetPictures.class);
    }

    public void clickLogout(View view) {
        //close app
        logout(this);
    }

    public void deleteUser(View view) {
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
