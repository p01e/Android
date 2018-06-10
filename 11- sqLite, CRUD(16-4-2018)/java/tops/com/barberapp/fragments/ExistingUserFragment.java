package tops.com.barberapp.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import tops.com.barberapp.R;
import tops.com.barberapp.adapters.UserAdapter;
import tops.com.barberapp.dao.UserDao;
import tops.com.barberapp.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExistingUserFragment extends Fragment implements UserAdapter.UserClickListener {

    RecyclerView recyclerView;
    Context context;
    ArrayList<User> users;
    UserDao dao;

    public ExistingUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_existing_user, container, false);
        context=getActivity();
        // Inflate the layout for this fragment
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        dao=new UserDao(context);
        setAdapter();
        return view;
    }

    private void setAdapter() {
        users=dao.getUsers();
        UserAdapter adapter=new UserAdapter(users,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onUserClick(User user) {
        new AlertDialog.Builder(context)
                .setTitle("Select Operation")
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Fragment fragment=new RegistrationFragment();
                        Bundle bundle=new Bundle();
                        bundle.putParcelable("user",user);
                        fragment.setArguments(bundle);
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frm_layout,fragment)
                                .addToBackStack(LoginFragment.class.getName())
                                .commit();
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int count=dao.deleteUser(user.getId());
                        if(count>0) {
                            Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show();
                            setAdapter();
                        }
                        else
                            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
