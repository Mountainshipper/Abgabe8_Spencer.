/**
 * Initialize database
 */

package com.example.joanneumprojekt.SignUP;

import android.app.Application;
import com.parse.Parse;

public class App extends Application {

//    Hallo
    @Override
    public void onCreate() {
        super.onCreate();


// This is for the data Server
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("mSRg6rBzjN8KegEQHFEdHZtMTvuErvZhTMpu0Kks")
                // if defined
                .clientKey("8bWVtUD9MWm86PMW9OMhIoSq9MWw0FwChfdmCr3K")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}



//APK
