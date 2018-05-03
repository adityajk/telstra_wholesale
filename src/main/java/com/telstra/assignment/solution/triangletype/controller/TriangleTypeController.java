package com.telstra.assignment.solution.triangletype.controller;

import com.telstra.assignment.solution.exception.WrongTriangleTypeException;
import com.telstra.assignment.solution.triangletype.service.TriangleTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@RestController
public class TriangleTypeController {

    TriangleTypeService triangleTypeService;

    public TriangleTypeController(TriangleTypeService triangleTypeService) {
        this.triangleTypeService = triangleTypeService;
    }

    @GetMapping("/api/TriangleType")
    public ResponseEntity<String> getTriangleType(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("c") int c) {
        return ResponseEntity.ok(triangleTypeService.getTriangleType(a, b, c));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String reverseWordNotFoundHandler(WrongTriangleTypeException ex) {
        String result = ex.getErrorMessage();
        return result;
    }
}
