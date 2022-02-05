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
import com.omkar.tenantmanager.entity.Payments;

import java.util.Objects;


public class UpdateReceiptActivity extends AppCompatActivity {

    EditText edtTenantName, edtRentAmount, edtHouseNumber, edtMobileNumber, edtPlace;
    TextView textDate;
    Button btnUpdateData;
    ImageView imgCal;
    Payments payments;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.update_receipt_activity);

        edtTenantName = findViewById(R.id.edtTenantName);
        edtRentAmount = findViewById(R.id.edtRentAmount);
        edtHouseNumber = findViewById(R.id.edtHouseNumber);
        edtMobileNumber = findViewById(R.id.edtMobileNumber);
        edtPlace =findViewById(R.id.edtPlace);
        textDate = findViewById(R.id.textDate);
        btnUpdateData = findViewById(R.id.btnUpdateData);
        imgCal = findViewById(R.id.imgCal);


        Intent i = getIntent();

        payments = (Payments) i.getSerializableExtra("PAYMENTS");

        edtTenantName.setText(payments.getTenantName());
        edtRentAmount.setText((payments.getRentAmount()));
        edtHouseNumber.setText(payments.getHouseNumber());
        edtMobileNumber.setText(payments.getMobileNumber());
        edtPlace.setText(payments.getPlace());
        textDate.setText(payments.getPaymentDate());

        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Payments m = new Payments();

                m.setTenantName(edtTenantName.getText().toString());
                m.setHouseNumber(edtHouseNumber.getText().toString());
                m.setMobileNumber(edtMobileNumber.getText().toString());
                m.setRentAmount(edtRentAmount.getText().toString());
                m.setPaymentDate(textDate.getText().toString());
                m.setPlace(edtPlace.getText().toString());

                Database database = new Database(UpdateReceiptActivity.this);

                database.updatePayments(m);


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

        DatePickerDialog dpd = new DatePickerDialog(UpdateReceiptActivity.this, new DatePickerDialog.OnDateSetListener() {

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
