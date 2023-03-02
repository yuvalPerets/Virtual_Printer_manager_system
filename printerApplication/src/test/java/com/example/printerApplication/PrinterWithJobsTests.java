package com.example.printerApplication;

import com.sun.jdi.LongValue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrinterWithJobsTests {

    @Test
    public void testEmptyConstructor () {
        // empty constructor is using 10 as the printer id , generally shouldn't be used
        PrinterWithJobs newPrinterWithJobs = new PrinterWithJobs();
        assertEquals( Long.valueOf(10) , newPrinterWithJobs.getPrinter().getId());
    }

    @Test
    public void testConstructorPrinter (){
        Printer printer = new Printer(Long.valueOf(500));
        ArrayList<Job> newArray = new ArrayList<Job>();
        PrinterWithJobs newPrinterWithJobs = new PrinterWithJobs(printer,newArray);
        assertEquals(printer , newPrinterWithJobs.getPrinter());
    }
    @Test
    public void testConstructorArrayList (){
        Printer printer = new Printer(Long.valueOf(500));
        ArrayList<Job> newArray = new ArrayList<Job>();
        PrinterWithJobs newPrinterWithJobs = new PrinterWithJobs(printer,newArray);
        assertEquals(newArray , newPrinterWithJobs.getJobList());
    }

}
