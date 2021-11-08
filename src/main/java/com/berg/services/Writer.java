package com.berg.services;

import lombok.Value;

@Value
public class Writer {

    public void write(Integer id, String firstName, String secondName, String department){
        System.out.println("Employee is: " + id + ", First Name: " + firstName
                            + ", Second Name: " + secondName
                            + ", Department: " + department);
    }
}
