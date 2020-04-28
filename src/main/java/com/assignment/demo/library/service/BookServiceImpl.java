package com.assignment.demo.library.service;

import com.assignment.demo.library.entities.Book;
import com.assignment.demo.library.facade.BookFacade;
import com.assignment.demo.library.model.BookDto;
import com.assignment.demo.library.model.request.BookRequest;
import com.assignment.demo.library.model.response.BaseResponse;
import com.assignment.demo.library.model.response.BookListResponse;
import com.assignment.demo.library.model.response.BookResponse;
import com.assignment.demo.library.util.EntityAndBeanConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    final static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    BookFacade bookFacade;

    @Autowired
    EntityAndBeanConversion entityAndBeanConversion;

    @Override
    public BookListResponse getBookList() {

        List<Book> books = bookFacade.getBookList();

        if (books != null && !books.isEmpty()) {
            List<BookDto> bookResponseList = books
                    .stream()
                    .map(book -> entityAndBeanConversion.getResponseByEntity(book))
                    .collect(Collectors.toList());
            BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Books found size: " + books.size());

            logger.info("Books found size: {}", books.size());
            return new BookListResponse(bookResponseList, baseResponse);
        } else {
            BaseResponse baseResponse = new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), "Book list is empty!");
            logger.info("Book list is empty!");
            return new BookListResponse(new ArrayList<>(), baseResponse);
        }

    }

    @Override
    public BookListResponse getBooksByLibraryId(long libraryId) {

        List<Book> books = bookFacade.getBooksByLibraryId(libraryId);

        if (books != null && !books.isEmpty()) {
            List<BookDto> bookResponseList = books
                    .stream()
                    .map(book -> entityAndBeanConversion.getResponseByEntity(book))
                    .collect(Collectors.toList());
            BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Books found by Library Id!");
            logger.info("Books found size: {}", books.size());
            return new BookListResponse(bookResponseList, baseResponse);
        } else {
            BaseResponse baseResponse = new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), "Book list is empty!");
            logger.info("Book list is empty!");
            return new BookListResponse(new ArrayList<>(), baseResponse);
        }
    }

    @Override
    public BookResponse getBookById(long bookId) {
        Book book = bookFacade.findBookById(bookId);

        if (book == null) {
            BaseResponse baseResponse = new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), "Book not found!");
            logger.info("Book not found!");
            return new BookResponse(null, baseResponse);
        }

        BookDto bookDto = entityAndBeanConversion.getResponseByEntity(book);
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Book found!");

        logger.info("Book found!");
        return new BookResponse(bookDto, baseResponse);
    }

    @Override
    public BaseResponse removeBookById(long bookId) {
        return bookFacade.removeBookById(bookId);
    }

    @Override
    public BaseResponse updateBook(BookRequest bookRequest) {
        Book book = entityAndBeanConversion.getEntityByRequest(bookRequest);
        return bookFacade.updateBook(book);
    }

    @Override
    public BaseResponse addBook(BookRequest bookRequest) {
        Book book = entityAndBeanConversion.getEntityByRequest(bookRequest);
        return bookFacade.addBook(book);
    }
}
