package com.leituraexpresso.challenge.mscustomer.enums;

public enum CustomerRole {
    UNREGISTERED_CUSTOMER("unregistered_customer"),
    REGISTERED_CUSTOMER("registered_customer");

    private String role;

    CustomerRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
