package com.assignment.demo.library.service;

import com.assignment.demo.library.model.request.BookRequest;
import com.assignment.demo.library.model.response.BaseResponse;
import com.assignment.demo.library.model.response.BookListResponse;
import com.assignment.demo.library.model.response.BookResponse;

public interface BookService {
    BookListResponse getBookList();

    BookListResponse getBooksByLibraryId(long libraryId);

    BookResponse getBookById(long bookId);

    BaseResponse removeBookById(long bookId);

    BaseResponse updateBook(BookRequest bookRequest);

    BaseResponse addBook(BookRequest bookRequest);
}
