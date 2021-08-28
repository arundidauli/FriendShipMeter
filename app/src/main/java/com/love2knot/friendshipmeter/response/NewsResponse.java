package com.love2knot.friendshipmeter.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.love2knot.friendshipmeter.model.Articles;

import java.util.List;

public class NewsResponse {
    @SerializedName("total")
    @Expose
    int total;
    @SerializedName("articles")
    @Expose
    List<Articles> articles;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
