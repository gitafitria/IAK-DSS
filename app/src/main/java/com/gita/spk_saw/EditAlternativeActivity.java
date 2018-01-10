package com.gita.spk_saw;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class EditAlternativeActivity extends AppCompatActivity {

    EditText etNama, etTempat, etTanggal;
    Spinner spJurusan;
    Button btnUpdate;

    DBController koneksiDB;
    protected Cursor cursor;
    String idAlter;

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
        setContentView(R.layout.activity_edit_alternative);

        // biodata
        etNama = (EditText)findViewById(R.id.edit_bio_nama);
        etTempat = (EditText)findViewById(R.id.edit_bio_tempate_lahir);
        etTanggal = (EditText)findViewById(R.id.edit_bio_tgl_lahir);
        spJurusan = (Spinner)findViewById(R.id.edit_bio_jurusan);
        btnUpdate = (Button)findViewById(R.id.btnEdit);

        // kriteria

//		mengambil id alternative
        idAlter = getIntent().getStringExtra("NO");

//		koneksi database
        koneksiDB = new DBController(this);

//		mengambil data alternative berdasarkan id
        SQLiteDatabase db = koneksiDB.getReadableDatabase();
        String querySelect = "SELECT * FROM alternative WHERE id='"+idAlter+"'";
        cursor = db.rawQuery(querySelect, null);

//		mengisi data ke textbox
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            etNama.setText(cursor.getString(1).toString());
            etTempat.setText(cursor.getString(2).toString());
//			etTanggal.setText(cursor.getString(3).toString());

        }

//		kode combobox jurusan
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
                new DatePickerDialog(EditAlternativeActivity.this, date, calTanggal.get(Calendar.YEAR), calTanggal.get(Calendar.MONTH),calTanggal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

//		kode untuk saat klik tombol update
        btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String varNama, varTempat, varTanggal, varJurusan;
                varNama = etNama.getText().toString();
                varTempat = etTempat.getText().toString();
                varTanggal = etTanggal.getText().toString();
                varJurusan = spJurusan.getSelectedItem().toString();

                String Query = "UPDATE alternative SET nama='"+varNama+"', tempatlahir='"+varTempat+"', tanggallahir='"+varTanggal+"', jurusan='"+varJurusan+"'" +
                        " WHERE " +
                        "id='"+idAlter+"'";
                SQLiteDatabase db = koneksiDB.getWritableDatabase();
                db.execSQL(Query);
                Toast.makeText(EditAlternativeActivity.this, "Data Berhasil di Update", Toast.LENGTH_LONG).show();
                AlternativeActivity.dataAct.TampilData();

                Intent toData = new Intent(EditAlternativeActivity.this, AlternativeActivity.class);
                startActivity(toData);
            }
        });
    }
}
