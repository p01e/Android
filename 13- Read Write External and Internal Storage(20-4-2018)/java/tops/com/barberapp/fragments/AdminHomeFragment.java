package tops.com.barberapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tops.com.barberapp.R;
import tops.com.barberapp.adapters.MyViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminHomeFragment extends Fragment {


    String menus[]={"Barbers","Clients","Barbers"};
    private Context context;
    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabs;
   // private GridView gridView;

    public AdminHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        View view=inflater.inflate(R.layout.fragment_admin_home, container, false);
        ButterKnife.bind(this,view);
        //viewPager=view.findViewById(R.id.viewPager);
        //tabs=view.findViewById(R.id.tabs);
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new BarberListFragment());
        fragments.add(new ClientListFragment());
        fragments.add(new BarberListFragment());
        MyViewPagerAdapter adapter=new MyViewPagerAdapter(getFragmentManager(),fragments,menus);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
       /* gridView=view.findViewById(R.id.gridView);
        // Inflate the layout for this fragment
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,R.layout.grid_row,menus);
        gridView.setAdapter(adapter);*/
        return view;
    }

}
