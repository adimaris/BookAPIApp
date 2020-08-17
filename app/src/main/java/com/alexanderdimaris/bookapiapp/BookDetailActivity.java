package com.alexanderdimaris.bookapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.alexanderdimaris.bookapiapp.databinding.ActivityBookDetailBinding;
import com.alexanderdimaris.bookapiapp.model.Book;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBookDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_book_detail);

        Bundle extras = getIntent().getExtras();
        Book book = null;

        if(extras != null) {
            book = (Book) extras.getSerializable("Book");
        }

        binding.setBook(book);
        assert book != null;
        binding.setImageUrl(book.getImage());
    }
}