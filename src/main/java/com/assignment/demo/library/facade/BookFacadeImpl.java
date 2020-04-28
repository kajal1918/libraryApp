package com.assignment.demo.library.facade;

import com.assignment.demo.library.dao.BookDao;
import com.assignment.demo.library.entities.Book;
import com.assignment.demo.library.model.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookFacadeImpl implements BookFacade {

    final static Logger logger = LoggerFactory.getLogger(BookFacadeImpl.class);
    @Autowired
    BookDao bookDao;

    @Override
    public List<Book> getBookList() {
        return bookDao.getBooks();
    }

    @Override
    public List<Book> getBooksByLibraryId(long libraryId) {
        return bookDao.getBooksByLibraryId(libraryId);
    }

    @Override
    public Book findBookById(long bookId) {
        if (!bookDao.findBookById(bookId).isPresent()) {

            return null;
        }
        return bookDao.findBookById(bookId).get();
    }

    @Override
    public BaseResponse removeBookById(long bookId) {
        if (!bookDao.existsById(bookId)) {
            logger.info("Book id doest not exist.");
            return new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), "Book id doest not exist.");
        }

        bookDao.deleteBookById(bookId);
        logger.info("Deleted Book by Id: {}", bookId);

        return new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Deleted Book by Id: " + bookId);
    }

    @Override
    public BaseResponse updateBook(Book book) {

        if (!bookDao.existsById(book.getBookId())) {
            logger.info("Book id doest not exist.");
            return new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), "Book id doest not exist.");
        }

        bookDao.updateOrSaveBook(book);
        logger.info("Updated Book by Id: {}", book.getBookId());

        return new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Updated Book by Id: " + book.getBookId());
    }

    @Override
    public BaseResponse addBook(Book book) {
        Book savedBook = bookDao.updateOrSaveBook(book);

        if (savedBook == null) {
            logger.info("Unable to save Book!");
            return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to save Book!");
        }

        logger.info("Book created successfully!");
        return new BaseResponse(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value(), "Book created successfully!");
    }
}
