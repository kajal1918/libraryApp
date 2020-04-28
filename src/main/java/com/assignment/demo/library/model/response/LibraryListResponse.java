package com.assignment.demo.library.model.response;

import com.assignment.demo.library.model.LibraryDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LibraryListResponse {
    List<LibraryDto> libraries;
    @JsonProperty("response_detail")
    private BaseResponse baseResponse;

    public LibraryListResponse() {

    }

    public LibraryListResponse(List<LibraryDto> libraries, BaseResponse baseResponse) {
        this.libraries = libraries;
        this.baseResponse = baseResponse;
    }

    public List<LibraryDto> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<LibraryDto> libraries) {
        this.libraries = libraries;
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }
}
