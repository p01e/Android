package tops.com.barberapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import tops.com.barberapp.activities.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context=this;
        //context=getApplicationContext();
        Handler handler=new Handler();
        handler.postDelayed(runnable,2000);

        Log.i("SplashActivity","onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("SplashActivity","onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("SplashActivity","onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("SplashActivity","onPause()");
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            Intent intent=new Intent(context,MainActivity.class);
            startActivity(intent);
            //finish();
        }
    };
}
