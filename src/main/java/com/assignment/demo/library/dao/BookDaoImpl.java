package com.assignment.demo.library.dao;

import com.assignment.demo.library.entities.Book;
import com.assignment.demo.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByLibraryId(long libraryId) {
        return bookRepository.findAllByLibraryLibraryId(libraryId);
    }

    @Override
    public Optional<Book> findBookById(long bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public void deleteBookById(long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public boolean existsById(long bookId) {
        return bookRepository.existsById(bookId);
    }

    @Override
    public Book updateOrSaveBook(Book book) {
        return bookRepository.save(book);
    }
}
