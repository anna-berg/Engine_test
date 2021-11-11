package com.berg.services;

import lombok.SneakyThrows;

import java.io.*;

public class Application {

    @SneakyThrows
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Writer writer = new Writer();
        TableHeaderParser tableHeaderParcer = new TableHeaderParser();

//     /home/anuta/JavaProjects/Engine_test/src/main/resources/Test_Task_2.csv
        System.out.println("Enter path to file with list of departments and their id");
        DepartmentCreator departmentCreator = new DepartmentCreator(tableHeaderParcer);
        var departmentMap = departmentCreator.departmentMap(reader.readLine());

//      /home/anuta/JavaProjects/Engine_test/src/main/resources/Test_Task_1.csv
        System.out.println("Enter path to file with list of employers");
        EmployeeCreator employeeCreator = new EmployeeCreator(writer, tableHeaderParcer);
        employeeCreator.createEmployee(departmentMap, reader.readLine());
    }
}
