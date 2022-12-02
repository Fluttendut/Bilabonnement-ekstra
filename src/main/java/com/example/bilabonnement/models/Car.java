package com.example.bilabonnement.models;

import java.time.temporal.ChronoUnit;

public class Car
{

    private int id;
    private int serialnumber;
    private String type;
    private int price;
    private boolean isDamaged = false;
    private boolean isAvailable = true;

    public Car()
    {
    }

    public Car(int id, int serialnumber, String type, int price, boolean isDamaged, boolean isAvailable)
    {
        this.id = id;
        this.serialnumber = serialnumber;
        this.type = type;
        this.price = price;
        this.isDamaged = isDamaged;
        this.isAvailable = isAvailable;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getSerialnumber()
    {
        return serialnumber;
    }

    public void setSerialnumber(int serialnumber)
    {
        this.serialnumber = serialnumber;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public boolean getIsDamaged()
    {
        return isDamaged;
    }

    public void setDamaged(boolean damaged)
    {
        isDamaged = damaged;
    }

    public boolean getIsAvailable()
    {
        return isAvailable;
    }

    public void setAvailable(boolean available)
    {
        isAvailable = available;
    }

    public String getIsAvailableString()
    {
        if (isAvailable == true)
        {
            return "is available";
        }
        else
        {
            return "car not available";
        }
    }

    public String getIsDamagedString()
    {
        if (isDamaged == true)
        {
            return "this card is damaged";
        }
        else
        {
            return "This car is not damaged";
        }
    }

    public int priceForCarExtendedPeriode()
    {
        LeasingContract leasingContract = new LeasingContract();
        int daysdiff = (int) ChronoUnit.DAYS.between(leasingContract.getEnddate(), leasingContract.getAdditionalTime());
        if(daysdiff < 120)
        {
            return 4* getPrice();
        }
        else {
            return 4 * getPrice() + (getPrice()/30) * daysdiff;
        }
    }

    @Override
    public String toString()
    {
        return "Car{" +
                "id=" + id +
                ", serialnumber=" + serialnumber +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", isDamaged=" + isDamaged +
                ", isAvailable=" + isAvailable +
                '}';
    }
}






