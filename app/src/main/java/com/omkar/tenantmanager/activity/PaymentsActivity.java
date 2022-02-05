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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tenantmanager.R;
import com.omkar.tenantmanager.database.Database;
import com.omkar.tenantmanager.entity.Payments;

import java.io.FileNotFoundException;
import java.util.Objects;


public class PaymentsActivity extends AppCompatActivity {

        EditText edtTenantName, edtRentAmount, edtHouseNumber, edtMobileNumber,edtPlace;
        Button btnSubmit;
        ImageView imgCal;
        TextView textDate;
        Database db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.payments_activity);

        edtTenantName = findViewById(R.id.edtTenantName);
        edtRentAmount = findViewById(R.id.edtRentAmount);
        edtHouseNumber = findViewById(R.id.edtHouseNumber);
        edtMobileNumber = findViewById(R.id.edtMobileNumber);
        edtPlace =findViewById(R.id.edtPlace);
        textDate = findViewById(R.id.textDate);
        btnSubmit = findViewById(R.id.btnSubmit);
        imgCal = findViewById(R.id.imgCal);


        db = new Database(this);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenantName = edtTenantName.getText().toString();
                String houseNumber = edtHouseNumber.getText().toString();
                String mobileNumber = edtMobileNumber.getText().toString();
                String rentAmount = edtRentAmount.getText().toString();
                String place = edtPlace.getText().toString();
                String date = textDate.getText().toString();


                Intent i = new Intent(PaymentsActivity.this , PaymentListActivity.class);

                i.putExtra("tenantName" , tenantName);
                i.putExtra("houseNumber" , houseNumber);
                i.putExtra("mobileNumber" , mobileNumber);
                i.putExtra("rentAmount" , rentAmount);
                i.putExtra("place" , place);
                i.putExtra("date" , date);

                startActivity(i);
                finish();


                Payments p = new Payments();
                p.setTenantName(tenantName);
                p.setHouseNumber(houseNumber);
                p.setMobileNumber(mobileNumber);
                p.setRentAmount(rentAmount);
                p.setPaymentDate(date);
                p.setPlace(place);

                Database database = new Database(PaymentsActivity.this);
                database.insertPayments(p);

                if(tenantName.equals("") ||   houseNumber.equals("")  || mobileNumber.equals("") || date.equals("") ||  rentAmount.equals("") || place.equals("") ) {
                    Toast.makeText(PaymentsActivity.this, "Submit  All Details", Toast.LENGTH_SHORT).show();
                } else {






                    edtTenantName.setText("");
                    edtHouseNumber.setText("");
                    edtMobileNumber.setText("");
                    edtRentAmount.setText("");
                    edtPlace.setText("");
                    textDate.setText("");


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

        DatePickerDialog dpd = new DatePickerDialog(PaymentsActivity.this, new DatePickerDialog.OnDateSetListener() {

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



