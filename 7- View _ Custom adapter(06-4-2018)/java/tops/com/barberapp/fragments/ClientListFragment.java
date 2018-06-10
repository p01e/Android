package tops.com.barberapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tops.com.barberapp.R;
import tops.com.barberapp.adapters.ClientAdapter;
import tops.com.barberapp.model.Barber;
import tops.com.barberapp.model.Client;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientListFragment extends Fragment {

    RecyclerView recyclerView;
    Context context;
    public ClientListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_client_list, container, false);
        context=getActivity();
        // Inflate the layout for this fragment
        recyclerView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        ArrayList<Client> clients=new ArrayList<>();
        clients.add(new Client(1,"C1","1212121","b1@gmail.com"));
        clients.add(new Client(2,"C2","1212122","b2@gmail.com"));
        clients.add(new Client(3,"C3","1212123","b3@gmail.com"));
        clients.add(new Client(4,"C4","1212124","b4@gmail.com"));
        clients.add(new Client(5,"C5","1212125","b5@gmail.com"));
        ClientAdapter adapter=new ClientAdapter(clients);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
