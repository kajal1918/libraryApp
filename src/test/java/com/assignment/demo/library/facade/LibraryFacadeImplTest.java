package com.assignment.demo.library.facade;

import com.assignment.demo.library.dao.LibraryDao;
import com.assignment.demo.library.entities.Library;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LibraryFacadeImplTest {

    @InjectMocks
    LibraryFacadeImpl libraryFacade;

    @Mock
    LibraryDao libraryDao;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getLibraryList() {
        Library library = new Library();
        library.setLibraryId(1);
        when(libraryDao.getLibraries()).thenReturn(Collections.singletonList(library));
        assertEquals(1, libraryFacade.getLibraryList().size());
        assertEquals(1, libraryFacade.getLibraryList().get(0).getLibraryId());
    }
}