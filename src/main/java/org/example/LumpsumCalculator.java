package org.example;

import java.util.Scanner;

public class LumpsumCalculator {

    private Double principleAmount;
    private Double interestRate;
    private Double timePeriod;

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
    public Double getPrincipleAmount() {
        return principleAmount;
    }

    public void setPrincipleAmount(Double principleAmount) {
        this.principleAmount = principleAmount;
    }

    public Double getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(Double timePeriod) {
        this.timePeriod = timePeriod;
    }

    public LumpsumCalculator(){
    }

    public Long init(){

        Scanner scanner = new Scanner(System.in);
        Double val;
        
        while(true) {
            System.out.print("Enter your total investment ( Principle Amount ) : ");
            val = scanner.nextDouble();
            if(val>0)
                break;
            System.out.println("Please Enter positive amount value :: ");
        }
        setPrincipleAmount(val);

        while(true) {
            System.out.print("Enter rate of interest : ");
            val = scanner.nextDouble();
            if(val>=0 && val<=100){
                break;
            }
            System.out.println("Please Enter Non-negative interest rate in range 0 to 100 : ");
        }
        setInterestRate(val);

        while(true) {
            System.out.print("Enter Time period of investment terms of no. of years : ");
            val = scanner.nextDouble();
            if(val>0){
                break;
            }
            System.out.println("Please Enter positive no. of years in  : ");
        }
        setTimePeriod(val);
        
        Long totalAmnt = calculateReturn();
        System.out.println("Your total gain will be : " + totalAmnt);
        return totalAmnt;
    }
    
    private Long calculateReturn(){
        Double amnt = principleAmount * Math.pow(1 + (1.0*interestRate/100),timePeriod);
        return amnt.longValue();
    }
}


