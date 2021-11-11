package com.berg.services;

import java.util.HashMap;
import java.util.Map;

public class TableHeaderParser {

    public Map<String, Integer> firstColumnIndexing(String[] tableFirstLine) {
        Map <String, Integer> tableIndex = new HashMap<>();

        for (int i = 0; i < tableFirstLine.length; i++) {
            String columnName = tableFirstLine[i];

            var columnName1 = switch (columnName.toLowerCase()) {
                case "id" -> "id";
                case "department" -> "department";
                case "name" -> "name";
                case "surname" -> "surname";
                case "department_id" -> "department_id";
                default -> null;

            };
            tableIndex.put (columnName1, i);
        }
        return tableIndex;
    }

}
