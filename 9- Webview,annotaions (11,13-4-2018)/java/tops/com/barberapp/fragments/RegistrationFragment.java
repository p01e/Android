package tops.com.barberapp.fragments;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import tops.com.barberapp.R;
import tops.com.barberapp.TermsConditionActivity;
import tops.com.barberapp.TermsConditionActivity_;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment implements View.OnClickListener {

    ImageButton btnDob, btnState;
    EditText etDob,etState,etAge;
    RadioGroup rgGender;
    SeekBar sbAge;
    TextView tvTermsCondition;
    Spinner citySpinner;

    TextView tvMessage;
    Context context;
    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_registration, container, false);
        // Inflate the layout for this fragment
        context=getActivity();
        tvTermsCondition=view.findViewById(R.id.tvTermsCondition);
        citySpinner=view.findViewById(R.id.citySpinner);
        sbAge=view.findViewById(R.id.sbAge);
        tvMessage=view.findViewById(R.id.tvMessage);
        rgGender=view.findViewById(R.id.rgGender);
        etDob=view.findViewById(R.id.etDob);
        etState=view.findViewById(R.id.etState);
        btnDob=view.findViewById(R.id.btnDob);
        btnState=view.findViewById(R.id.btnState);
        etAge=view.findViewById(R.id.etAge);
        btnDob.setOnClickListener(this);
        btnState.setOnClickListener(this);
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbMale:
                        etAge.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rbFemale:
                        etAge.setVisibility(View.GONE);
                        break;
                }
            }
        });
        etAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    String age = etAge.getText().toString();
                    int ag = Integer.parseInt(age);
                    sbAge.setProgress(ag);
                }catch (Exception ex){
                    sbAge.setProgress(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                etAge.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        tvTermsCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TermsConditionActivity_.class));
            }
        });
        return view;
    }



    public void sendMessage(String msg) {
        tvMessage.setText(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDob:
                showDatePickerDialog();
                break;
            case R.id.btnState:
                showStateList();
                break;
        }
    }

    private void showStateList() {
        String[] states=getResources().getStringArray(R.array.states);
        new AlertDialog.Builder(context)
                .setTitle("Select State")
                .setItems(R.array.states, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String state=states[which];
                        //Toast.makeText(context, state, Toast.LENGTH_SHORT).show();
                        etState.setText(state);
                        String[] cities=null;
                        switch (which)
                        {
                            case 0:
                                cities=getResources().getStringArray(R.array.cities);
                                break;
                            case 1:
                                cities=getResources().getStringArray(R.array.cities_m);
                                break;
                        }
                        if(cities!=null)
                        {
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,cities);
                            citySpinner.setAdapter(adapter);
                        }
                    }
                })
                .create()
                .show();
    }

    private void showDatePickerDialog() {
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                etDob.setText(dayOfMonth+"-"+month+"-"+year);
            }
        },year,month,dayOfMonth).show();
    }
}
