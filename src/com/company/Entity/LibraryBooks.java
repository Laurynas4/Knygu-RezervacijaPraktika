package com.company.Entity;

import javax.persistence.*;

@Entity
@Table(name = "library")

public class LibraryBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private int bookId;
    @Column(name = "bookTitle")
    private String bookTitle;
    @Column(name = "bookSummary")
    private String bookSummary;
    @Column(name = "numberOfPages")
    private int numberOfPages;
    @Column(name = "reservation", columnDefinition = "SMALLINT")
    private boolean reservation;

    public LibraryBooks() {
    }

    public LibraryBooks(int bookId, String bookTitle, String bookSummary, int numberOfPages) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookSummary = bookSummary;
        this.numberOfPages = numberOfPages;
    }

    public LibraryBooks(String bookTitle, String bookSummary, int numberOfPages) {
        this.bookTitle = bookTitle;
        this.bookSummary = bookSummary;
        this.numberOfPages = numberOfPages;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookSummary() {
        return bookSummary;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return
                "Id of the book = " + bookId +
                ", Title of the book = '" + bookTitle + '\'' +
                ", Summary of the book = '" + bookSummary + '\'' +
                ", Number of pages in the book = " + numberOfPages + ".";
    }
}