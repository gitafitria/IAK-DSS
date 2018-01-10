package com.gita.spk_saw;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Gusdita on 8/7/2017.
 */

public class DBController extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "spk_db.db";
    public static final int DATABASE_VERSION = 1;

    public DBController(Activity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase dataBiodata) {

        String query;

        query = "CREATE TABLE alternative (id INTEGER PRIMARY KEY, nama VARCHAR(255), tempatlahir VARCHAR(20), tanggallahir DATETIME, jurusan VARCHAR(20))";
        dataBiodata.execSQL(query);
        Log.d("Buat Tabel Alternative", query);

        query = "INSERT INTO alternative(id, nama, tempatlahir, tanggallahir, jurusan) " +
                "VALUES ('1','Satria','Blahkiuh','1997-07-04','MTI')";
        dataBiodata.execSQL(query);
        Log.d("Tes insert data", query);

        query = "INSERT INTO alternative(id, nama, tempatlahir, tanggallahir, jurusan) " +
                "VALUES ('2','Gita','Denpasar','1996-07-04','MTI')";
        dataBiodata.execSQL(query);
        Log.d("Tes insert data", query);

        query = "CREATE TABLE kriteria (idkriteria INTEGER PRIMARY KEY, waktu_organisasi DOUBLE, tingkatan_kuliah VARCHAR(20), kegiatan VARCHAR(50), jarak_rumah DOUBLE, id INTEGER)";
        dataBiodata.execSQL(query);
        Log.d("Buat Tabel Kriteria", query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase dataBiodata, int oldVersion, int newVersion) {

    }
}
