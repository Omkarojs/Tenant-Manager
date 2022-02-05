package com.omkar.tenantmanager.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
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
import com.omkar.tenantmanager.entity.Tenant;

import java.util.Objects;


public class AddNewTenantActivity extends AppCompatActivity {

    EditText edtTenantName,edtMobileNumber,edtHouseNumber,edtJob, edtRent, edtDeposit;
    TextView textStartDate;
    ImageView imgCal;

    Button btnSubmitData;
    Database db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.add_new_tenant_activity);

        edtTenantName = findViewById(R.id.edtTenantName);
        edtMobileNumber = findViewById(R.id.edtMobileNumber);
        edtHouseNumber = findViewById(R.id.edtHouseNumber);
        edtJob = findViewById(R.id.edtJob);
        edtRent =findViewById(R.id.edtRent);
        edtDeposit =findViewById(R.id.edtDeposit);
        textStartDate =findViewById(R.id.textStartDate);
        imgCal = findViewById(R.id.imgCal);

        btnSubmitData =findViewById(R.id.btnSubmitData);

        db = new Database(this);

        btnSubmitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tenantName = edtTenantName.getText().toString();
                String houseNumber = edtHouseNumber.getText().toString();
                String deposit = edtDeposit.getText().toString();
                String job = edtJob.getText().toString();
                String mobileNumber = edtMobileNumber.getText().toString();
                String rent = edtRent.getText().toString();
                String startDate = textStartDate.getText().toString();

                Tenant t = new Tenant();
                t.setTenantName(tenantName);
                t.setDeposit(deposit);
                t.setHouseNumber(houseNumber);
                t.setJob(job);
                t.setMobileNumber(mobileNumber);
                t.setStartDate(startDate);
                t.setRent(rent);

                Database database = new Database(AddNewTenantActivity.this);
                database.insertTenant(t);

                if(tenantName.equals("") || deposit.equals("") || houseNumber.equals("") || job.equals("") || mobileNumber.equals("") || startDate.equals("") || rent.equals("") ) {
                    Toast.makeText(AddNewTenantActivity.this,"Submit  All Details", Toast.LENGTH_SHORT).show();
                } else {






                  edtTenantName.setText("");
                  textStartDate.setText("");
                  edtRent.setText("");
                  edtHouseNumber.setText("");
                  edtMobileNumber.setText("");
                  edtJob.setText("");
                  edtDeposit.setText("");
                }

                finish();
            }




        });

        textStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePicker();

            }
        });
    }

    private void showDatePicker(){

        DatePickerDialog dpd = new DatePickerDialog(AddNewTenantActivity.this, new DatePickerDialog.OnDateSetListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1 ;

                textStartDate.setText(dayOfMonth +  "/" +  month +  "/" +  year);
            }
        } , 2020 , 12 , 30);
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());

        dpd.show();

    }
}


