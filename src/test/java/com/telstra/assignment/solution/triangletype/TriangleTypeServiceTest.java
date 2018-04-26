package com.telstra.assignment.solution.triangletype;

import com.telstra.assignment.solution.exception.WrongTriangleTypeException;
import com.telstra.assignment.solution.triangletype.service.TriangleTypeService;
import com.telstra.assignment.solution.triangletype.service.TriangleTypeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class TriangleTypeServiceTest {

    TriangleTypeService triangleTypeService;

    @Before
    public void setUp() {
        triangleTypeService = new TriangleTypeServiceImpl();
    }

    @Test
    public void getTriangle_shouldReturnTriangleType() {
        assertThat(triangleTypeService.getTriangleType(1,1,1)).isEqualToIgnoringCase("Equilateral");
    }

    @Test(expected = WrongTriangleTypeException.class)
    public void getTriangle_whenTriangleTypeNotFound(){
        assertThat(triangleTypeService.getTriangleType(-1, -10, 99));
    }
}
