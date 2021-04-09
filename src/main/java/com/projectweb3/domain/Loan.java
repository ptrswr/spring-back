package com.projectweb3.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Loan.
 */
@Entity
@Table(name = "loan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "return_date")
    private Instant return_date;

    @Column(name = "returned")
    private Boolean returned;

    @ManyToOne
    @JsonIgnoreProperties(value = { "author" }, allowSetters = true)
    private Book book;

    @ManyToOne
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loan id(Long id) {
        this.id = id;
        return this;
    }

    public Instant getReturn_date() {
        return this.return_date;
    }

    public Loan return_date(Instant return_date) {
        this.return_date = return_date;
        return this;
    }

    public void setReturn_date(Instant return_date) {
        this.return_date = return_date;
    }

    public Boolean getReturned() {
        return this.returned;
    }

    public Loan returned(Boolean returned) {
        this.returned = returned;
        return this;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public Book getBook() {
        return this.book;
    }

    public Loan book(Book book) {
        this.setBook(book);
        return this;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return this.user;
    }

    public Loan user(User user) {
        this.setUser(user);
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Loan)) {
            return false;
        }
        return id != null && id.equals(((Loan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Loan{" +
            "id=" + getId() +
            ", return_date='" + getReturn_date() + "'" +
            ", returned='" + getReturned() + "'" +
            "}";
    }
}
