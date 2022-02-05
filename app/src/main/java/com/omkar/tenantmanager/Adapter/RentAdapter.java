package com.omkar.tenantmanager.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tenantmanager.R;
import com.omkar.tenantmanager.activity.UpdateRentActivity;
import com.omkar.tenantmanager.entity.Rent;

import java.util.ArrayList;

public class RentAdapter extends BaseAdapter {

    ArrayList<Rent> listOfRent;
    Context mContext;

    public RentAdapter(ArrayList<Rent> list, Context context) {
        this.listOfRent = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return listOfRent.size();
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


        convertView = View.inflate(mContext, R.layout.list_item_rent_activity, null);

        TextView textTenantName = convertView.findViewById(R.id.textTenantName);

        TextView textRentAmount = convertView.findViewById(R.id.textRentAmount);

        TextView textLightBill = convertView.findViewById(R.id.textLightBill);

        TextView textWaterBill = convertView.findViewById(R.id.textWaterBill);

        TextView textDate = convertView.findViewById(R.id.textDate);

        TextView textStatus = convertView.findViewById(R.id.textStatus);


        Rent b = listOfRent.get(position);
        textTenantName.setText(b.getTenantName());
        textRentAmount.setText(b.getRentAmount());
        textLightBill.setText(b.getLightBill());
        textWaterBill.setText(b.getWaterBill());
        textDate.setText(b.getRentDate());
        textStatus.setText(b.getStatus());


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, UpdateRentActivity.class);
                i.putExtra("RENT", listOfRent.get(position));
                mContext.startActivity(i);

            }

       });

        return convertView;


    }
}