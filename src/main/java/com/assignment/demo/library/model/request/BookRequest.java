package com.assignment.demo.library.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class BookRequest {
    @JsonProperty("book_id")
    private long bookId;

    @NotEmpty(message = "Name should not be Blank")
    private String title;

    @Min(value = 1, message = "price should be greater than 0")
    private double price;

    @Min(value = 1, message = "Volume should be greater than 0")
    private int volume;

    @JsonProperty("library_id")
    @Min(value = 1, message = "libraryId should be greater than 0")
    private long libraryId;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(long libraryId) {
        this.libraryId = libraryId;
    }
}
