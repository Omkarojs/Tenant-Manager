package com.omkar.tenantmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.omkar.tenantmanager.entity.Payments;
import com.omkar.tenantmanager.entity.Rent;
import com.omkar.tenantmanager.entity.Tenant;

import java.util.ArrayList;


public class Database extends SQLiteOpenHelper {

    private static String DB_NAME = "tenant_db";
    private static int DB_VERSION = 12;
    private Context mContext;


    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table tenant(tenantname text(30) ,   mobilenumber  text (30) , housenumber text(10) ,  job text(20), rent text(10) ,  deposit text(10) , startdate text(20))";

        db.execSQL(query);

        String query2 = "create table rent(tenantname text(30) ,   lightbill  text (30) , date text(20) ,  waterbill text(20), rentamount text(10), status text(20) )";

        db.execSQL(query2);

        String query3 = "create table payments(tenantname text(30) ,  mobilenumber  text (30),  paymentdate text(20) ,   rentamount text(10), housenumber text(10) , place text(20) ) ";

        db.execSQL(query3);

    }


    public Boolean insertTenant(Tenant t){

        ContentValues values = new ContentValues();
        values.put("tenantName" , t.getTenantName());

        values.put("deposit" , t.getDeposit());
        values.put("mobileNumber", t.getMobileNumber());
        values.put("houseNumber" , t.getHouseNumber());
        values.put("job",t.getJob());
        values.put("rent" , t.getRent());
        values.put("startDate" , t.getStartDate());

        SQLiteDatabase db = getWritableDatabase();

        long id = db.insert("tenant" , null , values);

        return id != -1;
    }



    public ArrayList<Tenant> getTenantList(){
        SQLiteDatabase db  = getReadableDatabase();

        ArrayList<Tenant> listOfTenant  = new ArrayList<>();

        Cursor c = db.query("Tenant" , null , null , null , null , null , null);

        if (c.getCount()>0 ){

            c.moveToFirst();

            while (!c.isAfterLast()){

                String tenantName = c.getString(0);
                String mobileNumber = c.getString(1);
                String deposit = c.getString(5);
                String job = c.getString(3);
                String rent = c.getString(4);
                String houseNumber = c.getString(2);
                String startDate = c.getString(6);


                Tenant t = new Tenant();
                t.setTenantName(tenantName);
                t.setDeposit(deposit);
                t.setHouseNumber(houseNumber);
                t.setJob(job);
                t.setMobileNumber(mobileNumber);
                t.setStartDate(startDate);
                t.setRent(rent);


                listOfTenant.add(t);

                c.moveToNext();

            }


        }

        return listOfTenant;

    }



    public void updateTenant(Tenant t){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cva = new ContentValues();
        cva.put("tenantName" , t.getTenantName());

        cva.put("deposit" , t.getDeposit());
        cva.put("mobileNumber", t.getMobileNumber());
        cva.put("houseNumber" , t.getHouseNumber());
        cva.put("job",t.getJob());
        cva.put("rent" , t.getRent());
        cva.put("startDate" , t.getStartDate());

      String[] args = {t.getMobileNumber()};

     int count = db.update("tenant" , cva , "mobileNumber=?" , args );
        Toast.makeText(mContext , "Details Updated"  + count, Toast.LENGTH_LONG).show();
    }


    public void deleteTenant(String tenantName){
        SQLiteDatabase db = getWritableDatabase();

        String[] args = new String[1];
        args[0] = tenantName;

        int count = db.delete("tenant" , "tenantName=?" , args );
        Toast.makeText(mContext , "Details Deleted"  +  count, Toast.LENGTH_LONG).show();
    }







    public void updateRent(Rent r){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cav = new ContentValues();
        cav.put("tenantName" , r.getTenantName());
        cav.put("rentAmount" , r.getRentAmount());
        cav.put("Date" , r.getRentDate());
        cav.put("status" ,r.getStatus());

        String[] args = {r.getTenantName()};

        int count = db.update("rent" , cav , "tenantName=?" , args );
        Toast.makeText(mContext , "Details Updated " + count , Toast.LENGTH_LONG).show();
    }



    public boolean insertRent(Rent r){

        ContentValues values = new ContentValues();
        values.put("tenantName" , r.getTenantName());
        values.put("lightBill", r.getLightBill());
        values.put("waterBill", r.getWaterBill());
        values.put("rentAmount", r.getRentAmount());
        values.put("date", r.getRentDate());
        values.put("status" ,r.getStatus() );

        SQLiteDatabase db = getWritableDatabase();

        long id = db.insert("rent" , null , values);


        return id != -1;

    }



    public ArrayList<Rent> getRentList(String name){
        SQLiteDatabase db  = getReadableDatabase();

        ArrayList<Rent> listOfRent  = new ArrayList<>();

        String[] args = {name};

        Cursor b = db.query("Rent" , null , "tenantname=?" , args , null , null , null);

        if (b.getCount()>0 ){

            b.moveToFirst();

            while (!b.isAfterLast()){

                String tenantName = b.getString(0);
                String lightBill =  b.getString(1);
                String waterBill = b.getString(3);
                String date = b.getString(2);
                String rentAmount = b.getString(4);
                String status = b.getString( 5);

                Rent r = new Rent();
                r.setTenantName(tenantName);
                r.setRentDate(date);
                r.setLightBill(lightBill);
                r.setWaterBill(waterBill);
                r.setRentAmount(rentAmount);
                r.setStatus(status);


                listOfRent.add(r);

                b.moveToNext();

            }


        }

        return listOfRent;

    }



    public Boolean insertPayments(Payments p){

        ContentValues values = new ContentValues();
        values.put("tenantName" , p.getTenantName());
        values.put("mobileNumber", p.getMobileNumber());
        values.put("houseNumber" , p.getHouseNumber());
        values.put("rentAmount" , p.getRentAmount());
        values.put("place" , p.getPlace());
        values.put("paymentDate" , p.getPaymentDate());

        SQLiteDatabase db = getWritableDatabase();

        long id = db.insert("payments" , null , values);

        return id != -1;

    }



    public ArrayList<Payments> getPaymentsList(){
        SQLiteDatabase db  = getReadableDatabase();

        ArrayList<Payments> listOfPayments  = new ArrayList<>();

        Cursor c = db.query("payments" , null , null , null , null , null , null);

        if (c.getCount()>0 ){

            c.moveToFirst();

            while (!c.isAfterLast()){

                String tenantName = c.getString(0);
                String mobileNumber = c.getString(1);
                String rentAmount = c.getString(3);
                String houseNumber = c.getString(4);
                String paymentDate = c.getString(2);
                String place = c.getString(5);

                Payments p = new Payments();
                p.setTenantName(tenantName);
                p.setHouseNumber(houseNumber);
                p.setMobileNumber(mobileNumber);
                p.setPaymentDate(paymentDate);
                p.setPlace(place);
                p.setRentAmount(rentAmount);

                listOfPayments.add(p);

                c.moveToNext();

            }


        }

        return listOfPayments;

    }


    public void updatePayments(Payments p){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cva = new ContentValues();
        cva.put("tenantName" , p.getTenantName());
        cva.put("mobileNumber", p.getMobileNumber());
        cva.put("houseNumber" , p.getHouseNumber());
        cva.put("rentAmount" , p.getRentAmount());
        cva.put("paymentDate" , p.getPaymentDate());
        cva.put("place" , p.getPlace());

        String[] args = {p.getHouseNumber()};

        int count = db.update("payments" , cva , "houseNumber=?" , args );
        Toast.makeText(mContext , "Details Updated"  + count , Toast.LENGTH_LONG).show();
    }


    public void deletePayments(String tenantName){
        SQLiteDatabase db = getWritableDatabase();

        String[] args = new String[1];
        args[0] = tenantName;

        int count = db.delete("payments" , "tenantName=?" , args );
        Toast.makeText(mContext , "Details Deleted"  + count , Toast.LENGTH_LONG).show();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists tenant");
        db.execSQL("drop table if  exists rent");
        db.execSQL("drop table if exists payments");

    }
}
