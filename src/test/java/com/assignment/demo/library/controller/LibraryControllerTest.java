package com.assignment.demo.library.controller;

import com.assignment.demo.library.model.LibraryDto;
import com.assignment.demo.library.model.response.BaseResponse;
import com.assignment.demo.library.model.response.LibraryListResponse;
import com.assignment.demo.library.service.LibraryService;
import junit.framework.TestCase;
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
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LibraryController.class)
public class LibraryControllerTest extends TestCase {
    String PATH = "/api/library";

    @MockBean
    LibraryService libraryService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testGreeting() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "/greeting")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", org.hamcrest.Matchers.is("Message from Library controller!")));
    }

    @Test
    public void testGetLibraryList() throws Exception {

        when(libraryService.getLibraryList()).thenReturn(getLibraryList());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.response_detail.code", org.hamcrest.Matchers.is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.libraries", hasSize(1)))
                .andExpect(jsonPath("$.libraries[0].library_id", org.hamcrest.Matchers.is(2)));
    }

    private LibraryListResponse getLibraryList(){
        LibraryListResponse libraryListResponse = new LibraryListResponse();
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setLibraryId(2);
        List<LibraryDto> libraries = Collections.singletonList(libraryDto);
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Libraries found size: " + libraries.size());
        libraryListResponse.setLibraries(libraries);
        libraryListResponse.setBaseResponse(baseResponse);
        return libraryListResponse;
    }
}