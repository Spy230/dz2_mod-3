package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private int id = 0;
    private EditText inputName;
    private EditText inputAuthor;
    private TextView output;
    private Button addBookButton;
    private Button sortBooksButton;
    private ArrayList<Book> books; //контейнер

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = findViewById(R.id.inputName);
        inputAuthor = findViewById(R.id.inputAuthor);
        output = findViewById(R.id.output);
        addBookButton = findViewById(R.id.button);
        sortBooksButton = findViewById(R.id.button2);
        books = new ArrayList<>();

        addBookButton.setOnClickListener(listener);
        sortBooksButton.setOnClickListener(sortBooksListener);
    }

    private View.OnClickListener  listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = inputName.getText().toString();
            String author = inputAuthor.getText().toString();
            id++;

            Book book = new Book(id, name, author);
            books.add(book);

            // Очищаем TextView перед выводом книг
            output.setText("");

            // Вывод книг без сортировки
            for (Book b : books) {
                output.append(b.getId() + " Автор: " + b.getAuthor() + ", Название книги: " + b.getName() + "\n");
            }
        }
    };

    private View.OnClickListener sortBooksListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sortBooks();
        }
    };

    private void sortBooks() {
        // Очищаем TextView перед выводом отсортированных книг
        output.setText("");

        // Сортируем книги по авторам с использованием Stream API
        books.stream()
                .sorted(Comparator.comparing(Book::getAuthor))
                .forEach(b -> output.append(b.getId() + " Автор: " + b.getAuthor() + ", Название книги: " + b.getName() + "\n"));
    }
}