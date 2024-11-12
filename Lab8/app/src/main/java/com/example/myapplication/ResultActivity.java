package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultView = findViewById(R.id.resultView);

        String name = getIntent().getStringExtra("name");
        String roll = getIntent().getStringExtra("roll");
        boolean consent = getIntent().getBooleanExtra("consent", false);
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        float rating = getIntent().getFloatExtra("rating", 0.0f);

        String resultText = "Name: " + name + "\nRoll No: " + roll + "\nConsent: " + consent
                + "\nDate: " + date + "\nTime: " + time + "\nRating: " + rating;

        resultView.setText(resultText);
    }
}
