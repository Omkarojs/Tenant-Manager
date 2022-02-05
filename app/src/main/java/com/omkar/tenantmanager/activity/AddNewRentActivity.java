package com.omkar.tenantmanager.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tenantmanager.R;
import com.omkar.tenantmanager.database.Database;
import com.omkar.tenantmanager.entity.Rent;
import com.omkar.tenantmanager.entity.Tenant;

import java.util.Objects;


public class AddNewRentActivity extends AppCompatActivity {

    EditText edtLightBill,edtWaterBill, edtStatus;
    TextView textDate,textTenantName,textRentAmount;
    ImageView imgCal;
    Button btnSubmit;
    Database db;

    Tenant tenant;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.add_new_rent_activity);

        textRentAmount =findViewById(R.id.textRentAmount);
        edtLightBill = findViewById(R.id.edtLightBill);
        edtWaterBill = findViewById(R.id.edtWaterBill);
        textDate = findViewById(R.id.textDate);
        textTenantName =findViewById(R.id.textTenantName);
        edtStatus =findViewById(R.id.edtStatus);
        btnSubmit =findViewById(R.id.btnSubmit);
        imgCal = findViewById(R.id.imgCal);

       tenant = (Tenant) getIntent().getSerializableExtra("TENANT");

       textTenantName.setText(tenant.getTenantName());
       textRentAmount.setText(tenant.getRent());

        db = new Database(this);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String tenantName = textTenantName.getText().toString();
                String lightBill = edtLightBill.getText().toString();
                String date = textDate.getText().toString();
                String waterBill = edtWaterBill.getText().toString();
                String rentAmount = textRentAmount.getText().toString();
                String status = edtStatus.getText().toString();


                Rent r = new Rent();
                r.setLightBill(lightBill);
                r.setTenantName(tenantName);
                r.setWaterBill(waterBill);
                r.setRentAmount(rentAmount);
                r.setRentDate(date);
                r.setStatus(status);

                Database database = new Database(AddNewRentActivity.this);
                database.insertRent(r);

                if(tenantName.equals("") || lightBill.equals("") || date.equals("") || status.equals("") || waterBill.equals("") || rentAmount.equals("") ) {
                    Toast.makeText(AddNewRentActivity.this,"Submit  All Details", Toast.LENGTH_SHORT).show();
                } else {

                    textTenantName.setText("");
                    textDate.setText("");
                    textRentAmount.setText("");
                    edtLightBill.setText("");
                    edtWaterBill.setText("");
                    edtStatus.setText("");


                }

                finish();


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

        DatePickerDialog dpd = new DatePickerDialog(AddNewRentActivity.this, new DatePickerDialog.OnDateSetListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1 ;
                textDate.setText(dayOfMonth + "/" + month + "/" + year );

            }
        } , 2022 , 12 , 12);

        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());


        dpd.show();

    }

}

