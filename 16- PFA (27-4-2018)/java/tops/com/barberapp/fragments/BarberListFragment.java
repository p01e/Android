package tops.com.barberapp.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import tops.com.barberapp.Constants;
import tops.com.barberapp.R;
import tops.com.barberapp.adapters.BarberBaseAdapter;
import tops.com.barberapp.model.Barber;
import tops.com.barberapp.other.MyAsyncTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarberListFragment extends Fragment {

    ListView listView;
    Context context;
    private ArrayList<Barber> barbers;
    private BarberBaseAdapter adapter;


    public BarberListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
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
        barbers=new ArrayList<>();
      /*  barbers.add(new Barber(1,"B1","1212121","b1@gmail.com"));
        barbers.add(new Barber(2,"B2","1212122","b2@gmail.com"));
        barbers.add(new Barber(3,"B3","1212123","b3@gmail.com"));
        barbers.add(new Barber(4,"B4","1212124","b4@gmail.com"));
        barbers.add(new Barber(5,"B5","1212125","b5@gmail.com"));*/
        getAllBarber();
        adapter=new BarberBaseAdapter(context,barbers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Barber barber=barbers.get(position);
                int bid=barber.getId();
                Toast.makeText(context, barber.getName(), Toast.LENGTH_SHORT).show();

                                new AlertDialog.Builder(context).setNeutralButton("cancel",null)
                                        .setPositiveButton("update", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                updateBarber(barber);
                                            }
                                        })
                                        .setNegativeButton("delete", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                delBarber(barber);
                                            }

                                        }).create().show();
            }
        });
        return view;
    }

    private void updateBarber(Barber barber) {
        Bundle b=new Bundle();
        b.putParcelable("BARBER",barber);
        Fragment f=new NewBarberFragment();
        f.setArguments(b);
        getFragmentManager().beginTransaction()
                .replace(R.id.frm_admin_layout,f)
                .addToBackStack(BarberListFragment.class.getName()).commit();
    }

    private void delBarber(Barber barber) {
        String url=Constants.BASE_URL+Constants.BARBER_PAGE+Constants.DELETE;
        HashMap<String ,String> hm=new HashMap<>();
        MyAsyncTask mydel=new MyAsyncTask(context, new MyAsyncTask.OnResponseListener() {
                   @Override
                   public void onResponse(String result) {
                       Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                       barbers.remove(barber);
                       adapter.notifyDataSetChanged();
                   }
               });
        hm.put("id", String.valueOf(barber.getId()));
        mydel.setHm(hm);
        mydel.execute(url);

    }

    private void getAllBarber() {
        String url= Constants.BASE_URL+Constants.BARBER_PAGE+Constants.GETALL;
        MyAsyncTask asyncTask=new MyAsyncTask(context, new MyAsyncTask.OnResponseListener() {
            @Override
            public void onResponse(String result) {
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    JSONArray barberArray=jsonObject.getJSONArray("barbers");
                    for(int i=0;i<barberArray.length();i++){
                        JSONObject barberObject=barberArray.getJSONObject(i);
                        /*Barber barber=new Barber();
                        barber.setId(Integer.parseInt(barberObject.getString("id")));
                        barber.setName(barberObject.getString("first_name")+" "
                        +barberObject.getString("last_name"));
                        barber.setMobile(barberObject.getString("mobile"));
                        barber.setEmail(barberObject.getString("email"));*/
                        Gson gson=new Gson();
                        Barber barber=gson.fromJson(barberObject.toString(),Barber.class);
                        barbers.add(barber);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        asyncTask.execute(url);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.barber,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_new)
        {
            Fragment fragment=new NewBarberFragment();
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frm_admin_layout,fragment)
                    .addToBackStack(BarberListFragment.class.getName())
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }


}
