package com.telstra.assignment.solution.makeonearray.controller;

import com.telstra.assignment.solution.exception.OneArrayNotFoundException;
import com.telstra.assignment.solution.makeonearray.domain.ArrayDto;
import com.telstra.assignment.solution.makeonearray.domain.OneArray;
import com.telstra.assignment.solution.makeonearray.service.MakeOneArrayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@RestController
public class MakeOneArrayController {

    MakeOneArrayService makeOneArrayService;

    public MakeOneArrayController(MakeOneArrayService makeOneArrayService) {
        this.makeOneArrayService = makeOneArrayService;
    }

    @PostMapping("/api/makeonearray")
    public ResponseEntity<OneArray> getOneArray(@RequestBody ArrayDto arrayDto) {
        OneArray oneArray = makeOneArrayService.getOneArray(arrayDto);
        return ResponseEntity.ok(oneArray);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void oneArrayNotFoundHandler(OneArrayNotFoundException ex) {
        // A proper exception message can be returned.
    }
}
