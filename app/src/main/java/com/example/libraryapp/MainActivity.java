package com.example.libraryapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Book> books;
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText titleInput = findViewById(R.id.editTextTitle);
        EditText authorInput = findViewById(R.id.editTextAuthor);
        EditText pagesInput = findViewById(R.id.editTextPages);
        Button addButton = findViewById(R.id.buttonAdd);

        initialization();

        addButton.setOnClickListener(v -> {
            addBook(titleInput, authorInput, pagesInput);
        });
    }

    private void initialization(){
        ListView bookListView = findViewById(R.id.listViewBooks);

        books = new ArrayList<>();
        adapter = new BookAdapter(this, books);
        bookListView.setAdapter(adapter);
    }

    private void addBook(EditText titleInput, EditText authorInput, EditText pagesInput){
        String title = titleInput.getText().toString().trim();
        String author = authorInput.getText().toString().trim();
        String pagesStr = pagesInput.getText().toString().trim();

        if (title.isEmpty() || author.isEmpty() || pagesStr.isEmpty()) {
            Toast.makeText(this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
            return;
        }

        int pages = Integer.parseInt(pagesStr);
        if (pages < 50) {
            Toast.makeText(this, "Az oldalszám nem lehet kevesebb, mint 50!", Toast.LENGTH_SHORT).show();
            return;
        }

        books.add(new Book(title, author, pages));
        adapter.notifyDataSetChanged();

        titleInput.setText("");
        authorInput.setText("");
        pagesInput.setText("");
    }
}
