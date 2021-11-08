package com.berg.services;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class EmployeeCreator {
    Writer writer;
    TableHeaderParcer tableHeaderParcer;

    public EmployeeCreator(Writer writer, TableHeaderParcer tableHeaderParcer) {
        this.writer = writer;
        this.tableHeaderParcer = tableHeaderParcer;
    }

    public void createEmployee(Map<Integer, String> depMap, String employeeFilePath) throws IOException {
        var csvReader = new CSVReader(new FileReader(employeeFilePath),
                ';', '"', 0);
        String[] employeeLine;
        boolean isFirstRow = true;
        while ((employeeLine = csvReader.readNext()) != null) {
            String dep = null;

            if (isFirstRow){
                tableHeaderParcer.firstColumnValidation(employeeLine);
                isFirstRow = false;
                continue;
            }
            for (Map.Entry<Integer, String> entry: depMap.entrySet()) {
                if (entry.getKey().equals(Integer.valueOf(employeeLine[3]))){
                    dep = entry.getValue();
                }
            }

            this.writer.write(Integer.valueOf(employeeLine[0]),
                    employeeLine[1],
                    employeeLine[2],
                    dep);
        }
    }

}
