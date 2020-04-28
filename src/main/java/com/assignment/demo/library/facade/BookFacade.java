package com.assignment.demo.library.facade;

import com.assignment.demo.library.entities.Book;
import com.assignment.demo.library.model.response.BaseResponse;

import java.util.List;

public interface BookFacade {
    List<Book> getBookList();

    List<Book> getBooksByLibraryId(long libraryId);

    Book findBookById(long bookId);

    BaseResponse removeBookById(long bookId);

    BaseResponse updateBook(Book book);

    BaseResponse addBook(Book book);
}
