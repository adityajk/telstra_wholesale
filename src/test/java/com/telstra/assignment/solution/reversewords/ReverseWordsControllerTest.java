package com.telstra.assignment.solution.reversewords;

import com.telstra.assignment.solution.exception.WordNotFoundException;
import com.telstra.assignment.solution.reversewords.controller.ReverseWordsController;
import com.telstra.assignment.solution.reversewords.service.ReverseWordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ReverseWordsController.class)
public class ReverseWordsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReverseWordService reverseWordService;

    private static final String WORD_URL = "/api/ReverseWords";
    private static final String MAIN_SENTENCE = "how%20are%20you";
    private static final String PARAM_VALUE = "sentence";
    private static final String REVERSE_SENTENCE = "woh era uoy";

    @Test
    public void getReverse_shouldReturnReverseWords() throws Exception {
        given(reverseWordService.reverseWords(MAIN_SENTENCE)).willReturn(REVERSE_SENTENCE);

        mockMvc.perform(MockMvcRequestBuilders.get(WORD_URL).param(PARAM_VALUE, MAIN_SENTENCE))
                .andExpect(status().isOk())
                .andExpect(content().string(REVERSE_SENTENCE));
    }

    @Test
    public void getFibonacci_notFound() throws Exception {
        given(reverseWordService.reverseWords(anyString())).willThrow(new WordNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get(WORD_URL).param(PARAM_VALUE, MAIN_SENTENCE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getFibonacci_checkCacheHeaders() throws Exception {
        given(reverseWordService.reverseWords(MAIN_SENTENCE)).willReturn(REVERSE_SENTENCE);

        mockMvc.perform(MockMvcRequestBuilders.get(WORD_URL).param(PARAM_VALUE, MAIN_SENTENCE))
                .andExpect(header().stringValues("no-cache,no-store,must-revalidate,private,max-age=0"))
                .andExpect(header().string("Pragma", "no-cache"));
    }
}
