package com.omkar.tenantmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.tenantmanager.R;
import com.omkar.tenantmanager.Adapter.PaymentsAdapter;
import com.omkar.tenantmanager.database.Database;
import com.omkar.tenantmanager.entity.Payments;


import java.util.ArrayList;


public class PaymentListActivity extends AppCompatActivity {

    private ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_list_activity);

        listView = findViewById(R.id.list_view);
    }

    protected void onResume() {
        super.onResume();

        Database database = new Database(PaymentListActivity.this);
        ArrayList<Payments> listOfPayments = database.getPaymentsList();

        PaymentsAdapter paymentsAdapter = new PaymentsAdapter(listOfPayments , PaymentListActivity.this);
        listView.setAdapter(paymentsAdapter);


    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater  = getMenuInflater();
        inflater.inflate(R.menu.menu_payments_list_activity , menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        if (item.getItemId() == R.id.action_addData){
            Intent i = new Intent(PaymentListActivity.this , PaymentsActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
