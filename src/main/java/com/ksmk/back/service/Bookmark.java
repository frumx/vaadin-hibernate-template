package com.ksmk.back.service;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mdcow on 02.03.17.
 */
@Entity
@Table(name="bookmark")
public class Bookmark implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="webpage")
    private String webPage;

    @Column(name="url")
    private String urlString;


    public Bookmark(String webPage, String urlString) {
        this.webPage = webPage;
        this.urlString = urlString;
    }

    public Bookmark() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }


    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", webPage='" + webPage + '\'' +
                ", urlString='" + urlString + '\'' +
                '}';
    }
}
