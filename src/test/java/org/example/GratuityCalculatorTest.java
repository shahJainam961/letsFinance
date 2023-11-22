package org.example;

import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayInputStream;

public class GratuityCalculatorTest {
    String input1 = "3400\n12\n"; // [0,1,2,3,4,5,6,7,8]
    String input2 = "-10000\n2300\n12\n"; // [0,1,2,1,2,3,4,5,6,7,8]
    String input3 = "2300\n-5\n12\n"; // [0,1,2,3,4,5,4,5,6,7,8]

    public void testing(String input, Long expectedTax){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        GratuityCalculator gratuityCalculator = new GratuityCalculator();
        Long actual = gratuityCalculator.init();
        Assert.assertEquals(expectedTax,actual);
    }

    @Test
    public void testCase1(){
        testing(input1, 23538L);
    }

    @Test
    public void testCase2(){
        testing(input2, 15923L);
    }
    @Test
    public void testCase3(){
        testing(input3, 15923L);
    }
}