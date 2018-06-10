package tops.com.barberapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tops.com.barberapp.R;
import tops.com.barberapp.activities.AdminActivity;
import tops.com.barberapp.activities.BarberActivity;
import tops.com.barberapp.activities.ClientActivity;

import static tops.com.barberapp.Constants.ADMIN;
import static tops.com.barberapp.Constants.BARBER;
import static tops.com.barberapp.Constants.CLIENT;
import static tops.com.barberapp.Constants.USER;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    TextView tvAdmin, tvBarber, tvClient;
    Context context;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        // Inflate the layout for this fragment
        context=getActivity();
        tvAdmin=view.findViewById(R.id.tvAdmin);
        tvBarber=view.findViewById(R.id.tvBarber);
        tvClient=view.findViewById(R.id.tvClient);
        tvAdmin.setOnClickListener(this);
        tvBarber.setOnClickListener(this);
        tvClient.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Select User");
    }

    @Override
    public void onClick(View v) {
        //Intent intent=null;
        Fragment fragment=new LoginFragment();
        Bundle bundle=new Bundle();
        switch (v.getId())
        {
            case R.id.tvAdmin:
                //intent=new Intent(context, AdminActivity.class);
                bundle.putInt(USER,ADMIN);
                break;
            case R.id.tvBarber:
                //intent=new Intent(context, BarberActivity.class);
                bundle.putInt(USER,BARBER);
                break;
            case R.id.tvClient:
                //intent=new Intent(context, ClientActivity.class);
                bundle.putInt(USER,CLIENT);
                break;
        }
        fragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frm_layout,fragment)
                .addToBackStack(MainFragment.class.getName())
                .commit();
        //startActivity(intent);
    }
}
