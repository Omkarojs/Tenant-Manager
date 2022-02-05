package com.omkar.tenantmanager.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
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
import com.omkar.tenantmanager.entity.Rent;

import java.util.Objects;


public class UpdateRentActivity extends AppCompatActivity {

     EditText edtTenantName, edtRentAmount, edtStatus;
     TextView textDate;
     Button btnSubmit;
     ImageView imgCal;
     Rent rent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.update_new_rent_activity);

        edtTenantName = findViewById(R.id.edtTenantName);
        edtRentAmount = findViewById(R.id.edtRentAmount);
        textDate = findViewById(R.id.textDate);
        edtStatus =findViewById(R.id.edtStatus);
        btnSubmit  = findViewById(R.id.btnSubmit);
        imgCal = findViewById(R.id.imgCall);

        Intent i = getIntent();

        rent = (Rent) i.getSerializableExtra("RENT");

        edtRentAmount.setText(rent.getRentAmount());
        edtTenantName.setText(rent.getTenantName());
        edtStatus.setText(rent.getStatus());
        textDate.setText(rent.getRentDate());


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rent r = new Rent();

                r.setTenantName(edtTenantName.getText().toString());
                r.setRentDate(textDate.getText().toString() );
                r.setRentAmount(edtRentAmount.getText().toString());
                r.setStatus(edtStatus.getText().toString());

                Database database = new Database(UpdateRentActivity.this);

                database.updateRent(r);

            }
        });

        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePicker();

            }
        });
    }

    public void showDatePicker(){

        DatePickerDialog dpd = new DatePickerDialog(UpdateRentActivity.this, new DatePickerDialog.OnDateSetListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1 ;
                textDate.setText(dayOfMonth + "/" + month + "/" + year);
            }
        } , 2022 , 12 , 12);

        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());

        dpd.show();

    }




}

