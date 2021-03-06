/**
 * Autor: Samuel Spencer
 * This is the code lets the student see his achievements
 * 06.06.2022
 */

package com.example.joanneumprojekt.ui.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import com.example.joanneumprojekt.Current_Login;
import com.example.joanneumprojekt.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;



public class Read_Student extends AppCompatActivity implements View.OnClickListener {

    private Button Button;
    private TextView setText;
    String txt_Work = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_student);


        setText = findViewById(R.id.Display_TXT);
        Button = findViewById(R.id.GetTXT);
        Button.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.GetTXT:
                txt_Work = "";
                setText.setText("");


                Current_Login current_user = (Current_Login) getIntent().getSerializableExtra("current_user");


                ParseQuery<ParseObject> query = ParseQuery.getQuery("New_User");
                query.whereEqualTo("email", current_user.getEmailOB());
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject work, ParseException e) {
                        if (e == null) {

                            if (current_user.getProjektOB().equals("Nein")) {
                                FancyToast.makeText(Read_Student.this, "No available works", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            } else{
                                txt_Work = "Project: " + work.getString("Project_txt");
                            }

                            if (current_user.getBachelorOB().equals("Nein")) {
                            } else {
                                txt_Work = txt_Work +" \n\nBachelor: "+ work.getString("Bachelor_txt");
                            }

                            if (current_user.getMasterOB().equals("Nein")) {
                            } else {
                                txt_Work = txt_Work +" \n\nMaster: "+ work.getString("Master_txt")+ "\nExam Date: "+work.getString("Exam_Date");
                            }
                            setText.setText(txt_Work);

                        } else {
                            FancyToast.makeText(Read_Student.this, "There was an error :(", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                        }
                    }
                });

        }
    }
    public void loginLayoutTapped (View view) {
        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
