package com.assignment.demo.library.model.response;

import com.assignment.demo.library.model.BookDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BookListResponse {

    List<BookDto> books;

    @JsonProperty("response_detail")
    private BaseResponse baseResponse;

    public BookListResponse() {
    }

    public BookListResponse(List<BookDto> books, BaseResponse baseResponse) {
        this.books = books;
        this.baseResponse = baseResponse;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }
}
