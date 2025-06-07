package com.example.ict602_electricitybillapp_arysha;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        TextView aboutText = findViewById(R.id.aboutText);
        aboutText.setText("Name: NUR ARYSHA IRDINA BINTI ZULKHAIREEE\n" +
                "Student ID: 2023141161\n" +
                "Course: ICT602 Mobile Technology and Development\n© 2025\nGitHub: https://github.com/aryshairdina/ICT602-ElectricityBillApp-Arysha");

        aboutText.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/aryshairdina/ICT602-ElectricityBillApp-Arysha"));
            startActivity(browserIntent);
        });

        // Back to main page button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

    }
}
