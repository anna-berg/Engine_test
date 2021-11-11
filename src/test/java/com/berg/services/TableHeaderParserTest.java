package com.berg.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.*;

class TableHeaderParserTest {
    String[] tableFirstLine = {"id", "name"};

    @Test
    void creationHeaderIndexMapTest(){
        TableHeaderParser tableHeaderParser = new TableHeaderParser();
        var headerIndexMap = tableHeaderParser.firstColumnIndexing(tableFirstLine);
        assertThat(headerIndexMap).isNotEmpty()
                .contains(entry("id",0), entry("name", 1))
                .hasSize(2)
                .isNotNull();
    }
}