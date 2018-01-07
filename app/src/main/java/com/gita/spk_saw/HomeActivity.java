package com.gita.spk_saw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button start_btn;
    Button alternative_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        start_btn = (Button)findViewById(R.id.start_btn);
        alternative_btn = (Button)findViewById(R.id.btnALternative);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goSelectAlternative = new Intent(HomeActivity.this, SelectAlternativeActivity.class);
                startActivity(goSelectAlternative);
            }
        });

        alternative_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toALternative = new Intent(HomeActivity.this, AlternativeActivity.class);
                startActivity(toALternative);
            }
        });
    }
}
