package com.telstra.assignment.solution.makeonearray.domain;

import java.util.List;

/**
 * Created by aditya.khajuria on 4/23/2018.
 */
public class ArrayDto {
    private List<Integer> array1;
    private List<Integer> array2;
    private List<Integer> array3;

    public ArrayDto(List<Integer> array1, List<Integer> array2, List<Integer> array3) {
        this.array1 = array1;
        this.array2 = array2;
        this.array3 = array3;
    }

    public ArrayDto() {
    }

    public List<Integer> getArray1() {
        return array1;
    }

    public void setArray1(List<Integer> array1) {
        this.array1 = array1;
    }

    public List<Integer> getArray2() {
        return array2;
    }

    public void setArray2(List<Integer> array2) {
        this.array2 = array2;
    }

    public List<Integer> getArray3() {
        return array3;
    }

    public void setArray3(List<Integer> array3) {
        this.array3 = array3;
    }
}
