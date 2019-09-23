package com.example.propertyonrent.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.propertyonrent.R;
import com.example.propertyonrent.Signpage.register_page;
import com.example.propertyonrent.Signpage.signin;
import com.example.propertyonrent.home_fragment;
import com.example.propertyonrent.registerPage;

//import com.example.propertyonrent.home_fragment;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private static int SPLASH_TIME_OUT = 9000;

    @Override
    protected void onDestroy() {

        super.onDestroy();
        videoView.pause();
        videoView = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "Authentication failed.",Toast.LENGTH_SHORT).show();


//        isUserAuthenticated.signOut();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        videoView = findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);

            }
        });
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                Toast.makeText(getApplicationContext(), "Authentication12 failed.",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), signin.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Authentication failed.",Toast.LENGTH_SHORT).show();

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
//

    }
}
