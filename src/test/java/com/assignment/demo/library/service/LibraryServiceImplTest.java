package com.assignment.demo.library.service;

import com.assignment.demo.library.entities.Library;
import com.assignment.demo.library.facade.LibraryFacade;
import com.assignment.demo.library.model.LibraryDto;
import com.assignment.demo.library.util.EntityAndBeanConversion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LibraryServiceImplTest {

    @InjectMocks
    LibraryServiceImpl libraryService;

    @Mock
    LibraryFacade libraryFacade;

    @Mock
    EntityAndBeanConversion entityAndBeanConversion;
    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getLibraryList() {
        when(libraryFacade.getLibraryList()).thenReturn(Collections.singletonList(new Library()));
        when(entityAndBeanConversion.getResponseByEntity(any(Library.class))).thenReturn(new LibraryDto());

        assertEquals(1, libraryService.getLibraryList().getLibraries().size());
        assertEquals(HttpStatus.OK.getReasonPhrase(), libraryService.getLibraryList().getBaseResponse().getStatus());
    }
}