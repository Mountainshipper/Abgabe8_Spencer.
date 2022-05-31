package com.example.joanneumprojekt;

import com.example.joanneumprojekt.ui.Student.LoginActivity;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;

public class Current_Login implements Serializable {


   private String master;
   private String bachelor;
   private String projekt;
   private String slots;
   private String username;
    private String id;
    private String password;
    private String email;
    private String user;

public Current_Login(){

}


public Current_Login (String master, String bachelor, String projekt,String slots, String username, String password, String email) {
    this.master = master;
    this.bachelor = bachelor;
    this.projekt = projekt;
    this.slots = slots;
    this.username = username;
    this.id = id;
    this.password = password;
    this.email = email;
    this.user = user;

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

    public String getSlotsOB() {
        return slots;
    }

    public String getUsernameOB() {
        return username;
    }

    public String getIdOB() {
        return id;
    }

    public String getPasswordOB() {
        return password;
    }

    public String getEmailOB() {
        return email;
    }

    public String getUserOB() {
        return user;
    }
}




//    ParseQuery<ParseObject> query = ParseQuery.getQuery("temprary_User_Pikka");
//// Retrieve the object by id
//                                    query.getInBackground("gH548pGCmO", new GetCallback<ParseObject>() {
//public void done(ParseObject appUser2, ParseException e) {
//        if (e == null) {
//
//        String username = user.getString("Username");
//        appUser2.put("username", username);
//
//        String email = user.getString("email");
//        appUser2.put("email", email);
//        appUser2.put("ID", "Student");
//
//        String project = user.getString("Projekt");
//        appUser2.put("Projekt", project);
//
//        String bachelore = user.getString("Bachelor");
//        appUser2.put("Bachelor",bachelore);
//
//        String master = user.getString("Master");
//        appUser2.put("Master",master);
//        appUser2.saveInBackground();
//
//
//
//        } else {
//        FancyToast.makeText(LoginActivity.this, "Temporary Login could not be generated. But you can continue", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
//        // Failed
//
//        }
//
//        }
//        });








//
//
//
//    LoginActivity loginActivity = new LoginActivity();
//    Current_Login curent_user = new Current_Login();
//                curent_user = loginActivity.current_user;
//                        User = curent_user.getEmailOB();
//                        trueFalse = curent_user.getProjektOB();
//
//                        FancyToast.makeText(Project.this, "" + curent_user.getEmailOB(), FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
