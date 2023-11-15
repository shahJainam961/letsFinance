package org.example;

import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.util.Optional;

public class TaxCalculatorTest{
    String input1 = "2\n200000\nabc\n"; // [0,1,2,3,7,8,9,10,4]
    String input2 = "3\nan\n"; // [0,1,2,1,4]
    String input3 = "1\n1\nabc\n"; // [0,1,2,3,5,6,7,4]
    String input4 = "1\n5\nabc\n"; // [0,1,2,3,5,6,5,4]
    String input5 = "2\n1200000\n0\n"; // [0,1,2,3,7,8,9,10,11,12,17,18]
    String input6 = "abc\n"; // [0,1,4]
    String input7 = "1\nabx\n"; // [0,1,2,3,5,4]
    String input8 = "4\n2\nabc\n"; // [0,1,2,1,2,3,7,4]
    String input9 = "1\n5\n1\nabc\n"; // [0,1,2,3,5,6,5,6,7,4]
    String input10 = "1\n1\n600000\nabc\n0\n0\n0\n0\n"; // [0,1,2,3,5,6,7,8,9,10,4]
    String input11 = "2\n6000000\n0\n"; // [0,1,2,3,7,8,9,11,12,13,17,18]
    String input12 = "2\n1-10000\n0\n"; // [0,1,2,3,7,8,9,11,12,14,17,18]
    String input13 = "2\n2-10000\n0\n"; // [0,1,2,3,7,8,9,11,12,15,17,18]
    String input14 = "2\n60000000\n0\n"; // [0,1,2,3,7,8,9,11,12,16,17,18]
    String input15 = "2\n-1000\n0\n"; // [0,1,2,3,7,8,9,11,12,16,17,18]
    String input16 = "1\n1\n12000000\n-10\n20000\n0\n0\n-100\n"; // [0,1,2,3,5,6,7,8,9,10,11,12,14,17,18]
    String input17 = "1\n1\n600000\n-10\n20000\n0\n0\n-100\n"; // [0,1,2,3,5,6,7,8,9,10,11,12,13,17,18]
    String input18 = "1\n1\n2-10000\n-10\n20000\n0\n0\n-100\n"; // [0,1,2,3,5,6,7,8,9,10,11,12,15,17,18]
    String input19 = "1\n1\n55000000\n-10\n20000\n0\n0\n-100\n"; // [0,1,2,3,5,6,7,8,9,10,11,12,16,17,18]
    String input20 = "1\n1\n200000\n-10\n20000\n0\n0\n-100\n"; // [0,1,2,3,5,6,7,8,9,10,11,12,17,18]
    String input21 = "2\n600000\n-100\n"; // [0,1,2,3,7,8,9,10,11,12,13,17,18]
    String input22 = "2\n2-10000\n-100\n"; // [0,1,2,3,7,8,9,10,11,12,15,17,18]
    String input23 = "2\n12000000\n-100\n"; // [0,1,2,3,7,8,9,10,11,12,14,17,18]
    String input24 = "2\n55000000\n-100\n"; // [0,1,2,3,7,8,9,10,11,12,16,17,18]
    String input25 = "2\nabc\n"; // [0,1,2,3,7,4]


    public void testing(String input, int expectedTax){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        TaxCalculator taxCalculator = new TaxCalculator();
        int netTax = taxCalculator.init().intValue();
        Assert.assertEquals(expectedTax,netTax);
    }

    @Test
    public void testCase1(){
        testing(input1, -1);
    }
    @Test
    public void testCase2(){
        testing(input2, -1);
    }
    @Test
    public void testCase3(){
        testing(input3, -1);
    }
    @Test
    public void testCase4(){
        testing(input4, -1);
    }
    @Test
    public void testCase5(){
        testing(input5, 115000);
    }
    @Test
    public void testCase6(){
        testing(input6, -1);
    }
    @Test
    public void testCase7(){
        testing(input7, -1);
    }
    @Test
    public void testCase8(){
        testing(input8, -1);
    }
    @Test
    public void testCase9(){
        testing(input9, -1);
    }
    @Test
    public void testCase10(){
        testing(input10, -1);
    }
    @Test
    public void testCase11(){
        testing(input11, 1691250);
    }
    @Test
    public void testCase12(){
        testing(input12, -1);
    }
    @Test
    public void testCase13(){
        testing(input13, -1);
    }
    @Test
    public void testCase14(){
        testing(input14, 24300375);
    }
    @Test
    public void testCase15(){
        testing(input15, 0);
    }
    @Test
    public void testCase16(){
        testing(input16, 3917512);
    }
    @Test
    public void testCase17(){
        testing(input17, 28522);
    }
    @Test
    public void testCase18(){
        testing(input18, -1);
    }
    @Test
    public void testCase19(){
        testing(input19, 22339950);
    }
    @Test
    public void testCase20(){
        testing(input20, 0);
    }
    @Test
    public void testCase21(){
        testing(input21, 22510);
    }
    @Test
    public void testCase22(){
        testing(input22, -1);
    }
    @Test
    public void testCase23(){
        testing(input23, 3838159);
    }
    @Test
    public void testCase24(){
        testing(input24, 22245416);
    }
    @Test
    public void testCase25(){
        testing(input25, -1);
    }

}