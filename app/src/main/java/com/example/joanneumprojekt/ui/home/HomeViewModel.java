package com.example.joanneumprojekt.ui.home;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.joanneumprojekt.R;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.GetCallback;
import com.parse.ParseException;









public class HomeViewModel extends ViewModel implements View.OnClickListener{
    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower;
    private TextView txtGetData;
    private MutableLiveData<String> mText;
    String Miau = "";



    public LiveData<String> getText() {
        return mText;
    }

    @Override
    public void onClick(View view) {


    }


    public HomeViewModel() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("temprary_User_Pikka");
        query.getInBackground("gH548pGCmO", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {



                    


                }else {


                }
            }
        });

        mText = new MutableLiveData<>();
        mText.setValue("sdkjfh");




            }


    }









