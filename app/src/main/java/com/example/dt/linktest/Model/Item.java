package com.example.dt.linktest.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Item {

    @SerializedName("sortBy")
    String sortBy;

    @SerializedName("source")
    String source;

    @SerializedName("status")
    String status;

    @SerializedName("articles")
    public
    ArrayList<articles> articles;


    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<articles> articles) {
        this.articles = articles;
    }
}
