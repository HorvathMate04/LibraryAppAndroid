package com.example.libraryapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView title = findViewById(R.id.textTitle);
        TextView author = findViewById(R.id.textAuthor);
        TextView pages = findViewById(R.id.textPages);
        TextView year = findViewById(R.id.textYear);
        Button backButton = findViewById(R.id.buttonBack);

        Book book = (Book) getIntent().getSerializableExtra("book");

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        pages.setText(String.valueOf(book.getPages()));
        year.setText("Évszám: " + (new Random().nextInt(2023 - 1900) + 1900));

        backButton.setOnClickListener(v -> finish());
    }
}

