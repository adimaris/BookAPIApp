package com.alexanderdimaris.bookapiapp.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;
    private String author;
    private String image;
    private String description;
    private String amazonUrl;

    public Book(String title, String author, String image, String description, String amazonUrl) {
        this.title = title;
        this.author = author;
        this.image = image;
        this.description = description;
        this.amazonUrl = amazonUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmazonUrl() {
        return amazonUrl;
    }

    public void setAmazonUrl(String amazonUrl) {
        this.amazonUrl = amazonUrl;
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .centerCrop()
                .into(view);
    }

    public String prettyPrint() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", amazonUrl='" + amazonUrl + '\'' +
                '}';
    }
}