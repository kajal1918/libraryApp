package com.assignment.demo.library.dao;

import com.assignment.demo.library.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    List<Book> getBooks();

    List<Book> getBooksByLibraryId(long libraryId);

    Optional<Book> findBookById(long bookId);

    void deleteBookById(long bookId);

    boolean existsById(long bookId);

    Book updateOrSaveBook(Book book);
}
