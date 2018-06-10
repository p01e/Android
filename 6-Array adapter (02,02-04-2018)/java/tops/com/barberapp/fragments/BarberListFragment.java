package tops.com.barberapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import tops.com.barberapp.R;

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
        ArrayList<String> barbers=new ArrayList<>();
        barbers.add("B1");
        barbers.add("B2");
        barbers.add("B3");
        barbers.add("B4");
        barbers.add("B5");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,barbers);
        listView.setAdapter(adapter);
        return view;
    }

}
