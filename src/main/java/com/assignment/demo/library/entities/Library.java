package com.assignment.demo.library.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "library")
public class Library implements Serializable {

    private long libraryId;
    private String name;
    private String type;

    public Library() {
    }

    @Id
    @Column(name = "library_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(long libraryId) {
        this.libraryId = libraryId;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return libraryId == library.libraryId &&
                name.equals(library.name) &&
                type.equals(library.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryId, name, type);
    }

    @Override
    public String toString() {
        return "Library{" +
                "libraryId=" + libraryId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
