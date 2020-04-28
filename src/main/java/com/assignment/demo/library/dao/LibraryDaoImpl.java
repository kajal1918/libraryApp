package com.assignment.demo.library.dao;

import com.assignment.demo.library.entities.Library;
import com.assignment.demo.library.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibraryDaoImpl implements LibraryDao {

    @Autowired
    LibraryRepository libraryRepository;

    @Override
    public List<Library> getLibraries() {
        return libraryRepository.findAll();
    }
}
