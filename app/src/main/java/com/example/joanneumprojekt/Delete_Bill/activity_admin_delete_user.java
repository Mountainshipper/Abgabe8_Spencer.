/**
 * Autor: Samuel Spencer
 * This is the code for deleting user and projects
 * 06.06.2022
 */

package com.example.joanneumprojekt.Delete_Bill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.joanneumprojekt.R;

public class activity_admin_delete_user extends AppCompatActivity{

    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_user);

    }
}

