package com.telstra.assignment.solution;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telstra.assignment.solution.makeonearray.domain.ArrayDto;
import com.telstra.assignment.solution.makeonearray.domain.OneArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getFibonacci_returnsFibonacciNumber() {
        //arrange

        //act
        ResponseEntity<Long> longResponseEntity = restTemplate.getForEntity("/api/Fibonacci?n=10", Long.class);

        //assert
        assertThat(longResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(longResponseEntity.getBody()).isEqualTo(55);
    }

    @Test
    public void getFibonacci_returnsReverseWords() {
        //arrange

        //act
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/api/ReverseWords?sentence=how%20are%20you", String.class);

        //assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("woh era uoy");
    }

    @Test
    public void getTriangle_returnsTriangleType() {
        //arrange

        //act
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/api/TriangleType?a=1&b=1&c=1", String.class);

        //assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualToIgnoringCase("Equilateral");
    }

    @Test
    public void getSingleArray_returnsSingleArray() throws JsonProcessingException {
        //arrange
        Integer[] array1 = { 1,2,3,4 };
        Integer[] array2 = { 3,4,5,6 };
        Integer[] array3 = { 6,1,3,11 };
        ArrayDto arrayDto = new ArrayDto(Arrays.asList(array1), Arrays.asList(array2), Arrays.asList(array3));
        Integer[] array = { 1,2,3,4,5,6,11 };
        OneArray oneArray = new OneArray(Arrays.asList(array));
        final String jString = objectMapper.writeValueAsString(arrayDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(jString,headers);

        //act
        ResponseEntity<OneArray> responseEntity = restTemplate.postForEntity("/api/makeonearray", entity, OneArray.class);

        //assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getArray()).isEqualTo(oneArray.getArray());
    }
}
