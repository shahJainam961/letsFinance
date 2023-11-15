package org.example;

import java.util.*;

public class TaxCalculator {
    private HashMap<Integer,HashMap<Integer,ArrayList<ArrayList<Integer>>>> slab;
    private Integer regimeId;
    private Scanner scanner;

    public Integer getRegimeId() {
        return regimeId;
    }

    public void setRegimeId(Integer regimeId) {
        this.regimeId = regimeId;
    }

    public Integer getAgeGroupId() {
        return ageGroupId;
    }

    public void setAgeGroupId(Integer ageGroupId) {
        this.ageGroupId = ageGroupId;
    }

    private Integer ageGroupId;
    private Integer deductedAmount;
    private Integer taxableAmount;
    private Integer taxAmount;
    private Integer surCharge;
    private Integer netTax;


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

    private void initSlab(){

        // 1--> old regime
        // 2--> new regime

        slab = new HashMap<Integer,HashMap<Integer,ArrayList<ArrayList<Integer>>>>();

        slab.put(1,new HashMap<Integer,ArrayList<ArrayList<Integer>>>());
        slab.put(2,new HashMap<Integer,ArrayList<ArrayList<Integer>>>());

        // < 60
        ArrayList<ArrayList<Integer>> less60 = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> a60l80 = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> a80 = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> all = new ArrayList<ArrayList<Integer>>();



        less60.add(new ArrayList<>(Arrays.asList(250000,0)));
        less60.add(new ArrayList<>(Arrays.asList(250000,5)));
        less60.add(new ArrayList<>(Arrays.asList(500000,20)));
        less60.add(new ArrayList<>(Arrays.asList(-1,30)));
        slab.get(1).put(1,less60);

        a60l80.add(new ArrayList<>(Arrays.asList(300000,0)));
        a60l80.add(new ArrayList<>(Arrays.asList(200000,5)));
        a60l80.add(new ArrayList<>(Arrays.asList(500000,20)));
        a60l80.add(new ArrayList<>(Arrays.asList(-1,30)));
        slab.get(1).put(2,a60l80);

        a80.add(new ArrayList<>(Arrays.asList(500000,0)));
        a80.add(new ArrayList<>(Arrays.asList(500000,20)));
        a80.add(new ArrayList<>(Arrays.asList(-1,30)));
        slab.get(1).put(3,a80);

        all.add(new ArrayList<>(Arrays.asList(250000,0)));
        all.add(new ArrayList<>(Arrays.asList(250000,5)));
        all.add(new ArrayList<>(Arrays.asList(250000,10)));
        all.add(new ArrayList<>(Arrays.asList(250000,15)));
        all.add(new ArrayList<>(Arrays.asList(250000,20)));
        all.add(new ArrayList<>(Arrays.asList(250000,25)));
        all.add(new ArrayList<>(Arrays.asList(-1,30)));
        slab.get(2).put(0,all);

    }

    public TaxCalculator(){
        initSlab();
    }

    public Integer init(){
        try{
            scanner = new Scanner(System.in);
            Integer schemeId;

            while(true) {
                System.out.println("Select you regime:\n1.Old Tax Regime\n2.New tax Regime\n\n0.To exit");
                schemeId = scanner.nextInt();
                if (schemeId == 0)
                    return -1;
                if (schemeId != 1 && schemeId != 2) {
                    System.out.println("Select valid schemeId\n");
                    continue;
                }
                break;
            }
            setRegimeId(schemeId);

            Integer ageGroup=0;

            while(schemeId==1){
                System.out.println("Select your age group:\n1. Below 60 year\n2. 60 or above 60 and below 80\n3. 80 or above 80\n");
                ageGroup = scanner.nextInt();
                if(ageGroup!=1 && ageGroup!=2 && ageGroup!=3)
                {
                    System.out.println("Please select correct age group\n");
                    continue;
                }
                break;
            }
            setAgeGroupId(ageGroup);

            Integer income;
            System.out.println("Enter your income:");
            income = scanner.nextInt();


            Integer deductedAmount = calcDeduction(income, getRegimeId());
            setDeductedAmount(deductedAmount);
            Integer taxableAmount = income-deductedAmount;
            setTaxableAmount(taxableAmount);
            Integer taxAmount = calcTax(taxableAmount,getRegimeId(),getAgeGroupId());
            setTaxAmount(taxAmount);
            Integer surCharge= calcSurcharge(taxAmount, taxableAmount);
            setSurCharge(surCharge);

            Integer netTax = taxAmount + surCharge;
            setNetTax(netTax);

            System.out.println("You have to pay " + netTax + " as tax!!");
            return netTax;
        }
        catch(InputMismatchException exception){
            return -1;
        }
    }
    Integer calcTax(Integer taxableAmount, Integer regimeId, Integer ageGroupId){

            Integer taxAmount = 0;

            ArrayList<ArrayList<Integer>> slabs = slab.get(regimeId).get(ageGroupId);

            for(int i=0;i<slabs.size();i++){

                Integer minA=0;
                if(taxableAmount <= 0)
                    break;

                if(i==slabs.size()-1){
                    minA = taxableAmount;
                }else{
                    minA = Math.min(taxableAmount,slabs.get(i).get(0));
                }

                taxAmount += (minA * slabs.get(i).get(1))/100;
                taxableAmount -= minA;
            }

            return taxAmount;
    }
    Integer calcDeduction(Integer income, Integer regimeId) throws InputMismatchException{

        Integer deductionAmount80c = 0;
        Integer deductionAmount80ccd1b = 0;
        Integer deductionAmount80d = 0;

        // for old regime
        if(regimeId==1){
            System.out.println("Enter amount of Life Insurance Premium (0 if none)");
            deductionAmount80c += scanner.nextInt();
            System.out.println("Enter amount of Provident Fund (0 if none)");
            deductionAmount80c += scanner.nextInt();
            System.out.println("Enter value of equity shares that you hold (0 if none)");
            deductionAmount80c += scanner.nextInt();
            System.out.println("Enter amount of tution fees (0 if none)");
            deductionAmount80c += scanner.nextInt();
            deductionAmount80c = Math.min(deductionAmount80c, 1500000);

            System.out.println("Enter Pension Scheme of Central Goverment amount(0 if none)");
            deductionAmount80ccd1b = Math.min(scanner.nextInt(), 50000);

        }
        // for new regime
        else{
            System.out.println("Enter Pension Scheme of Central Goverment amount(0 if none)");
            deductionAmount80ccd1b = Math.min(scanner.nextInt(), 50000);
        }

        return deductionAmount80ccd1b + deductionAmount80c + deductionAmount80d;
    }
    Integer calcSurcharge(Integer taxAmount, Integer taxableAmount){
        Double surCharge = 0.0;
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

        Integer var = surCharge.intValue();
        return var;
    }

}
