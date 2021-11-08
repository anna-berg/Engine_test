package com.berg.services;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DepartmentCreator {
    TableHeaderParcer tableHeaderParcer;

    public DepartmentCreator(TableHeaderParcer tableHeaderParcer) {
        this.tableHeaderParcer = tableHeaderParcer;
    }

    public Map<Integer, String> departmentMap(String filePath) throws IOException {

        var csvReader1 = new CSVReader(new FileReader(filePath),
                ';', '"', 0);
        String [] depLine;
        Map<Integer, String> depMap = new HashMap<>();
        boolean isFirstRow = true;
        Map <String, Integer> departmentIndexMap = new HashMap<>();
        while ((depLine = csvReader1.readNext()) != null){
            if (isFirstRow){
                departmentIndexMap = tableHeaderParcer.firstColumnValidation(depLine);
                isFirstRow = false;
                continue;
            }
            var id = departmentIndexMap.get("id");
            var department = departmentIndexMap.get("name");
            depMap.put(Integer.valueOf(depLine[id]), depLine[department]);
        }
        return depMap;
    }
}
