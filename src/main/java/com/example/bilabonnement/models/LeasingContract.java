package com.example.bilabonnement.models;


import java.io.IOException;

public class LeasingContract {

    //Attributes of the Car class
    private int contractID;
    private String type;
    private int priceMonthly;
    private int priceTotal;
    private int priceAnnual;
    private String serialnumber;

   private String startdate;
   private String enddate;
   private int leasingperiod;
   private int numberOfContracts;

    //empty constructor
    public LeasingContract() {
    }

    //Constructor that takes the selected parameters and refer to the attributes with this.
    public LeasingContract(String type, int priceMonthly, int priceTotal, String serialnumber, String startdate, String enddate, int leasingperiod, int contractID) throws IOException {
        this.type = type;
        this.priceMonthly = priceMonthly;
        this.priceTotal = priceTotal;
        this.serialnumber = serialnumber;
        this.startdate = startdate;
        this.enddate = enddate;
        this.leasingperiod = leasingperiod;
        this.contractID = contractID;
    }

    //constructor taking all parameters
    public LeasingContract(String type, int priceMonthly, int priceTotal,int priceAnnual, String serialnumber, String startdate, String enddate, int leasingperiod, int contractID) throws IOException {
        this.type = type;
        this.priceMonthly = priceMonthly;
        this.priceTotal = priceTotal;
        this.priceAnnual = priceAnnual;
        this.serialnumber = serialnumber;
        this.startdate = startdate;
        this.enddate = enddate;
        this.leasingperiod = leasingperiod;
        this.contractID = contractID;
    }

    //Constructor that takes the selected parameters and refer to the attributes with this.
    public LeasingContract(int contractID, String type, String serialnumber, String startdate, String enddate) throws IOException {
        this.contractID = contractID;
        this.type = type;
        this.serialnumber = serialnumber;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    //Constructor that takes the selected parameters and refer to the attributes with this.
    public LeasingContract(int priceMonthly, int priceAnnual) throws IOException {
        this.priceMonthly = priceMonthly;
        this.priceAnnual = priceAnnual;
    }

    //Constructor that takes the selected parameters and refer to the attributes with this.
    public LeasingContract(int numberOfContracts) {
        this.numberOfContracts = numberOfContracts;
    }

    //Constructor that takes the selected parameters and refer to the attributes with this.
    public LeasingContract(String serialnumber, String type) throws IOException {
        this.serialnumber = serialnumber;
    }

    //getters and setters
    public int getContractID() {
        return contractID;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getPriceMonthly()
    {
        return priceMonthly;
    }

    public void setPriceMonthly(int priceMonthly)
    {
        this.priceMonthly = priceMonthly;
    }

    public int getPriceTotal()
    {
        return priceTotal;
    }

    public void setPriceTotal(int priceTotal)
    {
        this.priceTotal = priceTotal;
    }

    public String getSerialnumber()
    {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber)
    {
        this.serialnumber = serialnumber;
    }

    public String getStartdate()
    {
        return startdate;
    }

    public void setStartdate(String startdate)
    {
        this.startdate = startdate;
    }

    public String getEnddate()
    {
        return enddate;
    }

    public void setEnddate(String enddate)
    {
        this.enddate = enddate;
    }

    public int getLeasingperiod()
    {
        return leasingperiod;
    }

    public void setLeasingperiod(int leasingperiod)
    {
        this.leasingperiod = leasingperiod;
    }

    public int getPriceAnnual() {
        return priceAnnual;
    }

    public void setPriceAnnual(int priceAnnual) {
        this.priceAnnual = priceAnnual;
    }

    public int getNumberOfContracts() {
        return numberOfContracts;
    }

    public void setNumberOfContracts(int numberOfContracts) {
        this.numberOfContracts = numberOfContracts;
    }

    //to string for printing values in a neat way
    @Override
    public String toString() {
        return "LeasingContract{" +
                "contractID=" + contractID +
                ", type='" + type + '\'' +
                ", priceMonthly=" + priceMonthly +
                ", priceTotal=" + priceTotal +
                ", priceAnnual=" + priceAnnual +
                ", serialnumber='" + serialnumber + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", leasingperiod=" + leasingperiod +
                ", numberOfContracts=" + numberOfContracts +
                '}';
    }
}
