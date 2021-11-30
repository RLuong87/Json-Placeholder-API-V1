package com.careerdevs.jsonplaceholderv1.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPhotos {

    private String albumId;
    private String id;
    private String title;
    private String url;
    private String thumbnailURL;

    public UserPhotos(String albumId, String title, String url, String thumbnailURL) {
        this.albumId = albumId;
        this.title = title;
        this.url = url;
        this.thumbnailURL = thumbnailURL;
    }

    public UserPhotos() {
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
