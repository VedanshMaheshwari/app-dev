package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.ToggleButton;  // Import ToggleButton
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput, rollInput;
    private Switch consentSwitch;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private RatingBar ratingBar;
    private Button nextButton;
    private ImageSwitcher imageSwitcher;
    private ToggleButton toggleButton; // Declare ToggleButton
    private int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
    private int currentImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Input Controls
        nameInput = findViewById(R.id.nameInput);
        rollInput = findViewById(R.id.rollInput);
        consentSwitch = findViewById(R.id.consentSwitch);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        ratingBar = findViewById(R.id.ratingBar);
        nextButton = findViewById(R.id.nextButton);

        // ImageSwitcher Setup
        imageSwitcher = findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(() -> {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            return imageView;
        });
        imageSwitcher.setImageResource(images[currentImageIndex]);

        imageSwitcher.setOnClickListener(view -> {
            currentImageIndex = (currentImageIndex + 1) % images.length;
            imageSwitcher.setImageResource(images[currentImageIndex]);
        });

        // Initialize ToggleButton
        toggleButton = findViewById(R.id.toggleButton);

        // Set an event listener to respond to toggle changes
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "Toggle is ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Toggle is OFF", Toast.LENGTH_SHORT).show();
            }
        });

        // Button to pass values to next activity
        nextButton.setOnClickListener(view -> openNextActivity());
    }

    private void openNextActivity() {
        String name = nameInput.getText().toString();
        String roll = rollInput.getText().toString();
        boolean consent = consentSwitch.isChecked();
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        float rating = ratingBar.getRating();
        boolean isToggleOn = toggleButton.isChecked();  // Get the state of the ToggleButton

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("roll", roll);
        intent.putExtra("consent", consent);
        intent.putExtra("date", day + "/" + (month + 1) + "/" + year);
        intent.putExtra("time", hour + ":" + minute);
        intent.putExtra("rating", rating);
        intent.putExtra("toggleState", isToggleOn);  // Pass the toggle state to next activity

        startActivity(intent);
        Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
    }
}
