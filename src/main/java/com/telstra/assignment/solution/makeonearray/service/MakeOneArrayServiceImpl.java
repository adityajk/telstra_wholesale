package com.telstra.assignment.solution.makeonearray.service;

import com.telstra.assignment.solution.exception.OneArrayNotFoundException;
import com.telstra.assignment.solution.makeonearray.domain.ArrayDto;
import com.telstra.assignment.solution.makeonearray.domain.OneArray;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by aditya.khajuria on 4/23/2018.
 */
@Service
public class MakeOneArrayServiceImpl implements MakeOneArrayService {

    public OneArray getOneArray(ArrayDto arrayDto) {
        if(null == arrayDto.getArray1() || null == arrayDto.getArray2() || null == arrayDto.getArray3()) {
            throw new OneArrayNotFoundException("Array cannot be empty !!");
        }

        List<Integer> newList = Stream.of(arrayDto.getArray1(), arrayDto.getArray2(), arrayDto.getArray3())
                .flatMap(Collection::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        return new OneArray(newList);
    }
}
