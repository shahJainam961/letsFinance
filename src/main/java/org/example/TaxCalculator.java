package org.example;

import java.util.*;

public class TaxCalculator {
    private HashMap<Integer,HashMap<Integer,ArrayList<ArrayList<Integer>>>> slab;

    private Long type;

    private Long regimeId;

    private Scanner scanner;

    private Long ageGroupId;

    private Double deductedAmount;

    private Double taxableAmount;

    private Double taxAmount;

    private Double surCharge;

    private Double netTax;

    private Double healthAndEduCess;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getRegimeId() {
        return regimeId;
    }

    public void setRegimeId(Long regimeId) {
        this.regimeId = regimeId;
    }

    public Long getAgeGroupId() {
        return ageGroupId;
    }

    public void setAgeGroupId(Long ageGroupId) {
        this.ageGroupId = ageGroupId;
    }

    public Double getDeductedAmount() {
        return deductedAmount;
    }

    public void setDeductedAmount(Double deductedAmount) {
        this.deductedAmount = deductedAmount;
    }

    public Double getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(Double taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getSurCharge() {
        return surCharge;
    }

    public void setSurCharge(Double surCharge) {
        this.surCharge = surCharge;
    }

    public Double getNetTax() {
        return netTax;
    }

    public void setNetTax(Double netTax) {
        this.netTax = netTax;
    }

    public Double getHealthAndEduCess() {
        return healthAndEduCess;
    }

    public void setHealthAndEduCess(Double healthAndEduCess) {
        this.healthAndEduCess = healthAndEduCess;
    }

    private void initSlab(){

        // 1--> old regime
        // 2--> new regime

        slab = new HashMap<>();

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

    public Long init(){
        try{
            scanner = new Scanner(System.in);
            Integer type;

            while (true) {
                System.out.println("Select type:\n1.Individual\n2.Domestic Company\n3.Foreign Company\n0.To Exit");
                type = scanner.nextInt();
                if(type==0){
                    return -1L;
                }
                if (type != 1 && type != 2 && type!=3) {
                    System.out.println("Select valid schemeId\n");
                    continue;
                }
                break;
            }
            Double income, deductedAmount, taxableAmount, taxAmount, surCharge, healthEduCess;
            // For Foreign Company
            if(type==3){
                System.out.println("Enter your total turnOver:");
                income = scanner.nextDouble();

                deductedAmount = calcDeductionForForeignCompany(income);
                setDeductedAmount(deductedAmount);
                taxableAmount = income - deductedAmount;
                setTaxableAmount(taxableAmount);
                taxAmount = calcTaxForForeignCompany(taxableAmount);
                setTaxAmount(taxAmount);
                surCharge = calcSurchargeForForeignCompany(taxAmount, taxableAmount);
                setSurCharge(surCharge);
                healthEduCess = calcHealthAndEducationCess(taxAmount);
                setHealthAndEduCess(healthEduCess);
                setNetTax(getTaxAmount() + getSurCharge() + getHealthAndEduCess());

                System.out.println("You have to pay " + getNetTax().longValue() + " as tax!!");
                return getNetTax().longValue();
            }
            // For Domestic Company
            else if(type==2){
                System.out.println("Enter your total turnOver:");
                income = scanner.nextDouble();

                deductedAmount = calcDeductionForDomesticCompany(income);
                setDeductedAmount(deductedAmount);
                taxableAmount = income - deductedAmount;
                setTaxableAmount(taxableAmount);
                taxAmount = calcTaxForDomesticCompany(taxableAmount);
                setTaxAmount(taxAmount);
                surCharge = calcSurchargeForDomesticCompany(taxAmount, taxableAmount);
                setSurCharge(surCharge);
                healthEduCess = calcHealthAndEducationCess(taxAmount);
                setHealthAndEduCess(healthEduCess);
                setNetTax(getTaxAmount() + getSurCharge() + getHealthAndEduCess());

                System.out.println("You have to pay " + getNetTax().longValue() + " as tax!!");
                return getNetTax().longValue();
            }
            // For an Individual
            else{
                Long schemeId;

                while (true) {
                    System.out.println("Select you regime:\n1.Old Tax Regime\n2.New tax Regime\n");
                    schemeId = scanner.nextLong();
                    if (schemeId != 1 && schemeId != 2) {
                        System.out.println("Select valid schemeId\n");
                        continue;
                    }
                    break;
                }
                setRegimeId(schemeId);

                Long ageGroup = 0L;

                while (getRegimeId() == 1) {
                    System.out.println("Select your age group:\n1. Below 60 year\n2. 60 or above 60 and below 80\n3. 80 or above 80\n");
                    ageGroup = scanner.nextLong();
                    if (ageGroup != 1 && ageGroup != 2 && ageGroup != 3) {
                        System.out.println("Please select correct age group\n");
                        continue;
                    }
                    break;
                }
                setAgeGroupId(ageGroup);

                System.out.println("Enter your income:");
                income = scanner.nextDouble();


                deductedAmount = calcDeductionForIndividual(income, getRegimeId());
                setDeductedAmount(deductedAmount);
                taxableAmount = income - deductedAmount;
                setTaxableAmount(taxableAmount);
                taxAmount = calcTaxForIndividual(getTaxableAmount(), getRegimeId(), getAgeGroupId());
                setTaxAmount(taxAmount);
                surCharge = calcSurchargeForIndividual(getTaxAmount(), getTaxableAmount());
                setSurCharge(surCharge);
                healthEduCess = calcHealthAndEducationCess(getTaxAmount());
                setHealthAndEduCess(healthEduCess);
                setNetTax(getTaxAmount() + getSurCharge() + getHealthAndEduCess());

                System.out.println("You have to pay " + getNetTax().longValue() + " as tax!!");
                return netTax.longValue();
            }
        }
        catch(InputMismatchException exception){
            return -1L;
        }
    }

    private Double calcSurchargeForDomesticCompany(Double taxAmount, Double taxableAmount) {
        Double surCharge = 0.0;
        if(taxableAmount>10000000 && taxableAmount<=100000000){
            surCharge = 0.07*taxAmount;
        }
        else if(taxableAmount>100000000){
            surCharge = 0.12*taxAmount;
        }
        return surCharge;
    }

    private Double calcTaxForDomesticCompany(Double taxableAmount) {
        Long  id;
        Double taxAmount;

        while (true) {
            System.out.println("Please choose which section you have opted:\n1. 115BA\n2. 115BAA\n3. 115BAB\n 4.None\n");
            id = scanner.nextLong();
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

    private Double calcDeductionForDomesticCompany(Double income) {
        Double deduction;
        Double ngoDonation, goDonation;
        Double netQualifyingLimit = 10.0*income/100.0;
        System.out.println("Enter your donation to any approved funds, trust, charitable institution:");
        ngoDonation = scanner.nextDouble();
        System.out.println("Enter your donation to any government funds:");
        goDonation = scanner.nextDouble();
        deduction = Math.min(ngoDonation + goDonation, netQualifyingLimit);
        return deduction;
    }

    private Double calcSurchargeForForeignCompany(Double taxAmount, Double taxableAmount) {
        Double surCharge = null;
        if(taxableAmount>10000000 && taxableAmount<=100000000){
            surCharge = 0.02*taxAmount;
        }
        else if(taxableAmount>100000000){
            surCharge = 0.05*taxAmount;
        }
        return surCharge;
    }

    private Double calcTaxForForeignCompany(Double taxableAmount) {
        Long  id;
        Double taxAmount = null;

        while (true) {
            System.out.println("Do you fall in the below category.\nRoyalty from Government or an Indian concern in pursuance of an agreement made with the Indian concern after 31st March 1961, but before 1st April 1976, or fees for rendering technical services in pursuance of an agreement made after 29th February 1964 but before 1st April 1976 and where such agreement has, in either case, been approved by the Central Government:\n1. Yes\n0. No\n");
            id = scanner.nextLong();
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

    private Double calcDeductionForForeignCompany(Double income) {
        Double deduction, ngoDonation, goDonation;
        Double netQualifyingLimit = 10.0*income/100.0;
        System.out.println("Enter your donation to any approved funds, trust, charitable institution:");
        ngoDonation = scanner.nextDouble();
        System.out.println("Enter your donation to any government funds:");
        goDonation = scanner.nextDouble();
        deduction = Math.min(ngoDonation + goDonation, netQualifyingLimit);
        return deduction;
    }

    private Double calcTaxForIndividual(Double taxableAmount, Long regimeId, Long ageGroupId){

            Double taxAmount = 0.0;
            ArrayList<ArrayList<Integer>> slabs = slab.get(getRegimeId().intValue()).get(getAgeGroupId().intValue());

            for(int i=0;i<slabs.size() && taxableAmount>0;i++){
                Double minA;
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

    private Double calcDeductionForIndividual(Double income, Long regimeId) throws InputMismatchException{

        Double deductionAmount80c = 0.0;
        Double deductionAmount80ccd1b = 0.0;

        // for old regime
        if(regimeId==1){
            System.out.println("Enter amount of Life Insurance Premium (0 if none)");
            deductionAmount80c += scanner.nextDouble();
            System.out.println("Enter amount of Provident Fund (0 if none)");
            deductionAmount80c += scanner.nextDouble();
            System.out.println("Enter value of equity shares that you hold (0 if none)");
            deductionAmount80c += scanner.nextDouble();
            System.out.println("Enter amount of tution fees (0 if none)");
            deductionAmount80c += scanner.nextDouble();
            deductionAmount80c = Math.min(deductionAmount80c, 1500000);

            System.out.println("Enter Pension Scheme of Central Government amount(0 if none)");
            deductionAmount80ccd1b = Math.min(scanner.nextDouble(), 50000);

        }
        // for new regime
        else{
            System.out.println("Enter Pension Scheme of Central Government amount(0 if none)");
            deductionAmount80ccd1b = Math.min(scanner.nextDouble(), 50000);
        }

        Double deduction = deductionAmount80ccd1b + deductionAmount80c;
        return deduction;
    }

    private Double calcSurchargeForIndividual(Double taxAmount, Double taxableAmount){
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
        return surCharge;
    }

    Double calcHealthAndEducationCess(Double taxAmount){
        Double charge;
        charge = 0.04*taxAmount;
        return charge;
    }

}