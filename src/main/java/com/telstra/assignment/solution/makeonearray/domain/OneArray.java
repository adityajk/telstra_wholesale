package com.telstra.assignment.solution.makeonearray.domain;

import java.util.List;

/**
 * Created by aditya.khajuria on 4/23/2018.
 */
public class OneArray {
    private List<Integer> array;

    public OneArray(List<Integer> array) {
        this.array = array;
    }

    public OneArray() {
    }

    public List<Integer> getArray() {
        return array;
    }

    public void setArray(List<Integer> array) {
        this.array = array;
    }
}
