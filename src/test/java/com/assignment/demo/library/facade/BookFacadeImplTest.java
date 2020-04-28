package com.assignment.demo.library.facade;

import com.assignment.demo.library.dao.BookDao;
import com.assignment.demo.library.entities.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookFacadeImplTest {
    @InjectMocks
    BookFacadeImpl bookFacade;

    @Mock
    BookDao bookDao;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getBookList() {
        when(bookDao.getBooks()).thenReturn(Collections.singletonList(getBookResponse()));
        assertEquals(1, bookFacade.getBookList().size());
        assertEquals(1, bookFacade.getBookList().get(0).getBookId());
    }

    @Test
    public void getBooksByLibraryId() {
        when(bookDao.getBooksByLibraryId(anyLong())).thenReturn(Collections.singletonList(getBookResponse()));
        assertEquals(1, bookFacade.getBooksByLibraryId(1).size());
        assertEquals(1, bookFacade.getBooksByLibraryId(1).get(0).getBookId());
    }

    @Test
    public void findBookById() {
        when(bookDao.findBookById(anyLong())).thenReturn(Optional.of(getBookResponse()));
        assertEquals(1, bookFacade.findBookById(1).getBookId());
    }

    @Test
    public void removeBookById() {
        doNothing().when(bookDao).deleteBookById(anyLong());
        when(bookDao.existsById(anyLong())).thenReturn(true);
        assertEquals(HttpStatus.OK.getReasonPhrase(), bookFacade.removeBookById(1).getStatus());
    }

    @Test
    public void updateBook() {
        when(bookDao.updateOrSaveBook(any(Book.class))).thenReturn(getBookResponse());
        when(bookDao.existsById(anyLong())).thenReturn(true);
        assertEquals(HttpStatus.OK.getReasonPhrase(), bookFacade.updateBook(new Book()).getStatus());
    }

    @Test
    public void addBook() {
        when(bookDao.updateOrSaveBook(any(Book.class))).thenReturn(getBookResponse());
        assertEquals(HttpStatus.CREATED.getReasonPhrase(), bookFacade.addBook(new Book()).getStatus());
    }

    Book getBookResponse(){
        Book book = new Book();
        book.setBookId(1);
        return book;
    }
}