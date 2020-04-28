package com.assignment.demo.library.controller;

import com.assignment.demo.library.model.request.BookRequest;
import com.assignment.demo.library.model.response.BaseResponse;
import com.assignment.demo.library.model.response.BookListResponse;
import com.assignment.demo.library.model.response.BookResponse;
import com.assignment.demo.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/book")
public class BookController {
    @Autowired
    BookService bookService;

    /**
     * Get the message from Book Controller for testing
     * @return
     */
    @GetMapping(value = "greeting")
    public BaseResponse greeting() {

        return new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Message from Book controller!");
    }

    /**
     * Get the list of Books
     * @return
     */
    @GetMapping
    public BookListResponse getBookList() {
        return bookService.getBookList();
    }

    /**
     * Get the Book by Id
     * @param bookId
     * @return
     */
    @GetMapping("{bookId}")
    public BookResponse getBookById(@PathVariable("bookId") long bookId) {
        return bookService.getBookById(bookId);
    }

    /**
     * Get the list of Books by Library Id
     * @param libraryId
     * @return
     */
    @GetMapping("library/{libraryId}")
    public BookListResponse getBookByLibraryId(@PathVariable("libraryId") long libraryId) {
        return bookService.getBooksByLibraryId(libraryId);
    }

    /**
     * Remove a Book by Id
     * @param bookId
     * @return
     */
    @DeleteMapping("{bookId}")
    public BaseResponse removeBookById(@PathVariable("bookId") long bookId) {
        return bookService.removeBookById(bookId);
    }

    /**
     * Update a book details by passing book details along with Book iD
     * @param bookRequest
     * @return
     */
    @PutMapping
    public BaseResponse updateBookById(@RequestBody @Valid BookRequest bookRequest) {
        return bookService.updateBook(bookRequest);
    }

    /**
     * Add a new Book by proving the Book details
     * @param bookRequest
     * @return
     */
    @PostMapping
    public BaseResponse addBook(@RequestBody @Valid BookRequest bookRequest) {
        return bookService.addBook(bookRequest);
    }
}
