package com.projectweb3.service.dto;

import javax.persistence.Column;

public class BookDTO {

    private Long id;

    private String title;

    private String genre;

    private Integer pages;

    private Long authorId;

    public BookDTO() {}

    public BookDTO(Long id, String title, String genre, Integer pages, Long authorId) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.pages = pages;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getPages() {
        return pages;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
