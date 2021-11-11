package com.berg.services;

public class Writer {
    public void write(Integer id, String firstName, String secondName, String department){
        String result = """
            Employee = {id: %d, First Name: %s, Second Name: %s, Department: %s.}""".formatted(id, firstName, secondName, department);
        System.out.println(result);
    }
}
