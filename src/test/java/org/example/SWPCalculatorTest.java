package org.example;

import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayInputStream;

public class SWPCalculatorTest {
    String input1 = "100000\n5000\n5.5\n0\n"; // [0,1,2,3,4,5,6,7,8,9,10,11,12,13,17]
    String input2 = "100000\n-5000\n5000\n5.5\n3\n"; // [0,1,2,3,4,5,4,5,6,7,8,9,10,11,12,13,17]
    String input3 = "100000\n5000\n5.5\n-2\n3\n"; // [0,1,2,3,4,5,6,7,8,9,8,9,10,11,12,13,17]
    String input4 = "-3500\n100000\n5000\n5.5\n3\n"; // [0,1,2,1,2,3,4,5,6,7,8,9,10,11,12,13,17]
    String input5 = "500000\n500000\n5.5\n2\n"; // [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,17]
    String input6 = "500000\n250000\n5.5\n2\n"; // [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,13,14,17]
    String input7 = "600000\n200000\n5.5\n2\n"; // [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,13,14,15,16,13,17]
    
   

    public void testing(String input, Long expectedTax){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        SWPCalculator swpCalculator = new SWPCalculator();
        Long actual = swpCalculator.init();
        Assert.assertEquals(expectedTax,actual);
    }

    @Test
    public void testCase1(){
        testing(input1, 0L);
    }

    @Test
    public void testCase2(){
        testing(input2, 4621L);
    }

    @Test
    public void testCase3(){
        testing(input3, 4621L);
    }

    @Test
    public void testCase4(){
        testing(input4, 4621L);
    }

    @Test
    public void testCase5(){
        testing(input5, 4621L);
    }

    @Test
    public void testCase6(){
        testing(input6, 4621L);
    }

    @Test
    public void testCase7(){
        testing(input7, 4621L);
    }

}