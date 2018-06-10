package tops.com.barberapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import tops.com.barberapp.Constants;
import tops.com.barberapp.R;
import tops.com.barberapp.model.Barber;
import tops.com.barberapp.other.MyAsyncTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewBarberFragment extends Fragment implements View.OnClickListener {

    EditText etFirstName, etLastName, etMobile,
    etEmail,etAddress;
    Button btnSave;
    Context context;

    Barber barber=null;


    public NewBarberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_new_barber, container, false);
        context=getActivity();
        etFirstName=view.findViewById(R.id.etFirstName);
        etLastName=view.findViewById(R.id.etLastName);
        etMobile=view.findViewById(R.id.etMobile);
        etEmail=view.findViewById(R.id.etEmail);
        etAddress=view.findViewById(R.id.etAddress);
        btnSave=view.findViewById(R.id.btnSave);
        // Inflate the layout for this fragment
        btnSave.setOnClickListener(this);
        Bundle b=getArguments();
        if(b!=null)
        {
            barber=b.getParcelable("BARBER");
            String[] names=barber.getName().split(" ");
            etFirstName.setText(names[0]);
            etLastName.setText(names[1]);
            etMobile.setText(barber.getMobile());
            etEmail.setText(barber.getEmail());
            btnSave.setText("Update");

        }
        return view;
    }

    @Override
    public void onClick(View v) {

        HashMap<String,String> hm=new HashMap<>();
        hm.put("first_name",etFirstName.getText().toString());
        hm.put("last_name",etLastName.getText().toString());
        hm.put("mobile",etMobile.getText().toString());
        hm.put("email",etEmail.getText().toString());
        hm.put("address",etAddress.getText().toString());
        String url;
        if(barber==null) {
            url = Constants.BASE_URL +
                    Constants.BARBER_PAGE +
                    Constants.INSERT;
        }else{
            url=Constants.BASE_URL+Constants.BARBER_PAGE+Constants.UPDATE;
            hm.put("id", String.valueOf(barber.getId()));
        }
        MyAsyncTask asyncTask=new MyAsyncTask(context, new MyAsyncTask.OnResponseListener() {
            @Override
            public void onResponse(String result) {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            }
        });
        asyncTask.setHm(hm);
        asyncTask.execute(url);
    }
}
