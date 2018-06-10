package tops.com.barberapp.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tops.com.barberapp.R;
import tops.com.barberapp.fragments.LoginFragment;
import tops.com.barberapp.fragments.MainFragment;
import tops.com.barberapp.fragments.SplashFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment=new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frm_layout,fragment)
                .commit();
    }
}
