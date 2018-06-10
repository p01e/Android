package tops.com.barberapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
public class LoginFragment extends Fragment {

    Button btnLogin;
    EditText etUsername, etPassword;
    TextView tvNewUser, tvExistingUser;
    Context context;
    private int user;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_login, container, false);

        checkUser();
        Bundle bundle=getArguments();
        user=bundle.getInt(USER);
        context=getActivity();
        // Inflate the layout for this fragment
        btnLogin=view.findViewById(R.id.btnLogin);
        etUsername=view.findViewById(R.id.etUsername);
        etPassword=view.findViewById(R.id.etPassword);
        tvNewUser=view.findViewById(R.id.tvNewUser);
        tvExistingUser=view.findViewById(R.id.tvExistingUser);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                String msg=username+" "+password;
                FragmentManager fragmentManager=getFragmentManager();
                RegistrationFragment fragment= (RegistrationFragment) fragmentManager.findFragmentById(R.id.registrationFragment);
                fragment.sendMessage(msg);*/
                Toast.makeText(context, "Login Here", Toast.LENGTH_SHORT).show();
                changeActivity();
            }
        });
        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new RegistrationFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frm_layout,fragment)
                        .addToBackStack(LoginFragment.class.getName())
                        .commit();
            }
        });
        tvExistingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new ExistingUserFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frm_layout,fragment)
                        .addToBackStack(LoginFragment.class.getName())
                        .commit();
            }
        });
        return view;
    }

    private void changeActivity() {
        Intent intent=null;
        switch (user)
        {
            case ADMIN:
                intent=new Intent(context, AdminActivity.class);
                break;
            case BARBER:
                intent=new Intent(context, BarberActivity.class);
                break;
            case CLIENT:
                intent=new Intent(context, ClientActivity.class);
                break;
        }
        intent.putExtra(USER,user);
        startActivity(intent);
    }

    private void checkUser() {

        switch (user)
        {
            case ADMIN:
                getActivity().setTitle("Admin Login");
                break;
            case BARBER:
                getActivity().setTitle("Barber Login");
                break;
            case CLIENT:
                getActivity().setTitle("Client Login");
                break;
        }
    }

}
