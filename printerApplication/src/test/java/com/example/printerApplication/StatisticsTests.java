package com.example.printerApplication;

import org.junit.jupiter.api.Test;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticsTests {

    @Test
    public void CheckConnectedPrinters()
    {
        Statistics newStat = new Statistics(Long.valueOf(1),Long.valueOf(0),null,null);
        assertEquals(Long.valueOf(1),newStat.getPrinterConnectedAmount());
    }

       @Test
       public void checkSetConnectedPrinters(){
            Long Amount = Long.valueOf(1);
            Statistics newStat = new Statistics(Long.valueOf(0),Long.valueOf(0),null,null);
            newStat.setPrinterConnectedAmount(Amount);
            assertEquals(Amount,newStat.getPrinterConnectedAmount());
        }


    @Test
    public void CheckDisConnectedPrinters()
    {
        Statistics newStat = new Statistics(Long.valueOf(1),Long.valueOf(1),null,null);
        newStat.setPrinterDisConnectedAmount(Long.valueOf(5));
        assertEquals(Long.valueOf(5),newStat.getPrinterDisConnectedAmount());
    }

    @Test
    public void checkSetDisConnectedPrinters(){
        Long Amount = Long.valueOf(1);
        Statistics newStat = new Statistics(Long.valueOf(0),Long.valueOf(1),null,null);
        newStat.setPrinterConnectedAmount(Amount);
        assertEquals(Amount,newStat.getPrinterDisConnectedAmount());
    }

    //Test for how many jobs exist in each status
    @Test
    public void TestSetStatus_average_time_dir () {
        Statistics newStat = new Statistics(Long.valueOf(0),Long.valueOf(1),null,null);
        Hashtable<String, Long> Status_average_time_dir = new Hashtable<String, Long>();
        Status_average_time_dir.put("something" , Long.valueOf(5));
        newStat.setStatus_average_time_dir(Status_average_time_dir);
        assertEquals(Status_average_time_dir , newStat.getStatus_average_time_dir());


    }
    @Test
    public void TestSetStatus_amount_dir () {
        Statistics newStat = new Statistics(Long.valueOf(0),Long.valueOf(1),null,null);
        Hashtable<String, Long> Status_amount_dir = new Hashtable<String, Long>();
        Status_amount_dir.put("something" , Long.valueOf(5));
        newStat.setStatus_amount_dir(Status_amount_dir);
        assertEquals(Status_amount_dir , newStat.getStatus_amount_dir());


    }



}
