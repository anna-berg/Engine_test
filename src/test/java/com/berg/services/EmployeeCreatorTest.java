package com.berg.services;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeCreatorTest {
    Writer writer;
    TableHeaderParser tableHeaderParser;

    @BeforeEach
    void init() {
        this.writer = Mockito.mock(Writer.class);
        this.tableHeaderParser = Mockito.mock(TableHeaderParser.class);
    }

    @SneakyThrows
    @Test
    void test() {
        HashMap<String, Integer> tableIndex = new HashMap<String, Integer>(
                Map.of("id", 0,
                        "name", 1,
                        "surname", 2,
                        "department_id", 3)
        );
        Map<Integer, String> departmentMap = new HashMap<>(
                Map.of(
                        1, "Marketing",
                        2, "Management",
                        3, "Finance",
                        4, "Production"
                )
        );
        String[] tableFirstLine = {"id", "name"};
        Mockito.doReturn(tableIndex).when(tableHeaderParser).firstColumnIndexing(tableFirstLine);

        EmployeeCreator employeeCreator = new EmployeeCreator(writer, tableHeaderParser);
        employeeCreator.createEmployee(departmentMap, "src/test/resources/Test_Task_1.csv");

        var integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        var stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(writer, Mockito.times(7))
                .write(integerArgumentCaptor.capture(), Mockito.anyString(),
                        Mockito.anyString(), stringArgumentCaptor.capture());

        var userId = integerArgumentCaptor.getValue();
        var departmentName = stringArgumentCaptor.getValue();

        assertThat(userId).isEqualTo(7);
        assertThat(departmentName).isEqualTo("Management");
    }

}