/**
 * Class for getting pictures from database
 */

package com.example.joanneumprojekt.Pictures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
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
import com.example.joanneumprojekt.Display_Interface.New_Bill;
import com.example.joanneumprojekt.After_Login.show_delete_bill;
import com.example.joanneumprojekt.R;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.File;
import java.util.List;

public class GetPictures extends AppCompatActivity {
    private LinearLayout linearLayout;
    private File file;
    private TextView changetoolbarText;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_pictures);

        linearLayout = findViewById(R.id.SetLayoutForPictures);
        drawerLayout = findViewById(R.id.drawer_layout);
        changetoolbarText = findViewById(R.id.tv_toolbarText);
        changetoolbarText.setText("Show All Pictures");



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

                        final TextView postDescription = new TextView(GetPictures.this);
                        postDescription.setText(post.get("image_des") + "");


                        ParseFile file2 = post.getParseFile("picture");
                        file2.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {

                                if (data != null && e == null) {

                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    ImageView postImageView = new ImageView(GetPictures.this);
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

                    FancyToast.makeText(GetPictures.this, " Cannot find any Pictures!", Toast.LENGTH_SHORT, FancyToast.INFO, true).show();
                    finish();
                }

                dialog.dismiss();
            }
        });

    }

    public void clickMenu(View view) {
        Main.openDrawer(drawerLayout);
    }

    public void clickLogo(View view) {
        Main.closeDrawer(drawerLayout);
    }

    public void clickHome(View view) {
        //Redirect activity to MainActivity (Home)
        Main.redirectActivity(this, Main.class);
    }

    public void clickApplications(View view) {
        //Redirect activity to New_Bill
        Main.redirectActivity(this, New_Bill.class);
    }

    public void getBillsPng(View view) {
        //Recreate the GetPictures
        recreate();
    }

    public void deleteUser(View view) {
        //Redirect activity to deleteUser
        Main.redirectActivity(this, show_delete_bill.class);
    }


    public void clickLogout(View view) {
        //Close app
        Main.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Main.closeDrawer(drawerLayout);
    }

}