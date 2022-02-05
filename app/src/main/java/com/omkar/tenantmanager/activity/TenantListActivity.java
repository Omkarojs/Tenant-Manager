package com.omkar.tenantmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tenantmanager.R;
import com.omkar.tenantmanager.Adapter.TenantAdapter;
import com.omkar.tenantmanager.database.Database;
import com.omkar.tenantmanager.entity.Tenant;

import java.util.ArrayList;


public class TenantListActivity extends AppCompatActivity {

    private ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tenant_list_activity);

        listView = findViewById(R.id.list_view);
    }

    protected void onResume() {
        super.onResume();

        Database database = new Database(TenantListActivity.this);
        ArrayList<Tenant> listOfTenant = database.getTenantList();

        TenantAdapter tenantAdapter = new TenantAdapter(listOfTenant, TenantListActivity.this);
        listView.setAdapter(tenantAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater  = getMenuInflater();
        inflater.inflate(R.menu.menu_tenant_list_activity , menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        if (item.getItemId() == R.id.action_add){
            Intent i = new Intent(TenantListActivity.this , AddNewTenantActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}






