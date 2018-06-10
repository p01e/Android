package tops.com.barberapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tops.com.barberapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {


    TextView tvMessage;
    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_registration, container, false);

        // Inflate the layout for this fragment
        tvMessage=view.findViewById(R.id.tvMessage);
        return view;
    }

    public void sendMessage(String msg) {
        tvMessage.setText(msg);
    }
}
