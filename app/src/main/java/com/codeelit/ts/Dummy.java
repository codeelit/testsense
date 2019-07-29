package com.codeelit.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Dummy extends AppCompatActivity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        String name  = getIntent().getExtras().getString("title");
        tv=(TextView)findViewById(R.id.dummytext);
        tv.setText(name);
    }
}
