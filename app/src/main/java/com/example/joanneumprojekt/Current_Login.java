package com.example.joanneumprojekt;

import com.example.joanneumprojekt.ui.Student.LoginActivity;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;

public class Current_Login implements Serializable {


   private String master;
   private String bachelor;
   private String projekt;
    private String password;
    private String email;


public Current_Login(){

}


public Current_Login (String master, String bachelor, String projekt, String password, String email) {
    this.master = master;
    this.bachelor = bachelor;
    this.projekt = projekt;
    this.password = password;
    this.email = email;


}

    public String getMasterOB() {
        return master;
    }

    public String getBachelorOB() {
        return bachelor;
    }

    public String getProjektOB() {
        return projekt;
    }








    public String getEmailOB() {
        return email;
    }


}


