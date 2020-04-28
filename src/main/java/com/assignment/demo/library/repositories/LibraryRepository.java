package com.assignment.demo.library.repositories;

import com.assignment.demo.library.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    List<Library> findAll();
}
