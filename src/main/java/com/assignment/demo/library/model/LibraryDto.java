package com.assignment.demo.library.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LibraryDto {
    @JsonProperty("library_id")
    private long libraryId;
    private String name;
    private String type;

    public long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(long libraryId) {
        this.libraryId = libraryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
