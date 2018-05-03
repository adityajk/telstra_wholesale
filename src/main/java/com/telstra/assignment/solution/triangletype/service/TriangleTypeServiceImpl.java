package com.telstra.assignment.solution.triangletype.service;

import com.telstra.assignment.solution.exception.WrongTriangleTypeException;
import org.springframework.stereotype.Service;

import static com.telstra.assignment.solution.triangletype.TriangleType.EQUILATERAL;
import static com.telstra.assignment.solution.triangletype.TriangleType.INVALID;
import static com.telstra.assignment.solution.triangletype.TriangleType.ISOSCELES;
import static com.telstra.assignment.solution.triangletype.TriangleType.SCALENE;

/**
 * Created by aditya.khajuria on 4/22/2018.
 */
@Service
public class TriangleTypeServiceImpl implements TriangleTypeService {
    @Override
    public String getTriangleType(int a, int b, int c) {
        if(a < 0 || b < 0 || c < 0) {
            throw new WrongTriangleTypeException("Sides of triangle cannot be less than zero !!");
        }
        if (a <= 0 || b <= 0 || c <= 0) return INVALID.toString();
        if (a == b && b == c) return EQUILATERAL.toString();
        if (a >= b+c || c >= b+a || b >= a+c) return INVALID.toString();
        if (b==c || a==b || c==a) return ISOSCELES.toString();
        return SCALENE.toString();
    }
}
