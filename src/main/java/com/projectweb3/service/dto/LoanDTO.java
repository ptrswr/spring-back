package com.projectweb3.service.dto;

import java.time.Instant;

public class LoanDTO {

    private Long id;

    private Instant return_date;
    private Boolean returned;
    private Long userID;
    private Long bookID;

    public LoanDTO() {}

    public LoanDTO(Long id, Instant return_date, Boolean returned, Long userID, Long bookID) {
        this.id = id;
        this.return_date = return_date;
        this.returned = returned;
        this.userID = userID;
        this.bookID = bookID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public Instant getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Instant return_date) {
        this.return_date = return_date;
    }
}
