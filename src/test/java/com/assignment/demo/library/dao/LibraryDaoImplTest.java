package com.assignment.demo.library.dao;

import com.assignment.demo.library.entities.Library;
import com.assignment.demo.library.repositories.LibraryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LibraryDaoImplTest {

    @InjectMocks
    LibraryDaoImpl libraryDao;

    @Mock
    LibraryRepository libraryRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getLibraries() {
        when(libraryRepository.findAll()).thenReturn(Collections.singletonList(new Library()));
        assertEquals(1, libraryDao.getLibraries().size());
    }
}