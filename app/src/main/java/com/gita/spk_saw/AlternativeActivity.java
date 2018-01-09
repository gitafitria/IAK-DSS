package com.gita.spk_saw;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AlternativeActivity extends AppCompatActivity {

    DBController koneksiDB;

    ListView lvData;
    Button btnAdd;

    protected Cursor cursor;
    public static AlternativeActivity alternativeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternative);

        lvData = (ListView) findViewById(R.id.lvData);
        btnAdd = (Button)findViewById(R.id.btnAddAlternatif);

        koneksiDB = new DBController(this);

        TampilData();
        alternativeActivity = this;

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAddAlternative = new Intent(AlternativeActivity.this, AddAlternative.class);
                startActivity(toAddAlternative);
            }
        });
    }

    void TampilData() {
        String querySel;
        querySel = "SELECT * FROM alternative";
        SQLiteDatabase db = koneksiDB.getReadableDatabase();
        cursor = db.rawQuery(querySel, null);

        final ArrayList<HashMap<String, String>> daftarMHS;
        daftarMHS = new ArrayList<HashMap<String, String>>();

        Integer i = 1;

        if (cursor.moveToFirst() ){
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("key_no", cursor.getString(0));
                map.put("key_nama", cursor.getString(1));
                map.put("key_tempat", cursor.getString(2));
                map.put("key_tanggal", cursor.getString(3));
                map.put("key_jurusan", cursor.getString(4));
                daftarMHS.add(map);
            } while (cursor.moveToNext());
        }

//        do {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("key_no", i.toString());
//            map.put("key_nama", indeks);
//            daftarMHS.add(map);
//            i++;
//        } while (i < 4);

//        ListAdapter adapter = new SimpleAdapter(DataActivity.this, daftarMHS, R.layout.adapter_data_mhs, new String[]{"key_nim", "key_nama","key_jurusan"}, new int[]{R.id.tv_nim, R.id.tv_nama, R.id.tv_jurusan});
        ListAdapter adapter = new SimpleAdapter(AlternativeActivity.this, daftarMHS, R.layout.activity_data_adapter, new String[]{"key_no","key_nama", "key_tempat", "key_tanggal","key_jurusan"}, new int[]{R.id.tvNo, R.id.tvNama, R.id.tvTempat, R.id.tvTanggal, R.id.tvJurusan});
        lvData.setAdapter(adapter);

    }
}

