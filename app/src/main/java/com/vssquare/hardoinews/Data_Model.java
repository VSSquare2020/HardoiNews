package com.vssquare.hardoinews;

class Data_Model {
    private int id;
    private String date;
    private String title_rendered;
    private String author_name;
    private String featured_media_url;
    private String link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle_rendered() {
        return title_rendered;
    }

    public void setTitle_rendered(String title_rendered) {
        this.title_rendered = title_rendered;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getFeatured_media_url() {
        return featured_media_url;
    }

    public void setFeatured_media_url(String featured_media_url) {
        this.featured_media_url = featured_media_url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
