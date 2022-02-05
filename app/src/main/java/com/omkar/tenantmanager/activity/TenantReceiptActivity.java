package com.omkar.tenantmanager.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tenantmanager.R;
import com.omkar.tenantmanager.entity.Payments;


import java.io.File;


public class TenantReceiptActivity extends AppCompatActivity {



    TextView textTenantNameValue, textRentAmountValue, textMobileNumberValue, textHouseNumberValue, textPlaceValue, textDateValue;
    TextView textAppName, textReceiptHeader;
    Payments payments;
    ImageView imgShare;
    LinearLayout rootContent;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        setContentView(R.layout.tenant_receipt_activity);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder(); StrictMode.setVmPolicy(builder.build());


        textAppName = findViewById(R.id.textAppName);
        textReceiptHeader = findViewById(R.id.textReceiptHeader);
        textTenantNameValue = findViewById(R.id.textTenantNameValue);
        textRentAmountValue = findViewById(R.id.textRentAmountValue);
        textHouseNumberValue = findViewById(R.id.textHouseNumberValue);
        textMobileNumberValue = findViewById(R.id.textMobileNumberValue);
        textPlaceValue =findViewById(R.id.textPlaceValue);
        textDateValue = findViewById(R.id.textDateValue);
        imgShare = findViewById(R.id.imgShare);

        rootContent = (LinearLayout) findViewById(R.id.root_content);

        Intent i = getIntent();

        payments = (Payments) i.getSerializableExtra("Payments");


        textTenantNameValue.setText(payments.getTenantName());
        textRentAmountValue.setText(payments.getRentAmount());
        textMobileNumberValue.setText(payments.getMobileNumber());
        textPlaceValue.setText(payments.getPlace());
        textHouseNumberValue.setText(payments.getHouseNumber());
        textDateValue.setText(payments.getPaymentDate());



        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgShare.setVisibility(View.GONE);
                takeScreenshot(ScreenShotType.FULL);
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        imgShare.setVisibility(View.VISIBLE);
    }

    private void takeScreenshot(ScreenShotType screenshotType) {
        Bitmap b = null;
        if (screenshotType == ScreenShotType.FULL) {//If Screenshot type is FULL take full page screenshot i.e our root content.
            b = ScreenshotUtils.getScreenShot(rootContent);

        }

        if (b != null) {
            showScreenShotImage(b);//show bitmap over imageview
            File saveFile = ScreenshotUtils.getMainDirectoryName(this);//get the path to save screenshot
            File file = ScreenshotUtils.store(b, "screenshot" + screenshotType + ".jpg", saveFile);//save the screenshot to selected path
            shareScreenshot(file);//finally share screenshot
        } else

            Toast.makeText(this, R.string.screenshot_take_failed, Toast.LENGTH_SHORT).show();
    }

    private void showScreenShotImage(Bitmap b) {

    }

    private void shareScreenshot(File file) {
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.sharing_text));
        intent.putExtra(Intent.EXTRA_STREAM, uri);//pass uri here
        startActivity(Intent.createChooser(intent, getString(R.string.share_title)));
    }


}

