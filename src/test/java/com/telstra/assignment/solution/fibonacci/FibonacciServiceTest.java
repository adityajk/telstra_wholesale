package com.telstra.assignment.solution.fibonacci;

import com.telstra.assignment.solution.exception.FibonacciNumberNotFoundException;
import com.telstra.assignment.solution.fibonacci.service.FibonacciService;
import com.telstra.assignment.solution.fibonacci.service.FibonacciServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class FibonacciServiceTest {

    private FibonacciService fibonacciService;

    @Before
    public void setUp() {
        fibonacciService = new FibonacciServiceImpl();
    }

    @Test
    public void getFibonacci_returnsFibonacciNumber(){
        assertThat(fibonacciService.getFibonacciNumber(0)).isEqualTo(0L);
        assertThat(fibonacciService.getFibonacciNumber(1)).isEqualTo(1L);
        assertThat(fibonacciService.getFibonacciNumber(2)).isEqualTo(1L);
        assertThat(fibonacciService.getFibonacciNumber(3)).isEqualTo(2L);
        assertThat(fibonacciService.getFibonacciNumber(10)).isEqualTo(55L);
    }

    @Test(expected = FibonacciNumberNotFoundException.class)
    public void getFibonacci_whenFibonacciNumberNotFound(){
        assertThat(fibonacciService.getFibonacciNumber(-10)).isEqualTo(0L);
    }
}
