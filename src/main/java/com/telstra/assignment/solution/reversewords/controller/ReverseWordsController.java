package com.telstra.assignment.solution.reversewords.controller;

import com.telstra.assignment.solution.exception.WordNotFoundException;
import com.telstra.assignment.solution.reversewords.service.ReverseWordService;
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
public class ReverseWordsController {

    ReverseWordService reverseWordService;

    public ReverseWordsController(ReverseWordService reverseWordService) {
        this.reverseWordService = reverseWordService;
    }

    @GetMapping("/api/ReverseWords")
    public ResponseEntity<String> getReverseWords(@RequestParam("sentence") String sentence) {
        return ResponseEntity.ok(reverseWordService.reverseWords(sentence));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String reverseWordNotFoundHandler(WordNotFoundException ex) {
        String result = ex.getErrorMessage();
        return result;
    }
}
