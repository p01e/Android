package tops.com.barberapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import tops.com.barberapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminHomeFragment extends Fragment {


    String menus[]={"Barbers","Clients","Report"};
    private Context context;
    private GridView gridView;

    public AdminHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        View view=inflater.inflate(R.layout.fragment_admin_home, container, false);
        gridView=view.findViewById(R.id.gridView);
        // Inflate the layout for this fragment
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,R.layout.grid_row,menus);
        gridView.setAdapter(adapter);
        return view;
    }

}
