package org.example;

import java.util.Scanner;

public class SIPCalculator {
    Double monthlyInvestment;
    Double expectedReturnRateInPercentage;
    Double timePeriodInYear;

    public Double getMonthlyInvestment() {
        return monthlyInvestment;
    }

    public void setMonthlyInvestment(Double monthlyInvestment) {
        this.monthlyInvestment = monthlyInvestment;
    }

    public Double getExpectedReturnRateInPercentage() {
        return expectedReturnRateInPercentage;
    }

    public void setExpectedReturnRateInPercentage(Double expectedReturnRateInPercentage) {
        this.expectedReturnRateInPercentage = expectedReturnRateInPercentage;
    }

    public Double getTimePeriodInYear() {
        return timePeriodInYear;
    }

    public void setTimePeriodInYear(Double timePeriodInYear) {
        this.timePeriodInYear = timePeriodInYear;
    }

    public Long init(){
        try{
            Double val;
            Scanner scanner = new Scanner(System.in);

            while(true){
                System.out.print("Enter your monthly investment amount : ");
                val = scanner.nextDouble();
                if(val>=0){
                    break;
                }
                System.out.println("Please enter positive investment amount : ");
            }
            setMonthlyInvestment(val);

            while(true){
                System.out.print("Enter Expected Return Rate in percentage (per annum) : ");
                val = scanner.nextDouble();
                if(val>=0){
                    break;
                }
                System.out.println("Please enter valid return rate");
            }
            setExpectedReturnRateInPercentage(val*1.0/12);

            while(true){
                System.out.print("Enter time period for which you want to invest : ");
                val = scanner.nextDouble();
                if(val>=0){
                    break;
                }
                System.out.println("Please enter valid return rate");
            }
            setTimePeriodInYear(val);

            Long totalValue = calculateReturn();
            System.out.println("Your total value will be : " + totalValue);
            return totalValue;
        }
        catch(Exception e){
            return -1L;
        }
    }

    public Long calculateReturn(){
        Double amount = getMonthlyInvestment() *
                 ((Math.pow((1 + 1.0*getExpectedReturnRateInPercentage()/100), getTimePeriodInYear()*12)-1)/(1.0*getExpectedReturnRateInPercentage()/100)) * (1 + (1.0*getExpectedReturnRateInPercentage()/100));
        Long totalValue = amount.longValue();
        return totalValue;
    }
}