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
    private ResponseEntity<Long> getFibonacciNumber(@RequestParam(value="n", defaultValue = "0.1") double n) {
        if(n == 0.1 || (n-(int)n)!=0) {
            throw new FibonacciNumberNotFoundException("Number cannot be empty or in decimal !!");
        }
        return ResponseEntity.ok(fibonacciService.getFibonacciNumber((int) n));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String fibonacciNumberNotFoundHandler(FibonacciNumberNotFoundException ex) {
        String result = ex.getErrorMessage();
        return result;
    }
}
