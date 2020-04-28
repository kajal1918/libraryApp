package com.assignment.demo.library.service;

import com.assignment.demo.library.entities.Library;
import com.assignment.demo.library.facade.LibraryFacade;
import com.assignment.demo.library.model.LibraryDto;
import com.assignment.demo.library.model.response.BaseResponse;
import com.assignment.demo.library.model.response.LibraryListResponse;
import com.assignment.demo.library.util.EntityAndBeanConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {
    final static Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);
    @Autowired
    LibraryFacade libraryFacade;

    @Autowired
    EntityAndBeanConversion entityAndBeanConversion;

    @Override
    public LibraryListResponse getLibraryList() {

        List<Library> libraries = libraryFacade.getLibraryList();

        if (libraries != null && !libraries.isEmpty()) {
            List<LibraryDto> libraryResponseList = libraries
                    .stream()
                    .map(library -> entityAndBeanConversion.getResponseByEntity(library))
                    .collect(Collectors.toList());
            BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Libraries found size: " + libraries.size());
            logger.info("Libraries found size: {}", libraries.size());
            return new LibraryListResponse(libraryResponseList, baseResponse);
        } else {
            BaseResponse baseResponse = new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), "Library list is empty!");
            logger.info("Library list is empty!");
            return new LibraryListResponse(new ArrayList<>(), baseResponse);
        }
    }
}
