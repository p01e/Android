package tops.com.barberapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tops.com.barberapp.R;
import tops.com.barberapp.model.User;

/**
 * Created by Admin on 4/16/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    public interface UserClickListener
    {
        public void onUserClick(User user);
    }

    private ArrayList<User> users;
    private UserClickListener listener;
    public UserAdapter(ArrayList<User> users, UserClickListener listener) {
        this.users=users;
        this.listener=listener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false);
        UserViewHolder viewHolder=new UserViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user=users.get(position);
        holder.tvName.setText(user.getFirstName()+" "+user.getLastName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        View view;
        public UserViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            tvName=itemView.findViewById(R.id.tvName);
        }
    }
}
