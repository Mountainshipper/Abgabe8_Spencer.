package com.example.joanneumprojekt.Pictures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joanneumprojekt.After_Login.Main;
import com.example.joanneumprojekt.After_Login.New_Bill;
import com.example.joanneumprojekt.After_Login.show_delete_bill;
import com.example.joanneumprojekt.R;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class Show_Pictures extends AppCompatActivity {
    private LinearLayout linearLayout;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_pictures);
        linearLayout = findViewById(R.id.linearLayout);
        drawerLayout = findViewById(R.id.drawer_layout);



        final ProgressDialog dialog = new ProgressDialog(this);




        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Photo");
        parseQuery.whereEqualTo("Name", "1");


        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size() > 0 && e == null) {
                    dialog.setMessage("Loading...");
                    dialog.show();

                    for (ParseObject post : objects) {

                        final TextView postDescription = new TextView(Show_Pictures.this);
                        postDescription.setText(post.get("image_des") + "");


                        ParseFile file2 = post.getParseFile("picture");
                        file2.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {

                                if (data != null && e == null) {

                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    ImageView postImageView = new ImageView(Show_Pictures.this);
                                    LinearLayout.LayoutParams imageView_params =
                                            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT);
                                    imageView_params.setMargins(5, 5, 5, 5);
                                    postImageView.setLayoutParams(imageView_params);
                                    postImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                    postImageView.setImageBitmap(bitmap);

                                    LinearLayout.LayoutParams des_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    des_params.setMargins(5, 5, 5, 15);
                                    postDescription.setLayoutParams(des_params);
                                    postDescription.setGravity(Gravity.CENTER);
                                    postDescription.setTextColor(Color.WHITE);
                                    postDescription.setTextSize(30f);
                                    postDescription.setText("\n\n");


                                    linearLayout.addView(postImageView);
                                    linearLayout.addView(postDescription);

                                }

                            }
                        });
                    }
                } else {

                    FancyToast.makeText(Show_Pictures.this, " Cannot find any Pictures!", Toast.LENGTH_SHORT, FancyToast.INFO, true).show();
                    finish();
                }

                dialog.dismiss();
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
