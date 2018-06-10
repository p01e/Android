package tops.com.barberapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tops.com.barberapp.R;
import tops.com.barberapp.model.Barber;

/**
 * Created by Admin on 4/6/2018.
 */

public class BarberBaseAdapter extends BaseAdapter {

    private ArrayList<Barber> barbers;
    private Context context;

    public BarberBaseAdapter(Context context, ArrayList<Barber> barbers) {
        this.barbers=barbers;
        this.context=context;
    }

    @Override
    public int getCount() {
        return barbers.size();
    }

    @Override
    public Object getItem(int position) {
        return barbers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.barber_row_item,
                    parent,false);
        }
        Barber barber=barbers.get(position);
        TextView tvName=convertView.findViewById(R.id.tvName);
        TextView tvMobile=convertView.findViewById(R.id.tvMobile);
        TextView tvEmail=convertView.findViewById(R.id.tvEmail);
        tvName.setText(barber.getName());
        tvMobile.setText(barber.getMobile());
        tvEmail.setText(barber.getEmail());
        return convertView;
    }
}
