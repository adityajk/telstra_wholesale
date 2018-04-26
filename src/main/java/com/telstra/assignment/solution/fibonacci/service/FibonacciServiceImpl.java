package com.telstra.assignment.solution.fibonacci.service;

import com.telstra.assignment.solution.exception.FibonacciNumberNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@Service
public class FibonacciServiceImpl implements FibonacciService {

    @Override
    //get the nth element from the stream
    public Long getFibonacciNumber(int n) {
        if (n < 0) {
            throw new FibonacciNumberNotFoundException();
        }
            return Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
                    .limit(n + 1L)
                    .reduce((a, b) -> b)
                    .get()[0];
    }
}
