package com.example.bilabonnement.models;

import java.time.LocalDateTime;

public class LeasingContract {

    private String type;
    private int priceMonthly;
    private int priceTotal;
    private int serialnumber;

   private String startdate;
   private String enddate;
   private int leasingperiod;


    public LeasingContract() {
        
    }

    public LeasingContract(String type, int priceMonthly, int priceTotal, int serialnumber, String startdate, String enddate, int leasingperiod)
    {
        this.type = type;
        this.priceMonthly = priceMonthly;
        this.priceTotal = priceTotal;
        this.serialnumber = serialnumber;
        this.startdate = startdate;
        this.enddate = enddate;
        this.leasingperiod = leasingperiod;
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

    public int getSerialnumber()
    {
        return serialnumber;
    }

    public void setSerialnumber(int serialnumber)
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

    @Override
    public String toString()
    {
        return "LeasingContract{" +
                "type='" + type + '\'' +
                ", priceMonthly=" + priceMonthly +
                ", priceTotal=" + priceTotal +
                ", serialnumber=" + serialnumber +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", leasingperiod=" + leasingperiod +
                '}';
    }
}
