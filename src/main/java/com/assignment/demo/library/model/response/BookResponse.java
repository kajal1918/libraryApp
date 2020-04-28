package com.assignment.demo.library.model.response;

import com.assignment.demo.library.model.BookDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookResponse {
    @JsonProperty("book")
    private BookDto bookDto;
    @JsonProperty("response_detail")
    private BaseResponse baseResponse;

    public BookResponse() {
    }

    public BookResponse(BookDto bookDto, BaseResponse baseResponse) {
        this.bookDto = bookDto;
        this.baseResponse = baseResponse;
    }

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }
}
