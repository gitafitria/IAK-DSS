//package com.gita.spk_saw;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
///**
// * Created by Gusdita on 8/7/2017.
// */
//
//public class DBController extends SQLiteOpenHelper{
//
//    public static final String DATABASE_NAME = "spk_db.db";
//    public static final int DATABASE_VERSION = 1;
//
//    public DBController(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase dataBiodata) {
//
//        String query;
//
//        query = "CREATE TABLE user (id integer primary key, nama varchar(255), username varchar(10), password varchar(100)";
//        Log.d("create table biodata", query);
//        dataBiodata.execSQL(query);
//
//
//            query = "INSERT INTO user (nim, nama, jurusan, email, nohp) VALUES ('10101010', 'Gusdita', 'TI-MTI', 'ida.bagus.anandita@gmail.com', '081805400517')";
//            Log.d("Insert Data", query);
//            //Log.d("insert data");
//            dataBiodata.execSQL(query);
//
//
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase dataBiodata, int oldVersion, int newVersion) {
//
//    }
//}
