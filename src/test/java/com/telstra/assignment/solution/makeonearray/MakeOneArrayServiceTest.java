package com.telstra.assignment.solution.makeonearray;

import com.telstra.assignment.solution.exception.OneArrayNotFoundException;
import com.telstra.assignment.solution.makeonearray.domain.ArrayDto;
import com.telstra.assignment.solution.makeonearray.domain.OneArray;
import com.telstra.assignment.solution.makeonearray.service.MakeOneArrayService;
import com.telstra.assignment.solution.makeonearray.service.MakeOneArrayServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class MakeOneArrayServiceTest {

    private MakeOneArrayService makeOneArrayService;

    private ArrayDto arrayDto;
    private OneArray oneArray;

    @Before
    public void setUp() {
        makeOneArrayService = new MakeOneArrayServiceImpl();
        Integer[] array1 = { 1,2,3,4 };
        Integer[] array2 = { 3,4,5,6 };
        Integer[] array3 = { 11,1,3,6 };
        arrayDto = new ArrayDto(Arrays.asList(array1), Arrays.asList(array2), Arrays.asList(array3));
        Integer[] array = { 1,2,3,4,5,6,11 };
        oneArray = new OneArray(Arrays.asList(array));
    }

    @Test
    public void getArray_shouldReturnOneArray() {
        assertThat(makeOneArrayService.getOneArray(arrayDto).getArray()).isEqualTo(oneArray.getArray());
    }

    @Test(expected = OneArrayNotFoundException.class)
    public void getFibonacci_whenFibonacciNumberNotFound() throws Exception{
        assertThat(makeOneArrayService.getOneArray(new ArrayDto()).getArray()).isEqualTo(oneArray.getArray());
    }
}
