package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SWPCalculator {

    Double totalInvestment;

    public Double getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(Double totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public Double getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(Double withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public Double getExpectedReturnRate() {
        return expectedReturnRate;
    }

    public void setExpectedReturnRate(Double expectedReturnRate) {
        this.expectedReturnRate = expectedReturnRate;
    }

    public Double getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(Double timePeriod) {
        this.timePeriod = timePeriod;
    }

    Double withdrawalAmount;
    Double expectedReturnRate;
    Double timePeriod;

    public SWPCalculator(){
    }

    public Long init(){
        try{
            Scanner scanner = new Scanner(System.in);
            Double val;

            while(true) {
                System.out.print("Enter total amount of investment : ");
                val = scanner.nextDouble();

                if(val>=0)
                    break;
                System.out.println("Please enter correct non-negative amount of investment ::");
            }
            setTotalInvestment(val);

            while(true) {
                System.out.print("Enter amount of withdrawal per month : ");
                val = scanner.nextDouble();

                if(val>=0)
                    break;
                System.out.println("Please enter correct non-negative amount of withdrawal ::");
            }
            setWithdrawalAmount(val);

            System.out.print("Enter amount of expected annual return rate : ");
            val = scanner.nextDouble();
            setExpectedReturnRate(val);

            while(true) {
                System.out.print("Enter amount of time period as no. of years : ");
                val = scanner.nextDouble();

                if(val>=0)
                    break;
                System.out.println("Please enter correct non-negative years value ::");
            }
            setTimePeriod(val);

            Double returnAmnt = calculateReturn();
            System.out.println("Your interest gain will be : "+ Math.round(returnAmnt));
            return returnAmnt.longValue();
        }
        catch(Exception exception){
            return -1L;
        }
    }

    private Double calculateReturn(){

        double deduct = getWithdrawalAmount().doubleValue();
        double val = getTotalInvestment().doubleValue();
        int n = getTimePeriod().intValue()*12;
        double rate = getExpectedReturnRate().doubleValue() / 12;
        double gain = 0;

        for(int i=0;i<n && val>0;i++){

            val -= deduct;

            if(val <=0 )
                break;

            double tmp = val * (rate/100);
            gain += tmp;
            val += tmp;
        }

        return gain;
    }
}
