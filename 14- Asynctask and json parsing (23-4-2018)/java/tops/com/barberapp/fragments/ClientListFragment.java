package tops.com.barberapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import tops.com.barberapp.R;
import tops.com.barberapp.adapters.ClientAdapter;
import tops.com.barberapp.model.Barber;
import tops.com.barberapp.model.Client;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientListFragment extends Fragment implements ClientAdapter.ClientCallBack {

    EditText etName, etMobile, etEmail;
    Button btnSave, btnDelete, btnEdit, btnCancel;
    LinearLayout layoutEdit;
    RecyclerView recyclerView;
    Context context;
    private ArrayList clients;
    Client client;
    private ClientAdapter adapter;

    public ClientListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_client_list, container, false);
        ButterKnife.bind(this,view);
        context=getActivity();
        // Inflate the layout for this fragment
        etName=view.findViewById(R.id.etName);
        etMobile=view.findViewById(R.id.etMobile);
        etEmail=view.findViewById(R.id.etEmail);
        btnSave=view.findViewById(R.id.btnSave);
        layoutEdit=view.findViewById(R.id.editLayout);
        btnDelete=view.findViewById(R.id.btnDelete);
        btnEdit=view.findViewById(R.id.btnEdit);
        btnCancel=view.findViewById(R.id.btnCancel);

        recyclerView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        clients=new ArrayList<>();
        clients.add(new Client(1,"C1","1212121","b1@gmail.com"));
        clients.add(new Client(2,"C2","1212122","b2@gmail.com"));
        clients.add(new Client(3,"C3","1212123","b3@gmail.com"));
        clients.add(new Client(4,"C4","1212124","b4@gmail.com"));
        clients.add(new Client(5,"C5","1212125","b5@gmail.com"));
        adapter=new ClientAdapter(clients, this);
        recyclerView.setAdapter(adapter);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearControls();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clients.remove(client);
                adapter.notifyDataSetChanged();
                clearControls();
            }
        });
        return view;
    }

    @OnClick(R.id.btnSave)
    public void save(){
        Client client=new Client();
        client.setId(System.currentTimeMillis());
        client.setName(etName.getText().toString());
        client.setEmai(etEmail.getText().toString());
        client.setMobile(etMobile.getText().toString());
        clients.add(client);
        adapter.notifyDataSetChanged();
    }

    private void clearControls() {
        btnSave.setVisibility(View.VISIBLE);
        layoutEdit.setVisibility(View.GONE);
        etName.setText("");
        etEmail.setText("");
        etMobile.setText("");
    }

    @Override
    public void clientClick(Client client) {
        this.client=client;
        etName.setText(client.getName());
        etEmail.setText(client.getEmai());
        etMobile.setText(client.getMobile());
        btnSave.setVisibility(View.GONE);
        layoutEdit.setVisibility(View.VISIBLE);
    }
}
