package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer id = -1;
        while(true){
            System.out.println("Which calculator you want to try?");
            System.out.println("1. EMI\n2. Gratuity\n3. Lumpsum\n4. NPS\n5. PPF \n6. SIP\n7. SWP\n8. Tax\nPress any other key to exit\n");
            try{
                id = scanner.nextInt();
            }
            catch(InputMismatchException exception){
                return;
            }
            if(id>=0 && id<9){
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
            NPSCalculator calc = new NPSCalculator();
            calc.init();
        }
        else if(id==5){
            PPFCalculator calc = new PPFCalculator();
            calc.init();
        }
        else if(id==6){
            SIPCalculator calc = new SIPCalculator();
            calc.init();
        }
        else if(id==7){
            SWPCalculator calc = new SWPCalculator();
            calc.init();
        }
        else if(id==8){
            TaxCalculator calc = new TaxCalculator();
            calc.init();
        }
    }
}