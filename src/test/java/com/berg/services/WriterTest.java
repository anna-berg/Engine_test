package com.berg.services;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class WriterTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    public static final PrintStream STD_OUT = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output, true));
    }

    @Test
    public void testString() {
        Writer writer = new Writer();
        writer.write(7, "Nicol", "Green", "Management");
        assertEquals("Employee = {id: 7, First Name: Nicol, Second Name: Green, Department: Management.}\n",
                output.toString());
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(STD_OUT);
    }

}