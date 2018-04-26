package com.telstra.assignment.solution.reversewords.service;

import com.telstra.assignment.solution.exception.WordNotFoundException;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@Service
public class ReverseWordServiceImpl implements ReverseWordService {
    @Override
    public String reverseWords(String sentence) {
        if(null == sentence) {
            throw new WordNotFoundException();
        }
        return Pattern.compile("%20+").splitAsStream(sentence)
                .map(word->new StringBuilder(word).reverse())
                .collect(Collectors.joining(" "));
    }
}
