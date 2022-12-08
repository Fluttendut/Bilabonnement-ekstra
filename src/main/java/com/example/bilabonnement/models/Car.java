package com.example.bilabonnement.models;

public class Car
{

    private int id;
    private int serialnumber;
    private String type;
    private boolean isDamaged = false;
    private boolean isAvailable = true;

    public Car()
    {
    }

    public Car(int id, int serialnumber, String type, boolean isDamaged, boolean isAvailable)
    {
        this.id = id;
        this.serialnumber = serialnumber;
        this.type = type;
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
            return "X";
        }
        else
        {
            return "";
        }
    }

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



    @Override
    public String toString()
    {
        return "Car{" +
                "id=" + id +
                ", serialnumber=" + serialnumber +
                ", type='" + type + '\'' +
                ", isDamaged=" + isDamaged +
                ", isAvailable=" + isAvailable +
                '}';
    }
}






