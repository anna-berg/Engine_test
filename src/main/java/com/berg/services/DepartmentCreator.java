package com.berg.services;


import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DepartmentCreator {
    TableHeaderParser tableHeaderParser;

    public DepartmentCreator(TableHeaderParser tableHeaderParser) {
        this.tableHeaderParser = tableHeaderParser;
    }
//  read the csv and put it into a map
    public Map<Integer, String> departmentMap(String filePath) throws IOException {

        CSVReader csvReader1 = new CSVReader(new FileReader(filePath),
                ';', '"', 0);
        String [] departmentLine;
        Map<Integer, String> depMap = new HashMap<>();
        boolean isFirstRow = true;
        Map <String, Integer> departmentIndexMap = new HashMap<>();
        while ((departmentLine = csvReader1.readNext()) != null){
            if (isFirstRow){
                departmentIndexMap = tableHeaderParser.firstColumnIndexing(departmentLine);
                isFirstRow = false;
                continue;
            }
            var id = departmentIndexMap.get("id");
            var department = departmentIndexMap.get("name");
            depMap.put(Integer.valueOf(departmentLine[id]), departmentLine[department]);
        }
        return depMap;
    }
}
