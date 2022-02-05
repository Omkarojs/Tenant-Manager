package com.omkar.tenantmanager.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tenantmanager.R;
import com.omkar.tenantmanager.database.Database;
import com.omkar.tenantmanager.entity.Tenant;


import java.util.ArrayList;
import java.util.Objects;


public class UpdateActivity extends AppCompatActivity {

    EditText edtTenantName, edtMobileNumber, edtHouseNumber, edtJob, edtRent, edtDeposit;

    ImageView imgCall;
    TextView textStartDate;
    Button btnUpdate;
    Tenant tenant;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.update_new_tenant_activity);


        edtTenantName = findViewById(R.id.edtTenantName);
        edtMobileNumber = findViewById(R.id.edtMobileNumber);
        edtHouseNumber = findViewById(R.id.edtHouseNumber);
        edtJob = findViewById(R.id.edtJob);
        edtRent = findViewById(R.id.edtRent);
        edtDeposit = findViewById(R.id.edtDeposit);
        textStartDate = findViewById(R.id.textStartDate);
        btnUpdate =findViewById(R.id.btnUpdate);
        imgCall = findViewById(R.id.imgCall);

        Intent i = getIntent();

        tenant = (Tenant) i.getSerializableExtra("TENANT");

        edtDeposit.setText(tenant.getDeposit());
        edtHouseNumber.setText(tenant.getHouseNumber());
        edtJob.setText(tenant.getJob());
        edtRent.setText(tenant.getRent());
        edtMobileNumber.setText(tenant.getMobileNumber());
        edtTenantName.setText(tenant.getTenantName());
        textStartDate.setText(tenant.getStartDate());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Tenant n = new Tenant();

                n.setDeposit(edtDeposit.getText().toString());
                n.setHouseNumber(edtHouseNumber.getText().toString());
                n.setJob(edtJob.getText().toString());
                n.setRent(edtRent.getText().toString());
                n.setMobileNumber(edtMobileNumber.getText().toString());
                n.setTenantName(edtTenantName.getText().toString());
                n.setStartDate(textStartDate.getText().toString());

                Database database = new Database(UpdateActivity.this);

                database.updateTenant(n);


            }

        });

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Intent.ACTION_CALL);

               Intent i = new Intent();


                i.setData(Uri.parse("tel:/" + tenant.getMobileNumber()));
                 startActivity(i);


            }
        });



        textStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePicker();

            }
        });
    }

    public void showDatePicker(){

        DatePickerDialog dpd = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1 ;
                textStartDate.setText(dayOfMonth + "/" + month + "/" + year);
            }
        } , 2022 , 12 , 12);
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());

        dpd.show();

    }



    }

