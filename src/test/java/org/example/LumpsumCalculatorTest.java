package org.example;

import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayInputStream;

public class LumpsumCalculatorTest {
    String input1 = "1000000\n5.5\n2\n"; // [0,1,2,3,4,5,6,7,8,9,10,11]
    String input2 = "2000000\n-5\n3.5\n2\n"; // [0,1,2,3,4,5,4,5,6,7,8,9,10,11]
    String input3 = "2000000\n3.5\n-2\n2\n"; //  [0,1,2,3,4,5,6,7,8,7,8,9,10,11]
    String input4 = "-10000\n2000000\n3.5\n2\n"; // [0,1,2,1,2,3,4,5,6,7,8,9,10,11]

    public void testing(String input, Long expectedTax){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        LumpsumCalculator lumpsumCalculator = new LumpsumCalculator();
        Long actual = lumpsumCalculator.init();
        Assert.assertEquals(expectedTax,actual);
    }

    @Test
    public void testCase1(){
        testing(input1, 1113025L);
    }

    @Test
    public void testCase2(){
        testing(input2, 2142449L);
    }
    @Test
    public void testCase3(){
        testing(input3, 2142449L);
    }

    @Test
    public void testCase4(){
        testing(input4, 2142449L);
    }
}