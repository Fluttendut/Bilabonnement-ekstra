package com.example.bilabonnement.models;

public class Rentee {

    private String name;
    private String email;
    private int cpr;
    private String address;

    public Rentee() {
    }

    public Rentee(String name, String email, int cpr, String address) {
        this.name = name;
        this.email = email;
        this.cpr = cpr;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCpr() {
        return cpr;
    }

    public void setCpr(int cpr) {
        this.cpr = cpr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Rentee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpr=" + cpr +
                ", address='" + address + '\'' +
                '}';
    }
}
