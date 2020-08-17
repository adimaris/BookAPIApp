package com.alexanderdimaris.bookapiapp.utilities;

import com.alexanderdimaris.bookapiapp.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
public class NetworkUtils {

    private static final String BASE_URL = "https://api.nytimes.com/svc/books/v3/lists/current/";
    private static final String END_URL = ".json?api-key=";
    private static final String API = BuildConfig.ApiKey; // TODO update this variable with your own API Key

    private static final String[] COLLECTIONS = {
            "combined-print-and-e-book-fiction",
            "hardcover-fiction",
            "hardcover-nonfiction",
            "trade-fiction-paperback",
            "mass-market-paperback",
            "paperback-nonfiction",
            "hardcover-graphic-books",
            "paperback-graphic-books"
    };


    public static URL buildUrl(int collectionsIndex) {
        String uri = BASE_URL + COLLECTIONS[collectionsIndex] + END_URL + API;

        URL url = null;

        try {
            url = new URL(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}

