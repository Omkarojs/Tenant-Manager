package com.omkar.tenantmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tenantmanager.R;

import java.util.Objects;


public class FirstScreenActivity extends AppCompatActivity {


    CardView cardAddTenant, cardAddRent;
    TextView textAddTenant, textAddRent;
    ImageView imgAddTenant, imgAddRent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.first_screen_activity);

        cardAddRent   = findViewById(R.id.cardAddRent);
        cardAddTenant = findViewById(R.id.cardAddTenant);
        textAddTenant =findViewById(R.id.textAddTenant);
        textAddRent = findViewById(R.id.textAddRent);
        imgAddRent = findViewById(R.id.imgAddRent);
        imgAddTenant = findViewById(R.id.imgAddTenant);

           imgAddTenant.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {

               Intent i = new Intent(FirstScreenActivity.this , TenantListActivity.class);
               startActivity(i);

      }
});

     imgAddRent.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          Intent i= new Intent(FirstScreenActivity.this , PaymentListActivity.class);
          startActivity(i);


    }
});

    }
}
