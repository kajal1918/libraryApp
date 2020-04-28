package com.assignment.demo.library.dao;

import com.assignment.demo.library.entities.Book;
import com.assignment.demo.library.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookDaoImplTest {

    @InjectMocks
    BookDaoImpl bookDao;

    @Mock
    BookRepository bookRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getBooks() {
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(new Book()));
        assertEquals(1, bookDao.getBooks().size());
    }

    @Test
    public void getBooksByLibraryId() {
        when(bookRepository.findAllByLibraryLibraryId(1)).thenReturn(Collections.singletonList(new Book()));
        assertEquals(1, bookDao.getBooksByLibraryId(1).size());
    }

    @Test
    public void findBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(new Book()));
        assertNotNull(bookDao.findBookById(1));
    }

    @Test
    public void deleteBookById() {
        doNothing().when(bookRepository).deleteById(1L);
        bookDao.deleteBookById(1);
        verify(bookRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void existsById() {
        when(bookRepository.existsById(1L)).thenReturn(true);
        assertTrue(bookDao.existsById(1));
    }

    @Test
    public void updateOrSaveBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(new Book());
        assertNotNull(bookDao.updateOrSaveBook(new Book()));
    }
}