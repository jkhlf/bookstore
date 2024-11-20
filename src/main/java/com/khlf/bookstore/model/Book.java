package com.khlf.bookstore.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "author_life")
    private String authorLife;

    @ElementCollection
    @CollectionTable(name = "book_subjects", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "subject")
    private List<String> subjects;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "download_url")
    private String downloadUrl;

    @Column(name = "download_count")
    private int downloadCount;

    public Book() {}

    public Book(Long id, String title, String author, String authorLife, List<String> subjects, String coverImage, String downloadUrl, int downloadCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.authorLife = authorLife;
        this.subjects = subjects;
        this.coverImage = coverImage;
        this.downloadUrl = downloadUrl;
        this.downloadCount = downloadCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public String getAuthorLife() {
        return authorLife;
    }

    public void setAuthorLife(String authorLife) {
        this.authorLife = authorLife;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}