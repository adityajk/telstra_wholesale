package com.telstra.assignment.solution.makeonearray;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telstra.assignment.solution.exception.OneArrayNotFoundException;
import com.telstra.assignment.solution.makeonearray.controller.MakeOneArrayController;
import com.telstra.assignment.solution.makeonearray.domain.ArrayDto;
import com.telstra.assignment.solution.makeonearray.domain.OneArray;
import com.telstra.assignment.solution.makeonearray.service.MakeOneArrayService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MakeOneArrayController.class)
public class MakeOneArrayControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MakeOneArrayService makeOneArrayService;

    private static final String ARRAY_URL= "/api/makeonearray";

    private JacksonTester<ArrayDto> jsonTester;
    private ArrayDto arrayDto;
    private OneArray oneArray;

    @Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);
        Integer[] array1 = { 1,2,3,4 };
        Integer[] array2 = { 3,4,5,6 };
        Integer[] array3 = { 6,1,3,11 };
        arrayDto = new ArrayDto(Arrays.asList(array1), Arrays.asList(array2), Arrays.asList(array3));
        Integer[] array = { 1,2,3,4,5,6,11 };
        oneArray = new OneArray(Arrays.asList(array));
    }

    @Test
    public void getOneArray_returnOneArray() throws Exception {
        final String arrayDTOJson = jsonTester.write(arrayDto).getJson();
        given(makeOneArrayService.getOneArray(any())).willReturn(oneArray);

        mockMvc.perform(MockMvcRequestBuilders.post(ARRAY_URL).
                content(arrayDTOJson).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"array\":[1,2,3,4,5,6,11]}"));
    }

    @Test
    public void getOneArray_notFound() throws Exception {
        final String arrayDTOJson = jsonTester.write(new ArrayDto()).getJson();
        given(makeOneArrayService.getOneArray(any())).willThrow(new OneArrayNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.post(ARRAY_URL).
                content(arrayDTOJson).contentType(APPLICATION_JSON));
    }

    @Test
    public void getFibonacci_checkCacheHeaders() throws Exception {
        final String arrayDTOJson = jsonTester.write(new ArrayDto()).getJson();
        given(makeOneArrayService.getOneArray(any())).willReturn(oneArray);

        mockMvc.perform(MockMvcRequestBuilders.post(ARRAY_URL).
                content(arrayDTOJson).contentType(APPLICATION_JSON))
                .andExpect(header().stringValues("no-cache,no-store,must-revalidate,private,max-age=0"))
                .andExpect(header().string("Pragma", "no-cache"));
    }
}
