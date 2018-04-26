package com.telstra.assignment.solution.triangletype;

import com.telstra.assignment.solution.exception.WrongTriangleTypeException;
import com.telstra.assignment.solution.triangletype.controller.TriangleTypeController;
import com.telstra.assignment.solution.triangletype.service.TriangleTypeService;
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
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TriangleTypeController.class)
public class TriangleTypeControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private TriangleTypeService triangleTypeService;
    private static final String TRIANGLE_TYPE = "Equilateral";

    @Test
    public void getTriangle_returnTriangleType() throws Exception {
        given(triangleTypeService.getTriangleType(1, 1, 1)).willReturn(TRIANGLE_TYPE);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/TriangleType?a=1&b=1&c=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(TRIANGLE_TYPE));
    }

    @Test
    public void getFibonacci_notFound() throws Exception {
        given(triangleTypeService.getTriangleType(anyInt(), anyInt(), anyInt())).willThrow(new WrongTriangleTypeException());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/TriangleType?a=-1&b=10&c=-61"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getFibonacci_checkCacheHeaders() throws Exception {
        given(triangleTypeService.getTriangleType(1, 1, 1)).willReturn(TRIANGLE_TYPE);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/TriangleType?a=1&b=1&c=1"))
                .andExpect(header().stringValues("no-cache,no-store,must-revalidate,private,max-age=0"))
                .andExpect(header().string("Pragma", "no-cache"));
    }
}
