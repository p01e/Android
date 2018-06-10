package tops.com.barberapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import tops.com.barberapp.R;
import tops.com.barberapp.adapters.BarberBaseAdapter;
import tops.com.barberapp.model.Barber;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarberListFragment extends Fragment {

    ListView listView;
    Context context;

    public BarberListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity();
        View view= inflater.inflate(R.layout.fragment_barber_list, container, false);
        listView=view.findViewById(R.id.listView);
        /*ArrayList<String> barbers=new ArrayList<>();
        barbers.add("B1");
        barbers.add("B2");
        barbers.add("B3");
        barbers.add("B4");
        barbers.add("B5");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,barbers);*/
        ArrayList<Barber> barbers=new ArrayList<>();
        barbers.add(new Barber(1,"B1","1212121","b1@gmail.com"));
        barbers.add(new Barber(2,"B2","1212122","b2@gmail.com"));
        barbers.add(new Barber(3,"B3","1212123","b3@gmail.com"));
        barbers.add(new Barber(4,"B4","1212124","b4@gmail.com"));
        barbers.add(new Barber(5,"B5","1212125","b5@gmail.com"));
        BarberBaseAdapter adapter=new BarberBaseAdapter(context,barbers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Barber barber=barbers.get(position);
                Toast.makeText(context, barber.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
