package com.example.bilabonnement.services;

public class StatusService {

    //TODO create rentedOUT and isDamaged
    public String carAvailability(boolean isRentedOut,boolean isDamaged, int serialnumber) {
        if (isRentedOut == true || isDamaged == true) {
            return "YOU SHALL NOT PASS!!";
        }
        //TODO add car to return
        else { return "";}
    }
    //if(isDamaged == true || rentedOut == true) {
        //return: "Car is not available."; }
    //else { return: Car.****;


    public void damageStatus(boolean isDamaged, int serialnumber){
        //TODO Insert button logic where car status is set to damaged
    }


}
