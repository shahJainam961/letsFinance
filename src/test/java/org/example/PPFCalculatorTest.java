package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class PPFCalculatorTest {

    String input1 = "100000\n2\n"; // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
    String input2 = "-10000\n200000\n-2\n2\n"; // [0, 1, 2, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 7, 8, 9, 10, 11]

    public void testing(String input, Long expectedTax){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        PPFCalculator ppfCalculator = new PPFCalculator();
        Long actual = ppfCalculator.init();
        Assert.assertEquals(expectedTax,actual);
    }

    @Test
    public void testCase1(){
        testing(input1, 207099L);
    }

    @Test
    public void testCase2(){
        testing(input2, 414199L);
    }

}