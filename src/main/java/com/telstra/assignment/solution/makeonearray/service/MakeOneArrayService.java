package com.telstra.assignment.solution.makeonearray.service;

import com.telstra.assignment.solution.makeonearray.domain.ArrayDto;
import com.telstra.assignment.solution.makeonearray.domain.OneArray;

/**
 * Created by aditya.khajuria on 4/23/2018.
 */
public interface MakeOneArrayService {
    OneArray getOneArray(ArrayDto arrayDto);
}
