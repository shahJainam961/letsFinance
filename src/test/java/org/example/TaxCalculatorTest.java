package org.example;

import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.util.Optional;

public class TaxCalculatorTest{

    // [0,1,2,37,38,39,40,41,42,43,44,45,46,48,49,50,51,52,53,54,56,57,58,52,59,60,61,65,66,67,68]
    String input1 = "1\n1\n1\n8000000\n10000\n25000\n1000\n2000\n6788\n";

    // [0,1,2,37,38,39,40,41,42,43,44,45,46,48,49,50,51,52,53,55,56,57,58,52,59,60,61,65,66,67,68]
    String input2 = "1\n1\n1\n8000000\n10000\n25000\n1000\n2000\n6788\n";

    //  [0,1,2,37,38,39,40,41,42,43,44,45,46,48,49,50,51,52,53,55,56,57,58,52,53,54,56,57,58,52,59,60,64,65,66,67,68]
    String input3 = "1\n1\n1\n80000000\n10000\n25000\n1000\n2000\n6788\n";

    // [0,1,2,37,38,39,40,41,42,43,44,45,46,48,49,50,51,52,53,55,56,57,58,52,53,54,56,57,58,52,59,60,61,65,66,67,68]
    String input4 = "1\n1\n1\n8000000\n10000\n25000\n1000\n2000\n6788\n";

    // [0,1,2,37,38,39,40,41,42,43,44,45,46,48,49,50,51,52,53,55,56,57,58,52,53,54,56,57,58,52,59,60,62,65,66,67,68]
    String input5 = "1\n1\n1\n15000000\n10000\n25000\n1000\n2000\n6788\n";

    // [0,1,2,37,38,39,40,41,42,43,44,45,46,48,49,50,51,52,53,55,56,57,58,52,53,54,56,57,58,52,59,60,63,65,66,67,68]
    String input6 = "1\n1\n1\n40000000\n10000\n25000\n1000\n2000\n6788\n";

    // [0,1,2,37,38,39,40,41,43,44,45,47,48,49,50,51,52,53,55,56,57,58,52,53,54,56,57,58,52,59,60,64,65,66,67,68]
    String input7 = "1\n2\n80000000\n10000";

    // [0,1,2,37,38,39,40,41,42,43,44,45,46,48,49,50,51,52,53,54,56,57,58,52,53,55,56,57,58,52,59,60,64,65,66,67,68]
    String input8 = "1\n1\n1\n70000000\n10000\n25000\n1000\n2000\n6788\n";

    // [0,1,2,37,38,39,40,41,42,41,42,43,44,45,46,48,49,50,51,52,53,55,56,57,58,52,53,54,56,57,58,52,59,60,64,65,66,67,68]
    String input9 = "1\n1\n-1\n1\n70000000\n10000\n25000\n1000\n2000\n6788\n";



    // Following test-paths are not possible practically based on business logic, although they are possible theoretically
    // [0,1,2,37,38,39,40,41,42,43,44,45,46,48,49,50,51,52,53,54,56,57,58,52,53,54,56,57,58,52,59,60,64,65,66,67,68]
    // [0,1,2,37,38,39,40,41,42,43,44,45,46,48,49,50,51,52,59,60,64,65,66,67,68]
    // [0,1,2,37,38,39,40,41,42,43,44,45,46,48,49,50,51,52,59,60,64,65,66,67,68]
    // [0,1,2,37,38,39,40,41,42,43,44,45,46,48,49,50,51,52,59,60,63,65,66,67,68]
    // [0,1,2,37,38,39,40,41,42,43,44,45,46,48,49,50,51,52,59,60,62,65,66,67,68]
    // [0,1,2,37,38,39,40,41,42,43,44,45,46,48,49,50,51,52,59,60,61,65,66,67,68]



    // [0,1,2,1,2,3,4,5,6,7,8,9,10,12,13,15,16,17,18]
    String input10 = "-1\n3\n80000000\n200000\n300000\n1\n";

    // [0,1,2,1,2,3,4,5,6,7,8,9,11,12,13,15,16,17,18]
    String input11 = "-1\n3\n80000000\n200000\n300000\n0\n";

    // [0,1,2,3,4,5,6,7,8,9,10,12,13,15,16,17,18]
    String input12 = "3\n80000000\n200000\n300000\n1\n";

    // [0,1,2,3,4,5,6,7,8,9,8,9,10,12,13,15,16,17,18]
    String input13 = "3\n80000000\n200000\n300000\n-1\n1\n";

