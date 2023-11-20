package org.example;

import java.util.Scanner;

public class PPFCalculator {
    private Double yearlyInvestmentAmount;
    private Double timePeriodInYears;
    private Double rateOfInterestInPercentage;

    public Double getYearlyInvestmentAmount() {
        return yearlyInvestmentAmount;
    }

    public void setYearlyInvestmentAmount(Double yearlyInvestmentAmount) {
        this.yearlyInvestmentAmount = yearlyInvestmentAmount;
    }

    public Double getTimePeriodInYears() {
        return timePeriodInYears;
    }

    public void setTimePeriodInYears(Double timePeriodInYears) {
        this.timePeriodInYears = timePeriodInYears;
    }

    public Double getRateOfInterestInPercentage() {
        return rateOfInterestInPercentage;
    }

    public void setRateOfInterestInPercentage(Double rateOfInterestInPercentage) {
        this.rateOfInterestInPercentage = rateOfInterestInPercentage;
    }

    PPFCalculator(){
        rateOfInterestInPercentage = 7.1;
    }

    public Long init(){
        Double yi, tp;
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Enter your yearly investment amount : ");
            yi = scanner.nextDouble();
            if(yi>=0){
                break;
            }
            System.out.println("Please enter positive investment amount : ");
        }
        setYearlyInvestmentAmount(yi);

        while(true){
            System.out.print("Enter time period for which you want to invest : ");
            tp = scanner.nextDouble();
            if(tp>=0){
                break;
            }
            System.out.println("Please enter valid return rate");
        }
        setTimePeriodInYears(tp);

        Long totalValue = calculateReturn();
        System.out.println("Your total value will be : " + totalValue);
        return totalValue;
    }

    public Long calculateReturn(){
            Double amount = getYearlyInvestmentAmount() *
                    ((Math.pow((1 + 1.0*getRateOfInterestInPercentage()/100), getTimePeriodInYears())-1)/(1.0*getRateOfInterestInPercentage()/100));
        Long totalValue = amount.longValue();
        return totalValue;
    }
}
