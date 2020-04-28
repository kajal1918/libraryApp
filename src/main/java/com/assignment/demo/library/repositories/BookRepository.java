package com.assignment.demo.library.repositories;

import com.assignment.demo.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByLibraryLibraryId(long libraryId);
}
