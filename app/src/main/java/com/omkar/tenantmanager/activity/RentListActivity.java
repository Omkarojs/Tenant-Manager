package com.omkar.tenantmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;



import com.example.tenantmanager.R;
import com.omkar.tenantmanager.Adapter.RentAdapter;
import com.omkar.tenantmanager.database.Database;
import com.omkar.tenantmanager.entity.Rent;
import com.omkar.tenantmanager.entity.Tenant;

import java.util.ArrayList;


public class RentListActivity extends AppCompatActivity {

    private ListView listView;
    Tenant tenant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_list_activity);

        listView = findViewById(R.id.list_view);

        tenant = (Tenant) getIntent().getSerializableExtra("TENANT");


    }

    protected void onResume() {
        super.onResume();

        Database database = new Database(RentListActivity.this);
        ArrayList<Rent> listOfRent = database.getRentList(tenant.getTenantName());

        RentAdapter rentAdapter = new RentAdapter(listOfRent, RentListActivity.this);
        listView.setAdapter(rentAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater  = getMenuInflater();
        inflater.inflate(R.menu.menu_rent_list_activity , menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        if (item.getItemId() == R.id.action_addRentList){
            Intent i = new Intent(RentListActivity.this , AddNewRentActivity.class);
            i.putExtra("TENANT" , tenant);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
