package com.telstra.assignment.solution.fibonacci.controller;

import com.telstra.assignment.solution.exception.FibonacciNumberNotFoundException;
import com.telstra.assignment.solution.fibonacci.service.FibonacciService;
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
public class FibonacciController {

    private FibonacciService fibonacciService;

    public FibonacciController(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }

    @GetMapping("/api/Fibonacci")
    private ResponseEntity<Long> getFibonacciNumber(@RequestParam("n") int n) {
        return ResponseEntity.ok(fibonacciService.getFibonacciNumber(n));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void fibonacciNumberNotFoundHandler(FibonacciNumberNotFoundException ex) {
        // If required a proper messgae can be returned.
    }
}
