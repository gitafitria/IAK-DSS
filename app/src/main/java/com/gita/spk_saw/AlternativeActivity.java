package com.gita.spk_saw;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AlternativeActivity extends AppCompatActivity {

    DBController koneksiDB;

    ListView lvData;
    Button btnAdd;

    protected Cursor cursor;
    public static AlternativeActivity dataAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternative);

        lvData = (ListView) findViewById(R.id.lvData);
        btnAdd = (Button)findViewById(R.id.btnAddAlternatif);

        koneksiDB = new DBController(this);

        TampilData();
        dataAct = this;

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


//        ListAdapter adapter = new SimpleAdapter(DataActivity.this, daftarMHS, R.layout.adapter_data_mhs, new String[]{"key_nim", "key_nama","key_jurusan"}, new int[]{R.id.tv_nim, R.id.tv_nama, R.id.tv_jurusan});
        ListAdapter adapter = new SimpleAdapter(AlternativeActivity.this, daftarMHS, R.layout.activity_data_adapter, new String[]{"key_no","key_nama", "key_tempat", "key_tanggal","key_jurusan"}, new int[]{R.id.tvNo, R.id.tvNama, R.id.tvTempat, R.id.tvTanggal, R.id.tvJurusan});
        lvData.setAdapter(adapter);

        lvData.setSelected(true);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                final HashMap<String, String> selection = daftarMHS.get(arg2);
                final CharSequence[] dialogitem = {"Kriteria","Edit","Delete"};

                AlertDialog.Builder adbuilder = new AlertDialog.Builder(AlternativeActivity.this);
                adbuilder.setTitle("Menu Pilihan");
                adbuilder.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        // TODO Auto-generated method stub
                        String cell_id = selection.get("NO");
                        switch (item) {
                            case 0:

//							Intent toKriteria = new Intent(AlternativeActivity.this, EditAlternativeActivity.class);
//							toEditAlter.putExtra("NO", cell_id);
//							startActivity(toEditAlter);
                                break;

                            case 1:
                                Intent toEditAlter = new Intent(AlternativeActivity.this, EditAlternativeActivity.class);
                                toEditAlter.putExtra("NO", cell_id);
                                startActivity(toEditAlter);
                                break;

                            case 2:
                                String queryDel = "DELETE FROM alternative WHERE id='"+ cell_id +"'";
                                SQLiteDatabase db = koneksiDB.getWritableDatabase();
                                db.execSQL(queryDel);
                                dataAct.TampilData();
                                Toast.makeText(AlternativeActivity.this, "Data berhasil dihapus", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                    }
                });
                adbuilder.create().show();

            }

        });

    }
}

