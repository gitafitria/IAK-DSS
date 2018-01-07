package com.gita.spk_saw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AlternativeActivity extends AppCompatActivity {
    ListView lvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternative);

        lvData = (ListView) findViewById(R.id.lvData);

        TampilData();
    }

    void TampilData() {
        String querySel;
        String indeks = "gita";
//        querySel = "SELECT * FROM tbl_biodata";
//        SQLiteDatabase db = koneksiDB.getReadableDatabase();
//        cursor = db.rawQuery(querySel, null);

        final ArrayList<HashMap<String, String>> daftarMHS;
        daftarMHS = new ArrayList<HashMap<String, String>>();

//
//        if (cursor.moveToFirst() ){
//            do {
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put("key_nim", cursor.getString(0));
//                map.put("key_nama", cursor.getString(1));
//                map.put("key_jurusan", cursor.getString(2));
//                daftarMHS.add(map);
//            } while (cursor.moveToNext());
//        }
        Integer i = 1;
        do {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("key_no", i.toString());
            map.put("key_nama", indeks);
            daftarMHS.add(map);
            i++;
        } while (i < 4);

//        ListAdapter adapter = new SimpleAdapter(DataActivity.this, daftarMHS, R.layout.adapter_data_mhs, new String[]{"key_nim", "key_nama","key_jurusan"}, new int[]{R.id.tv_nim, R.id.tv_nama, R.id.tv_jurusan});
        ListAdapter adapter = new SimpleAdapter(AlternativeActivity.this, daftarMHS, R.layout.activity_data_adapter, new String[]{"key_no", "key_nama"}, new int[]{R.id.tvNo, R.id.tvNama});
        lvData.setAdapter(adapter);

    }
}

