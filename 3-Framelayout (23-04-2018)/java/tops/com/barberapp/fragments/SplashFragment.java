package tops.com.barberapp.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tops.com.barberapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {


    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_splash, container, false);
        // Inflate the layout for this fragment
        Handler handler=new Handler();
        handler.postDelayed(runnable,2000);
        return view;
    }

    Runnable runnable=() -> {
        Fragment fragment=new LoginFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frm_layout,fragment)
                .commit();
    };
}
