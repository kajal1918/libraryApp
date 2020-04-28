package com.assignment.demo.library.controller;

import com.assignment.demo.library.model.response.BaseResponse;
import com.assignment.demo.library.model.response.LibraryListResponse;
import com.assignment.demo.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/library")
public class LibraryController {
    @Autowired
    LibraryService libraryService;

    /**Get the messgae from Library Controller for testing
     * @return
     */
    @GetMapping(value = "greeting")
    public BaseResponse greeting() {
        return new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Message from Library controller!");
    }

    /**
     * Get the list of Library
     * @return
     */
    @GetMapping
    public LibraryListResponse getLibraryList() {
        return libraryService.getLibraryList();
    }
}
