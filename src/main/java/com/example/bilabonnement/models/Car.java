package com.example.bilabonnement.models;

public class Car
{
    //Attributes of the Car class
    private int id;
    private String serialnumber;
    private String type;
    private boolean isDamaged = false;
    private boolean isAvailable = true;
    private String priceForCollectiveDamages;

    //empty constructor
    public Car()
    {
    }
    //constructor taking all parameters
    public Car(int id, String serialnumber, String type, boolean isDamaged, boolean isAvailable, String priceForCollectiveDamages)
    {
        this.id = id;
        this.serialnumber = serialnumber;
        this.type = type;
        this.isDamaged = isDamaged;
        this.isAvailable = isAvailable;
        this.priceForCollectiveDamages = priceForCollectiveDamages;
    }
    //Constructor that takes the selected parameters and refer to the attributes with this.
    public Car(int id, String serialnumber, String type, boolean isDamaged, boolean isAvailable)
    {
        this.id = id;
        this.serialnumber = serialnumber;
        this.type = type;
        this.isDamaged = isDamaged;
        this.isAvailable = isAvailable;

    }


    //getters and setters
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSerialnumber()
    {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber)
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

    //method to determine if a car is available or not called via getter / setter
    public String getIsAvailableString()
    {
        if (isAvailable == true)
        {
            return "X";
        }
        else
        {
            return "";
        }
    }
    //method to determine if a car is damaged or not called via getter / setter
    public String getIsDamagedString()
    {
        if (isDamaged == true)
        {
            return "X";
        }
        else
        {
            return "";
        }
    }

    //These are never used and we forgot to remove them
    public String getPriceForCollectiveDamages() {
        return priceForCollectiveDamages;
    }
    //These are never used and we forgot to remove them
    public void setPriceForCollectiveDamages(String priceForCollectiveDamages) {
        this.priceForCollectiveDamages = priceForCollectiveDamages;
    }

    //to string for printing values in a neat way
    @Override
    public String toString()
    {
        return "Car{" +
                "id=" + id +
                "priceForCollectiveDamages=" + priceForCollectiveDamages +
                ", serialnumber=" + serialnumber +
                ", type='" + type + '\'' +
                ", isDamaged=" + isDamaged +
                ", isAvailable=" + isAvailable +
                '}';
    }
}






