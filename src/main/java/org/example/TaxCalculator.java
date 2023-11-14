package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TaxCalculator {
    HashMap<Integer,HashMap<Integer,ArrayList<ArrayList<Integer>>>> slab;
    private byte regimeId;
    private byte ageGroupId;
    private Integer deductedAmount;
    private Integer taxableAmount;
    private Integer taxAmount;
    private Integer surCharge;
    private Integer netTax;

    public Integer getNetTax() {
        return netTax;
    }

    public void setNetTax(Integer netTax) {
        this.netTax = netTax;
    }

    public Integer getDeductedAmount() {
        return deductedAmount;
    }

    public void setDeductedAmount(Integer deductedAmount) {
        this.deductedAmount = deductedAmount;
    }

    public Integer getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(Integer taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public Integer getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Integer taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Integer getSurCharge() {
        return surCharge;
    }

    public void setSurCharge(Integer surCharge) {
        this.surCharge = surCharge;
    }

    public void setRegime(byte id){
        this.regimeId = id;
    }

    public void setAgeGroupId(byte id){
        this.ageGroupId = id;
    }

    private void initSlab(){

        // 1--> old regime
        // 2--> new regime

        slab = new HashMap<Integer,HashMap<Integer,ArrayList<ArrayList<Integer>>>>();

        slab.put(1,new HashMap<Integer,ArrayList<ArrayList<Integer>>>());
        // < 60
        ArrayList<ArrayList<Integer>> less60 = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> a60l80 = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> a80 = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> all = new ArrayList<ArrayList<Integer>>();


        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(250000);
        data.add(0);
        less60.add(data);
        data.clear();

        data.add(250000);
        data.add(5);
        less60.add(data);
        data.clear();

        data.add(500000);
        data.add(20);
        less60.add(data);
        data.clear();

        data.add(-1);
        data.add(30);
        less60.add(data);
        data.clear();
        slab.get(1).put(1,less60);



        data.add(300000);
        data.add(0);
        a60l80.add(data);
        data.clear();

        data.add(200000);
        data.add(5);
        a60l80.add(data);
        data.clear();

        data.add(500000);
        data.add(20);
        a60l80.add(data);
        data.clear();

        data.add(-1);
        data.add(30);
        a60l80.add(data);
        data.clear();
        slab.get(1).put(2,a60l80);

        data.add(500000);
        data.add(0);
        a80.add(data);
        data.clear();

        data.add(500000);
        data.add(20);
        a80.add(data);
        data.clear();

        data.add(-1);
        data.add(30);
        a80.add(data);
        data.clear();
        slab.get(1).put(3,a80);


        data.add(250000);
        data.add(0);
        all.add(data);
        data.clear();

        data.add(250000);
        data.add(5);
        all.add(data);
        data.clear();

        data.add(250000);
        data.add(10);
        all.add(data);
        data.clear();

        data.add(250000);
        data.add(15);
        all.add(data);
        data.clear();

        data.add(250000);
        data.add(20);
        all.add(data);
        data.clear();

        data.add(250000);
        data.add(25);
        all.add(data);
        data.clear();


        data.add(-1);
        data.add(30);
        all.add(data);
        data.clear();

        slab.get(2).put(0,all);


    }

    public TaxCalculator(){
        initSlab();
    }


    public void init(){

        Scanner scanner = new Scanner(System.in);
        byte schemeId;

        while(true) {
            System.out.println("Select you regime:\n1.Old Tax Regime\n2.New tax Regime\n\n0.To exit");
            schemeId = scanner.nextByte();
            if (schemeId == 0)
                return;
            if (schemeId != 1 && schemeId != 2) {
                System.out.println("Select valid schemeId\n");
                continue;
            }
            setRegime(schemeId);
            break;
        }

        byte ageGroup;

        while(schemeId==1){
            System.out.println("Select your age group:\n1. Below 60 year\n2. 60 or above 60 and below 80\n3. 80 or above 80\n");
            ageGroup = scanner.nextByte();
            if(ageGroup!=1 && ageGroup!=2 && ageGroup!=3)
            {
                System.out.println("Please select correct age group\n");
                continue;
            }
            setAgeGroupId(ageGroup);
            break;
        }

        Integer income;
        System.out.println("Enter your income:");
        income = scanner.nextInt();

        Integer deductedAmount = calcDeduction(income);
        setDeductedAmount(deductedAmount);
        Integer taxableAmount = income-deductedAmount;
        setTaxableAmount(taxableAmount);
        Integer taxAmount = calcTax(taxableAmount);
        setTaxAmount(taxAmount);
        Integer surCharge= calcSurcharge(taxAmount, taxableAmount);
        setSurCharge(surCharge);

        Integer netTax = taxAmount + surCharge;
        setNetTax(netTax);

        System.out.println("You have to pay " + netTax + "as tax!!");
    }
    Integer calcTax(Integer taxableAmount){

    }
    Integer calcDeduction(Integer income){
        return 0;
    }

    Integer calcSurcharge(Integer taxAmount, Integer taxableAmount){
        double surCharge = 0;
        if(taxableAmount>5000000 && taxableAmount<=10000000){
            surCharge = 0.1*taxAmount;
        }
        else if(taxableAmount>10000000 && taxableAmount<=20000000){
            surCharge = 0.15*taxAmount;
        }
        else if(taxableAmount>20000000 && taxableAmount<=50000000){
            surCharge = 0.25*taxAmount;
        }
        else if(taxableAmount>50000000){
            surCharge = 0.37*taxAmount;
        }
        new Integer
        return new Integer((int)surCharge);
    }

}
