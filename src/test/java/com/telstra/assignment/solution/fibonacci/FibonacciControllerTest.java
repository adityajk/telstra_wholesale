package com.telstra.assignment.solution.fibonacci;

import com.telstra.assignment.solution.exception.FibonacciNumberNotFoundException;
import com.telstra.assignment.solution.fibonacci.controller.FibonacciController;
import com.telstra.assignment.solution.fibonacci.service.FibonacciService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FibonacciController.class)
public class FibonacciControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FibonacciService fibonacciService;

    private static final String FIBONACCI_URL = "/api/Fibonacci";

    @Test
    public void getFibonacci_ShouldReturnFibonacciNumber() throws Exception {
        given(fibonacciService.getFibonacciNumber(10)).willReturn(55L);

        mockMvc.perform(MockMvcRequestBuilders.get(FIBONACCI_URL).param("n", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string("55"));
    }

    @Test
    public void getFibonacci_notFound() throws Exception {
        given(fibonacciService.getFibonacciNumber(anyInt())).willThrow(new FibonacciNumberNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get(FIBONACCI_URL).param("n", "10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getFibonacci_checkCacheHeaders() throws Exception {
        given(fibonacciService.getFibonacciNumber(10)).willReturn(55L);

        mockMvc.perform(MockMvcRequestBuilders.get(FIBONACCI_URL).param("n", "10"))
                .andExpect(header().stringValues("no-cache,no-store,must-revalidate,private,max-age=0"))
                .andExpect(header().string("Pragma", "no-cache"));
    }
}
