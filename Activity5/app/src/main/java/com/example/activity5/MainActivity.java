package com.example.activity5;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextSwitcher textSwitcher;
    private String[] tips = {
            "Internship Tip 1: Be proactive.",
            "Internship Tip 2: Network with professionals.",
            "Internship Tip 3: Keep learning new skills."
    };
    private int currentTipIndex = 0;

    private EditText inputName, inputEmail;
    private Switch switchCompleted;
    private TextView selectedDate;
    private Button btnSelectDate, btnNext, btnWebsite, btnCall, btnSwitchTip;
    private RatingBar ratingBar;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        inputName = findViewById(R.id.editTextName);
        inputEmail = findViewById(R.id.editTextEmail);
        switchCompleted = findViewById(R.id.switchCompleted);
        selectedDate = findViewById(R.id.textSelectedDate);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        ratingBar = findViewById(R.id.ratingBar);
        btnSwitchTip = findViewById(R.id.btnSwitchTip);
        textSwitcher = findViewById(R.id.textSwitcher);
        btnNext = findViewById(R.id.btnNext);
        btnWebsite = findViewById(R.id.btnWebsite);
        btnCall = findViewById(R.id.btnCall);

        // Set up TextSwitcher
        textSwitcher.setFactory(() -> {
            TextView textView = new TextView(MainActivity.this);
            textView.setTextSize(18);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            return textView;
        });
        textSwitcher.setText(tips[currentTipIndex]);

        // Switch internship tips on button click
        btnSwitchTip.setOnClickListener(v -> {
            currentTipIndex = (currentTipIndex + 1) % tips.length;
            textSwitcher.setText(tips[currentTipIndex]);
        });

        // Select Internship Start Date
        calendar = Calendar.getInstance();
        btnSelectDate.setOnClickListener(v -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this,
                    (view, year1, month1, dayOfMonth) ->
                            selectedDate.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1),
                    year, month, day);
            datePickerDialog.show();
        });

        // Open website using Implicit Intent
        btnWebsite.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"));
            startActivity(browserIntent);
        });

        // Make a phone call using Implicit Intent
        btnCall.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+123456789"));
            startActivity(callIntent);
        });

        // Pass data to SecondActivity using Explicit Intent
        btnNext.setOnClickListener(v -> {
            String name = inputName.getText().toString();
            String email = inputEmail.getText().toString();
            boolean isCompleted = switchCompleted.isChecked();
            float rating = ratingBar.getRating();
            String date = selectedDate.getText().toString();

            if (name.isEmpty() || email.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("userName", name);
                intent.putExtra("userEmail", email);
                intent.putExtra("isCompleted", isCompleted);
                intent.putExtra("rating", rating);
                intent.putExtra("startDate", date);
                startActivity(intent);
            }
        });
    }
}
