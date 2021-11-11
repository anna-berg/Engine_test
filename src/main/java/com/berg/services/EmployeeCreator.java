package com.berg.services;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class EmployeeCreator {
    Writer writer;
    TableHeaderParser tableHeaderParser;

    public EmployeeCreator(Writer writer, TableHeaderParser tableHeaderParcer) {
        this.writer = writer;
        this.tableHeaderParser = tableHeaderParcer;
    }

    public void createEmployee(Map<Integer, String> depMap, String employeeFilePath) throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(employeeFilePath),
                ';', '"', 0);
        String[] employeeLine;
        boolean isFirstRow = true;
        while ((employeeLine = csvReader.readNext()) != null) {
            String dep = null;

            if (isFirstRow){
                tableHeaderParser.firstColumnIndexing(employeeLine);
                isFirstRow = false;
                continue;
            }

//          compare Map of department id and department name with department id column in parsing table
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
