package com.gita.spk_saw;

import android.content.Intent;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class AddAlternative extends AppCompatActivity {

    DBController koneksiDB;

    Button btnAdd;
    EditText etNama, etTempat, etTanggal;
    Spinner spJurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alternative);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        etNama = (EditText)findViewById(R.id.etNama);
        etTanggal = (EditText)findViewById(R.id.etTanggal);
        etTempat = (EditText)findViewById(R.id.etTempat);
        spJurusan = (Spinner)findViewById(R.id.spJurusan);

        koneksiDB = new DBController(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String varNama, varTempat, varTanggal, varJurusan;

                varNama = etNama.getText().toString();
                varTanggal = etTanggal.getText().toString();
                varTempat = etTempat.getText().toString();
                varJurusan = spJurusan.getSelectedItem().toString();

                String query;
                query = "INSERT INTO alternative(nama,tempat,tanggal,jurusan) VALUES ('"+ varNama +"','"+ varTempat +"','"+ varTanggal +"','"+ spJurusan +"')";
                SQLiteDatabase db = koneksiDB.getWritableDatabase();
                db.execSQL(query);

                Toast.makeText(AddAlternative.this, "Alternatif Berhasil Ditambah", Toast.LENGTH_LONG).show();

                Intent toAlternative = new Intent(AddAlternative.this, AlternativeActivity.class);
                startActivity(toAlternative);







            }
        });

    }
}
