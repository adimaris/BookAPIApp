package com.alexanderdimaris.bookapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.alexanderdimaris.bookapiapp.model.Book;
import com.alexanderdimaris.bookapiapp.utilities.NYTBookJsonUtils;
import com.alexanderdimaris.bookapiapp.utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView mGridView;
    private ProgressBar mProgressBar;
    private TextView errorTextView;
    private List<Book> mBooksList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = findViewById(R.id.grid_view);
        mProgressBar = findViewById(R.id.progress_bar);
        errorTextView = findViewById(R.id.tv_error);

        Spinner mSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new FetchBooksTask().execute(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // intentionally left blank
            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BookDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Book", mBooksList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void showErrorMessage() {
        mGridView.setVisibility(View.INVISIBLE);
        errorTextView.setVisibility(View.VISIBLE);
    }

    public class FetchBooksTask extends AsyncTask<Integer, Void, List<Book>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
            mBooksList.clear();
        }

        @Override
        protected List<Book> doInBackground(Integer... integers) {
            if(integers.length == 0) return null;

            URL apiRequest = NetworkUtils.buildUrl(integers[0]);

            try {
                String jsonResponse = NetworkUtils.getResponseFromHttpUrl(apiRequest);
                return NYTBookJsonUtils.getBookFromJson(jsonResponse);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            mProgressBar.setVisibility(View.INVISIBLE);

            if(books == null) {
                showErrorMessage();
            } else {
                mBooksList = books;
                MyAdapter myAdapter = new MyAdapter(MainActivity.this, mBooksList);
                mGridView.setAdapter(myAdapter);
                mGridView.setVisibility(View.VISIBLE);
            }
        }
    }
}