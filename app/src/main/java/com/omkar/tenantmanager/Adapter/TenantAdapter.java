package com.omkar.tenantmanager.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tenantmanager.R;
import com.omkar.tenantmanager.activity.RentListActivity;
import com.omkar.tenantmanager.activity.UpdateActivity;
import com.omkar.tenantmanager.database.Database;
import com.omkar.tenantmanager.entity.Tenant;

import java.util.ArrayList;

public class TenantAdapter extends BaseAdapter {


    ArrayList<Tenant> listOfTenant;
    Context mContext;

    public TenantAdapter(ArrayList<Tenant> list , Context context)
    {
        this.listOfTenant = list;
        this.mContext = context;
    }
    @Override
    public int getCount() {

        return listOfTenant.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        convertView = View.inflate(mContext , R.layout.list_item_tenant_activity  , null);

        TextView textName = convertView.findViewById(R.id.textName);
        TextView textStartDate = convertView.findViewById(R.id.textStartDate);
        ImageView imgUpdate = convertView.findViewById(R.id.imgUpdate);
        ImageView imgDelete = convertView.findViewById(R.id.imgDelete);



        Tenant c = listOfTenant.get(position);
        textName.setText(c.getTenantName());
        textStartDate.setText(c.getStartDate());


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(mContext, RentListActivity.class);
                i.putExtra("TENANT" , listOfTenant.get(position));
                mContext.startActivity(i);

     }
       });

        imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, UpdateActivity.class);
                i.putExtra("TENANT" , listOfTenant.get(position));
                mContext.startActivity(i);

            }
        });


        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try (Database database = new Database(mContext)) {
                    database.deleteTenant(c.getTenantName());
                }
                listOfTenant.remove(position);
                notifyDataSetChanged(); // list has been updated , refresh all views

            }
        });


        return convertView;

    }


}


