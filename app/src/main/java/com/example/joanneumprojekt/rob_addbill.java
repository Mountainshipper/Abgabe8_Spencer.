package com.example.joanneumprojekt;

/*
    rob_addbill und rob_main
 */



import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        rob_main.openDrawer(drawerLayout);

    }

    public void clickLogo(View view){
        rob_main.closeDrawer(drawerLayout);
    }

    public void clickHome(View view){
        //Redirect activity to MainActivity (Home)
        rob_main.redirectActivity(this, rob_main.class);
    }

    public void clickApplications(View view){
        //Recreate the ApplicationsActivity
        recreate();
    }

    public void clickLogout(View view){
        //Close app
        rob_main.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        rob_main.closeDrawer(drawerLayout);
    }
}