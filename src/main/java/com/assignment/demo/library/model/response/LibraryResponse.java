package com.assignment.demo.library.model.response;

import com.assignment.demo.library.model.LibraryDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LibraryResponse {
    @JsonProperty("library")
    private LibraryDto libraryDto;
    @JsonProperty("response_detail")
    private BaseResponse baseResponse;

    public LibraryDto getLibraryDto() {
        return libraryDto;
    }

    public void setLibraryDto(LibraryDto libraryDto) {
        this.libraryDto = libraryDto;
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }
}
