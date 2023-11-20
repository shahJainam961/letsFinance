package org.example;

import javax.print.attribute.standard.Finishings;
import java.util.Scanner;

public class EMICalculator {
    private Double loanAmount;
    private Double interestRate;
    private Double loanTenure;

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getLoanTenure() {
        return loanTenure;
    }

    public void setLoanTenure(Double loanTenure) {
        this.loanTenure = loanTenure;
    }

    public Long init(){
        Scanner scanner = new Scanner(System.in);
        Double val;

        while(true) {
            System.out.print("Enter your loan amount : ");
            val = scanner.nextDouble();
            if(val>0)
                break;
            System.out.println("Please enter valid loan amount: ");
        }
        setLoanAmount(val);

        while(true) {
            System.out.print("Enter rate of interest : ");
            val = scanner.nextDouble();
            if(val>=0 && val<=100){
                break;
            }
            System.out.println("Please Enter valif rate of interest: ");
        }
        setInterestRate(val);

        while(true) {
            System.out.print("Enter loan tenure in years : ");
            val = scanner.nextDouble();
            if(val>0){
                break;
            }
            System.out.println("Please Enter positive no. of years in  : ");
        }
        setLoanTenure(val);

        Long totalAmnt = calculateReturn();
        System.out.println("Your EMI : " + totalAmnt);
        return totalAmnt;
    }

    public Long calculateReturn(){
        Double amnt = (getLoanAmount() * (getInterestRate()/1200) * (Math.pow(1+(getInterestRate()/1200), getLoanTenure()*12))) / (Math.pow(1+(getInterestRate()/1200), (getLoanTenure()*12))-1);
        return  amnt.longValue();
    }

}

