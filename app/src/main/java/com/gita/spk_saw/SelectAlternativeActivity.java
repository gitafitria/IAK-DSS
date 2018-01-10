package com.gita.spk_saw;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectAlternativeActivity extends AppCompatActivity {
    DBController koneksiDB;

    ListView lvData;
    Button btnNextResult;

    protected Cursor cursor;
    ArrayList<String> selected_ids = new ArrayList<String>();
    ArrayList<String> selected_names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_alternative);

        lvData = (ListView) findViewById(R.id.lvSelectAlternative);
        btnNextResult = (Button)findViewById(R.id.btnNextCalculate);

        koneksiDB = new DBController(this);

        TampilData();

        btnNextResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected_ids.size() < 2) {
                    Toast.makeText(getApplicationContext(), "Pilih lebih dari 1 alternative", Toast.LENGTH_LONG).show();
                } else {
                  Intent goResult = new Intent(SelectAlternativeActivity.this, WithViewpagerActivity.class);

                  goResult.putExtra("selected_ids", selected_ids);
                  goResult.putExtra("selected_names", selected_names);

                    startActivity(goResult);
                }
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
                daftarMHS.add(map);
            } while (cursor.moveToNext());
        }

        ListAdapter adapter = new SimpleAdapter(SelectAlternativeActivity.this, daftarMHS, R.layout.activity_select_alternative_adapter, new String[]{"key_no","key_nama"}, new int[]{R.id.select_alternative_cb, R.id.select_alternative_name});
        lvData.setAdapter(adapter);

        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                selectedStrings.add(lvData.getSelectedItem().toString());
                CheckBox select_cb = (CheckBox) view.findViewById(R.id.select_alternative_cb);
                TextView select_nama = (TextView) view.findViewById(R.id.select_alternative_name);

                if (select_cb.isChecked()) {
                    select_cb.setChecked(false);
                    selected_ids.remove(select_cb.getText().toString());
                    selected_names.remove(select_nama.getText().toString());
                } else if (!select_cb.isChecked()) {
                    selected_ids.add(select_cb.getText().toString());
                    selected_names.add(select_nama.getText().toString());
                    select_cb.setChecked(true);
                    Toast.makeText(getApplicationContext(),"" + select_nama.getText().toString() + " terpilih", Toast.LENGTH_SHORT).show();
                }
            }

        });

        lvData.setSelected(true);

    }

}