    // [0,1,2,3,4,5,6,7,8,9,11,12,13,15,16,17,18]
    String input14 = "3\n80000000\n200000\n300000\n0\n";

    // [0,1,2,1,2,3,4,5,6,7,8,9,11,12,14,15,16,17,18]
    String input15 = "-1\n3\n150000000\n200000\n300000\n1\n";
    // [0,1,2,1,2,3,4,5,6,7,8,9,10,12,14,15,16,17,18]

    // [0,1,2,19,20,21,22,23,24,25,26,30,31,33,34,35,36]
    String input16 = "2\n70000000\n200000\n300000\n1\n";

    // [0,1,2,19,20,21,22,23,24,25,27,30,31,33,34,35,36]
    String input17 = "2\n70000000\n200000\n300000\n2\n";

    // [0,1,2,19,20,21,22,23,24,25,24,25,26,30,31,33,34,35,36]
    String input18 = "2\n70000000\n200000\n300000\n-1\n1\n";

    // [0,1,2,19,20,21,22,23,24,25,28,30,31,33,34,35,36]
    String input19 = "2\n70000000\n200000\n300000\n3\n";

    // [0,1,2,19,20,21,22,23,24,25,24,25,26,30,32,33,34,35,36]
    String input20 = "2\n150000000\n200000\n300000\n-1\n1\n";

    // [0,1,2,19,20,21,22,23,24,25,29,30,31,33,34,35,36]
    String input21 = "2\n70000000\n200000\n300000\n4\n";

    // [0,1,2,19,20,21,22,23,24,25,26,30,32,33,34,35,36]
    String input22 = "2\n250000000\n200000\n300000\n1\n";

    // [0,1,2,19,20,21,22,23,24,25,27,30,32,33,34,35,36]
    String input23 = "2\n250000000\n200000\n300000\n2\n";

    // [0,1,2,19,20,21,22,23,24,25,28,30,32,33,34,35,36]
    String input24 = "2\n250000000\n200000\n300000\n3\n";

    // [0,1,2,19,20,21,22,23,24,25,29,30,32,33,34,35,36]
    String input25 = "2\n250000000\n200000\n300000\n4\n";



    public void testing(String input, int expectedTax){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        TaxCalculator taxCalculator = new TaxCalculator();
        int netTax = taxCalculator.init().intValue();
        Assert.assertEquals(expectedTax,netTax);
    }

    @Test
    public void testCase1(){
        testing(input1, 2506932);
    }
    @Test
    public void testCase2(){
        testing(input2, 2506932);
    }
    @Test
    public void testCase3(){
        testing(input3, 33556679);
    }
    @Test
    public void testCase4(){
        testing(input4, 2506932);
    }
    @Test
    public void testCase5(){
        testing(input5, 5115885);
    }
    @Test
    public void testCase6(){
        testing(input6, 15220792);
    }
    @Test
    public void testCase7(){
        testing(input7, 33465645);
    }
    @Test
    public void testCase8(){
        testing(input8, 29326679);
    }
    @Test
    public void testCase9(){
        testing(input9, 29326679);
    }
    @Test
    public void testCase10(){
        testing(input10, 42135000);
    }
    @Test
    public void testCase11(){
        testing(input11, 33708000);
    }
    @Test
    public void testCase12(){
        testing(input12, 42135000);
    }
    @Test
    public void testCase13(){
        testing(input13, 42135000);
    }
    @Test
    public void testCase14(){
        testing(input14, 33708000);
    }
    @Test
    public void testCase15(){
        testing(input15, 81477500);
    }
    @Test
    public void testCase16(){
        testing(input16, 19286250);
    }
    @Test
    public void testCase17(){
        testing(input17, 16971900);
    }
    @Test
    public void testCase18(){
        testing(input18, 19286250);
    }
    @Test
    public void testCase19(){
        testing(input19, 11571750);
    }
    @Test
    public void testCase20(){
        testing(input20, 43355000);
    }
    @Test
    public void testCase21(){
        testing(input21, 23143500);
    }
    @Test
    public void testCase22(){
        testing(input22, 72355000);
    }
    @Test
    public void testCase23(){
        testing(input23, 63672400);
    }
    @Test
    public void testCase24(){
        testing(input24, 43413000);
    }
    @Test
    public void testCase25(){
        testing(input25, 86826000);
    }

}