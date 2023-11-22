package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            Scanner scanner = new Scanner(System.in);
            Integer id = -1;
            while(true){
                System.out.println("Which calculator you want to try?");
                System.out.println("1. EMI\n2. Gratuity\n3. Lumpsum\n4. PPF \n5. SIP\n6. SWP\n7. Tax\nPress any other key to exit\n");
                try{
                    id = scanner.nextInt();
                }
                catch(InputMismatchException exception){
                    return;
                }
                if(id>=0 && id<8){
                    break;
                }
                else{
                    System.out.println("Please enter valid option!");
                }
            }

            if(id==0){
                System.out.println("Thanks!!");
            }
            else if(id==1){
                EMICalculator calc = new EMICalculator();
                calc.init();
            }
            else if(id==2){
                GratuityCalculator calc = new GratuityCalculator();
                calc.init();
            }
            else if(id==3){
                LumpsumCalculator calc = new LumpsumCalculator();
                calc.init();
            }
            else if(id==4){
                PPFCalculator calc = new PPFCalculator();
                calc.init();
            }
            else if(id==5){
                SIPCalculator calc = new SIPCalculator();
                calc.init();
            }
            else if(id==6){
                SWPCalculator calc = new SWPCalculator();
                calc.init();
            }
            else if(id==7){
                TaxCalculator calc = new TaxCalculator();
                calc.init();
            }
        } catch (Exception exception){
            return;
        }
    }
}