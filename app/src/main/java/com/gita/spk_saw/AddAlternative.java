package com.gita.spk_saw;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AddAlternative extends AppCompatActivity {

    EditText etNama, etTempat, etTanggal;
    Spinner spJurusan;
    Button btnAdd;

    DBController koneksiDB;

    Calendar calTanggal = Calendar.getInstance();

    //	kode date picker
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
//	    String myFormat = "MM/dd/yy"; //In which you need put here
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etTanggal.setText(sdf.format(calTanggal.getTime()));
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alternative);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        etNama = (EditText)findViewById(R.id.etNama);
        etTanggal = (EditText)findViewById(R.id.etTanggal);
        etTempat = (EditText)findViewById(R.id.etTempat);
        spJurusan = (Spinner)findViewById(R.id.spJurusan);

        koneksiDB = new DBController(AddAlternative.this);

//        kode untuk menampilkan combobox menggunakan spinner
        ArrayList<String> classJurusan = new ArrayList<String>();
        classJurusan.add("MDI");
        classJurusan.add("DGM");
        classJurusan.add("KAB");

        ArrayAdapter<String> classJurusanAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classJurusan );
        classJurusanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJurusan.setAdapter(classJurusanAdapter);

//		kode datepicker
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                calTanggal.set(Calendar.YEAR, year);
                calTanggal.set(Calendar.MONTH, monthOfYear);
                calTanggal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }
        };

//		kode date picker
        etTanggal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddAlternative.this, date, calTanggal.get(Calendar.YEAR), calTanggal.get(Calendar.MONTH),calTanggal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

//        kode simpan saat menekan tombol save
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
