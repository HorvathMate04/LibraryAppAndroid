package com.example.libraryapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private List<Book> books;

    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
        this.context = context;
        this.books = books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_book, parent, false);
        }

        Book currentBook = books.get(position);

        TextView title = convertView.findViewById(R.id.textTitle);
        TextView author = convertView.findViewById(R.id.textAuthor);
        TextView pages = convertView.findViewById(R.id.textPages);
        Button deleteButton = convertView.findViewById(R.id.buttonDelete);

        title.setText(currentBook.getTitle());
        author.setText(currentBook.getAuthor());
        pages.setText(String.valueOf(currentBook.getPages()));

        deleteButton.setOnClickListener(v -> {
            deleteBook(position);
        });

        convertView.setOnClickListener(v -> {
            seeDetails(currentBook);
        });

        return convertView;
    }

    private void deleteBook(int position){
        new AlertDialog.Builder(context)
                .setTitle("Törlés")
                .setMessage("Biztosan törölni szeretné ezt a könyvet?")
                .setPositiveButton("Igen", (dialog, which) -> {
                    books.remove(position);
                    notifyDataSetChanged();
                })
                .setNegativeButton("Nem", null)
                .show();
    }

    private void seeDetails(Book currentBook){
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("book", currentBook);
        context.startActivity(intent);
    }
}

