package com.gita.spk_saw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class AlternativeTwoActivity extends AppCompatActivity {
    Button go_2;
    EditText performance, bravery;
    RadioGroup temperament;
    RadioButton selected_temperament, last_radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternative_two);

        bravery = (EditText)findViewById(R.id.score_bravery_2);
        performance = (EditText)findViewById(R.id.score_performance_2);
        temperament = (RadioGroup)findViewById(R.id.score_temperament_2);

        final ArrayList<String> score_1 = getIntent().getStringArrayListExtra("score_1");

        go_2 = (Button)findViewById(R.id.next_to_2);

        go_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bravery.setError(null);
                performance.setError(null);
                last_radio = (RadioButton)findViewById(R.id.temperament_calm_2);
                last_radio.setError(null);

                String score_brave, score_perform;
                boolean valid = true;

                score_brave = bravery.getText().toString();
                score_perform = performance.getText().toString();
                int selected_temper = temperament.getCheckedRadioButtonId();

                if (score_brave.length() < 1) {
                    valid = false;
                    bravery.setError("can't be blank");
                } else if (Integer.parseInt(score_brave) > 10 || Integer.parseInt(score_brave) < 0 ) {
                    valid = false;
                    bravery.setError("score 1-10 only");
                }

                if (score_perform.length() < 1) {
                    valid = false;
                    performance.setError("can't be blank");
                } else if (Integer.parseInt(score_perform) > 10 || Integer.parseInt(score_perform) < 0 ) {
                    valid = false;
                    bravery.setError("score 1-10 only");
                }

                if (selected_temper == -1) {
                    valid = false;
                    last_radio.setError("can't be blank");
                }

                if (valid == false) {
                    Toast.makeText(getApplicationContext(), "Data is invalid", Toast.LENGTH_LONG).show();
                } else {
                    selected_temperament = (RadioButton)findViewById(selected_temper);
                    String score_temper = selected_temperament.getText().toString();

                    Intent next_candidate = new Intent(AlternativeTwoActivity.this, AlternativeThreeActivity.class);

                     ArrayList<String> score = new ArrayList<String>();
                     score.add(score_brave);
                     score.add(score_perform);
                     score.add(score_temper);

                     next_candidate.putExtra("score_1", score_1);
                     next_candidate.putExtra("score_2", score);

                    startActivity(next_candidate);
                }

            }
        });
    }
}
