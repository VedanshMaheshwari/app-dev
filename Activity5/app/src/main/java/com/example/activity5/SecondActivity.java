package com.example.activity5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewName, textViewEmail, textViewCompleted, textViewRating, textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewCompleted = findViewById(R.id.textViewCompleted);
        textViewRating = findViewById(R.id.textViewRating);
        textViewDate = findViewById(R.id.textViewDate);

        // Retrieve data from Intent
        String name = getIntent().getStringExtra("userName");
        String email = getIntent().getStringExtra("userEmail");
        boolean isCompleted = getIntent().getBooleanExtra("isCompleted", false);
        float rating = getIntent().getFloatExtra("rating", 0.0f);
        String date = getIntent().getStringExtra("startDate");

        // Display the received data
        textViewName.setText("Name: " + name);
        textViewEmail.setText("Email: " + email);
        textViewCompleted.setText("Completed: " + (isCompleted ? "Yes" : "No"));
        textViewRating.setText("Rating: " + rating);
        textViewDate.setText("Start Date: " + date);
    }
}
