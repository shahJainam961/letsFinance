package org.example;

import java.util.*;

public class TaxCalculator {
    private HashMap<Integer,HashMap<Integer,ArrayList<ArrayList<Integer>>>> slab;

    private Integer type;

    private Integer regimeId;

    private Scanner scanner;

    private Integer ageGroupId;

    private Integer deductedAmount;

    private Integer taxableAmount;

    private Integer taxAmount;

    private Integer surCharge;

    private Integer netTax;

    private Integer healthAndEduCess;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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

    public Integer getNetTax() {
        return netTax;
    }

    public void setNetTax(Integer netTax) {
        this.netTax = netTax;
    }

    public Integer getHealthAndEduCess() {
        return healthAndEduCess;
    }

    public void setHealthAndEduCess(Integer healthAndEduCess) {
        this.healthAndEduCess = healthAndEduCess;
    }

    private void initSlab(){

        // 1--> old regime
        // 2--> new regime

        slab = new HashMap<Integer,HashMap<Integer,ArrayList<ArrayList<Integer>>>>();

        slab.put(1,new HashMap<>());
        slab.put(2,new HashMap<>());

        // < 60
        ArrayList<ArrayList<Integer>> less60 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> a60l80 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> a80 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> all = new ArrayList<>();



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
            Integer type;

            while (true) {
                System.out.println("Select type:\n1.Individual\n2.Domestic Company\n3.Foreign Company\n0.To Exit");
                type = scanner.nextInt();
                if(type==0){
                    return -1;
                }
                if (type != 1 && type != 2 && type!=3) {
                    System.out.println("Select valid schemeId\n");
                    continue;
                }
                break;
            }

            // For Foreign Company
            if(type==3){
                Integer income;
                System.out.println("Enter your total turnOver:");
                income = scanner.nextInt();

                Integer deductedAmount = calcDeductionForForeignCompany(income);
                setDeductedAmount(deductedAmount);
                Integer taxableAmount = income - deductedAmount;
                setTaxableAmount(taxableAmount);
                Integer taxAmount = calcTaxForForeignCompany(taxableAmount);
                setTaxAmount(taxAmount);
                Integer surCharge = calcSurchargeForForeignCompany(taxAmount, taxableAmount);
                setSurCharge(surCharge);
                Integer healthEduCess = calcHealthAndEducationCess(taxAmount);
                Integer netTax = taxAmount + surCharge + healthEduCess;
                setNetTax(netTax);

                System.out.println("You have to pay " + getNetTax() + " as tax!!");
                return netTax;
            }
            // For Domestic Company
            else if(type==2){
                Integer income;
                System.out.println("Enter your total turnOver:");
                income = scanner.nextInt();

                Integer deductedAmount = calcDeductionForDomesticCompany(income);
                setDeductedAmount(deductedAmount);
                Integer taxableAmount = income - deductedAmount;
                setTaxableAmount(taxableAmount);
                Integer taxAmount = calcTaxForDomesticCompany(taxableAmount);
                setTaxAmount(taxAmount);
                Integer surCharge = calcSurchargeForDomesticCompany(taxAmount, taxableAmount);
                setSurCharge(surCharge);
                Integer healthEduCess = calcHealthAndEducationCess(taxAmount);
                Integer netTax = taxAmount + surCharge + healthEduCess;
                setNetTax(netTax);

                System.out.println("You have to pay " + getNetTax() + " as tax!!");
                return netTax;
            }
            // For an Individual
            else{
                Integer schemeId;

                while (true) {
                    System.out.println("Select you regime:\n1.Old Tax Regime\n2.New tax Regime\n");
                    schemeId = scanner.nextInt();
                    if (schemeId != 1 && schemeId != 2) {
                        System.out.println("Select valid schemeId\n");
                        continue;
                    }
                    break;
                }
                setRegimeId(schemeId);

                Integer ageGroup = 0;

                while (schemeId == 1) {
                    System.out.println("Select your age group:\n1. Below 60 year\n2. 60 or above 60 and below 80\n3. 80 or above 80\n");
                    ageGroup = scanner.nextInt();
                    if (ageGroup != 1 && ageGroup != 2 && ageGroup != 3) {
                        System.out.println("Please select correct age group\n");
                        continue;
                    }
                    break;
                }
                setAgeGroupId(ageGroup);

                Integer income;
                System.out.println("Enter your income:");
                income = scanner.nextInt();


                Integer deductedAmount = calcDeductionForIndividual(income, getRegimeId());
                setDeductedAmount(deductedAmount);
                Integer taxableAmount = income - deductedAmount;
                setTaxableAmount(taxableAmount);
                Integer taxAmount = calcTaxForIndividual(taxableAmount, getRegimeId(), getAgeGroupId());
                setTaxAmount(taxAmount);
                Integer surCharge = calcSurchargeForIndividual(taxAmount, taxableAmount);
                setSurCharge(surCharge);
                Integer healthEduCess = calcHealthAndEducationCess(taxAmount);
                Integer netTax = taxAmount + surCharge + healthEduCess;
                setNetTax(netTax);

                System.out.println("You have to pay " + getNetTax() + " as tax!!");
                return netTax;
            }
        }
        catch(InputMismatchException exception){
            return -1;
        }
    }

    private Integer calcSurchargeForDomesticCompany(Integer taxAmount, Integer taxableAmount) {
        Double surCharge = 0.0;
        if(taxableAmount>10000000 && taxableAmount<=100000000){
            surCharge = 0.07*taxAmount;
        }
        else if(taxableAmount>100000000){
            surCharge = 0.12*taxAmount;
        }
        Integer var = surCharge.intValue();
        return var;
    }

    private Integer calcTaxForDomesticCompany(Integer taxableAmount) {
        Integer  id, taxAmount;

        while (true) {
            System.out.println("Please choose which section you have opted:\n1. 115BA\n2. 115BAA\n3. 115BAB\n 4.None\n");
            id = scanner.nextInt();
            if (id != 1 && id != 2 && id != 3 && id != 4) {
                System.out.println("Please select correct section\n");
                continue;
            }
            break;
        }
        if(id==1){
            taxAmount = 25*(taxableAmount/100);
        }
        else if(id==2){
            taxAmount = 22*(taxableAmount/100);
        }
        else if(id==3){
            taxAmount = 15*taxableAmount/100;
        }
        else{
            taxAmount = 30*taxableAmount/100;
        }
        return taxAmount;
    }

    private Integer calcDeductionForDomesticCompany(Integer income) {
        Integer deduction = 0;

        Integer ngoDonation, goDonation, totalDonation;
        Integer netQualifyingLimit = 10*income/100;
        System.out.println("Enter your donation to any approved funds, trust, charitable institution:");
        ngoDonation = scanner.nextInt();
        System.out.println("Enter your donation to any government funds:");
        goDonation = scanner.nextInt();
        totalDonation = Math.min(ngoDonation + goDonation, netQualifyingLimit);
        deduction += totalDonation;
        return deduction;
    }

    private Integer calcSurchargeForForeignCompany(Integer taxAmount, Integer taxableAmount) {
        Double surCharge = 0.0;
        if(taxableAmount>10000000 && taxableAmount<=100000000){
            surCharge = 0.02*taxAmount;
        }
        else if(taxableAmount>100000000){
            surCharge = 0.05*taxAmount;
        }
        Integer var = surCharge.intValue();
        return var;
    }

    private Integer calcTaxForForeignCompany(Integer taxableAmount) {
        Integer  id, taxAmount = 0;

        while (true) {
            System.out.println("Do you fall in the below category.\nRoyalty from Government or an Indian concern in pursuance of an agreement made with the Indian concern after 31st March 1961, but before 1st April 1976, or fees for rendering technical services in pursuance of an agreement made after 29th February 1964 but before 1st April 1976 and where such agreement has, in either case, been approved by the Central Government:\n1. Yes\n0. No\n");
            id = scanner.nextInt();
            if (id != 1 && id != 0) {
                System.out.println("Please select option\n");
                continue;
            }
            break;
        }
        if(id==1){
            taxAmount = 50*(taxableAmount/100);
        } else if (id==0) {
            taxAmount = 40*(taxableAmount/100);
        }
        return taxAmount;
    }

    private Integer calcDeductionForForeignCompany(Integer income) {
        Integer deduction = 0;

        Integer ngoDonation, goDonation, totalDonation;
        Integer netQualifyingLimit = 10*income/100;
        System.out.println("Enter your donation to any approved funds, trust, charitable institution:");
        ngoDonation = scanner.nextInt();
        System.out.println("Enter your donation to any government funds:");
        goDonation = scanner.nextInt();
        totalDonation = Math.min(ngoDonation + goDonation, netQualifyingLimit);
        deduction += totalDonation;
        return deduction;
    }

    Integer calcTaxForIndividual(Integer taxableAmount, Integer regimeId, Integer ageGroupId){

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

    Integer calcDeductionForIndividual(Integer income, Integer regimeId) throws InputMismatchException{

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

    Integer calcSurchargeForIndividual(Integer taxAmount, Integer taxableAmount){
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

    Integer calcHealthAndEducationCess(Integer taxAmount){
        Double charge = 0.0;
        charge = 0.04*taxAmount;
        Integer var = charge.intValue();
        return var;
    }

}