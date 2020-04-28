package com.assignment.demo.library.controller;

import com.assignment.demo.library.model.BookDto;
import com.assignment.demo.library.model.request.BookRequest;
import com.assignment.demo.library.model.response.BaseResponse;
import com.assignment.demo.library.model.response.BookListResponse;
import com.assignment.demo.library.model.response.BookResponse;
import com.assignment.demo.library.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class)
public class BookControllerTest {

    String PATH = "/api/book";

    @MockBean
    BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void greeting() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "/greeting")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", org.hamcrest.Matchers.is("Message from Book controller!")));
    }

    @Test
    public void getBookList() throws Exception {
        when(bookService.getBookList()).thenReturn(getBookResponseList());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.response_detail.code", org.hamcrest.Matchers.is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.books", hasSize(1)))
                .andExpect(jsonPath("$.books[0].book_id", org.hamcrest.Matchers.is(1)));
    }

    @Test
    public void getBookById() throws Exception {
        int bookId = 1;

        when(bookService.getBookById(anyLong())).thenReturn(getMockBookResponse());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "/" + bookId)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.response_detail.code", org.hamcrest.Matchers.is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.book.book_id", org.hamcrest.Matchers.is(bookId)));
    }

    @Test
    public void getBookByLibraryId() throws Exception {
        int libraryId;
        when(bookService.getBooksByLibraryId(anyLong())).thenReturn(getBookResponseList());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "/library/1")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.response_detail.code", org.hamcrest.Matchers.is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.books", hasSize(1)))
                .andExpect(jsonPath("$.books[0].book_id", org.hamcrest.Matchers.is(1)));
    }

    @Test
    public void removeBookById() throws Exception {
        int bookId = 1;

        when(bookService.removeBookById(anyLong())).thenReturn(getBaseResponse("Book Deleted!", HttpStatus.OK));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(PATH + "/" + bookId)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", org.hamcrest.Matchers.is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.message", org.hamcrest.Matchers.is("Book Deleted!")));
    }

    @Test
    public void updateBookById() throws Exception {
        String bookRequest = "{\n" +
                "    \"book_id\": 2,\n" +
                "    \"title\": \"Title 1\",\n" +
                "    \"price\": 100.00,\n" +
                "    \"volume\": 3,\n" +
                "    \"library_id\": 1\n" +
                "}";

        when(bookService.updateBook(any(BookRequest.class))).thenReturn(getBaseResponse("Book updated!", HttpStatus.OK));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(PATH )
                .accept(MediaType.APPLICATION_JSON)
                .content(bookRequest)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", org.hamcrest.Matchers.is(HttpStatus.OK.value())));
    }

    @Test
    public void addBook() throws Exception {
        String bookRequest = "{\n" +
                "    \"title\": \"Title 1\",\n" +
                "    \"price\": 100.00,\n" +
                "    \"volume\": 3,\n" +
                "    \"library_id\": 1\n" +
                "}";

        when(bookService.addBook(any(BookRequest.class))).thenReturn(getBaseResponse("Book created successfully!", HttpStatus.CREATED));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(PATH )
                .accept(MediaType.APPLICATION_JSON)
                .content(bookRequest)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", org.hamcrest.Matchers.is(HttpStatus.CREATED.value())));
    }

    private BookListResponse getBookResponseList(){
        BookListResponse bookListResponse = new BookListResponse();
        List<BookDto> books = Collections.singletonList(getBookDto());
        bookListResponse.setBooks(books);
        bookListResponse.setBaseResponse(getBaseResponse("Libraries found size: " + books.size(), HttpStatus.OK));
        return bookListResponse;
    }

    private BookResponse getMockBookResponse(){
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBaseResponse(getBaseResponse("Book found!", HttpStatus.OK));
        bookResponse.setBookDto(getBookDto());
        return  bookResponse;
    }

    private BookDto getBookDto(){
        BookDto bookDto = new BookDto();
        bookDto.setBookId(1);
        return bookDto;
    }

    private BaseResponse getBaseResponse(String message, HttpStatus httpStatus) {
        return new BaseResponse(httpStatus.getReasonPhrase(), httpStatus.value(), message);
    }
}