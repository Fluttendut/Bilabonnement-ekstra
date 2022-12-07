package com.example.bilabonnement.models;

import java.time.LocalDateTime;

public class LeasingContract {

    private int extradays;
    private String type;
    private int price;
    private int serialnumber;
    private int minimumDays = 120;
    private LocalDateTime startdate = LocalDateTime.of(2022, 12, 1, 0, 0);
    private LocalDateTime enddate = startdate.plusDays(minimumDays);
    private LocalDateTime additionalTime = enddate.plusDays(extradays);


    public LeasingContract() {
        
    }

    public LeasingContract(String type, int price, int serialnumber, LocalDateTime startdate, LocalDateTime enddate, LocalDateTime additionalTime) {
        this.type = type;
        this.price = price;
        this.serialnumber = serialnumber;
        this.startdate = startdate;
        this.enddate = enddate;
        this.additionalTime = additionalTime;
    }

    public int getExtradays() {
        return extradays;
    }

    public void setExtradays(int extradays) {
        this.extradays = extradays;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(int serialnumber) {
        this.serialnumber = serialnumber;
    }

 public LocalDateTime getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDateTime startdate) {
        this.startdate = startdate;
    }

    public LocalDateTime getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDateTime enddate) {
        this.enddate = enddate;
    }

    public LocalDateTime getAdditionalTime() {
        return additionalTime;
    }

    public void setAdditionalTime(LocalDateTime additionalTime) {
        this.additionalTime = additionalTime;
    }

    @Override
    public String toString() {
        return "LeasingContract{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", serialnumber=" + serialnumber +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", additionalTime=" + additionalTime +
                '}';
    }


}
