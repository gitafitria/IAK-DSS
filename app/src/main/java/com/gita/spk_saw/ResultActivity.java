package com.gita.spk_saw;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ResultActivity extends AppCompatActivity {
    TextView txt_score_1, txt_score_2;
    Button back_home_btn;

    static DBController koneksiDB;
    protected static Cursor cursor;

    protected void onCreate(Bundle savedInstanceState) {
        koneksiDB = new DBController(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ArrayList<String> selected_ids, score_2, score_3;
        selected_ids = getIntent().getStringArrayListExtra("selected_ids");

        for (int idx = 1 ; idx <= selected_ids.size(); idx++) {
            String querySel;
            querySel = "SELECT * FROM kriteria where id = " + selected_ids.get(idx);
            SQLiteDatabase db = koneksiDB.getReadableDatabase();
            cursor = db.rawQuery(querySel, null);
        }

        if (cursor.moveToFirst() ){
            do {
//                bio_nama.setText(cursor.getString(1));
//                bio_tempat_lahir.setText(cursor.getString(2));
//                bio_tgl_lahir.setText(cursor.getString(3));
//                bio_jurusan.setText(cursor.getString(4));
            } while (cursor.moveToNext());
        }
//        txt_score_1 = (TextView)findViewById(R.id.score_1);
//        for (score_1) {
//
//        }

//
//        // matrix keputusan
//        score_1 = getIntent().getStringArrayListExtra("score_1");
//        score_2 = getIntent().getStringArrayListExtra("score_2");
//        score_3 = getIntent().getStringArrayListExtra("score_3");
//
//        // bobot
//        ArrayList<Double> bobot = new ArrayList<>();
//        bobot.add(Double.parseDouble("30")); // benefit
//        bobot.add(Double.parseDouble("30")); // benefit
//        bobot.add(Double.parseDouble("40")); // cost
//
//        // konversi radio value to double
//        score_1.set(2, getTemperamentValue(score_1.get(2)).toString());
//        score_2.set(2, getTemperamentValue(score_2.get(2)).toString());
//        score_3.set(2, getTemperamentValue(score_3.get(2)).toString());
//
//        // matriks keputusan terbobot
//        //
//        // cek min / max
//        Double max_kriteria_1, max_kriteria_2, min_kriteria_3;
//
//        ArrayList<Double> kriteria_1 = new ArrayList<>();
//        kriteria_1.add(Double.parseDouble(score_1.get(0)));
//        kriteria_1.add(Double.parseDouble(score_2.get(0)));
//        kriteria_1.add(Double.parseDouble(score_3.get(0)));
//
//        ArrayList<Double> kriteria_2 = new ArrayList<>();
//        kriteria_2.add(Double.parseDouble(score_2.get(1)));
//        kriteria_2.add(Double.parseDouble(score_2.get(1)));
//        kriteria_2.add(Double.parseDouble(score_3.get(1)));
//
//        ArrayList<Double> kriteria_3 = new ArrayList<>();
//        kriteria_3.add(Double.parseDouble(score_3.get(2)));
//        kriteria_3.add(Double.parseDouble(score_3.get(2)));
//        kriteria_3.add(Double.parseDouble(score_3.get(2)));
//
//        max_kriteria_1 = Collections.max(kriteria_1);
//        max_kriteria_2 = Collections.max(kriteria_2);
//        min_kriteria_3 = Collections.min(kriteria_3);
//
//        // matriks terbobot
//        ArrayList<Double> row_1 = new ArrayList<>();
//        row_1.add(Double.parseDouble(score_1.get(0))/max_kriteria_1);
//        row_1.add(Double.parseDouble(score_1.get(1))/max_kriteria_2);
//        row_1.add(Double.parseDouble(score_1.get(2))/min_kriteria_3);
//
//        ArrayList<Double> row_2 = new ArrayList<>();
//        row_2.add(0, Double.parseDouble(score_2.get(0))/max_kriteria_1);
//        row_2.add(1, Double.parseDouble(score_2.get(1))/max_kriteria_2);
//        row_2.add(2, Double.parseDouble(score_2.get(2))/min_kriteria_3);
//
//        ArrayList<Double> row_3 = new ArrayList<>();
//        row_3.add(0, Double.parseDouble(score_3.get(0))/max_kriteria_1);
//        row_3.add(1, Double.parseDouble(score_3.get(1))/max_kriteria_2);
//        row_3.add(2, Double.parseDouble(score_3.get(2))/min_kriteria_3);
//
//        // Nilai preferensi
//        Double v1, v2, v3;
//        v1 = (row_1.get(0) * bobot.get(0)) + (row_1.get(1) * bobot.get(1)) + (row_1.get(2) * bobot.get(2));
//        v2 = (row_2.get(0) * bobot.get(0)) + (row_2.get(1) * bobot.get(1)) + (row_2.get(2) * bobot.get(2));
//        v3 = (row_3.get(0) * bobot.get(0)) + (row_3.get(1) * bobot.get(1)) + (row_3.get(2) * bobot.get(2));
//
//        txt_score_1 = (TextView)findViewById(R.id.score_1);
//        txt_score_2 = (TextView)findViewById(R.id.score_2);
//        txt_score_3 = (TextView)findViewById(R.id.score_3);
//
//        txt_brave_1 = (TextView)findViewById(R.id.brave_1);
//        txt_performance_1 = (TextView)findViewById(R.id.performance_1);
//        txt_temperament_1 = (TextView)findViewById(R.id.temperament_1);
//
//        txt_brave_2 = (TextView)findViewById(R.id.brave_2);
//        txt_performance_2 = (TextView)findViewById(R.id.performance_2);
//        txt_temperament_2 = (TextView)findViewById(R.id.temperament_2);
//
//        txt_brave_3 = (TextView)findViewById(R.id.brave_3);
//        txt_performance_3 = (TextView)findViewById(R.id.performance_3);
//        txt_temperament_3 = (TextView)findViewById(R.id.temperament_3);
//
//        // Set Text
//        txt_brave_1.setText(score_1.get(0));
//        txt_performance_1.setText(score_1.get(1));
//        txt_temperament_1.setText(score_1.get(2));
//
//        txt_brave_2.setText(score_2.get(0));
//        txt_performance_2.setText(score_2.get(1));
//        txt_temperament_2.setText(score_2.get(2));
//
//        txt_brave_3.setText(score_3.get(0));
//        txt_performance_3.setText(score_3.get(1));
//        txt_temperament_3.setText(score_3.get(2));
//
//        txt_score_1.setText(v1.toString());
//        txt_score_2.setText(v2.toString());
//        txt_score_3.setText(v3.toString());
//
//        back_home_btn = (Button)findViewById(R.id.back_home);
//        back_home_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent go_home = new Intent(ResultActivity.this, HomeActivity.class);
//                startActivity(go_home);
//            }
//        });

    }

    public Double getTemperamentValue(String radio_temperament) {
        Double score = 0.0;
        switch (radio_temperament) {
            case "Always Angry":
                score = 10.0/1;
                break;
            case "Netral":
                score = 10.0/2;
                break;
            case "Calm":
                score = 10.0/3;
                break;
            default:
                score = 0.0;
                break;
        }
        return score;
    }
}
