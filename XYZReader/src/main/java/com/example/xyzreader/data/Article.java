package com.example.xyzreader.data;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ismail.Khan2 on 7/28/2017.
 */

public class Article implements Parcelable{
    private String id, serverId, title, author, body, thumbUrl, photoUrl, aspectRatio, pulishDate;

    public Article() {
    }

    protected Article(Parcel in) {
        id = in.readString();
        serverId = in.readString();
        title = in.readString();
        author = in.readString();
        body = in.readString();
        thumbUrl = in.readString();
        photoUrl = in.readString();
        aspectRatio = in.readString();
        pulishDate = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public static Article getArticleObjectFromCursor(Cursor cursor){
        Article article = new Article();
        article.setTitle(cursor.getString(ArticleLoader.Query.TITLE));
        article.setBody(cursor.getString(ArticleLoader.Query.BODY));
        article.setPhotoUrl(cursor.getString(ArticleLoader.Query.PHOTO_URL));

        return article;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getPulishDate() {
        return pulishDate;
    }

    public void setPulishDate(String pulishDate) {
        this.pulishDate = pulishDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(serverId);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(body);
        dest.writeString(thumbUrl);
        dest.writeString(photoUrl);
        dest.writeString(aspectRatio);
        dest.writeString(pulishDate);
    }
}
