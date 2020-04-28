package com.assignment.demo.library.util;

import com.assignment.demo.library.entities.Book;
import com.assignment.demo.library.entities.Library;
import com.assignment.demo.library.model.BookDto;
import com.assignment.demo.library.model.LibraryDto;
import com.assignment.demo.library.model.request.BookRequest;
import org.springframework.stereotype.Component;

@Component
public class EntityAndBeanConversion {
    public BookDto getResponseByEntity(Book book) {

        BookDto bookDto = new BookDto();
        bookDto.setBookId(book.getBookId());
        bookDto.setPrice(book.getPrice());
        bookDto.setTitle(book.getTitle());
        bookDto.setVolume(book.getVolume());
        bookDto.setLibraryId(book.getLibrary().getLibraryId());

        return bookDto;
    }

    public LibraryDto getResponseByEntity(Library library) {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setLibraryId(library.getLibraryId());
        libraryDto.setName(library.getName());
        libraryDto.setType(library.getType());
        return libraryDto;
    }

    public Book getEntityByRequest(BookRequest bookRequest) {
        Book book = new Book();
        Library library = new Library();
        library.setLibraryId(bookRequest.getLibraryId());
        book.setVolume(bookRequest.getVolume());
        book.setTitle(bookRequest.getTitle());
        book.setPrice(bookRequest.getPrice());
        book.setBookId(bookRequest.getBookId());
        book.setLibrary(library);
        return book;
    }
}
