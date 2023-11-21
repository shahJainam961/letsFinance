package org.example;

import java.util.Scanner;

public class NPSCalculator {

    private Double totalInvestment;
    private Double expectedReturnRatePerAnnum;
    private Double timePeriod;

    public Double getExpectedReturnRatePerAnnum() {
        return expectedReturnRatePerAnnum;
    }

    public void setExpectedReturnRatePerAnnum(Double expectedReturnRatePerAnnum) {
        this.expectedReturnRatePerAnnum = expectedReturnRatePerAnnum;
    }
    public Double getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(Double totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public Double getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(Double timePeriod) {
        this.timePeriod = timePeriod;
    }

    public NPSCalculator(){
    }

    public Long init(){
        try
        {
            Scanner scanner = new Scanner(System.in);
            Double val, ipm;

            while (true) {
                System.out.print("Enter your investment per month : ");
                ipm = scanner.nextDouble();
                if (ipm > 0)
                    break;
                System.out.println("Please Enter positive amount value :: ");
            }

            while (true) {
                System.out.print("Enter expected return rate in percentage : ");
                val = scanner.nextDouble();
                if (val >= 0 && val <= 100) {
                    break;
                }
                System.out.println("Please Enter Non-negative expected rate : ");
            }
            setExpectedReturnRatePerAnnum(val);

            while (true) {
                System.out.print("Enter your age : ");
                val = scanner.nextDouble();
                if (val > 0 && val <= 60) {
                    break;
                }
                System.out.println("Please Enter positive age : ");
            }
            setTimePeriod(60 - val);
            setTotalInvestment(ipm * (60 - val) * 12);

            Long totalValue = calculateReturn();
            System.out.println("Your total value will be : " + totalValue);
            return totalValue;
        }catch (Exception e){
            return -1L;
        }
    }
    
    private Long calculateReturn(){
        Double amnt = totalInvestment * Math.pow(1 + (1.0* expectedReturnRatePerAnnum /1200), 12*timePeriod);
        return amnt.longValue();
    }

    public static void main(String a[]){
        NPSCalculator npsCalculator = new NPSCalculator();
        System.out.println(npsCalculator.init());;
    }
}


