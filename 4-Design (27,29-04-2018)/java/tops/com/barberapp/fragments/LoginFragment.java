package tops.com.barberapp.fragments;


import android.content.Context;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    Button btnLogin;
    EditText etUsername, etPassword;
    TextView tvNewUser;
    Context context;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_login, container, false);
        context=getActivity();
        // Inflate the layout for this fragment
        btnLogin=view.findViewById(R.id.btnLogin);
        etUsername=view.findViewById(R.id.etUsername);
        etPassword=view.findViewById(R.id.etPassword);
        tvNewUser=view.findViewById(R.id.tvNewUser);
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
        return view;
    }

}
