package org.example;

import java.util.Scanner;

public class GratuityCalculator {
    private Double monthlySalary;
    private Double yearsOfService;

    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Double getYearsOfService() {
        return yearsOfService;
    }

    public void setYearsOfService(Double yearsOfService) {
        this.yearsOfService = yearsOfService;
    }

    public Long init(){
        try{
            Double ms, yos;
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter your monthly salary amount : ");
                ms = scanner.nextDouble();
                if (ms >= 0) {
                    break;
                }
                System.out.println("Please enter positive monthly salary : ");
            }
            setMonthlySalary(ms);

            while (true) {
                System.out.print("Enter years of service : ");
                yos = scanner.nextDouble();
                if (yos >= 0) {
                    break;
                }
                System.out.println("Please enter valid year of service");
            }
            setYearsOfService(yos);

            Long totalValue = calculateReturn();
            System.out.println("You are eligible for " + totalValue + " gratuity");
            return totalValue;
        } catch (Exception e){
            return -1L;
        }
    }

    public Long calculateReturn(){
        Double amnt = getYearsOfService() * getMonthlySalary() * 15 / 26;
        return Math.min(1000000, amnt.longValue());
    }

}
