package com.example.printerApplication;

import org.junit.jupiter.api.Test;

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
        assertEquals(Long.valueOf(1),newStat.getPrinterDisConnectedAmount());
    }

    @Test
    public void checkSetDisConnectedPrinters(){
        Long Amount = Long.valueOf(1);
        Statistics newStat = new Statistics(Long.valueOf(0),Long.valueOf(1),null,null);
        newStat.setPrinterConnectedAmount(Amount);
        assertEquals(Amount,newStat.getPrinterDisConnectedAmount());
    }

    //Test for how many jobs exist in each status



}
