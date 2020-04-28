package com.assignment.demo.library.facade;

import com.assignment.demo.library.dao.LibraryDao;
import com.assignment.demo.library.entities.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryFacadeImpl implements LibraryFacade {

    @Autowired
    LibraryDao libraryDao;

    @Override
    public List<Library> getLibraryList() {
        return libraryDao.getLibraries();
    }
}
