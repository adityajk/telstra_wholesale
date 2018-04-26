package com.telstra.assignment.solution.reversewords;

import com.telstra.assignment.solution.exception.WordNotFoundException;
import com.telstra.assignment.solution.reversewords.service.ReverseWordService;
import com.telstra.assignment.solution.reversewords.service.ReverseWordServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReverseWordServiceTest {

    ReverseWordService reverseWordService;

    @Before
    public void setUp() {
        reverseWordService = new ReverseWordServiceImpl();
    }

    @Test
    public void getReverse_shouldReturnReverseWord() {
        assertThat(reverseWordService.reverseWords("how%20are%20you")).isEqualTo("woh era uoy");
    }

    @Test(expected = WordNotFoundException.class)
    public void getFibonacci_whenFibonacciNumberNotFound(){
        assertThat(reverseWordService.reverseWords(null));
    }
}
