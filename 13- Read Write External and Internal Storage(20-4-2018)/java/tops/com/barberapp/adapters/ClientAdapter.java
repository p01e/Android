package tops.com.barberapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tops.com.barberapp.R;
import tops.com.barberapp.model.Client;

/**
 * Created by Admin on 4/6/2018.
 */

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {

    public interface ClientCallBack
    {
        void clientClick(Client client);
    }

    ClientCallBack callBack;
    private ArrayList<Client> clients;
    public ClientAdapter(ArrayList<Client> clients, ClientCallBack callBack) {
        this.clients=clients;
        this.callBack=callBack;
    }

    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.barber_row_item,parent,false);
        ClientViewHolder viewHolder=new ClientViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClientViewHolder holder, int position) {
        Client client=clients.get(position);
        holder.tvName.setText(client.getName());
        holder.tvMobile.setText(client.getMobile());
        holder.tvEmail.setText(client.getEmai());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.clientClick(client);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvMobile, tvEmail;
        View view;
        public ClientViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            tvName=itemView.findViewById(R.id.tvName);
            tvMobile=itemView.findViewById(R.id.tvMobile);
            tvEmail=itemView.findViewById(R.id.tvEmail);
        }
    }
}
