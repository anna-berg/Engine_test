package com.berg.services;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class DepartmentCreatorTest {
    private DepartmentCreator departmentCreator;
    private TableHeaderParser tableHeaderParser;

    @BeforeEach
    void init(){
        this.tableHeaderParser = Mockito.mock(TableHeaderParser.class);
        this.departmentCreator = new DepartmentCreator(tableHeaderParser);
    }

    @SneakyThrows
    @ParameterizedTest
//    @CsvFileSource(resources = "/Test_Task_1.csv", delimiter = ';', numLinesToSkip = 1)
    @ValueSource(strings = {
            "/home/anuta/JavaProjects/Engine_test/src/main/resources/Test_Task_2.csv"
    })
    void readingLineFromFileTest(String departmentCsvUrl){
        HashMap<String, Integer> tableIndex = new HashMap<String, Integer>(
                Map.of("id",0 ,
                        "name", 1)
        );
        String[] tableFirstLine = {"id", "name"};
        Mockito.doReturn(tableIndex).when(tableHeaderParser).firstColumnIndexing(tableFirstLine);
        var departmentMap = departmentCreator.departmentMap(departmentCsvUrl);
        assertThat(departmentMap).isNotEmpty().hasSize(4);
        assertThat(departmentMap).contains(entry(1, "Marketing"));
    }
}