package com.alexanderdimaris.bookapiapp.utilities;

import android.util.Log;

import com.alexanderdimaris.bookapiapp.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NYTBookJsonUtils {

    private static final String TAG = NYTBookJsonUtils.class.getSimpleName();

    public static List<Book> getBookFromJson(String json) throws JSONException {

        List<Book> resultList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);
        JSONObject resultObjects = jsonObject.getJSONObject("results");
        JSONArray jsonArray = resultObjects.getJSONArray("books");

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String title = object.getString("title");
            String author = object.getString("author");
            String image = object.getString("book_image");
            String description = object.getString("description");
            String amazonUrl = object.getString("amazon_product_url");

            Book book = new Book(title, author, image, description, amazonUrl);
            Log.d(TAG, book.prettyPrint());
            resultList.add(book);
        }

        Log.d(TAG, "" + resultList.size());
        return resultList;
    }

}
