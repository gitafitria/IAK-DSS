package com.gita.spk_saw;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Gusdita on 8/7/2017.
 */

public class DBController extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "spk_db.db";
    public static final int DATABASE_VERSION = 1;

    public DBController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase dataBiodata) {

        String query;

        query = "CREATE TABLE alternative (id integer primary key, nama varchar(255), tempatlahir varchar(50), tanggallahir DATETIME, jurusan varchar(20))";
        Log.d("create table biodata", query);
        dataBiodata.execSQL(query);

            query = "INSERT INTO alternative (id, nama, tempatlahir, tanggallahir, jurusan) VALUES ('1', 'Nugie','Blahkiuh','1997-07-04','Informatika')";
            Log.d("Insert Data", query);
            //Log.d("insert data");
            dataBiodata.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dataBiodata, int oldVersion, int newVersion) {

    }
}
