package com.example.bilabonnement.models;

public class Accounting {

    private int currentLeasedCars = 0;
    private int monthlyIncome = 0;
    private int annualIncome = 0;
    private int accumulatedAnnualIncome = 0;

    public int money(){
        accumulatedAnnualIncome = annualIncome + accumulatedAnnualIncome;
        return accumulatedAnnualIncome;
    }

    public Accounting() {
    }

    public Accounting(int currentLeasedCars, int monthlyIncome, int annualIncome) {
        this.currentLeasedCars = currentLeasedCars;
        this.monthlyIncome = monthlyIncome;
        this.annualIncome = annualIncome;
    }

    public int getCurrentLeasedCars() {
        return currentLeasedCars;
    }

    public void setCurrentLeasedCars(int currentLeasedCars) {
        this.currentLeasedCars = currentLeasedCars;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(int annualIncome) {
        this.annualIncome = annualIncome;
    }

    @Override
    public String toString() {
        return "Accounting{" +
                "currentLeasedCars=" + currentLeasedCars +
                ", monthlyIncome=" + monthlyIncome +
                ", annualIncome=" + annualIncome +
                '}';
    }
}
