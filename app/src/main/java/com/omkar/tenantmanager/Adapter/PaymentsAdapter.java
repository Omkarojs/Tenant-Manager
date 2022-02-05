package com.omkar.tenantmanager.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tenantmanager.R;
import com.omkar.tenantmanager.activity.TenantReceiptActivity;
import com.omkar.tenantmanager.activity.UpdateReceiptActivity;
import com.omkar.tenantmanager.database.Database;
import com.omkar.tenantmanager.entity.Payments;

import java.io.Serializable;
import java.util.ArrayList;



public class PaymentsAdapter extends BaseAdapter {


        ArrayList<Payments> listOfPayments;
        Context mContext;
        public PaymentsAdapter(ArrayList<Payments> list , Context context){
            this.listOfPayments = list;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return listOfPayments.size();
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

            convertView = View.inflate(mContext , R.layout.list_item_payments_activity , null);

            TextView textName = convertView.findViewById(R.id.textName);
            TextView textRentDate = convertView.findViewById(R.id.textRentDate);
            ImageView imgUpdateData = convertView.findViewById(R.id.imgUpdateData);

            ImageView imgDelete = convertView.findViewById(R.id.imgDelete);

       Payments  p = listOfPayments.get(position);
            textName.setText(p.getTenantName());
            textRentDate.setText(p.getPaymentDate());

            imgUpdateData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, UpdateReceiptActivity.class);
                    i.putExtra("PAYMENTS" , listOfPayments.get(position));
                    mContext.startActivity(i);

                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try (Database database = new Database(mContext)) {
                        database.deletePayments(p.getTenantName());
                    }
                    listOfPayments.remove(position);
                    notifyDataSetChanged();

                }
            });








            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i= new Intent(mContext, TenantReceiptActivity.class);
                    i.putExtra("Payments" , listOfPayments.get(position));
                    mContext.startActivity(i);

                }
            });

            return convertView;
        }
    }


