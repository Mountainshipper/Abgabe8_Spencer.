package com.example.joanneumprojekt.After_Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.joanneumprojekt.R;

public class show_delete_bill extends AppCompatActivity {
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_delete_bill);
        drawerLayout = findViewById(R.id.drawer_layout);
    }











    public void clickMenu (View view){
        Main.openDrawer(drawerLayout);

    }

    public void clickLogo (View view){
        Main.closeDrawer(drawerLayout);
    }

    public void clickHome (View view){
        //Redirect activity to MainActivity (Home)
        Main.redirectActivity(this, Main.class);
    }

    public void clickApplications (View view){
        //Recreate the ApplicationsActivity
        Main.redirectActivity(this, New_Bill.class);;
    }

    public void deleteUser(View view){
        //Redirect activity to deleteUser
        recreate();
    }


    public void clickLogout (View view){
        //Close app
        Main.logout(this);
    }

    @Override
    protected void onPause () {
        super.onPause();
        Main.closeDrawer(drawerLayout);
    }
}




