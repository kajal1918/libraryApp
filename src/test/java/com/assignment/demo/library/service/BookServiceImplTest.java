package com.assignment.demo.library.service;

import com.assignment.demo.library.entities.Book;
import com.assignment.demo.library.facade.BookFacade;
import com.assignment.demo.library.model.BookDto;
import com.assignment.demo.library.model.request.BookRequest;
import com.assignment.demo.library.model.response.BaseResponse;
import com.assignment.demo.library.util.EntityAndBeanConversion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookServiceImplTest {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookFacade bookFacade;

    @Mock
    EntityAndBeanConversion entityAndBeanConversion;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getBookList() {
        when(bookFacade.getBookList()).thenReturn(Collections.singletonList(getBookResponse()));
        when(entityAndBeanConversion.getResponseByEntity(any(Book.class))).thenReturn(getBookDtoResponse());
        assertEquals(1, bookService.getBookList().getBooks().size());
        assertEquals(1, bookService.getBookList().getBooks().get(0).getBookId());
    }

    @Test
    public void getBooksByLibraryId() {
        when(bookFacade.getBooksByLibraryId(anyLong())).thenReturn(Collections.singletonList(getBookResponse()));
        when(entityAndBeanConversion.getResponseByEntity(any(Book.class))).thenReturn(getBookDtoResponse());
        assertEquals(1, bookService.getBooksByLibraryId(1).getBooks().size());
        assertEquals(1, bookService.getBooksByLibraryId(1).getBooks().get(0).getBookId());
    }

    @Test
    public void getBookById() {
        when(bookFacade.findBookById(anyLong())).thenReturn(getBookResponse());
        when(entityAndBeanConversion.getResponseByEntity(any(Book.class))).thenReturn(getBookDtoResponse());
        assertEquals(1, bookService.getBookById(1).getBookDto().getBookId());
    }

    @Test
    public void removeBookById() {
        when(bookFacade.removeBookById(anyLong())).thenReturn(getBaseResponse("Boom removed", HttpStatus.OK));
        assertEquals(HttpStatus.OK.getReasonPhrase(), bookService.removeBookById(1).getStatus());
    }

    @Test
    public void updateBook() {
        when(bookFacade.updateBook(any(Book.class))).thenReturn(getBaseResponse("Boom updated", HttpStatus.OK));
        when(entityAndBeanConversion.getEntityByRequest(any(BookRequest.class))).thenReturn(new Book());
        assertEquals(HttpStatus.OK.getReasonPhrase(), bookService.updateBook(new BookRequest()).getStatus());
    }

    @Test
    public void addBook() {
        when(bookFacade.addBook(any(Book.class))).thenReturn(getBaseResponse("Boom added", HttpStatus.CREATED));
        when(entityAndBeanConversion.getEntityByRequest(any(BookRequest.class))).thenReturn(new Book());
        assertEquals(HttpStatus.CREATED.getReasonPhrase(), bookService.addBook(new BookRequest()).getStatus());
    }

    private Book getBookResponse(){
        Book book = new Book();
        book.setBookId(1);
        return book;
    }

    private BookDto getBookDtoResponse(){
        BookDto bookDto = new BookDto();
        bookDto.setBookId(1);
        return bookDto;
    }

    private BaseResponse getBaseResponse(String message, HttpStatus httpStatus) {
        return new BaseResponse(httpStatus.getReasonPhrase(), httpStatus.value(), message);
    }
}