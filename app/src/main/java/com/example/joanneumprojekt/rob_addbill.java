package com.example.joanneumprojekt;

/*
    rob_addbill und Main
 */



import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.joanneumprojekt.Admin.Main;

public class rob_addbill extends AppCompatActivity {
    private TextView changetoolbarText;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rob_addbill);

        drawerLayout = findViewById(R.id.drawer_layout);
        changetoolbarText = findViewById(R.id.tv_toolbarText);
        changetoolbarText.setText("ADD BILL");
    }

    public void clickMenu(View view){
        Main.openDrawer(drawerLayout);

    }

    public void clickLogo(View view){
        Main.closeDrawer(drawerLayout);
    }

    public void clickHome(View view){
        //Redirect activity to MainActivity (Home)
        Main.redirectActivity(this, Main.class);
    }

    public void clickApplications(View view){
        //Recreate the ApplicationsActivity
        recreate();
    }

    public void clickLogout(View view){
        //Close app
        Main.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Main.closeDrawer(drawerLayout);
    }
}